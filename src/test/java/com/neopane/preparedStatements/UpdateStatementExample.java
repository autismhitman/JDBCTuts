package com.neopane.preparedStatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementExample {
    
	public static final String UPDATE_USER = "update users set name= ?  where id= ?;" ;
	
	public static void main(String[] args) {
		 
       try(
    		   Connection con  =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false","root","password");
    		                         
    		  PreparedStatement pst = con.prepareStatement(UPDATE_USER);
          ){
    	   
    	   pst.setString(1, "Ramesh");
    	   pst.setInt(2,5);
    	   pst.executeUpdate();
    	   
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
