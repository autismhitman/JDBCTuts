package com.neopane.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectClass {

	public static void main(String[] args)   {

       try( Connection con = DriverManager.getConnection(
    		   "jdbc:mysql://localhost:3306/jdbc_base?useSSL=false", "root", "password");
    		   
    	Statement statement=	con.createStatement()   ;
    	ResultSet resultset=  statement.executeQuery("select * from users");

          ) 
    		
       
       {
    	   
    	   while(resultset.next()) {
    		   
    		   int id= resultset.getInt("id");
    		   String name= resultset.getString("name");
    		   String email= resultset.getString("email");
    		   String country= resultset.getString("country");
    		   String password= resultset.getString("password");
    		   
    		   System.out.println( id +"-" +name+"-"+ email+"-"+country+"-"+password);
    	   }
       }
    	   catch (SQLException e) {
    		   
    		 e.printStackTrace()  ;
    	   
       }

	}

}
