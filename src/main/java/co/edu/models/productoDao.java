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


/**
 *
 * @author abayo
 */
public class productoDao {
    private Connection conexion = null;
    co.edu.services.Conexion conectar = new co.edu.services.Conexion();
    private ResultSet rs = null;
    ArrayList<Producto> listadoProductos = new ArrayList();
    
    
    public ArrayList<Producto> consultar(){
        //conectar();
        conexion = conectar.conectar();
        try {
            Statement consulta = conexion.createStatement();
            String sql = "SELECT * FROM producto";
            rs = consulta.executeQuery(sql);
            while(rs.next()==true){    
                Producto producto = new Producto(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                listadoProductos.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return listadoProductos;
    }
    
    public ArrayList<Producto> consultaxProdU(Integer prodID){
        conexion = conectar.conectar();
        ArrayList<Producto> listaUnica = new ArrayList<>();
        try {
            PreparedStatement consultaxProduct =null;
            String sql = "SELECT * FROM producto where idproducto = ?";
            consultaxProduct = conexion.prepareStatement(sql);
            consultaxProduct.setInt(1, prodID);
            rs = consultaxProduct.executeQuery();
            while(rs.next()==true){    
                Producto producto = new Producto(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                listaUnica.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return listaUnica;
    }
    
    public int consultaxProdBU(Integer prodID){
        conexion = conectar.conectar();
        ArrayList<Producto> listaUnica = new ArrayList<>();
        int resultado = 0;
        try {
            PreparedStatement consultaxProduct =null;
            String sql = "SELECT * FROM producto where idproducto = ?";
            consultaxProduct = conexion.prepareStatement(sql);
            consultaxProduct.setInt(1, prodID);
            rs = consultaxProduct.executeQuery();
            while(rs.next()==true){    
                Producto producto = new Producto(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                listaUnica.add(producto);
                resultado = 1;
            }
        } catch (SQLException ex) {
            System.out.println("Ah ocurrido un Error : "+ex.getErrorCode()+" Mensaje "+ex.getMessage());
        }
        return resultado;
    }
    
    public int actualizarProducto(Producto pro){
        conexion = conectar.conectar();
        PreparedStatement update = null;
        String sql = "UPDATE producto SET nombre_producto= ? , precio= ? , stock = ? where idproducto = ?";
        int resultado = 0;
        try {
            update = conexion.prepareStatement(sql);
            update.setString(1, pro.getPro_nombre());
            update.setInt(2, pro.getPro_precio());
            update.setInt(3, pro.getPro_stock());
            update.setInt(4, pro.getPro_id());
            resultado = update.executeUpdate();
            if(resultado != 0){
            System.out.println("se actualizo el registro : "+pro.getPro_id());
            }else{
                System.out.println("No se ha Actualizado el registro");
            }
        } catch (SQLException e) {
            System.out.println("ah ocurrido un error "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }
    
    public int agregarProducto(Producto pro){
        conexion = conectar.conectar();
        PreparedStatement insert = null;
        String sql = "INSERT INTO producto VALUES (?, ?, ?, ?)";
        int resultado = 0;
        try {
            insert = conexion.prepareStatement(sql);
            insert.setInt(1, pro.getPro_id());
            insert.setString(2, pro.getPro_nombre());
            insert.setInt(3, pro.getPro_precio());
            insert.setInt(4, pro.getPro_stock());
            resultado = insert.executeUpdate();
            if(resultado != 0){
            System.out.println("Se ha insertado el registro "+pro.getPro_id());
            }else{
                System.out.println("No se ha Insertado el registro");
            }
        } catch (SQLException e) {
            System.out.println("Ah ocurrido un Error : "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }
    
    
    public int eliminarProducto(Integer idProducto){
        conexion = conectar.conectar();
        PreparedStatement insert = null;
        String sql = "DELETE FROM producto WHERE idproducto = ?";
        int resultado = 0;
        try {
            insert = conexion.prepareStatement(sql);
            insert.setInt(1, idProducto);
            resultado = insert.executeUpdate();
            if(resultado != 0){
                System.out.println("Se ha eliminado el registro "+idProducto);
            }else{
                System.out.println("No se ha eliminado el registro");
            }
            
        } catch (SQLException e) {
            System.out.println("Ah ocurrido un Error : "+e.getErrorCode()+" Mensaje "+e.getMessage());
        }
        return resultado;
    }
    
    public Integer consultaIdProducto(){
        conexion = conectar.conectar();
        Integer id = 0;
        try {
            Statement consulta = conexion.createStatement();
            String sql = "SELECT max(idproducto) FROM producto";
            
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
