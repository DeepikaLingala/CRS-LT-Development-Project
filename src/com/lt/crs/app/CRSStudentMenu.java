package com.lt.crs.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.business.CourseOperations;
import com.lt.crs.business.PaymentOperations;
import com.lt.crs.business.ReportCardOperations;
import com.lt.crs.business.StudentOperations;
import com.lt.crs.utils.DBUtilsImpl;

/**
 * StudentMenuPage 
 *
 */
public class CRSStudentMenu {
	static CourseOperations co = new CourseOperations();
	static PaymentOperations pay = new PaymentOperations();
	static ReportCardOperations report = new ReportCardOperations();
	private static Statement stmt;
	public static Connection conn = DBUtilsImpl.conn;
	private static ResultSet res;
	static final Logger log = Logger.getLogger(CRSStudentMenu.class);

	/**
	 *
	 * Call to displayCourse(),dropCourseFromStudent(), calculateBill(),displayReportCard()
	 * methods is done from here
	 *
	 *///@StudentFunctions
	public static void student() {

   	 System.out.println("---------------------------------------------------------------");
			System.out.println("		Welcome Student to The CRS Application");
			System.out.println("				********");
			System.out.println("                                                         Current Date and Time: "+LocalDateTime.now());
			log.info("STUDENT LOGIN TIME:"+LocalDateTime.now());
			System.out.println("Please Key In the Selection");
			System.out.println();			
			System.out.println("1. View and Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Make Payment");
			System.out.println("4. View Report Card");
			System.out.println("5. Logout");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice){
			case 1:
				co.displayCourse(); 
			break;
			case 2:
				System.out.println("You have selected drop course option. \nPlease enter the COURSE ID to remove: ");
				int dropc = sc.nextInt();
				boolean b=StudentOperations.dropCourseFromStudent(CRSApplication.id,dropc);
				if(b==true){
					try {				
					stmt=conn.createStatement();
					res=stmt.executeQuery("Select message from notifications where id=8");
					if(res.next()==true) {
					System.out.println(res.getString(1));}
				} catch (SQLException e) {
					log.error(e.getMessage());
				}}
				System.out.println("Do you wish to drop another course? Select either Y/N: ");
				String c1 = sc.next();
				if(!c1.equals("N")) {
				System.out.println("Please enter the COURSE ID to remove: ");
				int c = sc.nextInt();		
					boolean b1=StudentOperations.dropCourseFromStudent(CRSApplication.id,c);
					if(b1==true){
						try {				
						stmt=conn.createStatement();						
						res=stmt.executeQuery("Select message from notifications where id=8");
						if(res.next()==true) {
						System.out.println(res.getString(1));}
					} catch (SQLException e) {
						log.error(e.getMessage());
					}}
				}else
					student();
			break;
			case 3:
				//System.out.println("Payment Status: Yes");
				pay.calculateBill();  // yet be added
			break;
			case 4:
				System.out.println("REPORT CARD");
				report.displayReportCard(); // yet to be added 
			break;
			case 5:
				System.out.println();
			break;
			default:
				System.out.println("Select any one from 1-5");
			}
		}

}
