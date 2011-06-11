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

/**
 * This class encapsulate all by class JDBCLogger needed data around a column
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public class JDBCLogColumn {

	//column name
	String name = null;

	//column type
	String type = null;

	//column sql type
	int sqlType;

	//not nullability means that this column is mandatory
	boolean nullable = false;

	//isWritable means that the column can be updated, else column is only
	// readable
	boolean isWritable = false;

	//if ignore is true, this column will be ignored by building
	// sql-statements.
	boolean ignore = false;

	int logtype = JDBCLogType.EMPTY;

	Object value = null;

	//Generic storage for typewrapper-classes Long, String, etc...
	JDBCIDHandler idhandler = null;

	//Handler to support dynamic column content
	JDBCColumnHandler columnHandler = null;
}