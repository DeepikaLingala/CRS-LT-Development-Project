package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.CRSAdminMenu;
import com.lt.crs.business.ReportCardOperations;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.exceptions.ProfessorNotFoundForApprovalException;
import com.lt.crs.exceptions.StudentNotFoundForApprovalException;
import com.lt.crs.utils.DBUtilsImpl;
import com.lt.crs.validation.LoginValidation;
/**
 * Admin Data Access Objects goes here
 * AdminDAOImpl implements AdminDAOInterface
 * @param report defines a ReportCardOperations Class Object's reference 
 * @param pdao defines a ProfessorDAOImpl Class Object's reference 
 * @param a defines a CRSAdminMenu Class Object's reference 
 * @param lv defines a LoginValidation Class Object's reference 
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 */
public class AdminDAOImpl implements AdminDAOInterface{
	ReportCardOperations report= new ReportCardOperations();
	ProfessorDAOImpl pdao= new ProfessorDAOImpl();
	Scanner sc = new Scanner(System.in);
	LoginValidation lv= new LoginValidation();
	public CRSAdminMenu a= new CRSAdminMenu();
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet res;
	private PreparedStatement pstmt;
	CRSAdminMenu c=new CRSAdminMenu();
	private Statement stmt;
	private int max;
	private String str;
	private String str1;
	private String s;
	private String s1;
	private ResultSet res2;
	static final Logger log = Logger.getLogger(AdminDAOImpl.class);

	/**

	 * approval() method is defined here
	 * @param id professor ID
	 * @param name professor name
	 * @param dpt department 
	 * @param pwd Password 
	 * Call to registerProfessor() is done from here 
	 */

	public boolean approval(String name, String cname, int id, String dpt,String pwd) {
		// TODO Auto-generated method stub
		Boolean b=pdao.registerProfessor(name,cname,id,dpt,pwd);
		if(b==true) {
			return true;
		}
		return false;
	}
	/**

	 * passwordUpdation() method is defined here 	
	 * @param pid is the professor ID 
	 * @param ppwd is the Current Password  
	 * @param npwd is the New Password 
	 * Call to updatePassword()method is done from here
	 */
	public void passwordUpdation() {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------------------------");
		System.out.println("Password Updation Process Initiated:");
		//	System.out.println("Please enter your Name");
		//	String pname=sc.next();
		System.out.println("Please enter your ID");
		int pid=sc.nextInt();
		System.out.println("Please enter your Current Password");
		String ppwd=sc.next();
		System.out.println("Please enter New Password");
		String npwd=sc.next();
		boolean b = lv.updatePassword(pid,ppwd,npwd);
		if(b==true) {
			String pname = lv.pname;
			System.out.println("************************************************************");
			System.out.println("Password Updation successful");
			System.out.println("************************************************************");
		}else{
			System.out.println();
			System.out.println("************************************************************");
			System.out.println(" Updation failed");
			System.out.println("************************************************************");

		}

	}

