
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception CourseAlreadyRegisteredException
 *
 */
public class CourseAlreadyRegisteredException extends Exception{
   
	public String getMessage() {
		return "The Course already exists in the list";
	}
}
