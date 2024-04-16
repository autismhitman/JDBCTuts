package com.neopane.DynamicInsertions;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertDataPreparedStatement {
	
	
	private static final String INSERT_MULTIPLE_USER_SQL = "insert into users(id,name,email, country, password) values (?,?,?,?,?);";
	public static List<User> user=null;
	
	public static void main(String[] args) {
		
		 user= new ArrayList<>();
        user.add(new User(10,"Ghulam","g@email.com","India","123"));
		user.add(new User(11,"Maachis","ma@email.com","India","123"));
		user.add(new User(12,"URI","uri@email.com","India","123"));
		 
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
		          
		         for( User u: user ) {
		        	 
		        	  pst.setInt(1, u.getId());
		        	  pst.setString(2,u.getName());
		        	  pst.setString(3,u.getEmail());
		        	  pst.setString(4,u.getCountry());
		        	  pst.setString(5,u.getPassword());
		        	  pst.addBatch();
		         }
		              
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
