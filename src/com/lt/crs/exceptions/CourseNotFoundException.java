/**
 * 
 */
package com.lt.crs.exceptions;

/**
 * CustomExceptionClass
 *
 */
public class CourseNotFoundException extends Exception{
	public String getMessage() {
		return "The Course not found in the list";
	}
}
