/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class koneksi_database {
    private Connection koneksi;
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        } catch (ClassNotFoundException e){
            System.out.println("Gagal Koneksi"+e);
        }
        String url = "jdbc:mysql://localhost/fcopy";
        try{
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil koneksi database");
        } catch (SQLException e){
            System.out.println("Gagal koneksi database"+e);
        }
        return koneksi;
    }        
}
    
    
        
    