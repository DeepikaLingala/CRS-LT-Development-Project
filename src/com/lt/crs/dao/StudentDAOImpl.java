package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.crs.business.CourseOperations;
import com.lt.crs.constant.Constants;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.exceptions.StudentNotRegisteredException;
import com.lt.crs.utils.DBUtilsImpl;
/**
 * StudentDataAccessImplementation 
 * StudentDAOImpl implements StudentDAOInterface
 * studentRegistration() method is defined here
 * @param adao defines a AdminDAOImpl Class Object's reference 
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 */
 
public class StudentDAOImpl implements StudentDAOInterface{
	private static PreparedStatement pstmt;
	public static Connection conn = DBUtilsImpl.conn;
	private ResultSet rs;
	private AdminDAOImpl adao =new AdminDAOImpl();
	static final Logger log = Logger.getLogger(StudentDAOImpl.class);

	/**
	 * studentRegistration() method is defined here
	 * @param SELECT_MAX_USER_ID defines a SQL Constant
	 * @param APPROVAL_STATUS defines a SQL Constant
	 * @param ADD_STUDENT defines a SQL Constant
	 *  
	 * This method adds the student data to Student Table by auto incrementing the Student ID
	 */
	public void studentRegistration(List studInp) {
		try {

			pstmt = conn.prepareStatement(SQLConstants.SELECT_MAX_USER_ID); //SELECT_MAX_STUD_ID		  
			 rs = pstmt.executeQuery();
			int max=0;
			while(rs.next()){
				max=rs.getInt("ID");
			}
			pstmt= conn.prepareStatement(SQLConstants.APPROVAL_STATUS);
			pstmt.setInt(1, Constants.student);
			pstmt.setString(2,"Student");
			pstmt.setInt(3,++max);
			pstmt.setString(4,(String)studInp.get(0));
			pstmt.setString(5, Constants.status);
			if(Constants.status.equalsIgnoreCase("Approved")) {
				int y=pstmt.executeUpdate();
				if(y>0) {
					pstmt = conn.prepareStatement(SQLConstants.ADD_STUDENT);
					pstmt.setInt(1, max);  //id  was ++max
					pstmt.setString(2, (String)studInp.get(0)); //name
					pstmt.setString(3, (String)studInp.get(1)); //email
					pstmt.setString(4, (String)studInp.get(2)); //dob
					pstmt.setString(5, "Approval Pending");
					pstmt.executeUpdate();
					addUserByStudent(studInp);
					//adao.adminUpdateStatus();
					System.out.println("Student Registration is pending for Admin Approval");
					log.info("Student Registration is pending for Admin Approval");
					System.out.println();
					
				}else {
					StudentNotRegisteredException se = new StudentNotRegisteredException();
					System.out.println(se.getMessage());
					log.error(se.getMessage());

				}
			}
		
		}catch(Exception e){
			log.error(e.getMessage());
		  
		}finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				log.error(e.getMessage());

			}
		}
	}
	/**	
	 * addUserByStudent() method is defined here
	 * @param SELECT_MAX_USER_ID defines a SQL Constant
	 * @param INSERT_USER defines a SQL Constant
	 *  
	 * This method adds the student data to User Table
	 */
	public static void addUserByStudent(List studInp) throws SQLException{
		pstmt = conn.prepareStatement(SQLConstants.SELECT_MAX_USER_ID);
		ResultSet rs = pstmt.executeQuery();
		int max=0;

		while(rs.next()){
			max=rs.getInt("ID");
		}

		pstmt = conn.prepareStatement(SQLConstants.INSERT_USER);
		pstmt.setInt(3, ++max);
		pstmt.setString(1, (String)studInp.get(3)); //username
		pstmt.setString(2, (String)studInp.get(4)); //password
		pstmt.setInt(4,Constants.student );
		pstmt.executeUpdate();
	}
	
}
