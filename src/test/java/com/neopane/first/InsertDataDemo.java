package com.neopane.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataDemo {
	
	private static final String INSERT_MULTIPLE_USER_SQL =" insert into users values\r\n"
			+ "	 (2,'Pramod','pramod@gmail.com','India', 'aa123'),\r\n"
			+ "	 (3,'Rajesh','rajesh@gmail.com','India', 'aa123'),\r\n"
			+ "	 (4,'Deepa','deepa@gmail.com','India', 'aa123') ";
	
	
	public static void main(String[] args) {
		 
		InsertDataDemo insert = new InsertDataDemo();
		insert.insertRecord();
    }
	public static void insertRecord() {
		try(  
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
			     Statement st = con.createStatement();
	        ) 
				
				
				{	int rs = st.executeUpdate(INSERT_MULTIPLE_USER_SQL);
				}
				
				catch(SQLException e) {
					
					printSQLexception(e);
				}
	}
	
	public static void printSQLexception(SQLException ex) {
		
		for( Throwable  e :ex) {
			
			if(e instanceof SQLException) {
				
				e.printStackTrace(System.err);
				System.err.println( "SQL State " + ((SQLException)e ).getSQLState());
				System.err.println(((SQLException)e).getErrorCode());
				System.err.println(((SQLException)e).getMessage());
				Throwable t = ex.getCause();
				while (t!=null) {
					System.out.println("Cause" + t);
					t = t.getCause();
				}
			}
		}
	}

}
