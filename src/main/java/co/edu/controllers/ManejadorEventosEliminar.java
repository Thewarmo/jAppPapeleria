/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;

import co.edu.models.productoDao;
import co.edu.views.ElmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author abayo
 */
public class ManejadorEventosEliminar implements ActionListener {
    
    private final ElmProducto eliminar;

    public ManejadorEventosEliminar(ElmProducto eliminar) {
        this.eliminar = eliminar;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(eliminar.getjButtonEliminar())){
            productoDao prod = new productoDao();
            
            if("".equals(eliminar.getjTextIdProd().getText())){
                JOptionPane.showMessageDialog(null, "no existe producto solicitado");
            }else{
                Integer id = Integer.parseInt(eliminar.getjTextIdProd().getText());
                if(prod.eliminarProducto(id)!=0){
                JOptionPane.showMessageDialog(null, "Se elimino el producto solicitado");
            }else{
                JOptionPane.showMessageDialog(null, "no existe producto solicitado");
            }
            }
            
            
        }
    }
    
    
}
