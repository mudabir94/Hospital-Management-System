/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Mudabir Ahmad
 */
public class mysqlconnection {
    public Connection con=null;
    public static Connection ConnectDb(){
      try{
           
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rmc", "root","");
         System.out.println("Connection establised");
          return con;
        }
    catch(Exception ex)
    {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        return null;
    }
}
}
