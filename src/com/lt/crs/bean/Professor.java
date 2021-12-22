package com.lt.crs.bean;

import java.util.Scanner;

import com.lt.crs.business.*;

/**
 * @author deepikareddy 
 * ProfessorClass 
 * Professor details goes here 
 */
public class Professor {
private String professorName;
private String courseName;
private String department;
private int courseId;
private String grade;
private String credential;
private int pid;
ReportCardOperations report= new ReportCardOperations();
CourseOperations co= new CourseOperations();

public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
AdminOperations ao = new AdminOperations();
 public ProfessorOperations po= new ProfessorOperations();
private int sid;
private Scanner sc= new Scanner(System.in);
public String getCredential() {
	return credential;
}
public void setCredential(String credential) {
	this.credential = credential;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public Professor() {
	
}

public Professor(String professorName, String courseName, String department, int courseId,
		String credential, int pid) {
	super();
	this.professorName = professorName;
	this.courseName = courseName;
	this.department = department;
	this.courseId = courseId;
	this.credential = credential;
	this.pid = pid;
	
}
public String getProfessorName() {
	return professorName;
}
public void setProfessorName(String professorName) {
	this.professorName = professorName;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}

public void viewStudents() {
	ao.viewAllStudents();
}
public boolean addGrades() {
	boolean b=po.addGrades();
	return b;
}
/**
 * @ShowGrades 
 * call to  displayReportCard2() is done here
 */
public void showGrades() {
	//ao.showGrades();
	System.out.println();
	System.out.println("Please enter student ID to view report card");
	sid=sc.nextInt();
	report.displayReportCard2(sid);	
	
}
public void viewCourses() {
	co.viewCourses();
}
 
}
