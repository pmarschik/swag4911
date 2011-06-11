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
 * This class contains all constants which are necessary to define a columns
 * log-type.
 * 
 * @author 
 * <a href="mailto:t.fenner@klopotek.de">Thomas Fenner</a>,
 * <a href="http://www.mannhaupt.com/danko/contact/">Danko Mannhaupt</a>, 
 * many contributions by the community
 * @since 1.0
 * @version see jdbcappender.jar/META-INF/MANIFEST.MF for version information
 */
public class JDBCLogType {
	/**
	 * The column will get the non-layouted log-message. No explicit value necessary.
	 */
	public final static int MSG = 1;
	public final static String MSG_VAR = "MSG";

	/**
	 *  The column gets the priority of the log-message.
	 */
	public final static int PRIO = 2;
	public final static String PRIO_VAR = "PRIO";

	/**
	 *  The column gets the categorys name.
	 */
	public final static int CAT = 3;
	public final static String CAT_VAR = "CAT";

	/**
	 *  The column gets the threads name.
	 */
	public final static int THREAD = 4;
	public final static String THREAD_VAR = "THREAD";

	/**
	 *  The value must be a JDBCIDHandler, which returns the columns value.
	 */
	public final static int ID = 5;
	public final static String ID_VAR = "ID";

	/**
	 *  The column always gets this value.
	 */
	public final static int STATIC = 6;
	public final static String STATIC_VAR = "STATIC";

	/**
	 *  The column gets an actual timestamp.
	 */
	public final static int TIMESTAMP = 7;
	public final static String TIMESTAMP_VAR = "TIMESTAMP";

	/**
	 *  The column will be ignored.
	 */
	public final static int EMPTY = 8;
	public final static String EMPTY_VAR = "EMPTY";

	/**
	 *  The column gets a number, which begins with 1 and will be incremented with every log-message.
	 */
	public final static int INC = 9;
	public final static String INC_VAR = "INC";

	/**
	 *  The value must be a JDBCColumnHandler, which returns the columns value.
	 */
	public final static int DYNAMIC = 10;
	public final static String DYNAMIC_VAR = "DYNAMIC";

	/**
	 *  The column gets the throwable information, if available.
	 */
	public final static int THROWABLE = 11;
	public final static String THROWABLE_VAR = "THROWABLE";

	/**
	 * The column with LAYOUT:x will get the xth part of the layout
	 */
	public final static int LAYOUT = 12;
	public final static String LAYOUT_VAR = "LAYOUT";

	/**
	 * The column will get the NDC (nested diagnostic context).
	 */
	public final static int NDC = 13;
	public final static String NDC_VAR = "NDC";

	/**
	 * The column will get the MDC (mapped diagnostic context) for the given key
	 */
	public final static int MDC = 14;
	public final static String MDC_VAR = "MDC";

	/**
	 * The column gets the integer value of the priority of the log-message.
	 */
	public final static int IPRIO = 15;
	public final static String IPRIO_VAR = "IPRIO";

	/**
	 * The column gets the result of an Oracle sequence call..
	 */
	public final static int ORACLE_SEQUENCE = 16;
	public final static String ORACLE_SEQUENCE_VAR = "ORACLE_SEQUENCE";
	
	/**
	 *  Check the LogType attribute
	 *
	 *@param  _lt  Description of Parameter
	 *@return      The LogType value
	 */
	public static boolean isLogType(int _lt) {
		if (_lt == MSG
			|| _lt == PRIO
			|| _lt == CAT
			|| _lt == THREAD
			|| _lt == STATIC
			|| _lt == ID
			|| _lt == TIMESTAMP
			|| _lt == EMPTY
			|| _lt == INC
			|| _lt == DYNAMIC
			|| _lt == THROWABLE
			|| _lt == LAYOUT
			|| _lt == NDC
			|| _lt == MDC
			|| _lt == IPRIO
			|| _lt == ORACLE_SEQUENCE) {
			return true;
		}

		return false;
	}

	/**
	 *  Checks the LogType attribute
	 *
	 *@param  _lt  Description of Parameter
	 *@return      The LogType value
	 */
	public static boolean isLogType(String _lt) {
		if (_lt.equals(MSG_VAR)
			|| _lt.equals(LAYOUT_VAR)
			|| _lt.equals(PRIO_VAR)
			|| _lt.equals(CAT_VAR)
			|| _lt.equals(THREAD_VAR)
			|| _lt.equals(STATIC_VAR)
			|| _lt.equals(ID_VAR)
			|| _lt.equals(TIMESTAMP_VAR)
			|| _lt.equals(EMPTY_VAR)
			|| _lt.equals(INC_VAR)
			|| _lt.equals(THROWABLE_VAR)
			|| _lt.equals(NDC_VAR)
			|| _lt.startsWith(MDC_VAR)
			|| _lt.equals(DYNAMIC_VAR)
            || _lt.equals(IPRIO_VAR)
			|| _lt.equals(ORACLE_SEQUENCE_VAR)) {
			return true;
		}

		return false;
	}

	/**
	 *  Transforms the String-constant in the Number-constant
	 *
	 *@param  _lt  Description of Parameter
	 *@return      Description of the Returned Value
	 */
	public static int parseLogType(String _lt) {
		if (_lt.equals(MSG_VAR)) {
			return MSG;
		} else if (_lt.equals(LAYOUT_VAR)) {
			return LAYOUT;
		} else if (_lt.equals(PRIO_VAR)) {
			return PRIO;
		} else if (_lt.equals(CAT_VAR)) {
			return CAT;
		} else if (_lt.equals(THREAD_VAR)) {
			return THREAD;
		} else if (_lt.equals(ID_VAR)) {
			return ID;
		} else if (_lt.equals(STATIC_VAR)) {
			return STATIC;
		} else if (_lt.equals(TIMESTAMP_VAR)) {
			return TIMESTAMP;
		} else if (_lt.equals(EMPTY_VAR)) {
			return EMPTY;
		} else if (_lt.equals(INC_VAR)) {
			return INC;
		} else if (_lt.equals(DYNAMIC_VAR)) {
			return DYNAMIC;
		} else if (_lt.equals(THROWABLE_VAR)) {
			return THROWABLE;
		} else if (_lt.equals(NDC_VAR)) {
			return NDC;
		} else if (_lt.startsWith(MDC_VAR)) {
			return MDC;
		} else if (_lt.startsWith(IPRIO_VAR)) {
			return IPRIO;
		} else if (_lt.startsWith(ORACLE_SEQUENCE_VAR)) {
			return ORACLE_SEQUENCE;
		}

		return -1;
	}
}
