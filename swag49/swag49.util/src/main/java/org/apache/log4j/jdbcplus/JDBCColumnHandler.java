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

import org.apache.log4j.spi.LoggingEvent;

/**
 * This interface has to be implemented to provide DYNAMIC-columns with content
 * and its used in class JDBCLogger.
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>,
 * <a href="http://www.mannhaupt.com/danko/contact/">Danko Mannhaupt</a>
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public interface JDBCColumnHandler {

    /**
     * Defines the Object that is to be logged for this column.
     * Make sure the result is accepted as input type for your JDBC driver.
     * For Firebird, this includes for example:
     * BigDecimal, Blob, Boolean, byte[], Date, Double, Float, Integer, Long, Short, String, 
     * Time, Timestamp.
     * @param event
     *            LoggingEvent with message and other details
     * @param table
     *            table name
     * @param column
     *            column name
     * @return The Object to log. Type must match allowed types of JDBC driver (see above).
     * @exception Exception
     *                may throw any Exception
     */
    Object getObject(LoggingEvent event, String table, String column) throws Exception;
}

