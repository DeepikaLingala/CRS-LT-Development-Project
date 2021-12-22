package com.lt.crs.business;

/**
 * @author deepikareddy
 * Admin Interface
 * removeProfessor(),removeStudent(), addCourse(),removeCourse(),reportCardGeneration(),
 * approveStudent(), approveProfessor() methods are declared here
 * AdminOperations implements AdminInterface 
 *
 */
public interface AdminInterface {
	public void enrolledStudents();
    public void viewAllStudents();
    public void addGrades();
    public void showGrades();
    public void availableCourses();
    //-------------------------------------------

	public void removeProfessor();

	public void removeStudent();

	public void addCourse();

	public void removeCourse();

	public void reportCardGeneration();
	
	public void approveStudent();
	
	public void approveProfessor();
  } 
