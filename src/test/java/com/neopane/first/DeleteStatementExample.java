package com.neopane.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementExample {
	
	public static final String DELETE_FROM_USER= "delete from users where id=2";

	public static void main(String[] args) {
		DeleteStatementExample  dse = new DeleteStatementExample ();
		dse.deleteRecord();

	}
	
	 

	public void deleteRecord() {
		try(
				
			Connection con = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_base?useSSL=false","root","password");

            Statement st = con.createStatement(); 
				
		 )
		{
			int rs = st.executeUpdate(DELETE_FROM_USER);
			
		}
		catch(SQLException e) {
			
			printException(e);
		}
	}

	private void printException(SQLException ex) {

          for( Throwable e: ex) {
        	  
        	  if( e instanceof SQLException){
        		  
        		   e.printStackTrace(System.err);
        		   System.out.println(((SQLException)e).getSQLState());
        		   System.out.println(((SQLException)e).getMessage());
        		   System.out.println(((SQLException)e).getErrorCode());
        		   Throwable t = ex.getCause();
        		   while( t!=null) {
        			   t=t.getCause();
        		   }
        	  }
          }
		
	}

}
