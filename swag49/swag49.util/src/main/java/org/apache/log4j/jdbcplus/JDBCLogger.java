/*
 * Copyright 1999,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.log4j.jdbcplus;

import java.rmi.server.UID;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

/**
 * This class encapsulate the logic which is necessary to log into a table. Used
 * by JDBCAppender
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>,
 * <a href="http://www.mannhaupt.com/danko/contact/">Danko Mannhaupt</a>, 
 * many contributions by the community
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public class JDBCLogger {

	//All columns of the log-table
	private ArrayList logcols = null;

	//Only columns which will be provided by logging
	private String column_list = null;

	//Number of all columns
	private int num = 0;

	//Status for successful execution of method configure()
	private boolean isconfigured = false;

	//Status for ready to do logging with method append()
	private boolean ready = false;

	//This message will be filled with a error-string when method ready()
	// failes, and can be got by calling getMsg()
	private String errormsg = "";

	//Log-message incrementor for columns with JDBCLogType INC
	private long inc = 0;

	/**
	 * Defines whether updated messages should be committed to the database
	 */
	private boolean commit = true;

	/**
	 * whether to replace single quotes (') by 2 single quotes ('') in sql
	 * parameters
	 */
	private boolean quoteReplace = true;

	/**
	 * character to separate parts of layout if more than one part is to be used
	 */
	protected String layoutPartsDelimiter = "#";

	/**
	 * Defines whether to use Prepared Statements instead of updateable result
	 * sets (default false)
	 */
	protected boolean usePreparedStatements = false;

	/**
	 * Defines the maximum number of characters in a throwable stack trace. -1 if there is no limit.
	 */
	private int throwableMaxChars = -1;

	private JDBCSqlHandler sqlHandler = null;

	private JDBCPoolConnectionHandler poolConnectionHandler = null;

	private Connection con = null;

	private Statement stmt = null;

	private ResultSet rs = null;

	private String table = null;

	private String procedure = null;

	//Variables for static SQL-statement logging
	private String sql = null;

	// cached prepared statement
	private String preparedSql = null;

	private final static String MSG_WILDCARD = "@" + JDBCLogType.MSG_VAR + "@";

	private final static String PRIO_WILDCARD = "@" + JDBCLogType.PRIO_VAR + "@";

	private final static String CAT_WILDCARD = "@" + JDBCLogType.CAT_VAR + "@";

	private final static String THREAD_WILDCARD = "@" + JDBCLogType.THREAD_VAR + "@";

	private final static String TIMESTAMP_WILDCARD = "@" + JDBCLogType.TIMESTAMP_VAR + "@";

	private final static String INC_WILDCARD = "@" + JDBCLogType.INC_VAR + "@";

	private final static String THROWABLE_WILDCARD = "@" + JDBCLogType.THROWABLE_VAR + "@";

	private final static String NDC_WILDCARD = "@" + JDBCLogType.NDC_VAR + "@";

	// just the beginning
	private final static String MDC_WILDCARD = "@" + JDBCLogType.MDC_VAR + ":";

	// just the beginning
	private final static String LAYOUT_WILDCARD = "@" + JDBCLogType.LAYOUT_VAR;

	private final static String IPRIO_WILDCARD = "@" + JDBCLogType.IPRIO_VAR + "@";

	/**
	 * Sets a connection. Throws an exception, if the connection is not open !
	 * 
	 * @param obj
	 *            The new Connection value
	 * @exception Exception
	 *                Description of Exception
	 */
	public void setConnection(Object obj) throws Exception {
		if (obj instanceof JDBCPoolConnectionHandler) {
			poolConnectionHandler = (JDBCPoolConnectionHandler) obj;
		} else if (obj instanceof Connection) {
			con = (Connection) obj;

			if (!isConnected()) { throw new Exception(
					"JDBCLogger::setConnection(), Given connection isnt connected to database !"); }
		} else {
			throw new Exception("JDBCLogger::setConnection(), Unvalid type of argument "
					+ obj.getClass().getName() + " !");
		}
	}

	/**
	 * Sets the SqlHandler attribute of the JDBCLogger object
	 * 
	 * @param sqlHandler
	 *            The new SqlHandler value
	 * @exception Exception
	 *                Description of Exception
	 */
	public void setSqlHandler(JDBCSqlHandler sqlHandler) throws Exception {
		this.sqlHandler = sqlHandler;
		isconfigured = true;
	}

	/**
	 * Sets a columns logtype (LogTypes) and value, which depends on that
	 * logtype. Throws an exception, if the given arguments arent correct !
	 * 
	 * @param _name
	 *            The new JDBCLogType value
	 * @param _logtype
	 *            The new JDBCLogType value
	 * @param _value
	 *            The new JDBCLogType value
	 * @exception Exception
	 *                Description of Exception
	 */
	public void setLogType(String _name, int _logtype, Object _value) throws Exception {
		if (!isconfigured) { throw new Exception("JDBCLogger::setLogType(), Not configured !"); }

		//setLogType() makes only sense for further configuration of setTable()
		if (sql != null || sqlHandler != null) { return; }

		_name = _name.toUpperCase();

		if (_name == null || !(_name.trim().length() > 0)) { throw new Exception(
				"JDBCLogger::setLogType(), Missing argument name !"); }
		if (!JDBCLogType.isLogType(_logtype)) { throw new Exception(
				"JDBCLogger::setLogType(), Invalid JDBCLogType '" + _logtype + "' !"); }

		JDBCLogColumn logcol;

		for (int i = 0; i < num; i++) {
			logcol = (JDBCLogColumn) logcols.get(i);

			if (logcol.name.equals(_name)) {
				if (!logcol.isWritable) { throw new Exception("JDBCLogger::setLogType(), Column "
						+ _name + " is not writeable !"); }

				//Column will be provided by JDBCIDHandler::getID()
				if (_logtype == JDBCLogType.ID) {
					if (_value == null) { throw new Exception(
							"JDBCLogger::setLogType(), Missing argument value !"); }

					try {
						//Try to cast directly Object to JDBCIDHandler
						logcol.idhandler = (JDBCIDHandler) _value;
					} catch (Exception e) {
						try {
							//Assuming _value is of class string which contains
							// the classname of a JDBCIDHandler
							logcol.idhandler = (JDBCIDHandler) (Class.forName((String) _value)
									.newInstance());
						} catch (Exception e2) {
							throw new Exception(
									"JDBCLogger::setLogType(), Cannot cast value of class "
											+ _value.getClass() + " to class JDBCIDHandler !");
						}
					}

					logcol.logtype = _logtype;
					return;
				}

				//Columns content will be provided by a JDBCColumnHandler
				else if (_logtype == JDBCLogType.DYNAMIC) {
					if (_value == null) { throw new Exception(
							"JDBCLogger::setLogType(), Missing argument value !"); }

					try {
						//Try to cast directly Object to JDBCColumnHandler
						logcol.columnHandler = (JDBCColumnHandler) _value;
					} catch (Exception e) {
						try {
							//Assuming _value is of class string which contains
							// the classname of a JDBCColumnHandler
							logcol.columnHandler = (JDBCColumnHandler) (Class
									.forName((String) _value).newInstance());
						} catch (Exception e2) {
							throw new Exception(
									"JDBCLogger::setLogType(), Cannot cast value of class "
											+ _value.getClass() + " to class JDBCColumnHandler !");
						}
					}

					logcol.logtype = _logtype;
					return;
				}

				//Column will be statically defined with Object _value
				else if (_logtype == JDBCLogType.STATIC) {
					if (_value == null) { throw new Exception(
							"JDBCLogger::setLogType(), Missing argument value STATIC !"); }

					logcol.logtype = _logtype;
					logcol.value = _value;
					return;
				}

				//Column will be statically defined with Object _value
				else if (_logtype == JDBCLogType.LAYOUT) {
					Integer layoutIndex = null;
					if (_value == null) {
						// default: use first pattern
						layoutIndex = new Integer(1);
					} else if (_value instanceof Number) {
						layoutIndex = new Integer(((Number) _value).intValue());
					} else if (_value instanceof String) {
						try {
							layoutIndex = Integer.valueOf((String) _value);
						} catch (NumberFormatException numberFormatException) {
							throw new Exception(
									"JDBCLogger::setLogType(), layout index (Number/String) expected. Found: "
											+ _value);
						}
					} else {
						throw new Exception(
								"JDBCLogger::setLogType(), layout index (Number/String) expected. Found: "
										+ _value.getClass());

					}

					logcol.logtype = _logtype;
					logcol.value = layoutIndex;
					return;
				}

				//Column will be MDC with key value.
				else if (_logtype == JDBCLogType.MDC) {
					if (_value == null) { throw new Exception(
							"JDBCLogger::setLogType(), Missing argument value MDC !"); }
					logcol.logtype = _logtype;
					logcol.value = _value;
					return;
				}

				//Column will be using ORACLE_SEQUENCE
				else if (_logtype == JDBCLogType.ORACLE_SEQUENCE) {
					if (_value == null) { throw new Exception(
							"JDBCLogger::setLogType(), Missing argument value ORACLE_SEQUENCE !"); }

					logcol.logtype = _logtype;
					logcol.value = _value;
					return;
				}
				//Column will be fully ignored during process.
				//If this column is not nullable, the column has to be filled
				// by a database trigger,
				//else a database error occurs !
				//Columns which are not nullable, but should be not filled,
				// must be explicit assigned with JDBCLogType.EMPTY,
				//else a value is required !
				else if (_logtype == JDBCLogType.EMPTY) {
					logcol.logtype = _logtype;
					logcol.ignore = true;
					return;
				} else {
					logcol.logtype = _logtype;
					return;
				}
			}
		}
	}

	/**
	 * Configures this class, by reading in the structure of the log-table
	 * Throws an exception, if an database-error occurs !
	 * 
	 * @param _table
	 *            Description of Parameter
	 * @exception Exception
	 *                Description of Exception
	 */
	public void setTable(String _table) throws Exception {
		if (isconfigured) { return; }

		if (poolConnectionHandler != null) {
			con = poolConnectionHandler.getConnection();

			if (!isConnected()) { throw new Exception(
					"JDBCLogger::setTable(), Given connection isnt connected to database !"); }
		}

		//Fill logcols with META-informations of the table-columns
		stmt = this.createUpdatableStatement();

		rs = stmt.executeQuery("SELECT " + _table + ".* FROM " + _table + " WHERE 1 = 2");

		JDBCLogColumn logcol;

		ResultSetMetaData rsmd = rs.getMetaData();

		num = rsmd.getColumnCount();

		logcols = new ArrayList(num);

		for (int i = 1; i <= num; i++) {
			logcol = new JDBCLogColumn();
			logcol.name = rsmd.getColumnName(i).toUpperCase();
			logcol.sqlType = rsmd.getColumnType(i);
			logcol.type = rsmd.getColumnTypeName(i);
			logcol.nullable = (rsmd.isNullable(i) == ResultSetMetaData.columnNullable);
			logcol.isWritable = rsmd.isWritable(i);
			if (!logcol.isWritable) {
				logcol.ignore = true;
			}
			logcols.add(logcol);
		}

		table = _table;

		rs.close();
		stmt.close();
		freeConnection();

		isconfigured = true;
	}

	/**
	 * Configures this class, by storing and parsing the given sql-statement.
	 * Throws an exception, if somethings wrong !
	 * 
	 * @param _sql
	 *            Description of Parameter
	 * @exception Exception
	 *                Description of Exception
	 */
	public void setSQL(String _sql) throws Exception {
		if (isconfigured) { return; }

		if (_sql == null || _sql.trim().equals("")) { throw new Exception(
				"JDBCLogger::setSQL(), Invalid SQL-Statement !"); }

		sql = _sql.trim();

		isconfigured = true;
	}

	/**
	 * Return true, if this class is configured, else false.
	 * 
	 * @return The Configured value
	 */
	public boolean isConfigured() {
		return isconfigured;
	}

	/**
	 * Return true, if this connection is open, else false.
	 * 
	 * @return The Connected value
	 */
	public boolean isConnected() {
		try {
			return (con != null && !con.isClosed());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Return the internal error message stored in instance variable msg.
	 * 
	 * @return The ErrorMsg value
	 */
	public String getErrorMsg() {
		if (errormsg == null) return "";
		String r = new String(errormsg);
		errormsg = null;
		return r;
	}

	/**
	 * Description of the Method
	 * 
	 * @exception Exception
	 *                Description of Exception
	 */
	public void freeConnection() throws Exception {
		if (poolConnectionHandler != null) {
			// con == null may only happen in error situations
			if (con != null) {
				if (!con.isClosed() && !con.getAutoCommit()) {
					if (this.isCommit()) {
						con.commit();
					}
				}
				poolConnectionHandler.freeConnection(con);
			}

		}
	}

	/**
	 * Calls <code>freeConnection</code> but catches all raising exceptions.
	 * @see #freeConnection()
	 */
	public void tryToFreeConnection() {
		try {
			freeConnection();
		} catch (Exception e) {
			LogLog.error(e.getMessage());
		}
	}

	/**
	 * prepare connection
	 * 
	 * @return Description of the Returned Value
	 * @exception Exception
	 *                Description of Exception
	 */
	public boolean prepareConnection() throws Exception {
		if (poolConnectionHandler != null) {
			con = poolConnectionHandler.getConnection();
		}

		if (!isConnected()) { throw new Exception(
				"JDBCLogger::prepareConnection(), Given connection isnt connected to database !"); }

		if (procedure != null) {
			// procedure call
			stmt = this.createCallableStatement(this.logcols.size());
		} else if (sql == null && sqlHandler == null && !this.isUsePreparedStatements()) {
			// uses updateable result set
			try {
				stmt = this.createUpdatableStatement();
			} catch (SQLException exception) {
				// if this fails, connection may be broken. Try again with new
				// connection.
				if (poolConnectionHandler != null) {
					con = poolConnectionHandler.getConnection();
					stmt = this.createUpdatableStatement();
				} else {
					throw exception;
				}
			}
			rs = stmt.executeQuery("SELECT " + column_list + " FROM " + table + " WHERE 1 = 2");
		} else {
			// non-updatable statement
			try {
				stmt = this.createStatement();
			} catch (SQLException exception) {
				// if this fails, connection may be broken. Try again with new
				// connection.
				if (poolConnectionHandler != null) {
					con = poolConnectionHandler.getConnection();
					stmt = this.createStatement();
				} else {
					throw exception;
				}
			}
		}

		return true;
	}

	/**
	 * Writes a message into the database table. Throws an exception, if an
	 * database-error occurs !
	 * 
	 * @param event
	 *            the LoggingEvent to log
	 * @param layout
	 *            layout to use for message
	 * @exception Exception
	 *                Description of Exception
	 */
	public void append(LoggingEvent event, Layout layout) throws Exception {
		boolean errorOccurred = false;
		boolean usePrepStmts = this.isUsePreparedStatements();
		boolean useCallStmts = procedure != null;
		PreparedStatement prepStmt = null;
		CallableStatement callStmt = null;

		if (!ready) {
			if (!ready()) { throw new Exception("JDBCLogger::append(), Not ready to append !"); }
		}

		try {
			prepareConnection();
			if (sql != null || sqlHandler != null) {
				appendSQL(event, layout);
			} else {
				JDBCLogColumn logcol;
				int paramIndex = 1;

				if (useCallStmts) {
					callStmt = (CallableStatement) stmt;
				}
				if (usePrepStmts || useCallStmts) {
					prepStmt = (PreparedStatement) stmt;
				} else {
					rs.moveToInsertRow();
				}

				for (int i = 0; i < num; i++) {
					logcol = (JDBCLogColumn) logcols.get(i);
					int indexToUse = 0;
					if (usePrepStmts) {
						indexToUse = paramIndex;
					} else if (useCallStmts) {
						indexToUse = i + 1;
					}

					if (logcol.logtype == JDBCLogType.MSG) {
						String parameter = event.getRenderedMessage();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.LAYOUT) {
						String parameter = layout.format(event);
						// default: use first pattern
						int layoutIndex = ((Integer) logcol.value).intValue();

						// LAYOUT~x. use tokens in layout string
						List tokenList = getTokenList(parameter);
						String token = getTokenFromList(tokenList, layoutIndex);

						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, token);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, token);
						}
					} else if (logcol.logtype == JDBCLogType.PRIO) {
						String parameter = event.getLevel().toString();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.CAT) {
						String parameter = event.getLoggerName();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.THREAD) {
						String parameter = event.getThreadName();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.ID) {
						Object parameter = logcol.idhandler.getID();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setObject(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateObject(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.STATIC) {
						Object parameter = logcol.value;
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setObject(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateObject(logcol.name, logcol.value);
						}
					} else if (logcol.logtype == JDBCLogType.TIMESTAMP) {
						Timestamp parameter = new Timestamp((new java.util.Date()).getTime());
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setTimestamp(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateTimestamp(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.INC) {
						long parameter = ++inc;
						if (usePrepStmts || useCallStmts) {
							prepStmt.setLong(indexToUse, parameter);
							paramIndex = paramIndex + 1;
						} else {
							rs.updateLong(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.DYNAMIC) {
						Object parameter = logcol.columnHandler
								.getObject(event, table, logcol.name);
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setObject(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateObject(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.THROWABLE) {
						// extract throwable information from loggingEvent if
						// available
						String parameter = quotedString(this
								.getThrowableRepresentationFromLoggingEvent(event));
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.NDC) {
						String parameter = event.getNDC();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setString(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateString(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.MDC) {
						Object mdcObject = event.getMDC(logcol.value.toString());
						String parameter = mdcObject == null ? null : mdcObject.toString();
						if (usePrepStmts || useCallStmts) {
							if (parameter == null) {
								prepStmt.setNull(indexToUse, logcol.sqlType);
							} else {
								prepStmt.setObject(indexToUse, parameter);
							}
							paramIndex = paramIndex + 1;
						} else {
							rs.updateObject(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.IPRIO) {
						int parameter = event.getLevel().toInt();
						if (usePrepStmts || useCallStmts) {
							prepStmt.setInt(indexToUse, parameter);
							paramIndex = paramIndex + 1;
						} else {
							rs.updateInt(logcol.name, parameter);
						}
					} else if (logcol.logtype == JDBCLogType.ORACLE_SEQUENCE) {
						// do nothing
					}
				}

				if (useCallStmts) {
					callStmt.execute();
				} else if (usePrepStmts) {
					prepStmt.executeUpdate();
				} else {
					rs.insertRow();
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			errorOccurred = true;
			throw (e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				freeConnection();
			} catch (Exception exception) {
				if (errorOccurred) {
					// consume exception
				} else {
					throw (exception);
				}
			}
		}
	}

	/**
	 * Writes a message into the database using a given sql-statement. Throws an
	 * exception, if an database-error occurs !
	 * 
	 * @param event
	 *            Description of Parameter
	 * @param layout
	 *            layout to use for message
	 * @exception Exception
	 *                Description of Exception
	 */
	public void appendSQL(LoggingEvent aEvent, Layout layout) throws Exception {
		String new_sql = "";

		if (!ready) {
			if (!ready()) { throw new Exception("JDBCLogger::appendSQL(), Not ready to append !"); }
		}

		if (sql == null && sqlHandler == null) { throw new Exception(
				"JDBCLogger::appendSQL(), No SQL-Statement configured !"); }

		try {

			if (sqlHandler == null) {
				// These strings may contain embedded single-quote characters.
				// Therefore, scan the strings and replace all single-quote
				// characters with a pair of single-quote characters.
				// only done if option "quoteReplace" is not unset

				String t_msg = quotedString(aEvent.getRenderedMessage());
				String t_layout = layout.format(aEvent);
				String t_prio = quotedString(aEvent.getLevel().toString());
				String t_cat = quotedString(aEvent.getLoggerName());
				String t_thread = quotedString(aEvent.getThreadName());
				String t_ndc = quotedString(aEvent.getNDC());
				int t_iprio = aEvent.getLevel().toInt();
				new_sql = sql;

				new_sql = replace(new_sql, MSG_WILDCARD, t_msg);
				new_sql = replace(new_sql, PRIO_WILDCARD, t_prio);
				new_sql = replace(new_sql, CAT_WILDCARD, t_cat);
				new_sql = replace(new_sql, THREAD_WILDCARD, t_thread);
				new_sql = replace(new_sql, TIMESTAMP_WILDCARD, new Timestamp((new java.util.Date())
						.getTime()).toString());
				new_sql = replace(new_sql, INC_WILDCARD, String.valueOf(++inc));
				new_sql = replace(new_sql, THROWABLE_WILDCARD, quotedString(this
						.getThrowableRepresentationFromLoggingEvent(aEvent)));
				new_sql = replace(new_sql, NDC_WILDCARD, t_ndc);
				new_sql = replace(new_sql, IPRIO_WILDCARD, t_iprio);

				// MDC. determine key parameters
				boolean finishedMdc = false;
				do {
					// find match
					int index = new_sql.indexOf(MDC_WILDCARD);
					boolean contains = index != -1;
					if (contains) {
						// have found start. now find exact key
						String substringStartingWithKey = new_sql.substring(index
								+ MDC_WILDCARD.length());
						// find first @ that follows. key is between : and @
						int indexOfKeyEnd = substringStartingWithKey.indexOf("@");
						String key = substringStartingWithKey.substring(0, indexOfKeyEnd);

						Object mdcObject = aEvent.getMDC(key);
						String t_mdc = "";
						if (mdcObject != null) {
							t_mdc = quotedString(mdcObject.toString());
						}
						new_sql = new_sql.substring(0, index)
								+ t_mdc
								+ new_sql.substring(index + MDC_WILDCARD.length() + 1
										+ indexOfKeyEnd);
					} else {
						finishedMdc = true;
					}
				} while (!finishedMdc);

				// LAYOUT:x. replace by tokens in layout string
				List tokenList = getTokenList(t_layout);

				boolean finishedLayout = false;
				do {
					// find match
					int index = new_sql.indexOf(LAYOUT_WILDCARD);
					boolean contains = index != -1;
					if (contains) {
						// have found start. now find Number (2 in @LAYOUT:2@)
						String substringStartingWithNumber = new_sql.substring(index
								+ LAYOUT_WILDCARD.length());
						// starts with :? consume one more character
						if (substringStartingWithNumber.startsWith(":")) {
							substringStartingWithNumber = substringStartingWithNumber.substring(1);
						}
						// find first @ that follows. key is between : and @
						int indexOfNumberEnd = substringStartingWithNumber.indexOf("@");
						String numberString = substringStartingWithNumber.substring(0,
								indexOfNumberEnd);
						// default: 1
						int number = 1;
						if (!numberString.equals("")) {
							number = Integer.valueOf(numberString).intValue();
						}

						String token = getTokenFromList(tokenList, number);
						new_sql = new_sql.substring(0, index) + token
								+ substringStartingWithNumber.substring(+indexOfNumberEnd + 1);
					} else {
						finishedLayout = true;
					}
				} while (!finishedLayout);
			} else {
				new_sql = sqlHandler.getStatement(aEvent);
			}

			LogLog.debug("sql statement: " + new_sql);
			try {
				this.executeUpdateWhenNotEmpty(new_sql);
			} catch (SQLException exception) {
				this.tryToFreeConnection();
				// try again with new connection. May have happened due to broken db connection
				this.prepareConnection();
				this.executeUpdateWhenNotEmpty(new_sql);
			}
		} catch (Exception e) {
			LogLog.error("error during logging. " + new_sql, e);
			errormsg = new_sql;
			throw e;
		}

	}

	/**
	 * @param new_sql
	 * @throws SQLException
	 */
	private void executeUpdateWhenNotEmpty(String newSql) throws SQLException {
		if (newSql != null && newSql.length() > 0) {
			stmt.executeUpdate(newSql);
		}
	}

	/**
	 * @param tokenList
	 * @param index
	 *            list index, 1-based.
	 * @return (index-1)'th element in list
	 * @throws Exception
	 */
	protected String getTokenFromList(List tokenList, int index) throws Exception {
		// retrieve the value from tokenList, 0-based
		if (index > tokenList.size()) { throw new Exception("missing token " + index
				+ " separated by '" + this.getLayoutPartsDelimiter()
				+ "' in .layoutPartsDelimiter parameter."); }
		String token = (String) tokenList.get(index - 1);
		token = quotedString(token);
		return token;
	}

	/**
	 * create token list from layout string. Requires JDK 1.4, therefore
	 * commented out here.
	 * 
	 * @param t_layout
	 *            layout string to be tokenized
	 * @return list of layout parts 
	 * 
	 * protected List getTokenList(String t_layout) {
	 *         String[] tokens = t_layout.split(getLayoutPartsDelimiter());
	 * 
	 * ArrayList list = new ArrayList(tokens.length); for(int i=1; i
	 * <tokens.length; i++) { list.add(tokens[i]); }
	 * 
	 * return list; }
	 */

	/**
	 * create token list from layout string
	 * 
	 * @param t_layout
	 * @return list of layout parts
	 *  
	 */
	protected List getTokenList(String t_layout) {
		List tokenList = new ArrayList(5);
		// empty? return one token.
		if (t_layout.equals("")) {
			tokenList.add("");
		} else {
			// delimiter with one character? Use StringTokenizer.
			if (this.getLayoutPartsDelimiter().length() == 1) {
				// use StringBuffer to separate layout parts first
				StringTokenizer stringTokenizer = new StringTokenizer(t_layout, String.valueOf(this
						.getLayoutPartsDelimiter()), true);
				// put tokens to List
				// used to determine if two delimiters follow each other
				boolean lastWasDelimiter = false;
				while (stringTokenizer.hasMoreTokens()) {
					String token = stringTokenizer.nextToken();
					if (token.equals(this.getLayoutPartsDelimiter())) {
						if (lastWasDelimiter) {
							// two token after another. add empty String
							tokenList.add("");
						} else {
							lastWasDelimiter = true;
						}
					} else {
						tokenList.add(token);
						lastWasDelimiter = false;
					}
				}
			} else {
				// more than one character. 
				// requires J2SDK 1.4

				// problem: two following delimiters would be removed.
				// therefore, add randomdummy in that place.
				// Same applies to begin and end of string.
				String dummy = new UID().toString();
				if (t_layout.startsWith(this.getLayoutPartsDelimiter())) {
					t_layout = dummy + t_layout;
				}
				if (t_layout.endsWith(this.getLayoutPartsDelimiter())) {
					t_layout = t_layout + dummy;
				}
				// replace double occurrences
				String[] tokens = t_layout.split(this.getLayoutPartsDelimiter());

				for (int i = 0; i < tokens.length; i++) {
					if (tokens[i].equals(dummy)) {
						tokenList.add("");
					} else {
						tokenList.add(tokens[i]);
					}
				}

			}
		}
		return tokenList;
	}

	/**
	 * Return true, if this class is ready to append(), else false. When not
	 * ready, a reason-String is stored in the instance-variable msg.
	 * 
	 * @return Description of the Returned Value
	 */
	public boolean ready() {
		if (ready) { return true; }

		if (!isconfigured) {
			errormsg = "Not ready to append, because not configured !";
			return false;
		}

		//No need to doing the whole rest...
		if (sql != null || sqlHandler != null) {
			ready = true;
			return true;
		}

		boolean msgcol_defined = false;

		JDBCLogColumn logcol;

		for (int i = 0; i < num; i++) {
			logcol = (JDBCLogColumn) logcols.get(i);

			if (logcol.ignore || !logcol.isWritable) {
				continue;
			}
			if (!logcol.nullable && logcol.logtype == JDBCLogType.EMPTY) {
				errormsg = "Not ready to append ! Column " + logcol.name
						+ " is not nullable, and must be specified by setLogType() !";
				return false;
			}
			if (logcol.logtype == JDBCLogType.ID && logcol.idhandler == null) {
				errormsg = "Not ready to append ! Column " + logcol.name
						+ " is specified as an ID-column, and a JDBCIDHandler has to be set !";
				return false;
			} else if (logcol.logtype == JDBCLogType.STATIC && logcol.value == null) {
				errormsg = "Not ready to append ! Column " + logcol.name
						+ " is specified as a static field, and a value has to be set !";
				return false;
			} else if (logcol.logtype == JDBCLogType.MDC && logcol.value == null) {
				errormsg = "Not ready to append ! Column " + logcol.name
						+ " is specified as a MDC field, and a key has to be set !";
				return false;
			} else if (logcol.logtype == JDBCLogType.MSG) {
				msgcol_defined = true;
			}
		}

		if (!msgcol_defined) {
			// return false;
		}

		//create the column_list
		for (int i = 0; i < num; i++) {
			logcol = (JDBCLogColumn) logcols.get(i);

			if (logcol.ignore || !logcol.isWritable) {
				continue;
			}

			if (logcol.logtype != JDBCLogType.EMPTY) {
				if (column_list == null) {
					column_list = logcol.name;
				} else {
					column_list += ", " + logcol.name;
				}
			}
		}

		ready = true;

		return true;
	}

	/**
	 * Creates a SQL-quoted string. Any single-quotes ['] are doubled [''] in
	 * order to make the string "SQL compatible".
	 */
	private String quotedString(String s) {
		if (s == null) return "";
		if (this.isQuoteReplace()) {
			s = replace(s, "'", "''");
		}
		return s;
	}

	/**
	 * String-replacer
	 * 
	 * @param source
	 *            Description of Parameter
	 * @param find
	 *            Description of Parameter
	 * @param replacement
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public String replace(String source, String find, String replacement) {
		int i = 0;
		int j;
		int k = find.length();
		int m = replacement.length();

		while (i < source.length()) {
			j = source.indexOf(find, i);

			if (j == -1) {
				break;
			}

			source = replace(source, j, j + k, replacement);

			i = j + m;
		}

		return source;
	}

	/**
	 * int-replacer
	 * 
	 * @param source
	 *            Description of Parameter
	 * @param find
	 *            Description of Parameter
	 * @param replacement
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public String replace(String source, String find, int replacement) {
		String sReplacement = Integer.toString(replacement);

		return replace(source, find, sReplacement);
	}

	/**
	 * String-replacer
	 * 
	 * @param source
	 *            Description of Parameter
	 * @param start
	 *            Description of Parameter
	 * @param end
	 *            Description of Parameter
	 * @param replacement
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public String replace(String source, int start, int end, String replacement) {
		if (start == 0) {
			source = replacement + source.substring(end);
		} else if (end == source.length()) {
			source = source.substring(0, start) + replacement;
		} else {
			source = source.substring(0, start) + replacement + source.substring(end);
		}

		return source;
	}

	/**
	 * int-replacer
	 * 
	 * @param source
	 *            Description of Parameter
	 * @param start
	 *            Description of Parameter
	 * @param end
	 *            Description of Parameter
	 * @param replacement
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public String replace(String source, int start, int end, int replacement) {
		String sReplacement = Integer.toString(replacement);

		return replace(source, start, end, sReplacement);
	}

	private Statement createStatement() throws Exception {
		Statement retVal = null;

		if (this.isUsePreparedStatements()) {
			// create sql statement
			if (this.preparedSql == null) {
				String sql = "insert into " + table + " (" + column_list + ") values (";

				for (int i = 0; i < num; i++) {
					JDBCLogColumn logcol = (JDBCLogColumn) logcols.get(i);

					// only required columns
					if (!logcol.ignore) {
						// add , if required
						if (sql.endsWith(" ")) {
							sql += ", ";
						}

						if (logcol.logtype == JDBCLogType.ORACLE_SEQUENCE) {
							sql += logcol.value.toString() + ".NEXTVAL ";
						} else {
							sql += "? ";
						}
					}
				}
				sql += ")";
				LogLog.debug("prepared statement: " + sql);

				this.preparedSql = sql;
			}
			retVal = con.prepareStatement(this.preparedSql);
		} else {
			retVal = con.createStatement();
		}

		return retVal;
	}

	private CallableStatement createCallableStatement(int numberOfParameters) throws Exception {
		CallableStatement cStmt = null;

		String callString = "{call " + procedure + "( ";
		for (int i = 0; i < numberOfParameters - 1; i++) {
			callString = callString + "?, ";
		}
		if (numberOfParameters > 0) {
			callString = callString + "? ";
		}
		callString = callString + " )}";
		cStmt = con.prepareCall(callString);

		return cStmt;
	}

	private Statement createUpdatableStatement() throws Exception {
		Statement retVal = null;

		retVal = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		return retVal;
	}

	/**
	 * Extracts Stack trace of Throwable contained in LogginEvent, if there is
	 * any
	 * 
	 * @param aLoggingEvent
	 *            logging event
	 * @return stack trace of throwable
	 */
	public String getThrowableRepresentationFromLoggingEvent(LoggingEvent aLoggingEvent) {
		// extract throwable information from loggingEvent if available
		ThrowableInformation throwableinfo = aLoggingEvent.getThrowableInformation();
		StringBuffer throwableStringBuffer = new StringBuffer();

		if (throwableinfo != null) {
			String[] lines = throwableinfo.getThrowableStrRep();
			for (int index = 0; index < lines.length; index++) {
				throwableStringBuffer = (StringBuffer) throwableStringBuffer.append(lines[index]
						+ "\r\n");
			}
		}
		String result = throwableStringBuffer.toString();
		if (this.getThrowableMaxChars() != -1 && result.length() > this.getThrowableMaxChars()) {
			result = result.substring(0, this.getThrowableMaxChars() - 1);
		}

		return result;
	}

	/**
	 * @return docommit attribute
	 */
	public boolean isCommit() {
		return commit;
	}

	/**
	 * @param b
	 */
	public void setCommit(boolean b) {
		commit = b;
	}

	/**
	 * @return quoteReplace
	 */
	public boolean isQuoteReplace() {
		return quoteReplace;
	}

	/**
	 * @param b
	 */
	public void setQuoteReplace(boolean b) {
		quoteReplace = b;
	}

	/**
	 * @return layoutPartsDelimiter
	 */
	public String getLayoutPartsDelimiter() {
		return layoutPartsDelimiter;
	}

	/**
	 * @param c
	 */
	public void setLayoutPartsDelimiter(String c) {
		layoutPartsDelimiter = c;
	}

	/**
	 * @return Returns the usePreparedStatements.
	 */
	public boolean isUsePreparedStatements() {
		return usePreparedStatements;
	}

	/**
	 * @param usePreparedStatements
	 *            The usePreparedStatements to set.
	 */
	public void setUsePreparedStatements(boolean usePreparedStatements) {
		this.usePreparedStatements = usePreparedStatements;
	}

	/**
	 * @return Returns the procedure.
	 */
	public String getProcedure() {
		return procedure;
	}

	/**
	 * @param procedure The procedure to set.
	 * @param columns columns
	 */
	public void setProcedure(String procedure, ArrayList columns) throws Exception {
		if (isconfigured) { return; }

		if (poolConnectionHandler != null) {
			con = poolConnectionHandler.getConnection();

			if (!isConnected()) { throw new Exception(
					"JDBCLogger::setProcedure(), Given connection isnt connected to database !"); }
		}

		this.procedure = procedure;
		// prepare call
		CallableStatement cStmt = this.createCallableStatement(columns.size());

		JDBCLogColumn logcol;

		ParameterMetaData pmd;

		// 2.6.2005 jschmied
		// ParameterMetaData is supported on different levels by Oracle
		try {
			// J2SDK 1.4+; limited support by Oracle drivers 10.x and 9.x
			pmd = cStmt.getParameterMetaData();
			num = pmd.getParameterCount();
			if (num >= 1) {
				// oracle 10.1.0.4 has some stubs in ParameterMetaData, 
				// try if a function throws a UnsupportedFeature exception
				pmd.getParameterType(1);
				pmd.getParameterTypeName(1);
				pmd.isNullable(1);
			}
		} catch (Exception e) {
			pmd = null;
			num = columns.size();
		}

		logcols = new ArrayList(num);

		for (int i = 1; i <= num; i++) {
			logcol = new JDBCLogColumn();
			JDBCColumnStorage col = (JDBCColumnStorage) columns.get(i - 1);
			logcol.name = col.column.toUpperCase();
			if (pmd == null) {
				logcol.type = col.type;
				logcol.sqlType = col.sqlType;
				logcol.nullable = true; // assume true
			} else {
				logcol.type = pmd.getParameterTypeName(i);
				logcol.sqlType = pmd.getParameterType(i);
				logcol.nullable = (pmd.isNullable(i) == ParameterMetaData.parameterNullable);
			}
			logcol.isWritable = true;
			logcols.add(logcol);
		}

		cStmt.close();
		freeConnection();

		isconfigured = true;

	}

	/**
	 * @return Returns the throwableMaxChars.
	 */
	public int getThrowableMaxChars() {
		return throwableMaxChars;
	}

	/**
	 * @param throwableMaxChars The throwableMaxChars to set.
	 */
	public void setThrowableMaxChars(int throwableMaxChars) {
		this.throwableMaxChars = throwableMaxChars;
	}
}

