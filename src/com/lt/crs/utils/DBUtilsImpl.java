package com.lt.crs.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
/**	
 * @author deepikareddy
 * getConnection() and closeConnection() methods are defined here
 * Config.properties is called from here.
 * Database related inputs are read from config.properties
 */
public class DBUtilsImpl {
	private static final Logger log = Logger.getLogger(DBUtilsImpl.class);
	public static Connection conn = null;
	/**	
	 * getConnection() method is defined here
	 * This method establishes JDBC connection
	 * Config.properties is called from here.
	 * Database related inputs are read from config.properties
	 */
	public static Connection getConnection() {
		
        if (conn != null)
            return conn;
        else {
            try {
            	Properties prop = new Properties();
                InputStream inputStream = DBUtilsImpl.class.getClassLoader().getResourceAsStream("./config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
            	log.error(e.getMessage());
            } catch (SQLException e) {
            	log.error(e.getMessage());
            } catch (FileNotFoundException e) {
            	log.error(e.getMessage());
            } catch (IOException e) {
            	log.error(e.getMessage());
            }
            return conn;
        }

    }
	/**	
	 * closeConnection() method is defined here
	 * This method close JDBC connection
	 */
public void closeConnection() {
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		log.error(e.getMessage());
	}
}

}
