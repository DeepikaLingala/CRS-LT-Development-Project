/**
 * 
 */
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception CourseNotDeletedException
 *
 */
public class CourseNotDeletedException extends Exception{
	public String getMessage() {
		return "Failed to Delete the Course from the List";
	}
}
