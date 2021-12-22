package com.lt.crs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC Operations 
public class DummyJDBCOperations{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	private ResultSet res;
	private Statement stmt;
	private String cname;
	private String cid;
	private String cfee;
	public static String pname;	

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCfee() {
		return cfee;
	}
	public void setCfee(String cfee) {
		this.cfee = cfee;
	}
/*	public void connection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				   			       
	}	*/
/*	public boolean registerProfessor(int pid, String name, String cname, int cid, String dpt, String pwd) {

		try {
			pstmt=conn.prepareStatement("Insert into Professor values(?,?,?,?,?,?)");
			pstmt.setString(1,name);
			pstmt.setInt(2, pid);
			pstmt.setString(3,cname);
			pstmt.setInt(4, cid);
			pstmt.setString(5,dpt);
			pstmt.setString(6, pwd);

			int x= pstmt.executeUpdate();
			if(x>0) {
				return true;
			}else {
				return false; }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	} */
/*	public boolean validation(int pid, String pwd) {
		try {
			pstmt=conn.prepareStatement("Select * From Professor WHERE ID=? AND Password=?");
			pstmt.setInt(1, pid);
			pstmt.setString(2,pwd);

			res=pstmt.executeQuery();
			if(res.next()==true) {
				pname= res.getString("Name");
				return true;
			}else {return false;}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}  */
/*	public void viewCourses() {
		try {
			String sql= "Select * from Course";
			stmt=conn.createStatement();
			res=stmt.executeQuery(sql);
			while(res.next()==true) {
				cname=res.getString("CourseName");
				cid=res.getString("CourseID");
				System.out.println(cname+"		"+cid);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} */
}
