package com.neopane.preparedStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDataDemo {
	
	private static final String SELECT_USERS = "Select * from users where id=?;";
	
	public static void main(String[] args) {
		 
		SelectDataDemo select = new SelectDataDemo();
		select.selectRecord();
    }
	public static void selectRecord() {
		try(  
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
			     PreparedStatement pst = con.prepareStatement(SELECT_USERS)
	        ) {
				
			           pst.setInt(1, 5);
			          ResultSet rs= pst.executeQuery();
			          
			          while  (rs.next()) {
			        	  int id = rs.getInt("id");
			        	  String name = rs.getString("name");
			        	  String email = rs.getString("email");
			        	  String country = rs.getString("country");
			        	  String password = rs.getString("password");
			        	  System.out.println(id+"-"+name+"-"+email+"-"+country+"-"+password);
			          }
					 
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
