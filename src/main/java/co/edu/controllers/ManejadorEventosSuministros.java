/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;

import co.edu.models.Producto;
import co.edu.models.productoDao;
import co.edu.views.ElmProducto;
import co.edu.views.RegProducto;
import co.edu.views.Suministros;
import co.edu.views.modProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abayo
 */
public class ManejadorEventosSuministros implements ActionListener {

    private final Suministros suministros;
    
    

    public ManejadorEventosSuministros(Suministros suministros) {
        this.suministros = suministros;    
    }
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(suministros.getjButtonConsultar())){
            productoDao prod= new productoDao();
            ArrayList<Producto> produ = new ArrayList<>();
            produ.addAll(prod.consultar());
            String consultar = suministros.getjTextFieldConsultar().getText();
            if("".equals(suministros.getjTextFieldConsultar().getText())){ 
            DefaultTableModel modelo = new DefaultTableModel();
            String [] datos = new String[4];
            llenaModelo(modelo,1); 
                    for(Producto p : produ){
                        datos[0]= p.getPro_id().toString();
                        datos[1]= p.getPro_nombre();
                        datos[2]= p.getPro_precio().toString();
                        datos[3]= p.getPro_stock().toString();
                        modelo.addRow(datos);
                    }
                    suministros.getjTable1().setModel(modelo);
        }else if (Integer.parseInt(suministros.getjTextFieldConsultar().getText())!=0){
            if(prod.consultaxProdBU(Integer.parseInt(suministros.getjTextFieldConsultar().getText()))==1){
                for(Producto po:produ){
                if(Integer.parseInt(suministros.getjTextFieldConsultar().getText())==po.getPro_id()){
                  DefaultTableModel modelo = new DefaultTableModel();
                  String [] datos = new String[4];
                  llenaModelo(modelo,1); 
                  for(Producto pro: prod.consultaxProdU(Integer.parseInt(suministros.getjTextFieldConsultar().getText()))){
                      datos[0]= pro.getPro_id().toString();
                      datos[1]= pro.getPro_nombre();
                      datos[2]= pro.getPro_precio().toString();
                      datos[3]= pro.getPro_stock().toString();
                      modelo.addRow(datos);
                  }
                    suministros.getjTable1().setModel(modelo);
            }
        } 
        }else{
              JOptionPane.showMessageDialog(null,"no se encuentra el producto");
            }
             
}
            
        }else if (e.getSource().equals(suministros.getjButtonRegistrar())){
            RegProducto regproducto = new RegProducto();
            regproducto.setVisible(true);
        }else if(e.getSource().equals(suministros.getjButtonModificar())){
            modProducto modprod = new modProducto();
            modprod.setVisible(true);
        }else if(e.getSource().equals(suministros.getjButtonEliminar())){
            ElmProducto elmprod = new ElmProducto();
            elmprod.setVisible(true);
        }
    }
    
    
    
    
    public DefaultTableModel llenaModelo(DefaultTableModel modelo,int numero){
        switch (numero) {
            case 1:
                modelo.addColumn("pro_id");
                modelo.addColumn("pro_nombre");
                modelo.addColumn("pro_precio");
                modelo.addColumn("pro_stock");
                modelo.setRowCount(0);
                break;
         
            default:
                break;
        }
        return modelo;
    }
}
