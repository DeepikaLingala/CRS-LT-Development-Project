package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.CRSApplication;
import com.lt.crs.app.CRSStudentMenu;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.utils.DBUtilsImpl;
/**
 * PaymentOperations
 * All the Payment Operations goes here
 * calculateBill() method is defined here
 * PaymentOperations implements PaymentInterface 
 * PaymentOperations extends StudentOperations
 *@param conn defines a JDBC connection
 *@param res defines result set variable
 *@param pstmt defines a prepared statement 
 */
public class PaymentOperations extends StudentOperations implements PaymentInterface{
	private Statement stmt;
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet res;
	private PreparedStatement pstmt;
	Scanner sc = new Scanner(System.in);
	CRSStudentMenu s= new CRSStudentMenu();
	private int courseid;
	private ResultSet res1;
	private int fees;
	private int count;
	private String Status;
	static final Logger log = Logger.getLogger(PaymentOperations.class);
	 
	/**
	 *calculateBill() method is defined here
	 * @param Course_Count is an SQL constant 
	 */
	public void calculateBill(){
		try { 
			count=0;
			pstmt=conn.prepareStatement(SQLConstants.COURSE_DTL);
			pstmt.setInt(1, CRSApplication.id);   // sid
			pstmt.setString(2, "Pending");
			res=pstmt.executeQuery();
			while(res.next()==true) {
				courseid=res.getInt(2); // CID
				//Status=res.getString(3);// Status
				//System.out.println("courseid "+courseid);
				
				pstmt=conn.prepareStatement(SQLConstants.COURSE_FEE_DTL);
				pstmt.setInt(1, courseid);   // cid
				res1=pstmt.executeQuery();
				if(res1.next()==true) {
					fees= res1.getInt("CourseFee");
					count = count+fees;
				}else {
					//PaymentFailedException pf = new PaymentFailedException();
					System.out.println("Failed to fetch the Course Fees");
					log.error("Failed to fetch the Course Fees");
					s.student();
				}
				}
			System.out.println("The bill amount for the course's selected is "+count);
			log.info("The bill amount for the course's selected is "+count);
			System.out.println();
			System.out.println("Would you like to make the payment for the courses selected.(Y/N)");
			String check=sc.next();
			if(check.equalsIgnoreCase("Y")) {
				makePayment(count);           	
			}else {
				//PaymentFailedException pf = new PaymentFailedException();
				//System.out.println(pf.getMessage());
				s.student();
			}
			/*   }else {
            	PaymentFailedException pf = new PaymentFailedException();
            	System.out.println(pf.getMessage());
            	s.student();
            } */ //while ended here before

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}
		//SQLConstants.Course_Count;
		//System.out.println("The bill amount for student "+Course.getStudentName()+" and course "+Course.getCourseName()+" is "+ (fee*count));

		CRSStudentMenu.student();
	}

}
