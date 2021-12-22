package com.lt.crs.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.business.AdminOperations;
import com.lt.crs.business.PaymentOperations;
import com.lt.crs.business.ProfessorOperations;
import com.lt.crs.business.StudentOperations;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.exceptions.ProfessorNotRegisteredException;
import com.lt.crs.exceptions.StudentNotRegisteredException;
import com.lt.crs.utils.DBUtilsImpl;
import com.lt.crs.validation.LoginValidation;
/**
 * @author deepikareddy
 * CRSMainMenuPage 
 * Call to Admin, Professor and Student menu's will be done from here
 *
 */
public class CRSApplication {
	static final Logger log = Logger.getLogger(CRSApplication.class);
	public static int id;
	/**
	 * CRS Main method 
	 * @param db defines a DBUtilsImpl Class Object's reference 
	 * @param lv defines a LoginValidation Class Object's reference 
	 * @param am defines a CRSAdminMenu Class Object's reference 
	 * @param sm defines a CRSStudentMenu Class Object's reference 
	 * @param pm defines a CRSProfessorMenu Class Object's reference 
	 * @param ao defines a AdminOperations Class Object's reference
	 * @param po defines a ProfessorOperations Class Object's reference
	 * @param so defines a StudentOperations Class Object's reference
	 * @param adao defines a AdminDAOImpl Class Object's reference
	 * @param sc defines Scanner Class Object's reference 
	 * 
	 * call to getConnection() is done from here 
	 * call to userValidation() is done from here
	 * call to admin() is done from here
	 * call to student() is done from here
	 * call to professor() is done from here
	 * call to registerStudent() is done from here
	 * call to professorReg() is done from here
	 * call to passwordUpdation() is done from here
	 * call to closeConnection() is done from here
	 * call to studentRegValidation() is done from here
	 * call to professorRegValidation() is done from here
	 * 
	 * @exception StudentNotRegisteredException() is used here
	 * @exception ProfessorNotRegisteredException() is used here
	 */
	public static void main(String[] args) {
		DBUtilsImpl db= new DBUtilsImpl(); 
		db.getConnection();
		log.info("Connection to Database is established-------");
		LoginValidation lv= new LoginValidation();
		CRSAdminMenu am=new CRSAdminMenu();
		CRSStudentMenu sm= new CRSStudentMenu();
		CRSProfessorMenu pm = new CRSProfessorMenu();
		AdminOperations ao= new AdminOperations();
		ProfessorOperations po= new ProfessorOperations(); 
		ao.enrolledStudents();
		ao.availableCourses();
		AdminDAOImpl adao= new AdminDAOImpl();		
		StudentOperations so= new StudentOperations();
		
		System.out.println(" *************  Welcome to CRS Application ************* ");
		System.out.println("---------------------------------------------------------------");
		//log.info(" *************  Welcome to CRS Application ************* ");
		//log.info("---------------------------------------------------------------");
		while(true) {
			System.out.println(" Please Select the required option  ");
			System.out.println("---------------------------------------------------------------");
			System.out.println("	1.Login");
			System.out.println("	2.Sign Up");
			System.out.println("	3.Update Password");
			System.out.println("	4.Exit");
			System.out.println("---------------------------------------------------------------");				
			System.out.println();

			Scanner sc = new Scanner(System.in);
			int option=sc.nextInt();
			if(option==1) {
				System.out.println(" Login Process Initaited");
				log.info(" Login Process Initaited");
				System.out.println();
				System.out.println(" Please enter your ID");
				 id= sc.nextInt();
				System.out.println(" Please enter your Password");
				String pwd=sc.next();
				if(lv.userValidation(id, pwd)==true) {
					if(LoginValidation.role.equals("Admin")) {					
						am.admin(); // Calling CRSAdmin Menu
						System.out.println("---------------------------------------------------------------");
					}else if(LoginValidation.role.equals("Student")) {
						boolean b=lv.studentRegValidation(id);
						if(b==true) {
							sm.student(); // Calling CRSStudentMenu
							System.out.println("---------------------------------------------------------------");						
						}else {
							StudentNotRegisteredException se= new StudentNotRegisteredException();
							System.out.println(se.getMessage());
						}
						}else{
							boolean b=lv.professorRegValidation(id);
							if(b==true) {
								pm.professor(); // Calling CRSProfessorMenu
								System.out.println("---------------------------------------------------------------");						
							}else {
								ProfessorNotRegisteredException se= new ProfessorNotRegisteredException();
								System.out.println(se.getMessage());
							}						
					}
				}else {
					System.out.println("************************************************************");
					System.out.println("Invalid User");
					log.error("-----Invalid User-----");
					System.out.println("************************************************************");
				}

			}else if(option==2) {
				System.out.println("************************************************************");
				System.out.println(" Please Select the required option  ");				
				System.out.println("1. Student Registration");
				System.out.println("2. Professor Registration");
				System.out.println("3. Exit");

				System.out.println("---------------------------------------------------------------");
				System.out.println(" Please enter your option");
				int op= sc.nextInt();
				if(op==1) {
					    so.registerStudent();
				}else if(op==2){
					po.professorReg();										
				}else {
					break;}								
			}else if(option==3) {
				adao.passwordUpdation();
			}else {
				System.out.println("---------------------------------------------------------------");
				System.out.println("Thank you, Have a nice day!");
				log.info("Thank you, Have a nice day!");
				db.closeConnection();
				System.exit(0);			
			}
		}
		

	} }
