package com.lt.crs.app;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.business.AdminOperations;
import com.lt.crs.business.ReportCardOperations;

/**
 * AdminMenuPage 
 * @param report defines a ReportCardOperations Class Object's reference
 * @param adSer defines a AdminOperations Class Object's reference
 * @param sc defines Scanner Class Object's reference 
 * @param sid defines Student ID
 */
public class CRSAdminMenu {
     private AdminOperations adSer;
     public Scanner sc=new Scanner(System.in);
	private int sid;
	private ReportCardOperations report = new ReportCardOperations();
	static final Logger log = Logger.getLogger(CRSAdminMenu.class);
	/**
	 * AdminFunctions
	 * Call to removeProfessor(),removeStudent(), addCourse(),removeCourse(),displayReportCard2(),
	 * approveStudent(), approveProfessor() methods is done from here
	 *
	 */
	public void admin() {
    
    	 Boolean adminMenu=true;
 		while(adminMenu) {
 			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("		Welcome Admin to The CRS Application");
			System.out.println("				********");
			System.out.println("                                                         Current Date and Time: "+LocalDateTime.now());
			log.info("ADMIN LOGIN TIME:"+LocalDateTime.now());
			System.out.println("Please Key In the Selection");
 			System.out.println("\n 1. Remove Professor \n 2. Remove Student  \n 3. Add Course \n 4. Remove Course \n "
 					+ "5. Report Card Generation \n 6. Approve Student \n 7. Approve Professor \n 8. Logout");
 			int adminSelect=sc.nextInt();
 			switch(adminSelect) {
 			case 1:
 				adSer=new AdminOperations();
 				adSer.removeProfessor();
 				break;
 			case 2:
 				adSer=new AdminOperations();
 				adSer.removeStudent();				
 				break;	
 			case 3:
 				adSer=new AdminOperations();
 				adSer.addCourse();
 				break;
 			case 4:
 				adSer=new AdminOperations();
 				adSer.removeCourse();
 				break;
 			case 5:
 				adSer=new AdminOperations();
 				System.out.println();
 				System.out.println("Please enter student ID to view report card");
 				sid=sc.nextInt();
 				report .displayReportCard2(sid);	
 				break;
 			case 6:
 				adSer=new AdminOperations();
 				adSer.approveStudent();
 				break;
 			case 7:
 				adSer=new AdminOperations();
 				adSer.approveProfessor();
 				break;
 			case 8:
 				System.out.println(" Are you sure you want to log out Y or N: ");
 				char ask=sc.next().charAt(0);
 				if(ask=='y' || ask=='Y') {
 					CRSApplication.main(null);
 				}
 				
 			
 			}
 			
 		}
 		
 		

     }
}
