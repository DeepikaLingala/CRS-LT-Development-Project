
package com.lt.crs.exceptions;
/**
 * @author deepikareddy
 * @exception ProfessorNotFoundForApprovalException
 *
 */
public class ProfessorNotFoundForApprovalException extends Exception{
	public String getMessage() {
		return "The Professor not found for Approval";
	}
}
