package com.lt.crs.validation;

// Login validation Interface
/**
 * @author deepikareddy
 *  LoginValidations Interface
 *  userValidation(), updatePwdValidation() methods are declared here
 *  LoginValidation implements LoginValidations 
 *
 */
public interface LoginValidations {
	public boolean validation(int pid, String pwd);
	public boolean updatePwdValidation(int pid, String pwd, String npwd);
	
	public boolean userValidation(int id, String pwd);
	public boolean updatePassword(int id, String pwd, String npwd);
}
