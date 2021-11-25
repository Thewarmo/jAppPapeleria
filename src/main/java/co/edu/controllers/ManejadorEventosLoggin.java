/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;


import co.edu.models.Usuarios;
import co.edu.models.usuarioDao;
import co.edu.views.loggin;
import co.edu.views.Registrarse;
import co.edu.views.Suministros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author abayo
 */
public class ManejadorEventosLoggin implements ActionListener {
    
    private final loggin loggin;

    public ManejadorEventosLoggin(loggin loggin) {
        this.loggin = loggin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loggin.getjButtonIngresar()){
            usuarioDao us = new usuarioDao();
            String user = loggin.getjTextUsuario().getText();
            if(us.consultaxClienteBU(user)==1){
                String password = loggin.getjTextPassword().getText();
                ArrayList<Usuarios> usu = new ArrayList<>(); 
                usu.addAll(us.consultaxClienteU(user));
                for(Usuarios u : usu){
                    if(u.getPassword().compareTo(password)==0){
                        JOptionPane.showMessageDialog(null,"Autenticado "+u.getTipo());
                        Suministros sum = new Suministros();
                        sum.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "password erroneo");
                        JOptionPane.showMessageDialog(null, "Por favor registrarse");
                        
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no existe");
            }
            
        }else if(e.getSource()==loggin.getjButtonRegistar()){
            System.out.println("pulsado");
            Registrarse reg = new Registrarse();
            reg.setVisible(true);
        }
        
    }
    
    
    
    
}
