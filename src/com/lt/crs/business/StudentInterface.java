package com.lt.crs.business;

/**
 * @author deepikareddy
 *  Student Interface
 * registerStudent(), addCourseToStudent() methods are declared here
 * StudentOperations implements StudentInterface 
 *
 */
public interface StudentInterface {

	public void registerStudent();
	public boolean addCourseToStudent(int sid,int cid);
}
