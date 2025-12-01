/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcon;


import java.sql.* ;
import java.sql.DriverManager;

/**
 *
 * @author abhin
 */
public class dbConnection {
 static Connection con;
static public  Connection  getConnection(){
  try {
       Class.forName("com.mysql.cj.jdbc.Driver");
   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_software","root","root");
         
  } catch (Exception e) {
            System.out.println(e);
        }
       
        return con;
    
} 
   
}
