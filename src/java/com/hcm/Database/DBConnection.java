/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 *
 * @author Prasenjit
 */
public class DBConnection {
    
    public static Connection getConnectionDS(String datasource) throws SQLException,
                NamingException {
            Connection con = null;
            DataSource data = null;
            Context initialContext = new InitialContext();
            data = (DataSource) initialContext.lookup(datasource);
            if (data != null) {
                con = data.getConnection();
            } else {
                System.out.println("Failed to Find JDBC DataSource.");
            }
            return con;
    }
            
            
public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
    Connection con = null;
    try { 
    Class.forName("oracle.jdbc.driver.OracleDriver"); 
    con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.169:1521/XEPDB1", "tatweerprod", "tatweer123");
//          con = DriverManager.getConnection("jdbc:oracle:thin:@130.61.216.253:1521/almprod1.almisksubnet2.almiskvcn.oraclevcn.com", "xxpl", "");      
    } catch (SQLException ex) {
        ex.printStackTrace();
    } 
    return con;
}            
            
}
