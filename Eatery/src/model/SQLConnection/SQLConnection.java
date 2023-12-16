/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.SQLConnection;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author BARATHI
 */
public class SQLConnection {
    Connection con=null;
    public static Connection dbconnector()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ktkir\\Eatery.db");
            return con;
            
        }
        catch(Exception e)
                {
                    return null;
                }
        
    }
    
}
