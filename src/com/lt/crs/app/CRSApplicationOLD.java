package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.bean.Professor;
import com.lt.crs.business.AdminOperations;
import com.lt.crs.business.ProfessorOperations;
import com.lt.crs.dao.ProfessorDAOImpl;
import com.lt.crs.utils.DBUtilsImpl;
//import com.lt.crs.utils.DummyJDBCOperations;
import com.lt.crs.validation.LoginValidation;

public class CRSApplicationOLD {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminOperations ao= new AdminOperations();
		DBUtilsImpl db= new DBUtilsImpl(); // added by Deepika
		db.getConnection();
		LoginValidation lv= new LoginValidation(); // added by Deepika 
		//System.out.println(lv);
		//ProfessorOperations po= new ProfessorOperations(); // Added by Deepika
		ProfessorDAOImpl po=new ProfessorDAOImpl();
		//DummyJDBCOperations db = new DummyJDBCOperations();
		ao.enrolledStudents();
		ao.availableCourses();
		
		//Professor p=ao.enrollProfessors();
		Professor p= new Professor();
		System.out.println(" ********  Welcome to CRS Application ********* ");
		System.out.println("---------------------------------------------------------------");
		
		
		while(true) {
			System.out.println(" Please Select The Role You Belong To ");
			System.out.println("---------------------------------------------------------------");
			System.out.println("	1.Student");
			System.out.println("	2.Professor");
			System.out.println("	3.Admin");
			System.out.println("	4.Exit");
			
			Scanner sc = new Scanner(System.in);
			int option=sc.nextInt();
			if(option==1) {
				System.out.println("---------------------------------------------------------------");
				System.out.println("	Welcome Student to The CRS Application");
				System.out.println("			********");
				System.out.println();
				System.out.println("Please Key In Your Choice");
				System.out.println("1.Sign In");
				System.out.println("2.Sign Up");
				System.out.println("3.Update Password");
				System.out.println("4.Exit");
				System.out.println(" ");
			}
			else if(option==2)
			{   
				System.out.println("---------------------------------------------------------------");
				System.out.println("	Welcome Professor to The CRS Application");
				System.out.println("			********");
				System.out.println("Please Key In the Selection");
				System.out.println("	1.Sign In");
				System.out.println("	2.Sign Up");
				System.out.println("	3.Update Password");
				System.out.println("	4.Exit");
				
				int op=sc.nextInt();
				if(op==2) {
					System.out.println("---------------------------------------------------------------");
					System.out.println("Professor Registration Process Initaited");
					System.out.println("Please enter your Name");
					String name=sc.next();
					System.out.println("Please enter your ID");
					int pid=sc.nextInt();
					System.out.println("Please enter the Course Name");
					String cname=sc.next();					
					System.out.println("Please enter the Course ID");
					int id= sc.nextInt();
					System.out.println("Please enter the Department");
					String dpt=sc.next();
					System.out.println("Please set the Password");
				    String pwd=sc.next();
					boolean b=po.registerProfessor(name,cname,id,dpt,pwd); // changed from ao to po
					if(b==true) {
						System.out.println("************************************************************");
						System.out.println("User Registered successfully");
						System.out.println("************************************************************");
					 }else {System.out.println("************************************************************");
						System.out.println("User Registration Failed");
						System.out.println("************************************************************");
					}
					
				}else if(op==1){
					System.out.println("---------------------------------------------------------------");
					System.out.println("Professor Sign-In Process Initaited");
					System.out.println();
				System.out.println("Please enter your ID");
				int pid=sc.nextInt();
				System.out.println("Please enter your pwd");
				String ppwd=sc.next();
				if(lv.validation(pid,ppwd)==true) { // Changed from db to lv by Deepika // error
					String pname = lv.pname; // changed from db to lv / LoginValidation
					System.out.println("---------------------------------------------------------------");
					System.out.println("Hello "+pname+", Welcome!");
					while(true) {
						System.out.println("---------------------------------------------------------------");
						System.out.println("Please select the activity");
						System.out.println("	1. View All Students");
						System.out.println("	2. Add Grades");
						System.out.println("	3. View Grades");
						System.out.println("	4. View Courses");
						System.out.println("	5. Exit");

						int option2=sc.nextInt();
						if(option2==1) {
							System.out.println("Find The Attached Student Details");
							p.viewStudents();
						}else if(option2==2) {
							System.out.println("Grading Section");
							p.addGrades();
							System.out.println("************************************************************");
							System.out.println("Grades Updated successfully");
							System.out.println("************************************************************");
						}else if(option2==3){
							System.out.println("Grading Section");
							p.showGrades();
						}else if(option2==4){
							System.out.println("Course Section");
							p.viewCourses();
						}else {
							break;
						} } }
				else {
					System.out.println("************************************************************");
					System.out.println("Invalid User");
					System.out.println("************************************************************");
				} 
				}else if(op==3){
					System.out.println("---------------------------------------------------------------");
					System.out.println("Password Updation Process Initiated:");
				//	System.out.println("Please enter your Name");
				//	String pname=sc.next();
					System.out.println("Please enter your ID"); // Added by Deepika
					int pid=sc.nextInt();
					System.out.println("Please enter your pwd");
					String ppwd=sc.next();
					System.out.println("Please enter your new pwd");
					String npwd=sc.next();
					boolean b = lv.updatePwdValidation(pid,ppwd,npwd); // changed pname to pid by Deepika
					if(b==true) {
						String pname = lv.pname;// Line added by Deepika
						System.out.println("************************************************************");
						System.out.println("Password Updation successful for the Professor - "+pname);
						System.out.println("************************************************************");
					}else {
						System.out.println();
						System.out.println(" Updation failed");
					}
				}else {
					break;
				}
			}else if(option==3) {
				System.out.println("---------------------------------------------------------------");
				System.out.println("	Welcome Student to The CRS Application");
				System.out.println("			********");
				System.out.println();
				}else {
				break;
			}
			
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("Thank you, Have a nice day!");

	}

}
