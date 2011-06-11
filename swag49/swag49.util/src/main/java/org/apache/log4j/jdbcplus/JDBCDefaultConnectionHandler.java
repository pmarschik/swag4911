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

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is a default JDBCConnectionHandler used by JDBCAppender
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public class JDBCDefaultConnectionHandler implements JDBCPoolConnectionHandler {

	Connection con = null;

	String url = null;

	String username = null;

	String password = null;

	public JDBCDefaultConnectionHandler(String aUrl, String aUsername, String aPassword) {
		this.setUrl(aUrl);
		this.setUsername(aUsername);
		this.setPassword(aPassword);
	}

	/**
	 * Gets the Connection attribute of the JDBCDefaultConnectionHandler object
	 * 
	 * @return The Connection value
	 */
	public Connection getConnection() throws Exception {
		// test original connection.
		if (this.con == null || this.con.isClosed()) {
			this.con = this.getConnection(this.getUrl(), this.getUsername(), this.getPassword());
		}
		return this.con;
	}

	/**
	 * Gets the Connection attribute of the JDBCDefaultConnectionHandler object
	 * 
	 * @param _url
	 *            Description of Parameter
	 * @param _username
	 *            Description of Parameter
	 * @param _password
	 *            Description of Parameter
	 * @return The Connection value
	 * @exception Exception
	 *                Description of Exception
	 */
	public Connection getConnection(String _url, String _username, String _password)
			throws Exception {
		if (con != null && !con.isClosed()) {
			con.close();
		}

		con = DriverManager.getConnection(_url, _username, _password);

		//Catch this error, because some drivers/databases doesnt support this
		// method.
		try {
			con.setAutoCommit(false);
		} catch (Exception e) {
			/* empty */
		}

		return con;
	}

	/**
	 * interface implementation
	 * 
	 * @param con
	 * @exception Exception
	 */
	public void freeConnection(Connection con) throws Exception {
		// keep connection open
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return username 
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @param string
	 */
	public void setUsername(String string) {
		username = string;
	}

}