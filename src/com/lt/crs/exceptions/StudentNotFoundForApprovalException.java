
package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception StudentNotFoundForApprovalException
 *
 */
public class StudentNotFoundForApprovalException extends Exception {
	public String getMessage() {
		return "Student was not found for Approval";
	}
}
