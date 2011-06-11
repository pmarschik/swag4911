/*
 * Copyright 1999,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.log4j.jdbcplus;

/**
 * Storage of the columns
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a> 
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */

public class JDBCColumnStorage {

	/**
	 * Column name
	 */
	public String column = null;

	/**
	 * Constant of class JDBCLogType
	 */
	public int logtype = -1;

	/**
	 * JDBCLogType depending value
	 */
	public Object value = null;

	/**
	 * Column type name, e.g. VARCHAR, INTEGER
	 * @see java.sql.Types
	 */
	public String type = null;

	/**
	 * Column SQL type
	 * @see java.sql.Types
	 */
	public int sqlType = 0;

	/**
	 * Constructor for the JDBCColumnStorage object
	 * 
	 * @param column
	 *            Column name
	 * @param logtype
	 *             Constant of class JDBCLogType
	 * @param value
	 *            JDBCLogType depending value
	 * @param type
	 *            Column type name
	 * @param sqlType
	 *            Column SQL type
	 */
	public JDBCColumnStorage(String column, int logtype, Object value, String type, int sqlType) {
		this.column = column;
		this.logtype = logtype;
		this.value = value;
		this.type = type;
		this.sqlType = sqlType;
	}
}

