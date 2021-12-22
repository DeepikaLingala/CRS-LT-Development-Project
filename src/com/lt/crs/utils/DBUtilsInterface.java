package com.lt.crs.utils;

/**
 * @author deepikareddy
 *  Data Base Utils Interface
 *  getConnection(), closeConnection() methods are declared here
 *  DBUtilsImpl implements DBUtilsInterface 
 *
 */
public interface DBUtilsInterface {
   public void getConnection();
   public void closeConnection();
}
