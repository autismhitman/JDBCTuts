package com.neopane.batchRecords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class UpdateStatementPreparedStatement {
    
	public static final String UPDATE_USER = "update users set name=? where id=? ;";
	
	public static void main(String[] args) {
		 
       try(
    		   Connection con  =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false","root","password");
    		                         
    		  PreparedStatement st = con.prepareStatement(UPDATE_USER);
          ){
    	   
    	   con.setAutoCommit(false);
    	   
    	   st.setString(1, "TonyJha");
    	   st.setInt(2,8);
    	   st.addBatch();
    	   
    	   st.setString(1, "TomJHa");
    	   st.setInt(2,9);
    	   st.addBatch();
     
    	   
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
