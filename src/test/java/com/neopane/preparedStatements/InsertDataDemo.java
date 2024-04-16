package com.neopane.preparedStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataDemo {
	
	private static final String INSERT_MULTIPLE_USER_SQL = "insert into users(id,name,email, country, password) values (?,?,?,?,?);";
	
	public static void main(String[] args) {
		 
		InsertDataDemo insert = new InsertDataDemo();
		insert.insertRecord();
    }
	public static void insertRecord() {
		try(  
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
			     PreparedStatement pst = con.prepareStatement(INSERT_MULTIPLE_USER_SQL)
	        ) {
				
			           pst.setInt(1, 5);
			           pst.setString(2, "Tony");
			           pst.setString(3, "tony@gmail.com");
			           pst.setString(4, "US");
			           pst.setString(5, "aa123");
			           pst.executeUpdate();
					 
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
