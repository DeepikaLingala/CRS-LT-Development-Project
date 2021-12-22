package com.lt.crs.dao;

/**
 * @author deepikareddy
 *  Professor Data Access Interface
 *  registerProfessor() methods is declared here
 * ProfessorDAOImpl implements ProfessorDAOInterface 
 * @param name Professor Name
 *  @param cname Course Name
 *  @param cid Course ID
 *  @param dpt  Department
 *  @param pwd is PAssword
 */
public interface ProfessorDAOInterface {
	public boolean registerProfessor(String name, String cname, int cid, String dpt,String pwd);
}
