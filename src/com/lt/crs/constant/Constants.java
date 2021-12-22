package com.lt.crs.constant;
/**
 * @author deepikareddy 
 * App Constants Class 
 * App specific constants goes here 
 */
public class Constants {
	
	final public static int admin=1;
	final public static int professor=2;
	final public static int student=3;
	final public static String status="Approved";
	
	public static String marks(int m) {
		if(m>=90) { return "A";			
		}else if(m>80 && m<90)
		{return "B";
		}else if(m>70 && m<=80)
		{return "C";}else return "D";		
	}
}
