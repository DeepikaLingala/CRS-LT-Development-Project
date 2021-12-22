package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.CRSApplication;
import com.lt.crs.constant.Constants;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.exceptions.ProfessorNotRegisteredException;
import com.lt.crs.utils.DBUtilsImpl;

/**
 * CRSProfessorOperations 
 * All the Professor Operations goes here
 * Call to professorReg(),addGrades() methods is done from here
 * ProfessorOperations implements ProfessorInterface
 * 
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 *
 */
public class ProfessorOperations implements ProfessorInterface{
	
	public static AdminDAOImpl adao= new AdminDAOImpl();
	//private Statement stmt;
		public Connection conn = DBUtilsImpl.conn;
		private ResultSet res;
		private PreparedStatement pstmt;
		private Scanner sc= new Scanner(System.in);
		private String cname;
		private int cid;
		private String sname;
		private int sid;
		private ResultSet res1;
		private ResultSet res2;
		static final Logger log = Logger.getLogger(ProfessorOperations.class);

		/**
		 * professorReg() is defined here
		 * @category Registration of the professor is done here
		 * @param name is the professor name 
		 * @param cname is the Course name 
		 * @param id is the Course ID 
		 * @param dpt is the Department 
		 * @param pwd is the professor's password
		 * @exception ProfessorNotRegisteredException() is a custom exception
		 * These parameters are passed to approval() method, abcd Call to approval() method is done from here
		 *
		 */
	@Override
	public void professorReg() {
		//Scanner sc= new Scanner(System.in);
		System.out.println("---------------------------------------------------------------");
		System.out.println("Professor Registration Process Initaited");
		System.out.println();
		System.out.println("Please enter your Name");
		String name=sc.next();
		//System.out.println("Please enter your ID");
		//int pid=0;
		System.out.println("Please enter the Course Name");
		String cname=sc.next();					
		System.out.println("Please enter the Course ID");
		int id= sc.nextInt();
		System.out.println("Please enter the Department");
		String dpt=sc.next();
		System.out.println("Please set the Password");
	    String pwd=sc.next();
	    boolean b=adao.approval(name,cname,id,dpt,pwd);
		//boolean b=po.registerProfessor(pid,name,cname,id,dpt,pwd); // changed from ao to po
		if(b==true) {
			System.out.println("************************************************************");
			System.out.println("Professor Registration is pending for Admin Approval");
			System.out.println("************************************************************");
		 }else {
			 ProfessorNotRegisteredException pe= new ProfessorNotRegisteredException();
			 System.out.println("************************************************************");
			 System.out.println(pe.getMessage());
			 log.error(pe.getMessage());
			//System.out.println("User Registration Failed");
			System.out.println("************************************************************");
		}
		
	}
	
	/**
	 * {@code} addGrades() method is defined here
	 * @category Adding grades to students based on ID is done here
	 * @param name is the professor name 
	 * @param cname is the Course name 
	 * @param id is the Course ID 
	 * @param dpt is the Department 
	 * @param pwd is the professor's password
	 * @param Course_PRO_CHECK is an SQL constant 
	 * @param STUDENT_SPEC_DTL is an SQL constant 
	 * @param STUDENT_DTL is an SQL constant 
	 * @param ADD_GRADES is an SQL constant 
	 */

	public boolean addGrades() {
	     System.out.println("Enter the course ID");
	      cid=sc.nextInt();
	     try {
	    	
			pstmt=conn.prepareStatement(SQLConstants.Course_PRO_CHECK);
			pstmt.setInt(1, cid);
			res=pstmt.executeQuery();
			if(res.next()==true) {	
			 cname=res.getString("CourseName");
				pstmt=conn.prepareStatement(SQLConstants.STUDENT_SPEC_DTL);
				pstmt.setInt(1, cid);
				res1=pstmt.executeQuery();
				while(res1.next()==true) {
					sid=res1.getInt(1);					
					pstmt=conn.prepareStatement(SQLConstants.STUDENT_DTL);
					pstmt.setInt(1, sid);
					res2=pstmt.executeQuery();
					//while(res2.next()==true) {
					if(res2.next()==true) {
						sname=res2.getString("Name");
						System.out.println("Enter the marks for "+sname);
						int m=sc.nextInt();
						String c=Constants.marks(m);
						pstmt=conn.prepareStatement(SQLConstants.ADD_GRADES);
						pstmt.setString(1, sname);
						pstmt.setInt(2, sid);
						pstmt.setString(3, cname);
						pstmt.setInt(4, cid);
						pstmt.setString(5, c);
						int up=pstmt.executeUpdate();
						if(up<0) {
							return false;
						}
						//if(up>0) {
							//return true;
						//}else return false;						
					//}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return true;
	}


}
