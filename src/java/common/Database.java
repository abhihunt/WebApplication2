/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhishek
 */
public class Database {
   
   
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparestmt = null;
    String Contextpath = null;
    
    java.util.Date date= new java.util.Date();
    
    public Database(String contextpath) {
        Contextpath = contextpath;
    }
    
    public Connection getConnection(String contextpath){
        
        try{
            ReadXMLFile readXML = new ReadXMLFile();
            
            ArrayList dbCredential = readXML.readXMLFile(Contextpath);
            
            System.out.print("<---------------- dbCredential ----------------->");
            System.out.println("DRIVER :"+(String)dbCredential.get(1));
            System.out.println("Connection String :"+(String)dbCredential.get(2));
            System.out.println("USER NAME :"+(String)dbCredential.get(3));
            System.out.println("PWD :"+(String)dbCredential.get(4));
            
            Class.forName((String)dbCredential.get(1));
           
            connection = DriverManager.getConnection((String)dbCredential.get(2)+"?" +
                                   "user="+(String)dbCredential.get(3)+"&password="+(String)dbCredential.get(4));
            
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
       return connection;
    }
    
    
    
//    public int db_insert_large(ArrayList data){
//            int uid = 0 ;
//            try{
//            connection = getConnection(realPath);
//            preparestmt = connection.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
//            
//                preparestmt.setInt(1,25);
//                preparestmt.setString(2, firstname+""+lastname);
//                preparestmt.setString(3, email);
//                preparestmt.setString(4, username);
//                preparestmt.setString(5, password);
//                preparestmt.setString(6, "0");     
//                preparestmt.setString(7, String.valueOf(new Timestamp(date.getTime())));
//                
//            uid = preparestmt.executeUpdate();
//            System.out.print(" USER ID : =============> "+ uid);
//        }catch(Exception e){
//            e.printStackTrace();
//        
//        }
//        return uid; 
//    }
    
    
    public int db_insert(String qry){
            int uid = 0 ;
            try{
            connection = getConnection(Contextpath);
            statement = connection.createStatement();
            uid = statement.executeUpdate(qry);
            System.out.print(" USER ID : =============> "+ uid);
        }catch(SQLException e){
            e.printStackTrace();
        
        }
        return uid; 
    }
    
    
    public int db_update(String qry){
        
        int result = 0;
         try{
            
            connection = getConnection(Contextpath);
            statement = connection.createStatement();
            result = statement.executeUpdate(qry);            
            
        }catch(SQLException e){
            e.printStackTrace();
        
        }         
        return result;
    }
     
    public ArrayList db_setect(String qry){
        ResultSet resultSet = null;
         try{
            connection = getConnection(Contextpath);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);            
            
        }catch(SQLException e){
            e.printStackTrace();
        
        }         
        return convertResultsetToArraylist(resultSet);
    }
      
    
    public ArrayList convertResultsetToArraylist(ResultSet resultSet){
    
        ArrayList<Object> arrayList = new ArrayList<>(); 
        try {
            int numberOfColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {    
                ArrayList<String> columnList = new ArrayList<>(); 
                int i = 1;
                while(i <= numberOfColumns) {
                    columnList.add(resultSet.getString(i++));
                }  
                arrayList.add(columnList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayList;
    }
}
