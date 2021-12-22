
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception ProfessorNotRegisteredException
 *
 */
public class ProfessorNotRegisteredException extends Exception{
	public String getMessage() {
		return "Registration of the professor has failed";
	}
}
