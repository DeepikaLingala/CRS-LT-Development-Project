package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lt.crs.business.CourseOperations;
import com.lt.crs.constant.Constants;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.utils.DBUtilsImpl;
/**
 * Professor Data Access Objects goes here
 * ProfessorDAOImpl implements ProfessorDAOInterface 
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface{

	static final Logger log = Logger.getLogger(ProfessorDAOImpl.class);
	private PreparedStatement pstmt;
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet rs;
	//private String pname;
	/**

	 * registerProfessor() method is defined here
	 * @param name defines Professor name
	 * @param cid defines  Course ID
	 * @param cname defines Course name
	 * @param dpt defines department 
	 * @param pwd defines Password 
	 * @param SELECT_MAX_USER_ID is an SQL constant 
	 * @param APPROVAL_STATUS is an SQL constant
	 * @param INSERT_USER is an SQL constant
	 * @param INSERT_PROFESSOR is an SQL constant 
	 */
	@Override
	public boolean registerProfessor(String name, String cname, int cid, String dpt,String pwd) {

		try {

			pstmt = conn.prepareStatement(SQLConstants.SELECT_MAX_USER_ID); // max of user id
			  
			  rs = pstmt.executeQuery();
			  int max=0;
			  while(rs.next()){
				  max=rs.getInt("ID");
			  }
			pstmt=conn.prepareStatement(SQLConstants.APPROVAL_STATUS);
			pstmt.setInt(1, Constants.professor);
			pstmt.setString(2,"Professor");
			pstmt.setInt(3,++max);
			pstmt.setString(4, name);
			pstmt.setString(5, Constants.status);
			if(Constants.status.equalsIgnoreCase("Approved"))
			{
				int y=pstmt.executeUpdate();
				if(y>0) {										  
					pstmt=conn.prepareStatement(SQLConstants.INSERT_USER);
					pstmt.setString(1,name);
					pstmt.setString(2, pwd);
					pstmt.setInt(3, max);  //
					pstmt.setInt(4,Constants.professor);
					int x= pstmt.executeUpdate();
					if(x>0) {

						pstmt=conn.prepareStatement(SQLConstants.INSERT_PROFESSOR);
						pstmt.setString(1,name);
						pstmt.setInt(2, max);//
						pstmt.setString(3,cname);
						pstmt.setInt(4, cid);
						pstmt.setString(5,dpt);						
						pstmt.setString(6,"Approval Pending");
						int x1= pstmt.executeUpdate();
						if(x1>0) {
							return true;
						}else {
							return false; } 
					}else {
						return false; } 
				}}else {return false;} 
			 
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally { 
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		
		return false;
	}


}
