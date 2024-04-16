package com.neopane.batchRecords;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class InsertDataDemo {
	
	
	
	public static void main(String[] args) {
		 
		InsertDataDemo insert = new InsertDataDemo();
		insert.insertRecord();
    }
	public static void insertRecord() {
		try(  
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
			     Statement pst = con.createStatement() ;
			    		 
	        ) {
		         	con.setAutoCommit(false);
			        pst.addBatch("insert into users values (5,'Pramod','pramod@gmail.com','India', 'aa123');");
			        pst.addBatch("insert into users values (6,'Pramod6','pramod6@gmail.com','India', 'aa123');");
			        pst.addBatch("insert into users values (7,'Pramod7','pramod7@gmail.com','India', 'aa123');");
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
