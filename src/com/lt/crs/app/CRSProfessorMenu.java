package com.lt.crs.app;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.bean.Professor;
/**
 * @CRSProfessorMenu 
 *
 */
public class CRSProfessorMenu {
	/**
	 * @ProfessorFunctions 
	 * viewStudents, addGrades, showGrades,viewCourses methods will be called from here
	 *
	 */
	static final Logger log = Logger.getLogger(CRSProfessorMenu.class);
	 public static void professor() {
		 Professor p= new Professor();
			while(true) {
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("		Welcome Professor to The CRS Application");
				System.out.println("				********");
				System.out.println("                                                         Current Date and Time: "+LocalDateTime.now());
				log.info("PROFESSOR LOGIN TIME:"+LocalDateTime.now());
				System.out.println("Please Key In the Selection");
				System.out.println("	1. View All Students");
				System.out.println("	2. Add Grades");
				System.out.println("	3. View Grades");
				System.out.println("	4. View Courses");
				System.out.println("	5. Exit");
				
				Scanner sc = new Scanner(System.in);
				int option2=sc.nextInt();
				if(option2==1) {
					System.out.println("Find The Attached Student Details");
					p.viewStudents();
				}else if(option2==2) {
					System.out.println("Grading Section");
					boolean b=p.addGrades();
					if(b==true) {
					System.out.println("************************************************************");
					System.out.println("Grades Updated successfully");
					System.out.println("************************************************************");}
					else {
						System.out.println("************************************************************");
						System.out.println("Grades are not Updated ");
						System.out.println("************************************************************");}
										
				}else if(option2==3){
					System.out.println("************************************************************");
					System.out.println("			Grading Section				");
					System.out.println("************************************************************");

					p.showGrades();
					CRSProfessorMenu.professor();
				}else if(option2==4){
					System.out.println("Course Section");
					// 1. view course 2.select course( we will update the pcourse in db)
					p.viewCourses();
					
				}else {
					CRSApplication.main(null);
					//break;
				} }}
}
