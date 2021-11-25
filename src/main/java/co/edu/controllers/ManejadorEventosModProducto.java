/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;

import co.edu.models.productoDao;
import co.edu.models.Producto;
import co.edu.views.modProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author abayo
 */
public class ManejadorEventosModProducto implements ActionListener {
    
    private final modProducto modprod;

    public ManejadorEventosModProducto(modProducto modprod) {
        this.modprod = modprod;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(modprod.getjButtonModificar())){
            productoDao prod = new productoDao();
            String nombre = modprod.getjTextNombre().getText();
            Integer precio = Integer.parseInt(modprod.getjTextPrecio().getText());
            Integer stock = Integer.parseInt(modprod.getjTextStock().getText());
            Integer id = Integer.parseInt(modprod.getjTextId().getText());
            Producto pro = new Producto(id,nombre,precio,stock);
            if(prod.actualizarProducto(pro)!=0){
                JOptionPane.showMessageDialog(null , "se actualizo el producto solicitado");
            }else{
                JOptionPane.showMessageDialog(null , "el producto no fue actualizado");
            }
            
        }else if(e.getSource().equals(modprod.getjButtonConsultar())){
            productoDao prod = new productoDao();
            if("".equals(modprod.getjTextId().getText())){
                JOptionPane.showMessageDialog(null, "Escriba un id valido");
            }else{
                 Integer id = Integer.parseInt(modprod.getjTextId().getText());
            if(id>=0){
                ArrayList<Producto> listaprod = new ArrayList<>();
                listaprod.addAll(prod.consultaxProdU(id));
                    for(Producto p : listaprod){
                        modprod.getjTextNombre().setText(p.getPro_nombre());
                        modprod.getjTextPrecio().setText(p.getPro_precio().toString());
                        modprod.getjTextStock().setText(p.getPro_stock().toString());
            }
          }else{
                JOptionPane.showMessageDialog(null, "id invalido");
            }
            }
           
            
    }
    }
    
}
