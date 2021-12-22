package com.lt.crs.validation;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;

import com.lt.crs.utils.DBUtilsImpl;
import com.lt.crs.constant.*;
import com.lt.crs.exceptions.StudentNotRegisteredException;

/**	
 * @author deepikareddy
 * Login Validations goes here
 * @param roleid defines the Role ID
 * @param pname defines a Professor Name
 * @param conn defines a JDBC connection
 * @param res defines result set variable
 * @param pstmt defines a prepared statement 
 */
public class LoginValidation implements LoginValidations{
	
	private PreparedStatement pstmt;
	public Connection conn = DBUtilsImpl.conn;
	private ResultSet res;
	private int roleid;
	
	public static String role;
	public static String pname; // changed to static
	private static final Logger log = Logger.getLogger(LoginValidation.class);
	/**	
	 * validation() method is defined here
	 * @param pid defines a Professor ID
	 * @param pwd defines a Professor Password 
	 * This method checks for password validation
	 */
	public boolean validation(int pid, String pwd) {
		try {
			//System.out.println(pid +" "+ pwd);
			//System.out.println(conn);
			pstmt=conn.prepareStatement("Select * From professor WHERE ID=? AND Password=?"); // error
			pstmt.setInt(1, pid);
			pstmt.setString(2,pwd);
			
			res=pstmt.executeQuery();
			if(res.next()==true) {
				pname= res.getString("Name"); 
				return true;
			}else {return false;}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			
		}
		
		return false;
	}
	/**	
	 * updatePwdValidation() method is defined here
	 * @param pid defines a Professor ID
	 * @param pwd defines a Current Password
	 * @param pwd defines a New Password
	 *  
	 * This method updates your current password with new password
	 */
	@Override
	public boolean updatePwdValidation(int pid, String pwd, String npwd) {
	try {
		pstmt=conn.prepareStatement("Select * from Professor where password=?");
		pstmt.setString(1,pwd);
		res=pstmt.executeQuery();
		if(res.next()==true) {
			pstmt= conn.prepareStatement("Update Professor set Password=? where ID=?" );
			
			pstmt.setString(1, npwd);	
			pstmt.setInt(2,pid);
			int x= pstmt.executeUpdate();
			if(x>0) {
				return true;
			}else {return false;}
			
		}
		else {
			return false;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		log.error(e.getMessage());
	}finally {
		try {
			pstmt.close();
			res.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		
	}
	
		
		return false;
		
	
	}
	/**	
	 * userValidation() method is defined here
	 * @param id defines a User ID
	 * @param pwd defines a User Password
	 *  
	 * This method checks for password validation
	 */
	@Override
	public boolean userValidation(int id, String pwd) {
		try {
			//System.out.println(id +" "+ pwd);
			//log.debug(conn);
			pstmt=conn.prepareStatement("Select * From User WHERE ID=? AND Password=?"); // error
			pstmt.setInt(1, id);
			pstmt.setString(2,pwd);
			
			res=pstmt.executeQuery();
			if(res.next()==true) {
				pname= res.getString("Name"); 
				//id=res.getInt("ID");
				roleid=res.getInt("RoleID");
				//System.out.println(pname+" "+id);				
		pstmt=conn.prepareStatement("Select * From Role WHERE RoleID=?"); // error
				pstmt.setInt(1, roleid);
				res=pstmt.executeQuery();
				while(res.next()==true) {
					role=res.getString("Role");
					//System.out.println("Role  "+role);					
				} 
			pstmt=conn.prepareStatement("Insert into LoginTracker Values(?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, pname);
			pstmt.setString(3, LocalDate.now().toString());
			pstmt.setString(4, LocalTime.now().toString());
			pstmt.executeUpdate();
				return true;
			}else {return false;}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			
		}
		
		return false;
	}
	/**	
	 * updatePassword() method is defined here
	 * @param id defines a User ID
	 * @param pwd defines a Current Password
	 * @param npwd defines a New Password
	 *  
	 * This method updates your current password with new password
	 */
	@Override
	public boolean updatePassword(int id, String pwd, String npwd) {
		try {
			pstmt=conn.prepareStatement("Select * from User where password=?");
			pstmt.setString(1,pwd);
			res=pstmt.executeQuery();
			if(res.next()==true) {
				pstmt= conn.prepareStatement("Update User set Password=? where ID=?" );
				pstmt.setString(1, npwd);	
				pstmt.setInt(2,id);
				int x= pstmt.executeUpdate();
				if(x>0) {
					return true;
				}else {return false;}
				
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			
		}
					
			return false;			
			}
	/**	
	 * studentRegValidation() method is defined here
	 * @param id defines a Student ID
	 *  
	 * This method validates Student Registration
	 */
	public boolean studentRegValidation(int id) {
		try {
			pstmt=conn.prepareStatement("Select * from Student where id=? AND Status=?");
			pstmt.setInt(1, id);
			pstmt.setString(2,"Approved");
			res=pstmt.executeQuery();
			if(res.next()==true) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			
		}
		
		
		return false;
	}
/**	
* professorRegValidation() method is defined here
* @param id defines a Professor ID
*  
* This method validates Professor Registration
*/
	public boolean professorRegValidation(int id) {
		try {
			pstmt=conn.prepareStatement("Select * from Professor where id=? AND ApprovalStatus=?");
			pstmt.setInt(1, id);
			pstmt.setString(2,"Approved");
			res=pstmt.executeQuery();
			if(res.next()==true) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}finally {
			try {
				pstmt.close();
				res.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
			
		}
		
		return false;
	}	

}
