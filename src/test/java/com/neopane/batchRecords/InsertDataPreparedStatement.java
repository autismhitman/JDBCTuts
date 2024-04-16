package com.neopane.batchRecords;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class InsertDataPreparedStatement {
	
	private static final String INSERT_MULTIPLE_USER_SQL = "insert into users(id,name,email, country, password) values (?,?,?,?,?);";
	
	
	public static void main(String[] args) {
		 
		InsertDataPreparedStatement insert = new InsertDataPreparedStatement();
		insert.insertRecord();
    }
	public static void insertRecord() {
		try(  
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
			     PreparedStatement pst = con.prepareStatement(INSERT_MULTIPLE_USER_SQL)  ;
			    		 
	        ) {
		          con.setAutoCommit(false);
		          
		           pst.setInt(1, 8);
		           pst.setString(2, "Tony");
		           pst.setString(3, "tony@gmail.com");
		           pst.setString(4, "US");
		           pst.setString(5, "aa123");
		           pst.executeUpdate();
		            pst.addBatch();
		          
		            pst.setInt(1, 9);
			           pst.setString(2, "Tom");
			           pst.setString(3, "tom@gmail.com");
			           pst.setString(4, "US");
			           pst.setString(5, "aa123");
			           pst.executeUpdate();
			            pst.addBatch();
			            
			            int[] createdCount=  pst.executeBatch();
					       System.out.println(Arrays.toString(createdCount));
					       con.commit();
				}
				
				catch(BatchUpdateException ex) {
					
					printSQLexception(ex);
				}catch(SQLException e) {
					
					printSQLexception(e);
				}
	}
	
	public static void printSQLexception(SQLException   ex) {
		
		for( Throwable  e :ex) {
			
			if(e instanceof SQLException  ) {
				
				e.printStackTrace(System.err);
				System.err.println( "SQL State " + ((SQLException )e ).getSQLState());
				System.err.println(((SQLException )e).getErrorCode());
				System.err.println(((SQLException )e).getMessage());
				Throwable t = ex.getCause();
				while (t!=null) {
					System.out.println("Cause" + t);
					t = t.getCause();
				}
			}
		}
	}

}
