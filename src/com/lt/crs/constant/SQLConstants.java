package com.lt.crs.constant;
/**
 * @author deepikareddy 
 * SQL Constants Class 
 * SQL Constants goes here 
 */
public class SQLConstants {
	public static final String ADD_STUDENT = "insert into student values(?,?,?,?,?)";
	public static final String VIEW_USERS = "select * from user where username=?";
	public static final String SELECT_MAX_USER_ID = "SELECT MAX(ID) as ID from user";
	public static final String UPDATE_PASSWORD = "update user set password=? where name=?";
	public static final String View_Students = "Select * From Student";
	public static final String STUDENT_COUNT="Select count(*) From Student";
	public static final String APPROVAL_STATUS="Insert into ApprovalStatus values(?,?,?,?,?)";
	public static final String INSERT_USER="Insert into User values(?,?,?,?)";
	public static final String INSERT_PROFESSOR="Insert into Professor values(?,?,?,?,?,?)";
	public static final String STUD_COU_INS="insert into Student_Course values(?,?,?)";
	public static final String STUD_COU_DROP=" Delete from Student_Course where sid=? AND cid=?";
	public static final String STUD_GRADE=" Insert into Student_Grades values(?,?,?,?,?)";
	public static final String STUD_REPORT=" Select * From student_grades where SID=?";
	public static final String Course_Count="Select count(CID) As cid_count From student_course where SID=?";
	public static final String Course_Check="select * From Student_Course where cid=? And sid=?";
	public static final String Course_PRO_CHECK="Select * From Professor where CourseID=?";
	public static final String STUDENT_SPEC_DTL="select SID from Student_course where cid=?";
	public static final String STUDENT_DTL="select * from Student where id=?";
	public static final String ADD_GRADES="Insert into Student_Grades values(?,?,?,?,?)";
	public static final String REMOVE_PROF_1="Delete from Professor where id=?";
	public static final String REMOVE_PROF_2="Delete from User where id=?";
	public static final String REMOVE_STUD_1 = "Delete from Student where id=?";
	public static final String REMOVE_STUD_2 = "Delete from User where id=?";
	public static final String REMOVE_STUD_3 = "Delete from Student_Course where sid=?";
	public static final String REMOVE_STUD_4 = "Delete from Student_Grades where sid=?";
	public static final String ADD_COURSE = "Insert into Course values(?,?,?)";
	public static final String REMOVE_COURSE = "Delete from Course where CourseID=?";
	public static final String CHECK_LIMIT = "Select count(CID) from Student_Course where cid=?";
	public static final String APPROVE_STUD = "Update Student set status=? where (status=? OR status=?) And id=?";
	public static final String APPROVE_PROF ="Update Professor set ApprovalStatus=? where (ApprovalStatus=? OR ApprovalStatus=?) And id=?";
	public static final String STUDENT_DTL_1 = "Select * From Student where Status=? OR status=?";
	public static final String PROF_DTL = "Select Name,ID,ApprovalStatus From Professor where (ApprovalStatus=? OR ApprovalStatus=?)";
	public static final String COURSE_DTL = "Select * from student_course where sid=? and PaymentStatus=?";
	public static final String COURSE_FEE_DTL = "Select CourseFee from Course where CourseID=?";
	public static final String PAYMENT = "Insert into Transactions values(?,?,?,?,?,?)";


}
