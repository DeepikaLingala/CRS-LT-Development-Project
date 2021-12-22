package com.lt.crs.bean;

//Student details goes here
/**
 * @author deepikareddy 
 * StudentClass 
 * Student details goes here 
 */
public class Student {
private String studentName;
private int studentId;
private String department;
private String courseName;
private int courseId;
private char grade;

public Student() {
	// TODO Auto-generated constructor stub
}
public Student(String studentName, int studentId, String department, String courseName, int courseId) {
	super();
	this.studentName = studentName;
	this.studentId = studentId;
	this.department = department;
	this.courseName = courseName;
	this.courseId = courseId;

}
public char getGrade() {
	return grade;
}
public void setGrade(char grade) {
	this.grade = grade;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getCoursseName() {
	return courseName;
}
public void setCoursseName(String courseName) {
	this.courseName = courseName;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}


}
