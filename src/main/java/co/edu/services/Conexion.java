/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.services;

//import co.edu.models.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abayo
 */
public class Conexion {
    private Connection conexion = null;
    private Properties propiedades;
    private ResultSet rs;

   
    public Connection conectar(){
        datos();
        if(conexion==null){
        try {
            conexion = DriverManager.getConnection(propiedades.getProperty("bd")
            ,propiedades.getProperty("uss")
            ,propiedades.getProperty("pass"));
            System.out.println("conectado");
            
        } catch (SQLException ex) {
            System.out.println("Error : "+ex);
        }
        }
        else{
            return conexion;
        }
        return conexion;
    }
    
    public void datos(){
        propiedades = new Properties();
            try {
            InputStream entrada = new FileInputStream("jdbc.properties");
            propiedades.load(entrada);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }           
    }
    
    public int logID(){
        conexion = conectar();
        int logId = 0;
        try {
            Statement consulta = conexion.createStatement();
            String sql = "select max(idlog) from log";
            
            rs = consulta.executeQuery(sql);
            while(rs.next()==true){
               logId = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return logId;
    }
    
    
    public void log(String mensaje,String estado){
        conectar();
        if(conexion!=null){
        PreparedStatement insert = null;
        String sql = "INSERT INTO log VALUES (?, ?, ?, ?)";
        int resultado = 0;
        int id = logID()+1;
        Date date = new Date();
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            insert = conexion.prepareStatement(sql);
            insert.setInt(1, id);
            insert.setString(2, formateadorFecha.format(date));
            insert.setString(3, mensaje);
            insert.setString(4, estado);
            resultado = insert.executeUpdate();
            System.out.println("Se ha guardado el log "+id);
        } catch (SQLException e) {
            System.out.println("Ah ocurrido un Error : "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        }
    }
    
}
