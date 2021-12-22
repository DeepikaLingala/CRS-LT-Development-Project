package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.utils.DBUtilsImpl;
import com.lt.crs.app.CRSApplication;
import com.lt.crs.app.CRSStudentMenu;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.dao.StudentDAOImpl;
import com.lt.crs.exceptions.CourseAlreadyRegisteredException;
import com.lt.crs.exceptions.CourseLimitExceedException;
import com.lt.crs.exceptions.PaymentFailedException;
import com.lt.crs.exceptions.ProfessorNotRegisteredException;
import com.lt.crs.exceptions.StudentNotRegisteredException;
// 
/**
 * CRSStudentOperations
 * All the Student Operations goes here
 * addCourseToStudent(), dropCourseFromStudent(),registerStudent() methods are defined here
 * StudentOperations implements StudentInterface
 *@param conn defines a JDBC connection
 *@param res defines result set variable
 *@param pstmt defines a prepared statement 
 *@param sdao defines a StudentDAOImpl Class Object's reference
 *
 * @exception CourseAlreadyRegisteredException() is used here
 * @exception CourseLimitExceedException() is used here

 */
public class StudentOperations implements StudentInterface{
	static Scanner s= new Scanner(System.in);
	StudentDAOImpl sdao= new StudentDAOImpl();
	private static ResultSet res;
	private int count;
	static CRSStudentMenu sm= new CRSStudentMenu();
	public static Connection conn = DBUtilsImpl.conn;
	private static PreparedStatement pstmt;
	static Scanner sc= new Scanner(System.in);
	private static Statement stmt;
	private static int max;
	static final Logger log = Logger.getLogger(StudentOperations.class);

	/**

	 * addCourseToStudent() method is defined here
	 *@param conn defines a JDBC connection
	 *@param res defines result set variable
	 *@param pstmt defines a prepared statement 
	 * @param CHECK_LIMIT is an SQL constant 
	 * @param Course_Check is an SQL constant 
	 * @param STUD_COU_INS is an SQL constant 
	 * @exception CourseAlreadyRegisteredException() is used here
	 * @exception CourseLimitExceedException() is used here

	 */
	public boolean addCourseToStudent(int sid, int cid) {
		try {
			pstmt=conn.prepareStatement(SQLConstants.CHECK_LIMIT);
			pstmt.setInt(1, cid);
			res =pstmt.executeQuery();
			if(res.next()==true) {
				count=res.getInt(1);
				//System.out.println("Count "+count);
				if(count<16) { // there can be upto 15 students per course
					pstmt=conn.prepareStatement(SQLConstants.Course_Check);
					pstmt.setInt(2, sid);
					pstmt.setInt(1, cid);
					res =pstmt.executeQuery();
					if(res.next()==true) {
						//System.out.println("The course is already maped to the student");
						CourseAlreadyRegisteredException ca= new CourseAlreadyRegisteredException();
						System.out.println(ca.getMessage());
						log.error(ca.getMessage());
						return false;
					}else {
						pstmt=conn.prepareStatement(SQLConstants.STUD_COU_INS);
						pstmt.setInt(1, sid);
						pstmt.setInt(2, cid);
						pstmt.setString(3, "Pending");
						int x=pstmt.executeUpdate();
						if(x>0) {
							return true;
						} }
				}else {
					CourseLimitExceedException cl= new CourseLimitExceedException();
					System.out.println(cl.getMessage());
					log.error(cl.getMessage());
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return false;		

	}
	/**

	 * dropCourseFromStudent() method is defined here
	 *@param sid defines Student ID
	 *@param cid defines Course ID
	 *@param pstmt defines a prepared statement 
	 *@param STUD_COU_DROP is an SQL constant 

	 */
	public static boolean dropCourseFromStudent(int sid,int cid) {
		try {
			pstmt=conn.prepareStatement(SQLConstants.STUD_COU_DROP);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, cid);
			int x=pstmt.executeUpdate();
			if(x>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return false;
	}
	/**

	 * registerStudent() method is defined here
	 * we read details of the new student from here		
	 * Call to studentRegistration() is done from here 
	 */

	public void registerStudent() {
		System.out.println("*************** STUDENT REGISTRATION PAGE******************");
		List<String> l = new ArrayList<String>();
		System.out.println();
		System.out.println("Enter your Name: ");
		l.add(s.next());
		System.out.println("Enter your Email: ");
		l.add(s.next());
		System.out.println("Enter your DateOfBirth(DD-MM-YYYY): ");
		l.add(s.next());
		System.out.println("Create username: ");
		l.add(s.next());
		System.out.println("Create password: ");
		String pass1 = s.next();
		System.out.println("Confirm password: ");
		String pass2 = s.next();
		if(pass1.equals(pass2)){
			l.add(pass2);
			sdao.studentRegistration(l);
			System.out.println("Student Registration is pending for Admin Approval  ");
			log.info("Student Registration is pending for Admin Approval  ");

		}
		else
			System.out.println("Password does not match. Please verify.");
		log.info("Password does not match. Please verify.");
		System.out.println(l);
		System.out.println("-------------------------------------------");

	}
	public static void makePayment(int amount) {
		System.out.println("      Welcome Student to the Payment Page      ");
		System.out.println("-----------------------------------------------");
		System.out.println("Please enter the following Card details");
		System.out.println();
		System.out.println("Please enter the Card type.(Credit/Debit)");
		String cardtype=sc.next();
		System.out.println("Please enter your Card Number.");
		long cardno=sc.nextLong();
		Long i=new Long(cardno);
		if(i.toString().length()>=12) {
		System.out.println("Please enter your Name on Card.");
		String nameoncard=sc.next();
		System.out.println("Please enter the Expiry Date on the Card.(DD-MM-YYYY)");
		String expdt=sc.next();
		// taking directly from calculate bill
		try {
			stmt=conn.createStatement();			
			res=stmt.executeQuery("Select max(ID) as max_id From Transactions");
			if(res.next()==true) {
				max=res.getInt(1);
			}
				pstmt=conn.prepareStatement(SQLConstants.PAYMENT);
				pstmt.setInt(1, ++max); // transaction ID auto increment
				pstmt.setString(2,cardtype); // card type
				pstmt.setLong(3, cardno);// card no
				pstmt.setString(4, nameoncard); // Name on Card
				pstmt.setString(5, expdt); // Expiry date
				pstmt.setInt(6,amount); // Total amount to be paid
				int x=pstmt.executeUpdate(); // inserting data 
				if(x>0) {
					System.out.println("Your Payment is Successful. You are now enrolled to the courses on CRS Application.");
					log.info("Your Payment is Successful. You are now enrolled to the courses on CRS Application.");
					pstmt=conn.prepareStatement("Update student_course set PaymentStatus=? where sid=?");
					pstmt.setString(1, "Completed");
					pstmt.setInt(2,CRSApplication.id);
					pstmt.executeUpdate();
				}else {
					
	            	PaymentFailedException pf = new PaymentFailedException();
	            	System.out.println(pf.getMessage());
	            	log.error(pf.getMessage());
	            	sm.student();
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}finally {
				try {
					stmt.close();
					pstmt.close();
					res.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}
				
				
			}

		}else {
			System.out.println("Please enter the valid details:");
			makePayment(amount);
		}
	}

	}
