package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.lt.crs.app.CRSApplication;
import com.lt.crs.constant.SQLConstants;
import com.lt.crs.utils.DBUtilsImpl;
/**
 * CRSReportCardOperations
 * All the ReportCard Operations goes here
 * displayReportCard() method is defined here
 * ReportCardOperations implements ReportCardInterface
 *@param conn defines a JDBC connection
 *@param res defines result set variable
 *@param pstmt defines a prepared statement 
 */
public class ReportCardOperations implements ReportCardInterface {
	private Statement stmt;
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet res;
	private PreparedStatement pstmt;
	static final Logger log = Logger.getLogger(ReportCardOperations.class);

	/**
	 * {@code} displayReportCard() method is defined here
	 * @category Displaying grades to students based on ID is done here
	 * @param CRSApplication.id is the Student ID taken directly while login 
	 * @param STUD_REPORT is an SQL constant  
	 */
	@Override
	public void displayReportCard() {
		
		try {
			pstmt=conn.prepareStatement(SQLConstants.STUD_REPORT);	
			pstmt.setInt(1, CRSApplication.id);
			res=pstmt.executeQuery();
			System.out.println("*****************REPORT CARD******************");
			System.out.println();
			System.out.println("Please find the attached Report Card");
			System.out.println("----------------------------------------------");
			while(res.next()==true) {
			System.out.println("Student ID:\t\t"+res.getInt("SID"));// error
			System.out.println("Student Name:\t\t"+res.getString("SName"));
				System.out.println("Course ID:\t\t"+res.getInt("CID"));
				System.out.println("Course Name:\t\t"+res.getString("CName"));
				System.out.println("The Grade secured is:\t"+res.getString("Grade"));
			}
			System.out.println("----------------------------------------------");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally {  
			try {
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

	}

	/**
	 * {@code} displayReportCard2() method is defined here
	 * @category Displaying grades to students based on ID is done here
	 * @param sid is the Student ID 
	 * @param STUD_REPORT is an SQL constant  
	 */
public void displayReportCard2(int sid) {
	
	try {
		pstmt=conn.prepareStatement(SQLConstants.STUD_REPORT);	
		pstmt.setInt(1, sid);
		res=pstmt.executeQuery();
		System.out.println("*****************REPORT CARD******************");
		System.out.println();
		System.out.println("Please find the attached Report Card");
		System.out.println("----------------------------------------------");
		while(res.next()==true) {
		System.out.println("Student ID:\t\t"+res.getInt("SID"));// error
		System.out.println("Student Name:\t\t"+res.getString("SName"));
			System.out.println("Course ID:\t\t"+res.getInt("CID"));
			System.out.println("Course Name:\t\t"+res.getString("CName"));
			System.out.println("The Grade secured is:\t"+res.getString("Grade"));
		}
		System.out.println("----------------------------------------------");
	} catch (SQLException e) {
		log.error(e.getMessage());
	}finally {  
		try {
			res.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
}
}