/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcon;


import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import gettersetter.Registers;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author abhin
 */
public class dbOperations {
    public static  ResultSet login(String email1,String pass1){
        ResultSet rs = null ;
       try {
 Connection con =   dbConnection.getConnection();
           PreparedStatement ps = con.prepareStatement("select * from register where email = ? and password = ?");
            ps.setString(1, email1);
            ps.setString(2, pass1);
        rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
                    
        }  
       return  rs;
}
  public static   boolean register(Registers reg){
      boolean status = false;
      try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_software","root","root");
             Connection con = dbConnection.getConnection();
         
                     
            PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?)");
            
            ps.setString(1, reg.getName());
            ps.setString(2, reg.getEmail());
            ps.setString(3, reg.getPassword());
            ps.setString(4, reg.getGender());
            ps.setString(5, reg.getPhoneno());
            ps.setString(6, reg.getModule());
            int i = ps.executeUpdate();
            if(i>0){
                status =true;
       
            }
            else{
               status = false;
            }    
        } catch (Exception e) {
            System.out.println(e);
                    
        }
      return status;      
    }
  public static ResultSet showAllEmpsData(){
      ResultSet rs = null;
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps= con.prepareStatement("select * from register where module ='Employee'");
          
          rs = ps.executeQuery();
    
      } catch (Exception e) {
          System.out.println(e);
      }
      return rs;
  }
  public static ResultSet getSelectedEmployee(String email){
      ResultSet rs = null;
      try {
          Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement("select * from register where email=?");
         ps.setString(1, email);
         rs = ps.executeQuery();
      } catch (Exception e) {
          System.out.println(e);
      }
      return rs;
  }
  
  public static int updateEmpDetails(Registers reg){
      int i=0;
      try {
       Connection con = dbConnection.getConnection();
      PreparedStatement ps = con.prepareStatement("update register set name =?,password =?,gender=?,phone_no=? where email=?");
       ps.setString(1, reg.getName());
       ps.setString(2, reg.getPassword());
       ps.setString(3, reg.getGender());
       ps.setString(4, reg.getPhoneno());
       ps.setString(5, reg.getEmail());
     i = ps.executeUpdate();
      } catch (Exception e) {
          System.out.println(e);
      }
      System.out.println(i);
      return i;
      
      
  }
   public static int DeleteEmpDetails(String email){
      int i=0;
      try {
       Connection con = dbConnection.getConnection();
      PreparedStatement ps = con.prepareStatement("delete from register  where email=?");
    ps.setString(1, email);
   
     i = ps.executeUpdate();
      } catch (Exception e) {
          System.out.println(e);
      }
    
      return i;
     
  }
   public static boolean inserImageDetails(FileInputStream fis, String...str){
       boolean status = false;
       try {
           Connection con = dbConnection.getConnection();
           PreparedStatement ps= con.prepareStatement("insert into items values(?,?,?,?,?,?)");
          ps.setString(1, str[0]);
          ps.setString(2, str[1]);
          ps.setString(3, str[2]);
          ps.setString(4, str[3]);
          ps.setString(5, str[4]);
         ps.setBinaryStream(6, fis);
         int i =ps.executeUpdate();
         if(i>0){
             status = true;
             
         }
         else{
             status = false;
         }
       } catch (Exception e) {
       }
       return status;
   }
   public static ResultSet showAllItem(){
      ResultSet rs = null;
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps= con.prepareStatement("select * from items");
          
          rs = ps.executeQuery();
         
    
      } catch (Exception e) {
          System.out.println(e);
      }
      return rs;
  }
   public static ResultSet getSelectedItem(String item_id){
      ResultSet rs = null;
      try {
          Connection con = dbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement("select * from items where item_id=?");
         ps.setString(1, item_id);
         rs = ps.executeQuery();
      } catch (Exception e) {
          System.out.println(e);
      }
      return rs;
  }
   public static int updateItemDetails(String itemId,String itemName,String itemPrice, String itemDesc){
      int i=0;
      try {
       Connection con = dbConnection.getConnection();
      PreparedStatement ps = con.prepareStatement("update items set item_name =?,item_price =?,item_desc=? where item_id=?");
       ps.setString(1, itemName);
       ps.setString(2, itemPrice);
       ps.setString(3, itemDesc);
       ps.setString(4, itemId);
       
     i = ps.executeUpdate();
      } catch (Exception e) {
          System.out.println(e);
      }
      System.out.println(i);
      return i;
      
      
  }
      public static int DeleteItemDetails(String itemId){
      int i=0;
      try {
       Connection con = dbConnection.getConnection();
      PreparedStatement ps = con.prepareStatement("delete from items where item_id=?");
          ps.setString(1, itemId);
   
          i = ps.executeUpdate();
      } catch (Exception e) {
          System.out.println(e);
      }
          System.out.println(i);
      return i;
     
      }
      public static boolean checkOldPassword(String old_pas,String email){
          boolean status = false;
          try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from register where email = ? and password= ?");
            ps.setString(1, email);
            ps.setString(2, old_pas);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                status = true;
            }else{
                status = false;
            }
            
          } catch (Exception e) {
              e.printStackTrace();
          }
          return status;
      }
  public static boolean   updatePassword(String new_pas,String email){
        boolean status = false;
          try {
            Connection con = dbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update register set password = ? where email = ?");
            ps.setString(1, new_pas);
            ps.setString(2, email);
             int i = ps.executeUpdate();
            if(i>0){
               status = true;
            }else{
                status = false;
            }
            
          } catch (Exception e) {
              e.printStackTrace();
          }
          return status;
         }
  
  public static boolean updateMyProfile(String name,String gender,String phno , String email){
      boolean status = false;
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps = con.prepareStatement("update register set name=? , gender=?,phone_no=?  where email =?");
      ps.setString(1, name);
      ps.setString(2, gender);
      ps.setString(3, phno);
      ps.setString(4, email);
      int i =ps.executeUpdate();
      if(i>0){
         status = true; 
      }
      
      
      } catch (Exception e) {
          System.out.println(e);
      }
      return status;
  }
  public static boolean insertCustomerDetails(String...str){
      boolean status=false; 
    String name1 =str[0];
    String email1 =str[1];
    String pass1 =str[2];
    String gender1 =str[3];
    String phno1 =str[4];
    String module1 =str[5]; 
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps = con.prepareStatement("insert into register values (?,?,?,?,?,?)");
          ps.setString(1, name1);
          ps.setString(2, email1);
          ps.setString(3, pass1);
          ps.setString(4,  gender1);
          ps.setString(5, phno1);
          ps.setString(6, module1);
          int i = ps.executeUpdate();
          if(i>0){
              status = true;
          }else{
              status= false;
          }
      } catch (Exception e) {
          System.out.println(e);
      }
      return status;
  }
  public static ResultSet  checkCustomerExists(String phno1){
      
      ResultSet rs = null;
      try {
         
          Connection con = dbConnection.getConnection();
          
           PreparedStatement ps = con.prepareStatement("select * from register where phone_no=?");
          ps.setString(1, phno1);
          rs= ps.executeQuery();
      } catch (Exception e) {
          System.out.println(e);
      }
      
  
  return rs;
  }
  public static boolean customerBillingDetails(String customer_phno,String items,String date1,String time1,String emp_emailid){
    boolean status=false;
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps = con.prepareStatement("insert into shopping  values(?,?,?,?,?)");
          ps.setString(1, customer_phno);
          ps.setString(2, items);
          ps.setString(3, date1);
          ps.setString(4, time1);
          ps.setString(5, emp_emailid);
          
          int i = ps.executeUpdate();
          if(i>0){
            status = true;  
          }else{
              status = false;
          }
      
      } catch (Exception e) {
          System.out.println(e);
      }
      return status;
      
      
      
      
      
  }
   public static ResultSet getcustomerBillingDetails(String customer_phno){
 ResultSet rs=null;
      try {
          Connection con = dbConnection.getConnection();
          PreparedStatement ps = con.prepareStatement("select * from shopping  where customer_phno =?");
          ps.setString(1, customer_phno);
      
          
         rs =ps.executeQuery();
     
      } catch (Exception e) {
          System.out.println(e);
      }
      return rs;
   
  }
  
}
