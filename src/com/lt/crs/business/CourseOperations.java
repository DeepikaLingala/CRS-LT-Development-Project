package com.lt.crs.business;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.app.CRSAdminMenu;
import com.lt.crs.app.CRSApplication;
import com.lt.crs.app.CRSStudentMenu;
import com.lt.crs.utils.DBUtilsImpl;

/**
 * @CourseOperations
 * All the Course Operations goes here
 * viewCourses(),courseMapingToStud(), displayCourse(),removeCourse(),reportCardGeneration(),
 * approveStudent(), approveProfessor() methods are defined here
 * CourseOperations implements CourseInterface 
 * 
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 * @param cid Course ID
 * @param cfee Course Fee
 * @param cname Course Name
 * @param so is the reference object for StudentOperations
 *
 */
public class CourseOperations implements CourseInterface {

	private Statement stmt;
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet res;
	private String cname;
	private String cid;
	private int cfee;
	//public static String pname; // changed to static
	StudentOperations so = new StudentOperations();
	static final Logger log = Logger.getLogger(CourseOperations.class);

	/**
	 *@author deepikareddy
	 * viewCourses() method is defined here
	 * This method displays the course details of student from Database
	 * @param conn defines a JDBC connection
	 * @param res defines result set variable
	 * @param pstmt defines a prepared statement 
	 * @param cid Course ID
	 * @param cname Course Name
	 */
	public void viewCourses() {
		System.out.println("-----------------------------------");
		System.out.println("Course-Name"+"	"+"Course-ID");
		System.out.println("-----------------------------------");
		//db.viewCourses();
		try {
			String sql= "Select * from Course";
			stmt=conn.createStatement();
			res=stmt.executeQuery(sql);
			while(res.next()==true) {
				cname=res.getString("CourseName");
				cid=res.getString("CourseID");
				System.out.println(cname+"		"+cid);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {  
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}


		/*	 Iterator  it = m.keySet().iterator();
		 while(it.hasNext())  
		 {  
			 int key=(int)it.next();  
			 Course c = (Course) m.get(key);  
			 System.out.println(c.getCourseName()+"		"+c.getCourseId());
		 } } */
		/*
		 * for(int i=0;i<cou.length;i++) { Course c= cou[i];
		 * System.out.println(c.getCourseName()+" "+
		 * c.getCourseId()+" "+c.getCourseFee());
		 * 
		 * }
		 */
	}
	/**
	 *@author deepikareddy
	 * courseMapingToStud() method is defined here
	 * This method maps the course details to student database 
	 * @param conn defines a JDBC connection
	 * @param res defines result set variable
	 * @param pstmt defines a prepared statement 
	 * @param cid Course ID
	 * @param cfee Course Fee
	 * @param cname Course Name
	 */

	public void courseMapingToStud() {
		System.out.println("---------------------------------------------");
		System.out.println("Course-Name"+"	"+"Course-ID"+"	"+"Course-Fee");
		System.out.println("---------------------------------------------");
		//db.viewCourses();
		try {
			String sql= "Select * from Course";
			stmt=conn.createStatement();
			res=stmt.executeQuery(sql);
			while(res.next()==true) {
				cname=res.getString("CourseName");
				cid=res.getString("CourseID");
				cfee=res.getInt("CourseFee");
				System.out.println(cname+"		"+cid+"		"+cfee);
				System.out.println("---------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally { 
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}

	}
	/**
	 *@author deepikareddy
	 * displayCourse() method is defined here
	 * This method displays the course details of student from database 
	 * @param conn defines a JDBC connection
	 * @param res defines result set variable
	 * @param pstmt defines a prepared statement 
	 * @param CRSApplication.id Present login ID of the user
	 * @param courseinp Course ID
	 * Call to courseMapingToStud() method is done from here
	 */
	public void displayCourse(){
		courseMapingToStud();
		Scanner s = new Scanner(System.in);
		System.out.println("\nEnter the COURSEID to add course:");
		int courseinp = s.nextInt();

		boolean b=so.addCourseToStudent(CRSApplication.id,courseinp);
		if(b==true) {
			try {
				stmt=conn.createStatement();
				res=stmt.executeQuery("Select message from notifications where id=6");
				if(res.next()==true) {
					System.out.println(res.getString(1)); }//error 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}finally {  // added by Deepika
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}
			}

		}else {
			try {
				stmt=conn.createStatement();
				res=stmt.executeQuery("Select message from notifications where id=7");
				if(res.next()==true) {
					System.out.println(res.getString(1));}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}finally {  
				try {
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}
			}

		}
		System.out.println("Do you wish to add another course? \t [Y/N] ");
		String c = s.next();
		if(c.equals("Y")) {
			//System.out.println("Inside if Y");
			//courseMapingToStud();
			displayCourse(); }
		else
			CRSStudentMenu.student();
	}
}
