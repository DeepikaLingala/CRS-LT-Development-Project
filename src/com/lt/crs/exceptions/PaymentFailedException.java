package com.lt.crs.exceptions;

/**
 * @author deepikareddy
 * @exception PaymentFailedException
 *
 */
public class PaymentFailedException extends Exception{
	public String getMessage() {
		return "The Payment has failed";
	}
}
