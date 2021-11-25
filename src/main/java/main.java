
import co.edu.models.Usuarios;
import co.edu.models.productoDao;
import co.edu.models.usuarioDao;
import co.edu.services.Conexion;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abayo
 */
public class main {
    public static void main(String[] args) {
        /*Conexion con = new Conexion();
        con.conectar();*/
        
        /*usuarioDao user = new usuarioDao();
        System.out.println(user.consultaIdUser());*/
        /*ArrayList<Usuarios> usuario = new ArrayList<>();
        usuario.addAll(user.consultar());
        for(Usuarios usu: usuario){
            System.out.println(usu.getNombre());
        }*/
        
        //System.out.println(user.consultaxClienteBU(1));
        
        /*for(Usuarios usu: user.consultar()){
            System.out.println(usu.getNombre());
        }*/
        
        
        productoDao prod = new productoDao();
        System.out.println(prod.consultaIdProducto());
        
        
        
    }  
}
