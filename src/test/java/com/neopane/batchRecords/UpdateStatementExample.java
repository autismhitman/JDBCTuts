package com.neopane.batchRecords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class UpdateStatementExample {
    
	//public static final String UPDATE_USER = "update users set name=\"Ram\" where id= 2";
	
	public static void main(String[] args) {
		 
       try(
    		   Connection con  =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false","root","password");
    		                         
    		  Statement st = con.createStatement();
          ){
    	   
    	   con.setAutoCommit(false);
    	   st.addBatch("update users set name=\"TonyRam\" where id= 8;");
    	   st.addBatch("update users set name=\"TomRam\" where id= 9;");
    	   
    	   int[] updateCounts= st.executeBatch();
    	   System.out.println(Arrays.toString(updateCounts));
    	   con.commit();
       }
       catch(SQLException e) {
    	   
    	   printException(e);
       }
    		   
	}

	private static void printException(SQLException ex) {
		 
	   for( Throwable e : ex)	{
		   
		   if(e instanceof SQLException ) {
			   
			   e.printStackTrace(System.err);
			   System.err.println(((SQLException)e).getSQLState());
			   System.err.println(((SQLException)e).getMessage());
			   System.err.println(((SQLException)e).getErrorCode());
			   
			   Throwable t = ex.getCause() ;
			   while(t!=null) {
				   t.getCause();
			   }
		   }
	   }
		 
		
	}

}
