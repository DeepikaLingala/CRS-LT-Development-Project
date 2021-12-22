package com.lt.crs.dao;

/**
 * @author deepikareddy
 *  Admin Data Access Interface
 * AdminDAOImpl implements AdminDAOInterface 
 *
 */
public interface AdminDAOInterface {
	public boolean approval(String name, String cname, int id, String dpt,String pwd);

	public void removeProfessor();

	public void removeStudent();

	public void addCourse();

	public void removeCourse();

	public void reportCardGeneration();
	
	public void approveStudent();
	
	public void approveProfessor();

} 
