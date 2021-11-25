/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;

import co.edu.models.Producto;
import co.edu.models.productoDao;
import co.edu.views.RegProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author abayo
 */
public class ManejadorEventosRegProducto  implements ActionListener{
    
    private final RegProducto regproducto;

    public ManejadorEventosRegProducto(RegProducto regproducto) {
        this.regproducto = regproducto;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(regproducto.getjButtonRegProducto())){
            productoDao prod = new productoDao();
            String nombre = regproducto.getjTextNombre().getText();
            Integer precio = Integer.parseInt(regproducto.getjTextPrecio().getText());
            Integer stock = Integer.parseInt(regproducto.getjTextStock().getText());
            Producto producto = new Producto(prod.consultaIdProducto(), nombre, precio, stock);
            if(prod.agregarProducto(producto)!=0){
                JOptionPane.showMessageDialog(null, "se agrego el producto al inventario");
            }else{
                JOptionPane.showMessageDialog(null, "producto no registrado");
            }
        }else if(e.getSource().equals(regproducto.getjButtonLimpiar())){
            regproducto.getjTextNombre().setText("");
            regproducto.getjTextPrecio().setText("");
            regproducto.getjTextStock().setText("");
        }
    }
    
}
