package com.lt.crs.bean;

/**
 * @author deepikareddy 
 * CourseClass 
 * Course details goes here 
 */
public class Course {
private String courseName;
private int courseId;
private int courseFee;


public void setCourseName(String courseName) {
	this.courseName = courseName;
}


public void setCourseId(int courseId) {
	this.courseId = courseId;
}


public void setCourseFee(int courseFee) {
	this.courseFee = courseFee;
}


public String getCourseName() {
	return courseName;
}


public int getCourseId() {
	return courseId;
}


public int getCourseFee() {
	return courseFee;
}


public Course(String courseName, int courseId, int courseFee) {
	super();
	this.courseName = courseName;
	this.courseId = courseId;
	this.courseFee = courseFee;
}

}