	/**

	 * removeProfessor() method is defined here
	 * Admin can remove professors from here 
	 * @param REMOVE_PROF_1 is an SQL Constant 
	 * @param REMOVE_PROF_2 is an SQL Constant 
	 * @param pid is the professor ID 
	 */
	@Override
	public void removeProfessor() {
		System.out.println("Please enter the ID of the professor you would like to remove:");	
		int pid=sc.nextInt();
		try {
			pstmt=conn.prepareStatement(SQLConstants.REMOVE_PROF_2);
			pstmt.setInt(1, pid);
			int x=pstmt.executeUpdate();
			if(x>0) {
				pstmt=conn.prepareStatement(SQLConstants.REMOVE_PROF_1);
				pstmt.setInt(1, pid);
				int x1=pstmt.executeUpdate();
				if(x1>0) {
					System.out.println("Professor data has been deleted");
				}else {
					System.out.println("Professor does not exist in the Professor database");
				}
			}else {
				System.out.println("Professor does not exist in the database");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	/**

	 * removeStudent() method is defined here
	 * Admin can remove Students from here 
	 * @param REMOVE_STUD_1 is an SQL Constant 
	 * @param REMOVE_STUD_2 is an SQL Constant 
	 * @param REMOVE_STUD_3 is an SQL Constant 
	 * @param REMOVE_STUD_4 is an SQL Constant 
	 * @param sid is the Student ID 
	 */
	@Override
	public void removeStudent() {
		System.out.println("Please enter the ID of the Student you would like to remove:");	
		int sid=sc.nextInt();
		try {
			pstmt=conn.prepareStatement(SQLConstants.REMOVE_STUD_2);
			pstmt.setInt(1, sid);
			int x=pstmt.executeUpdate();
			if(x>0) {
				pstmt=conn.prepareStatement(SQLConstants.REMOVE_STUD_1);
				pstmt.setInt(1, sid);
				int x1=pstmt.executeUpdate();
				if(x1>0) {
					pstmt=conn.prepareStatement(SQLConstants.REMOVE_STUD_3);
					pstmt.setInt(1, sid);
					int x2=pstmt.executeUpdate();
					if(x2>0) {
						pstmt=conn.prepareStatement(SQLConstants.REMOVE_STUD_4);
						pstmt.setInt(1, sid);
						int x3=pstmt.executeUpdate();
						if(x3>0) {
							System.out.println("Student data has been deleted");
						}else {
							System.out.println("Student does not exist in the database");
						}
					}else {
						System.out.println("Student does not exist in the database");
					}
				}else {
					System.out.println("Student does not exist in the database");
				}
			}else {
				System.out.println("Student does not exist in the database");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}


	}
	/**

	 * addCourse() method is defined here
	 * Admin can add course from here 
	 * @param ADD_COURSE is an SQL Constant 
	 * @param cid is the Course ID 
	 * @param cname is the Course Name
	 * @param cfee is the Course Fee
	 * @param max is used to Auto increment the ID
	 * Call to admin() is done from here
	 */
	@Override
	public void addCourse() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("New Course Page");
		System.out.println("-------------------------------------------------------------");
		System.out.println(" Please give the Course Name");
		String cname=sc.next();
		System.out.println(" Please give the Course ID");
		int cid=sc.nextInt();
		System.out.println(" Please give the Course Fee");
		int cfee=sc.nextInt();
		try {
			stmt=conn.createStatement();			
			res=stmt.executeQuery("Select max(CourseID) as max_id From Course");
			if(res.next()==true) {
				max=res.getInt(1);
			}
			pstmt=conn.prepareStatement(SQLConstants.ADD_COURSE);
			pstmt.setString(1, cname);
			pstmt.setInt(2, ++max);
			pstmt.setInt(3, cfee);
			int y=pstmt.executeUpdate();
			if(y>0) {
				System.out.println("Course has been added successfully to the database");
				System.out.println("Would you like to add another Course.(Y/N)?");
				String n=sc.next();
				if(n.equals("Y")) {
					addCourse();
				}else {
					c.admin();
				}
			}else {
				System.out.println("Failed to add the Course to the database");
				System.out.println("Would you like to try again.(Y/N)?");
				String n=sc.next();
				if(n.equals("Y")) {
					addCourse();
				}else {
					c.admin();
				}
			}			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {pstmt.close();
			res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}

	}
	/**

	 * removeCourse() method is defined here
	 * Admin can remove course from here 
	 * @param REMOVE_COURSE is an SQL Constant 
	 * @param cid is the Course ID 
	 * Call to admin() is done from here
	 */
	@Override
	public void removeCourse() {
		System.out.println("Please enter the ID of the Course you would like to remove:");	
		int cid=sc.nextInt();

		try {
			pstmt=conn.prepareStatement(SQLConstants.REMOVE_COURSE);
			pstmt.setInt(1, cid);
			int x=pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Course has been removed from the database");
				System.out.println("Would you like to remove another Course.(Y/N)?");
				String n=sc.next();
				if(n.equals("Y")) {
					removeCourse();
				}else {
					c.admin();
				}
			}else {
				System.out.println("Failed to remove the Course from the database");
				System.out.println("Would you like to try again.(Y/N)?");
				String n=sc.next();
				if(n.equals("Y")) {
					removeCourse();
				}else {
					c.admin();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {pstmt.close();
			res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}



	}
	/**

	 * reportCardGeneration() method is defined here
	 * Call to displayReportCard() is done from here
	 */
	@Override
	public void reportCardGeneration() {
		//System.out.println("*************************REPORT CARD**************************");
		report.displayReportCard();
	}
	/**

	 * approveStudent() method is defined here
	 * Admin will first see all the pending approvals =and then approve them 
	 * @param STUDENT_DTL_1 is an SQL Constant 
	 * @param APPROVE_STUD is an SQL Constant 
	 * 	 * Call to admin() is done from here
	 * @exception StudentNotFoundForApprovalException()
	 */

	public void approveStudent() {
		try {
			pstmt=conn.prepareStatement(SQLConstants.STUDENT_DTL_1);
			pstmt.setString(1, "Approval Pending");
			pstmt.setString(2, "Rejected");			
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("Student-Name"+"\t"+ "Student-ID"+"\t"+ "Email ID"+"\t\t\t"+"Approval Status"+"	");
			System.out.println("---------------------------------------------------------------------------------");		
			res=pstmt.executeQuery();
			if(res.next()==true) {
				while(res.next()==true) {
					System.out.println(res.getString("Name")+"\t\t"+ res.getInt("ID")+"\t\t\t"+ res.getString("EmailID")+"\t\t\t"+res.getString("Status")+"	");
					System.out.println("---------------------------------------------------------------------------------");		 
				}

				System.out.println();
				System.out.println("Enter the Student ID to take action");
				int id=sc.nextInt();
				System.out.println("Type the action to be performed. (Approved/Rejected)");
				str=sc.next();			
				pstmt=conn.prepareStatement(SQLConstants.APPROVE_STUD);
				pstmt.setString(1, str);
				pstmt.setString(2, "Approval Pending");
				pstmt.setString(3, "Rejected");
				pstmt.setInt(4, id);
				int y=pstmt.executeUpdate();
				if(y>0) {
					System.out.println("Status Updated");
					log.info("Status Updated");
					System.out.println("Would you like to check other pending approvals? (Y/N)");
					s= sc.next();
					if(s.equalsIgnoreCase("Y")) {
						approveStudent();
					}else {
						a.admin();
					}
				}
				else {
					StudentNotFoundForApprovalException sa = new StudentNotFoundForApprovalException();
					System.out.println(sa.getMessage());
					System.out.println();
					log.info(sa.getMessage());
				}}else {
					StudentNotFoundForApprovalException sa = new StudentNotFoundForApprovalException();
					System.out.println(sa.getMessage());
					System.out.println();
					log.info(sa.getMessage());
					a.admin();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {pstmt.close();
			res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}

	}
	/**

	 * approveProfessor() method is defined here
	 * Admin will first see all the pending approvals =and then approve them 
	 * @param PROF_DTL is an SQL Constant 
	 * @param APPROVE_PROF is an SQL Constant 
	 * Call to admin() is done from here
	 * @exception ProfessorNotFoundForApprovalException()
	 */

	public void approveProfessor() {
		try {
			pstmt=conn.prepareStatement(SQLConstants.PROF_DTL);
			pstmt.setString(1, "Approval Pending");
			pstmt.setString(2, "Rejected");
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("Professor-Name"+"\t"+ "Professor-ID"+ "\t"+"Approval Status"+"	");
			System.out.println("---------------------------------------------------------------------------------");		
			res2=pstmt.executeQuery();
			//System.out.println(res2.next());
			if(res2.next()==true) {//System.out.println(res2.next());
				while(res2.next()==true) {
					//System.out.println(res2.next());

					System.out.println(res2.getString(1)+"\t\t"+ res2.getInt(2)+"\t"+res2.getString(3));
					System.out.println("---------------------------------------------------------------------------------");		 
				}
				System.out.println();
				System.out.println("Enter the Professor ID to take action");
				int id1=sc.nextInt();
				System.out.println("Type the action to be performed. Approved/Rejected");
				str1=sc.next();			

				pstmt=conn.prepareStatement(SQLConstants.APPROVE_PROF);
				pstmt.setString(1, str1);
				pstmt.setString(2, "Approval Pending");
				pstmt.setString(3, "Rejected");
				pstmt.setInt(4, id1);
				int y=pstmt.executeUpdate();
				if(y>0) {
					System.out.println("Status Updated");
					log.info("Status Updated");
					System.out.println("Would you like to check other pending approvals? (Y/N)");
					s1= sc.next();
					if(s1.equalsIgnoreCase("Y")) {
						approveProfessor();
					}else {
						a.admin();
					}
				}
				else {
					ProfessorNotFoundForApprovalException sa = new ProfessorNotFoundForApprovalException();
					System.out.println(sa.getMessage());
					System.out.println();
					log.info(sa.getMessage());
				}}else {
					ProfessorNotFoundForApprovalException sa = new ProfessorNotFoundForApprovalException();
					System.out.println(sa.getMessage());
					System.out.println();
					log.info(sa.getMessage());
					a.admin();
				} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {
				res2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}

	}



}
