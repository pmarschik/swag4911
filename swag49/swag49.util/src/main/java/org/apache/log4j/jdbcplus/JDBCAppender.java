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

/**
 * JDBCAppender implementation to log into a database.
 */
package org.apache.log4j.jdbcplus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * <p>
 * The JDBCAppender writes messages into a database via JDBC. Multiple
 * configuration options and parameters are supported.
 * </p>
 * 
 * <p>
 * <b>Configuration by file/code </b>
 * </p>
 * 
 * <p>
 * The JDBCAppender is configurable at runtime by setting options in two
 * alternatives :
 * </p>
 * 
 * <ol>
 * <li>Use a configuration-file
 * <ul>
 * <li>Here is a configuration-file example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j_test.xml">log4j_test.xml
 * </a>, which can be used as argument for the DOMConfigurator. Here is a
 * code-example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/DomConfiguratorTest.java"
 * >DomConfiguratorTest.java </a>) to configure the JDBCAppender with a
 * configuration-file.</li>
 * 
 * <li>Here is a configuration-file example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j_test_prepstmt.xml">log4j_test_prepstmt.xml
 * </a>), which can be used as argument for the DOMConfigurator. It uses
 * prepared statement configuration.</li>
 * 
 * <li>Here is a configuration-file example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j_test_storedproc.xml">log4j_test_storedproc.xml
 * </a>), which can be used as argument for the DOMConfigurator. It uses
 * stored procedure configuration (J2SDK 1.4+).</li>
 * 
 * <li>Here is a configuration-file example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j.properties">log4j.properties
 * </a>), which can be used as argument for the PropertyConfigurator. Some
 * options (e.g. usage of prepared statements) are not available in a property
 * configuration file.</li>
 * </ul>
 * </li>
 * <li>Call the methods of JDBCAppender directly to do it
 * 
 * <p>
 * Here is a another code-example ( <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/SourceConfigOracleTest.java">SourceConfigOracleTest.java
 * </a>) to configure the JDBCAppender without a configuration-file.
 * </p>
 * </li>
 * </ol>
 * 
 * <p>
 * <b>Configuration parameters </b>
 * </p>
 * 
 * <p>
 * Generally there are just a few basic things to get the JDBCAppender running :
 * </p>
 * 
 * <ol>
 * <li>
 * <p>
 * We need a database connection anyway ! There are 3 ways to to this :
 * </p>
 * 
 * <p>
 * a) Specify the URL, password and username (use the url-param, username-param
 * and password-param or the setUrl(), setUsername() and setPassword()-methods)
 * This will create a static connection by JDBCAppender.
 * </p>
 * 
 * <p>
 * b) Specify a JDBCConnectionHandler (use the connector-param or the
 * setConnector()-method) This handler will be called to return a connection.
 * When its a JDBCPoolConnectionHandler, the connection is checked in and out
 * everytime needing it.
 * </p>
 * 
 * <p>
 * c) Specify URL, password and username AND a JDBCConnectionHandler, then the
 * handler returns the desired connection.
 * </p>
 * </li>
 * <li>
 * <p>
 * We need to know the statement which should be inserted into the database !
 * There are also 5 ways to to this :
 * </p>
 * 
 * <p>
 * a) Specify a JDBCSqlHandler (use the sqlhandler-param or the
 * setSqlhandler()-method) This handler will be invoked with every message which
 * has to be logged and must return a SQL-statement. It may return null or empty 
 * string if nothing should be logged.
 * </p>
 * 
 * <p>
 * b) Specify a static sql-statement (use the sql-param or the setSql()-method)
 * This statement which will be performed with every occuring message-event. Use
 * wildcards @INC@, @PRIO@, @IPRIO@, @CAT@, @THREAD@, @MSG@, @LAYOUT@, @LAYOUT:x@, @TIMESTAMP@, @THROWABLE@, @NDC@, @MDC:key@ for dynamic replacement. See further description below.
 * </p>
 * 
 * <p>
 * c) Specify the table and describe the important columns. (use the table-param
 * and column-param or the setTable() and setColumn()-methods) Not nullable
 * columns are mandatory to describe ! The sql-statement will be created
 * automatically. We need to know exactly the column-name, the logical
 * columns-logtype and in dependency of that the value. The constants of
 * JDBCLogType are described below. This is the most dynamic variant to build
 * the sql-statement, because you can apply JDBCIDHandler and JDBCColumnHandler.
 * <b>Note: This configuration uses the "updatable ResultSets". This feature is
 * not implemented for some databases/drivers. As of 2004, it causes errors on
 * MySQL JDBC Connector ("Result Set not updateable"), Firebird JCA-JDBC Driver
 * ("not yet implemented"), Postgres. Use other configuration options instead if
 * any problems occur. </b>
 * </p>
 * 
 * <p>
 * d) Same as 2c) but using PreparedStatements. Example: <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j_test_prepstmt.xml">log4j_test_prepstmt.xml
 * </a> <b></b>
 * </p>
 * 
 * <p>
 * e) Same as 2c) but using Stored Procedure (CallableStatement). Specify procedure name parameter instead of table name.
 * Requires J2SDK 1.4+.
 * Example: <a
 * href="../../../../../src/org/apache/log4j/jdbcplus/examples/test/log4j_test_storedproc.xml">log4j_test_storedproc.xml
 * </a> <b></b>
 * </p>
 * </li>
 * </ol>
 * <p>
 * The class JDBCLogType provides you several possibilities to describe a
 * columns logtype/wildcard :
 * </p>
 * <p>
 * <ul>
 * <li>MSG - The column will get the non-layouted log-message. No explicit
 * value necessary.</li>
 * <li>LAYOUT, LAYOUT:1, :2, :x - The column will get the layouted log-event.
 * If more than one layout is used, configuration of PatternLayout conversion
 * pattern separated by .layoutPartsDelimiter is required.
 * layoutPartsDelimiter may be longer than one character to avoid mismatches (J2SDK 1.4+)
 * </li>
 * <li>ID - The value must be a JDBCIDHandler, which returns the columns value.
 * </li>
 * <li>INC - The column gets a number, which begins with 1 and will be
 * incremented with every log-message.</li>
 * <li>ORACLE_SEQUENCE - The column gets the result of an Oracle sequence call. 
 * Value is sequence name</li>
 * <li>DYNAMIC - The value must be a JDBCColumnHandler, which returns the
 * columns value.</li>
 * <li>STATIC - The column always gets this value.</li>
 * <li>THROWABLE - The column gets the throwable/exception information if
 * available.</li>
 * <li>TIMESTAMP - The column gets an actual timestamp.</li>
 * <li>PRIO - The column gets the priority/level of the log-message.</li>
 * <li>IPRIO - The column gets the integer value of the priority/level of the
 * log-message.</li>
 * <li>CAT - The column gets the categorys name.</li>
 * <li>THREAD - The column gets the threads name.</li>
 * <li>NDC - The column gets the NDC (nested diagnostic context).</li>
 * <li>MDC:key - The column gets the MDC (mapped diagnostic context) for the
 * given key.</li>
 * <li>EMPTY - The column will be ignored.</li>
 * </ul>
 * </p>
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>,
 * <a href="http://www.mannhaupt.com/danko/contact/">Danko Mannhaupt</a>, 
 * many contributions by the community
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public class JDBCAppender extends AppenderSkeleton {

	//Variables to store the options values setted by setXXX()-methods :
	/**
	 * Stores a database url of the form jdbc:subprotocol:subname
	 */
	private String url = null;

	/**
	 * Stores the database user
	 */
	private String username = null;

	/**
	 * Stores the database password
	 */
	private String password = null;

	/**
	 * Stores the table in which the logging will be done
	 */
	private String table = null;

	/**
	 * Stores the procedure which will be called
	 */
	private String procedure = null;

	/**
	 * Stores a classname which is implementing the
	 * JDBCConnectionHandler-interface.
	 */
	private String connector = null;

	/**
	 * Stores a sql-statement which will be used to insert into the database
	 */
	private String sql = null;

	/**
	 * Defines the class load string necessary to access the database driver
	 */
	private String dbclass = null;

	/**
	 * Defines the maximum number of characters in a throwable stack trace. -1 if there is no limit.
	 */
	private int throwableMaxChars = -1;

	/**
	 * Define whether to replace single quotes (') by 2 single quotes ('') in
	 * sql parameters. Will be applied to these wildcards:
	 * 
	 * @MSG@,
	 * @LAYOUT:x@,
	 * @PRIO@,
	 * @CAT@,
	 * @THREAD@,
	 * @NDC@,
	 * @MDC:x@,
	 * @THROWABLE@
	 */
	private boolean quoteReplace = true;

	/**
	 * character to separate parts of layout if more than one part is to be used
	 */
	private String layoutPartsDelimiter = "#";

	/**
	 * Defines whether updated messages should be committed to the database
	 */
	private boolean commit = true;

	/**
	 * Defines how many messages will be buffered until they will be updated to
	 * the database
	 */
	private int buffer_size = 1;

	/**
	 * Defines whether to use Prepared Statements instead of updateable result
	 * sets (default false)
	 */
	private boolean usePreparedStatements = false;

	/**
	 * Stores a JDBCConnectionHandler, which is instantiated from connector
	 */
	private JDBCConnectionHandler connectionHandler = null;

	/**
	 * Stores a JDBCSqlHandler, which allows you to build dynamic
	 * insert-statements
	 */
	private JDBCSqlHandler sqlHandler = null;

	/**
	 * Stores message-events. When the buffer_size is reached, the buffer will
	 * be flushed and the messages will inserted to the database.
	 */
	private ArrayList buffer = new ArrayList();

	/**
	 * Stores the columns, which will be used in the statement
	 */
	private ArrayList columns = new ArrayList();

	/**
	 * Stores the database-connection, if any static is given
	 */
	private Connection con = null;

	/**
	 * This class encapsulate the logic which is necessary to log into a table
	 */
	private JDBCLogger jlogger = new JDBCLogger();

	/**
	 * A flag to indicate a established database connection
	 */
	private boolean connected = false;

	/**
	 * A flag to indicate configuration status
	 */
	private boolean configured = false;

	/**
	 * A flag to indicate that everything is ready to execute append()-commands
	 */
	private boolean ready = false;

	/**
	 * Constructor for the JDBCAppender object
	 */
	public JDBCAppender() {
		super();
	}

	/**
	 * Constructor for the JDBCAppender object
	 * 
	 * @param layout
	 *            Allows you to set your Layout-instance
	 */
	public JDBCAppender(Layout layout) {
		super();
		this.setLayout(layout);
	}

	/**
	 * Sets the Layout attribute of the JDBCAppender object
	 * 
	 * @param layout
	 *            The new Layout value
	 */
	public void setLayout(Layout layout) {
		super.setLayout(layout);
	}

	/**
	 * Specify your own JDBCConnectionHandler / JDBCPoolConnectionHandler for
	 * connecting to the database. If the Database-Options are given, the
	 * handler is called with the desired database-params. If you give a
	 * JDBCPoolConnectionHandler, the connection will be checked out only when
	 * needed and checked in after .
	 * 
	 * @param value
	 *            The new Connector value
	 */
	public void setConnector(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		connector = value;
	}

	/**
	 * Specify your own JDBCConnectionHandler / JDBCPoolConnectionHandler for
	 * connecting to the database. If the Database-Options are given, the
	 * handler is called with the desired database-params. If you give a
	 * JDBCPoolConnectionHandler, the connection will be checked out only when
	 * needed and checked in after .
	 * 
	 * @param value
	 *            The new Connector value
	 */
	public void setConnectionHandler(JDBCConnectionHandler connectionHandler) {
		if (connectionHandler == null) return;
		this.connectionHandler = connectionHandler;
	}

	/**
	 * Specify your own JDBCSqlHandler to let him create dynamic sql-statements.
	 * 
	 * @param value
	 *            The new Sqlhandler value
	 */
	public void setSqlhandler(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		try {
			sqlHandler = (JDBCSqlHandler) (Class.forName(value).newInstance());
		} catch (Exception e) {
			String errorMsg = "JDBCAppender::setSqlhandler(), sqlhandler must be derived of JDBCSqlHandler !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}
	}

	/**
	 * Specify your own JDBCSqlHandler to let him create dynamic sql-statements.
	 * 
	 * @param value
	 *            The new Sqlhandler value
	 */
	public void setSqlHandler(JDBCSqlHandler sqlHandler) throws Exception {
		if (sqlHandler == null) return;
		this.sqlHandler = sqlHandler;
	}

	/**
	 * Sets database url of the form jdbc:subprotocol:subname
	 * 
	 * @param value
	 *            The new Url value
	 */
	public void setUrl(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		url = value;
	}

	/**
	 * Sets the database user
	 * 
	 * @param value
	 *            The new Username value
	 */
	public void setUsername(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		username = value;
	}

	/**
	 * Sets the database password
	 * 
	 * @param value
	 *            The new Password value
	 */
	public void setPassword(String value) {
		if (value == null) { return; }

		value = value.trim();

		password = value;
	}

	/**
	 * Specify a database class loader string. E.g.
	 * oracle.jdbc.driver.OracleDriver
	 * 
	 * @param value
	 *            The new database driver class
	 */
	public void setDbclass(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		dbclass = value;
	}

	/**
	 * Specify an sql-statement. Use wildcards @INC@, @PRIO@, @CAT@, @THREAD@, @MSG@, 
	 * ... for dynamic replacement. See class JDBCLogType for further
	 *        description.
	 * @param value
	 *            The new Sql value
	 */
	public void setSql(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		sql = value;
	}

	/**
	 * Specify the table, when you also describe all columns by setColumn()
	 * 
	 * @param value
	 *            The new Table value
	 */
	public void setTable(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		if (sql != null) { return; }

		table = value;
	}

	/**
	 * Sets the Column attribute of the JDBCAppender object. 
	 * Last two parameters only required for Stored Procedure configuration on Oracle.
	 * 
	 * @param column
	 *            The columns name
	 * @param logtype
	 *            Constant of class JDBCLogType
	 * @param value
	 *            The columns value depending by the logtype
	 * @param type
	 *            Column type name
	 * @param sqlType
	 *            Column SQL type
	 */
	public void setColumn(String column, int logtype, Object value, String type, int sqlType) {
		if (table == null && procedure == null) {
			String errorMsg = "JDBCAppender::setColumn(), table or procedure has to be set before !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}

		if (sql != null || sqlHandler != null) { return; }

		if (column == null || value == null) {
			String errorMsg = "JDBCAppender::setColumn(), Invalid arguments : column = " + column
					+ ", logtype = " + logtype + ", value = " + value + " !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}

		column = column.trim();

		if (column.length() == 0) {
			String errorMsg = "JDBCAppender::setColumn(), Argument column is missing !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}

		columns.add(new JDBCColumnStorage(column, logtype, value, type, sqlType));
	}

	/**
	 * Defines one colum in the format column~logtype~value~typeName~sqlType, e.g.
	 * id~ID~org.apache.log4j.jdbcplus.examples.MyIDHandler or
	 * id~ID~org.apache.log4j.jdbcplus.examples.MyIDHandler~INTEGER~4.
	 * 
	 * typeName and sqlType are required, if JDBC driver does not support 
	 * Statement.getParamaterMetaData, e.g. Oracle.
	 * 
	 * @param value
	 *            Concatenated string column~logtype~value~typeName
	 */
	public void setColumn(String value) {
		if (table == null && procedure == null) {
			String errorMsg = "JDBCAppender::setColumn(), table or procedure has to be set before !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}

		if (sql != null || sqlHandler != null) { return; }

		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		String name = null;
		int logtype = -1;
		String typeName = null;
		int sqlType = -1;
		String arg = null;
		int num_args = 0;
		StringTokenizer st_arg;

		//Arguments are ~-separated
		st_arg = new StringTokenizer(value, "~");

		num_args = st_arg.countTokens();

		if (num_args < 2 || num_args > 5) {
			String errorMsg = "JDBCAppender::setColumn(), Invalid column-option value : " + value
					+ " !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}

		for (int j = 1; j <= num_args; j++) {
			arg = st_arg.nextToken();

			if (j == 1) {
				name = arg;
			} else if (j == 2) {
				try {
					logtype = Integer.parseInt(arg);
				} catch (Exception e) {
					logtype = JDBCLogType.parseLogType(arg);
				}

				if (!JDBCLogType.isLogType(logtype)) {
					String errorMsg = "JDBCAppender::setColumn(), Invalid column-option JDBCLogType : "
							+ arg + " !";
					LogLog.error(errorMsg);
					errorHandler.error(errorMsg, null, 0);
					return;
				}
			} else if (j == 3) {
				value = arg;
			} else if (j == 4) {
				typeName = arg;
			} else if (j == 5) {
				try {
					sqlType = Integer.parseInt(arg);
				} catch (NumberFormatException nfe) {
					String errorMsg = "JDBCAppender::setColumn(), Invalid column-option sqlType : "
							+ arg + " !";
					LogLog.error(errorMsg);
					errorHandler.error(errorMsg, null, 0);
					return;
				}
			}
		}

		if (num_args == 2) {
			value = null;
		}

		columns.add(new JDBCColumnStorage(name, logtype, value, typeName, sqlType));
	}

	/**
	 * Defines how many messages will be buffered until they will be updated to
	 * the database.
	 * 
	 * @param value
	 *            The new Buffer value
	 */
	public void setBuffer(String value) {
		if (value == null) { return; }

		value = value.trim();

		if (value.length() == 0) { return; }

		try {
			buffer_size = Integer.parseInt(value);
		} catch (Exception e) {
			String errorMsg = "JDBCAppender::setBuffer(), Invalid BUFFER_OPTION value : " + value
					+ " !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}
	}

	/**
	 * Defines whether updated messages should be committed to the database.
	 * 
	 * @param value
	 *            The new Commit value
	 */
	public void setCommit(boolean value) {
		this.commit = value;
	}

	/**
	 * Gets the ConnectionHandler attribute of the JDBCAppender object
	 * 
	 * @return The ConnectionHandler value
	 */
	public JDBCConnectionHandler getConnectionHandler() {
		return this.connectionHandler;
	}

	/**
	 * Gets the Connector attribute of the JDBCAppender object
	 * 
	 * @return The Connector value
	 */
	public String getConnector() {
		return this.connector;
	}

	/**
	 * Gets the Url attribute of the JDBCAppender object
	 * 
	 * @return The Url value
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gets the Username attribute of the JDBCAppender object
	 * 
	 * @return The Username value
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the Password attribute of the JDBCAppender object
	 * 
	 * @return The Password value
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the Sql attribute of the JDBCAppender object
	 * 
	 * @return The Sql value
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * Gets the class loader string
	 * 
	 * @return The db class loader string
	 */
	public String getDbclass() {
		return dbclass;
	}

	/**
	 * Gets the Table attribute of the JDBCAppender object
	 * 
	 * @return The Table value
	 */
	public String getTable() {
		return table;
	}

	/**
	 * Gets the Buffer attribute of the JDBCAppender object
	 * 
	 * @return The Buffer value
	 */
	public String getBuffer() {
		return Integer.toString(buffer_size);
	}

	/**
	 * Gets the Commit attribute of the JDBCAppender object
	 * 
	 * @return The Commit value
	 */
	public boolean getCommit() {
		return this.commit;
	}

	/**
	 * If program terminates close the database-connection and flush the buffer
	 */
	public void finalize() {
		close();
		super.finalize();
	}

	/**
	 * Internal method. Returns true, you may define your own layout...
	 * 
	 * @return Description of the Returned Value
	 */
	public boolean requiresLayout() {
		return true;
	}

	/**
	 * Internal method. Close the database connection & flush the buffer.
	 */
	public void close() {
		flush_buffer();

		if (connector == null && con != null) {
			try {
				con.close();
			} catch (Exception e) {
				String errorMsg = "JDBCAppender::close(), ";
				LogLog.error(errorMsg, e);
				errorHandler.error(errorMsg, e, 0);
			}
		}
		this.closed = true;
	}

	/**
	 * Internal method. Appends the message to the database table.
	 * 
	 * @param event
	 *            Description of Parameter
	 */
	public void append(LoggingEvent event) {
		try {
			if (!ready) {
				if (!ready()) {
					String errorMsg = "JDBCAppender::append(), Not ready to append !";
					LogLog.error(errorMsg);
					errorHandler.error(errorMsg, null, 0);
					return;
				}
			}

			buffer.add(event);

			if (buffer_size > 1) {
				// MDC problem fix for buffer_size > 1 from Patrick Carlos
				// --- Taken from AsyncAppender ---
				// Set the NDC and thread name for the calling thread as these
				// LoggingEvent fields were not set at event creation time.
				event.getNDC();
				event.getThreadName();
				// Get a copy of this thread's MDC.
				event.getMDCCopy();
				// make sure to also remember locationinfo in the event
				event.getLocationInformation();
			}

			if (buffer.size() >= buffer_size) {
				flush_buffer();
			}
		} catch (Exception e) {
			String errorMsg = "JDBCAppender::append(), ";
			LogLog.error(errorMsg, e);
			errorHandler.error(errorMsg, e, 0);
		}
	}

	/**
	 * Internal method. Flushes the buffer.
	 */
	public void flush_buffer() {
		try {
			int size = buffer.size();

			if (size < 1) { return; }

			for (int i = 0; i < size; i++) {
				LoggingEvent event = (LoggingEvent) buffer.get(i);
				jlogger.append(event, layout);
			}

			buffer.clear();

			if (this.getCommit()) {
				if (con != null) con.commit();
			}
		} catch (Exception e) {
			String errorMsg = "JDBCAppender::flush_buffer(), : " + jlogger.getErrorMsg();
			LogLog.error(errorMsg, e);
			errorHandler.error(errorMsg, e, 0);
			buffer.clear();
			try {
				if (this.getCommit()) {
					if (con != null) con.rollback();
				}
			} catch (Exception ex) {
				/* empty */
			}
			return;
		}
	}

	/**
	 * Internal method. Returns true, when the JDBCAppender is ready to append
	 * messages to the database, else false.
	 * 
	 * @return Description of the Returned Value
	 */
	public boolean ready() {
		if (ready) { return true; }

		if (!configured) {
			if (!configure()) { return false; }
		}

		ready = jlogger.ready();

		if (!ready) {
			errorHandler.error(jlogger.getErrorMsg(), null, 0);
		}

		//Default Message-Layout
		if (layout == null) {
			layout = new PatternLayout("%m");
		}

		return ready;
	}

	/**
	 * Internal method. Connect to the database.
	 * 
	 * @exception Exception
	 *                Description of Exception
	 */
	protected void connect() throws Exception {
		if (connected) { return; }

		try {
			if (connectionHandler == null) {
				if (connector == null) {
					if (url == null) { throw new Exception(
							"JDBCAppender::connect(), No URL defined."); }

					if (username == null) { throw new Exception(
							"JDBCAppender::connect(), No USERNAME defined."); }

					if (password == null) { throw new Exception(
							"JDBCAppender::connect(), No PASSWORD defined."); }

					connectionHandler = new JDBCDefaultConnectionHandler(url, username, password);
				} else {
					connectionHandler = (JDBCConnectionHandler) (Class.forName(connector)
							.newInstance());
				}
			}

			if (connectionHandler instanceof JDBCPoolConnectionHandler) {
				jlogger.setConnection(connectionHandler);
			} else {
				if (url != null && username != null && password != null) {
					con = connectionHandler.getConnection(url, username, password);
				} else {
					con = connectionHandler.getConnection();
				}

				if (con.isClosed()) { throw new Exception(
						"JDBCAppender::connect(), JDBCConnectionHandler returns no connected Connection !"); }

				jlogger.setConnection(con);
			}
		} catch (Exception e) {
			throw new Exception("JDBCAppender::connect(), " + e);
		}

		connected = true;
	}

	/**
	 * Internal method. Configures for appending...
	 * 
	 * @return Description of the Returned Value
	 */
	protected boolean configure() {
		try {
			if (configured) { return true; }

			if (this.getDbclass() != null) {
				Class.forName(this.getDbclass());
			}
			connect();

			if (!connected) return false;

			if (sqlHandler != null) {
				jlogger.setSqlHandler(sqlHandler);
			} else if (sql != null) {
				jlogger.setSQL(sql);
			} else if (table != null) {
				jlogger.setTable(table);
			} else if (procedure != null) {
				jlogger.setProcedure(procedure, columns);
			} else {
				String errorMsg = "JDBCAppender::configure(), No SqlHandler or sql, table, procedure option given !";
				LogLog.error(errorMsg);
				errorHandler.error(errorMsg, null, 0);
				return false;
			}

			// set options in jlogger
			jlogger.setCommit(this.getCommit());
			jlogger.setQuoteReplace(this.isQuoteReplace());
			jlogger.setLayoutPartsDelimiter(this.getLayoutPartsDelimiter());
			jlogger.setThrowableMaxChars(this.getThrowableMaxChars());
			jlogger.setUsePreparedStatements(this.isUsePreparedStatements());

			for (int i = 0; i < columns.size(); i++) {
				JDBCColumnStorage col = (JDBCColumnStorage) columns.get(i);
				jlogger.setLogType(col.column, col.logtype, col.value);
			}
		} catch (Exception e) {
			String errorMsg = "JDBCAppender::configure()";
			LogLog.error(errorMsg, e);
			errorHandler.error(errorMsg, e, 0);
			return false;
		}

		configured = true;

		return true;
	}

	/**
	 * Define whether to replace single quotes (') by 2 single quotes ('') in
	 * sql parameters.
	 * @return quoteReplace
	 */
	public boolean isQuoteReplace() {
		return quoteReplace;
	}

	/**
	 * Define whether to replace single quotes (') by 2 single quotes ('') in
	 * sql parameters.
	 * 
	 * @param b
	 */
	public void setQuoteReplace(boolean b) {
		quoteReplace = b;
	}

	/**
	 * character to separate parts of layout if more than one part is to be used
	 * @return layoutPartsDelimiter
	 */
	public String getLayoutPartsDelimiter() {
		return layoutPartsDelimiter;
	}

	/**
	 * character to separate parts of layout if more than one part is to be used
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
	 * Defines whether to use Prepared Statements instead of updateable result
	 * sets (default false).
	 * 
	 * @param usePreparedStatements
	 *            The usePreparedStatements to set.
	 */
	public void setUsePreparedStatements(boolean usePreparedStatements) {
		if (table == null) {
			String errorMsg = "JDBCAppender::setUsePreparedStatements(), Table-Option has to be set before !";
			LogLog.error(errorMsg);
			errorHandler.error(errorMsg, null, 0);
			return;
		}
		this.usePreparedStatements = usePreparedStatements;
	}

	/**
	 * Returns the procedure.
	 * @return Returns the procedure.
	 */
	public String getProcedure() {
		return procedure;
	}

	/**
	 * Stores the procedure which will be called.
	 * @param procedure The procedure to set.
	 */
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	/**
	 * Returns the maximum number of characters in throwable/exception stack trace. Used to limit very long stack traces.
	 * @return Returns the throwableMaxChars.
	 */
	public int getThrowableMaxChars() {
		return throwableMaxChars;
	}

	/**
	 * Sets the maximum number of characters in throwable/exception stack trace. Used to limit very long stack traces.
	 * @param throwableMaxChars The throwableMaxChars to set.
	 */
	public void setThrowableMaxChars(int throwableMaxChars) {
		this.throwableMaxChars = throwableMaxChars;
	}
}

