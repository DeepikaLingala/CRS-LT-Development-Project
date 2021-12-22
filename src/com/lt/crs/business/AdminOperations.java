package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.CRSAdminMenu;
import com.lt.crs.bean.Course;
import com.lt.crs.bean.Student;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.dao.AdminDAOInterface;
import com.lt.crs.utils.DBUtilsImpl;
import com.lt.crs.utils.DummyJDBCOperations;

/**
 * AdminOperations
 * All the Admin Operations goes here
 * Call to removeProfessor(),removeStudent(), addCourse(),removeCourse(),reportCardGeneration(),
 * approveStudent(), approveProfessor() methods is done from here
 * AdminOperations implements AdminInterface
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 *
 */
public class AdminOperations implements AdminInterface {
	AdminDAOImpl admin=new AdminDAOImpl();
	private static PreparedStatement pstmt;
	public static Connection conn = DBUtilsImpl.conn;
	static final Logger log = Logger.getLogger(AdminOperations.class);

	static Student arr[];
	static Course cou[];
	static ArrayList<String> al= new ArrayList<String>();
	static ArrayList<String> al2= new ArrayList<String>();
	static DummyJDBCOperations db= new DummyJDBCOperations();
	static public Map m = new HashMap();
	private Statement stmt;
	private ResultSet res;

	//not calling this method anymore
	public void enrolledStudents() {
		Student s1= new Student("Ramesh",1,"IT","Java",101);
		Student s2= new Student("Deepika",2,"IT","Java",101);
		Student s3= new Student("Bharati",3,"IT","Java",101);
		Student s4= new Student("Bharati",3,"IT","SQL",102);
		Student s5= new Student("Shiva",4,"IT","Java",101);
		Student s6= new Student("Shiva",4,"IT","SQL",102);
		arr= new Student[6];  
		arr[0]=s1;
		arr[1]=s2;
		arr[2]=s3;
		arr[3]=s4;
		arr[4]=s5;
		arr[5]=s6;
		//System.out.println(arr.length);
	}
	/**

	 * viewAllStudents() method is defined here
	 * @param conn defines a JDBC connection
	 * @param res defines result set variable
	 * @param pstmt defines a prepared statement 
	 * Call to registerProfessor() is done from here 
	 */
	public void viewAllStudents() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Student-Name"+"\t"+ "Student-ID"+"\t"+ "Email ID"+"\t\t\t"+"DOB(DD-MM-YYYY)"+"	");
		System.out.println("---------------------------------------------------------------------------------");
		/*	for(int i=0;i<arr.length;i++) { // null pointer exception
			//System.out.println(arr.length);
			Student s= arr[i];
			System.out.println(s.getStudentName()+"		 "+ s.getStudentId()+"		"+ s.getCourseId()+"		"+s.getCoursseName()+"		"+ s.getDepartment());					
		} */
		try {
			stmt=conn.createStatement();
			res=stmt.executeQuery(SQLConstants.View_Students);
			while(res.next()==true) {
				System.out.println(res.getString("Name")+"\t\t  "+ res.getInt("ID")+"\t\t"+ res.getString("EmailID")+"\t\t"+res.getString("DOB"));					
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {  // added by Deepika
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}

	}
	//not calling this method anymore
	public void addGrades() {
		int score=0;
		for(int i=0;i<arr.length;i++) {
			Student s= arr[i];
			System.out.println("Enter the score of "+ s.getStudentName());
			Scanner sc= new Scanner(System.in);
			score=sc.nextInt();
			if(score>90)
			{
				s.setGrade('A');
			}else if(score>80 && score<90) {
				s.setGrade('B');
			}else if(score>70 && score<80) {
				s.setGrade('C');
			}else {
				s.setGrade('D');
			}

		} 

	}
	/*	static public void showGrades() {
		for(int i=0;i<arr.length;i++) {
			Student s= arr[i];
			System.out.println(s.getStudentName()+" "+ s.getGrade());

		}
	} */
	
	//not calling this method anymore
	public void showGrades() {
		System.out.println("-------------------------------");
		System.out.println("Student-Name"+"	"+"Grade");
		System.out.println("-------------------------------");
		for(int i=0;i<arr.length;i++) {
			Student s= arr[i];
			System.out.println(s.getStudentName()+"		"+ s.getGrade());

		}
	}
	
	//not calling this method anymore
	public void availableCourses() {

		Course c1 = new Course("Java", 101,5000);
		Course c2 = new Course("SQL", 102,3000);
		Course c3 = new Course("Oracle", 103,5000);
		Course c4 = new Course("DBMS", 104,4000);
		m.put(1, c1);
		m.put(2, c2);
		m.put(3, c3);
		m.put(4, c4);
		/*
		 * cou[0]=c1; cou[1]=c2; cou[2]=c3;
		 */

	}



	/* public Professor enrollProfessors() {
		Professor p1= new Professor("Deepika","Java","IT",101,"abcd1");
		Professor p2= new Professor("Awez","SQL","IT",102,"abcd2");
		Professor p3= new Professor("Shiva","Oracle","IT",103,"abcd3");
		Professor p4= new Professor("Pallavi","DBMS","IT",104,"abcd4");
		al2.add(p1.getCredential());
		al2.add(p2.getCredential());
		al2.add(p3.getCredential());
		al2.add(p4.getCredential());

		al.add(p1.getProfessorName());
		al.add(p2.getProfessorName());
		al.add(p3.getProfessorName());
		al.add(p4.getProfessorName());      
		return p1; 

	}  */
	/*
	public boolean validation(int pid, String pwd) {
		/*	if(al.contains(name)==true && al2.contains(pwd)) {	
			return true;
		}
		else {
			return false;
		} 

		boolean b=db.validation(pid,pwd);
		if(b==true) {
			return true;
		}else return false; 

	} */
	/*	public boolean registerProfessor(int pid, String name, String cname, int id, String dpt, String pwd) {
		// registering new professor
			Professor p= new Professor(name, cname, dpt, id, pwd);
		al.add(p.getProfessorName());
		al2.add(p.getCredential()); 
		boolean b=db.registerProfessor(pid, name, cname, id, dpt, pwd);
		if(b==true) {
			return true;
		}else return false;
	}  */
	/*	public boolean updatePwdValidation(String pname, String ppwd, String npwd) {
		if(al.contains(pname)==true && al2.contains(ppwd)) {	
			int x = al.indexOf(pname);
			int y = al2.indexOf(ppwd);
			if(x==y) {
				al2.set(y, npwd);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	 */


	@Override
	public void removeProfessor() {
		// TODO Auto-generated method stub
		admin.removeProfessor();

	}


	@Override
	public void removeStudent() {
		// TODO Auto-generated method stub
		admin.removeStudent();

	}

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		admin.addCourse();

	}

	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
		admin.removeCourse();

	}

	@Override
	public void reportCardGeneration() {
		// TODO Auto-generated method stub
		admin.reportCardGeneration();


	}
	public void approveStudent() {
		admin.approveStudent();

	}
	public void approveProfessor() {
		admin.approveProfessor();

	}



}
