/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import co.edu.services.Conexion;

/**
 *
 * @author abayo
 */
public class usuarioDao {
    
    private Connection conexion = null;
    private ResultSet rs = null;
    Conexion conectar = new Conexion();
    ArrayList<Usuarios> listadoUsuarios = new ArrayList();
    
    public ArrayList<Usuarios> consultar(){
        conexion = conectar.conectar();
        try {
            Statement consulta = conexion.createStatement();
            String sql = "SELECT * FROM usuarios";
            rs = consulta.executeQuery(sql);
            while(rs.next()==true){    
                Usuarios usuario = new Usuarios(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                listadoUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return listadoUsuarios;
    }
    
    public ArrayList<Usuarios> consultaxClienteU(String user){
        conexion = conectar.conectar();
        ArrayList<Usuarios> listaUnica = new ArrayList<>();
        try {
            PreparedStatement consultaxCliente =null;
            String sql = "SELECT * FROM usuarios where username = ?";
            consultaxCliente = conexion.prepareStatement(sql);
            consultaxCliente.setString(1, user);
            rs = consultaxCliente.executeQuery();
            while(rs.next()==true){    
                Usuarios usuario = new Usuarios(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                listaUnica.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return listaUnica;
    }
    
    public int consultaxClienteBU(String user){
        conexion = conectar.conectar();
        ArrayList<Usuarios> listaUnica = new ArrayList<>();
        int valor = 0;
        try {
            PreparedStatement consultaxCliente =null;
            String sql = "SELECT * FROM usuarios where username = ?";
            consultaxCliente = conexion.prepareStatement(sql);
            consultaxCliente.setString(1, user);
            rs = consultaxCliente.executeQuery();
            while(rs.next()==true){    
                Usuarios usuario = new Usuarios(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                listaUnica.add(usuario);
                valor=1;
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return valor;
    }
    
    public int actualizarCliente(Usuarios usu){
        conexion = conectar.conectar();
        PreparedStatement update = null;
        String sql = "UPDATE usuarios SET username= ? , password= ? , nombre = ?, tipo = ? where idusuarios = ?";
        int resultado = 0;
        try {
            update = conexion.prepareStatement(sql);
            update.setString(1, usu.getUsername());
            update.setString(2, usu.getPassword());
            update.setString(3, usu.getNombre());
            update.setString(4, usu.getTipo());
            update.setInt(5, usu.getIdUsuario());
            resultado = update.executeUpdate();
            if(resultado != 0){
            System.out.println("se actualizo el registro : "+usu.getIdUsuario());
            }else{
                System.out.println("No se ha Actualizado el registro");
            }
        } catch (SQLException e) {
            System.out.println("ah ocurrido un error "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }
    
    public int agregarCliente(Usuarios usu){
        conexion = conectar.conectar();
        PreparedStatement insert = null;
        String sql = "INSERT INTO usuarios VALUES (?, ?, ?, ?, ?)";
        int resultado = 0;
        try {
            insert = conexion.prepareStatement(sql);
            insert.setInt(1, usu.getIdUsuario());
            insert.setString(2, usu.getUsername());
            insert.setString(3, usu.getPassword());
            insert.setString(4, usu.getNombre());
            insert.setString(5, usu.getTipo());
            resultado = insert.executeUpdate();
            if(resultado != 0){
            System.out.println("Se ha insertado el registro "+usu.getIdUsuario());
            }else{
                System.out.println("No se ha Insertado el registro");
            }
        } catch (SQLException e) {
            System.out.println("Ah ocurrido un Error : "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }
    
    
    public int eliminarCliente(Integer idUsuario){
        conexion = conectar.conectar();
        PreparedStatement insert = null;
        String sql = "DELETE FROM usuarios WHERE idusuarios = ?";
        int resultado = 0;
        try {
            insert = conexion.prepareStatement(sql);
            insert.setInt(1, idUsuario);
            resultado = insert.executeUpdate();
            if(resultado != 0){
                System.out.println("Se ha eliminado el registro "+idUsuario);
            }else{
                System.out.println("No se ha eliminado el registro");
            }
            
        } catch (SQLException e) {
            System.out.println("Ah ocurrido un Error : "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }

    public Integer consultaIdUser(){
        conexion = conectar.conectar();
        Integer id = 0;
        try {
            Statement consulta = conexion.createStatement();
            String sql = "SELECT max(idusuarios) FROM usuarios";
            
            rs = consulta.executeQuery(sql);
            while(rs.next()==true){    
                id = rs.getInt(1);
                id += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return id;
    
    } 
}
