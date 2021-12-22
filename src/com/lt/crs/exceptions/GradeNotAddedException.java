/**
 * 
 */
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception GradeNotAddedException
 *
 */
public class GradeNotAddedException extends Exception {
	public String getMessage() {
		return "Failed to add the Grade for the Student";
	}
}
