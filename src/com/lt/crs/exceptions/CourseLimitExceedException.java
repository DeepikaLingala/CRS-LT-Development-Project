
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception CourseLimitExceedException
 *
 */
public class CourseLimitExceedException extends Exception{
	public String getMessage() {
		return "The Course Limit is exceeded";
	}
}
