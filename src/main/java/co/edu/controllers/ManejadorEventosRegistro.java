/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.controllers;

import co.edu.models.Usuarios;
import co.edu.models.usuarioDao;
import co.edu.views.Registrarse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author abayo
 */
public class ManejadorEventosRegistro implements ActionListener {
    
    private final Registrarse reg;

    public ManejadorEventosRegistro(Registrarse reg) {
        this.reg = reg;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(reg.getjButtonRegistrar())){
            String username = reg.getjTextFieldUsername().getText();
            String password = reg.getjTextFieldPassword().getText();
            String nombre = reg.getjTextFieldNombre().getText();
            String tipo = reg.getjTextFieldTipo().getText();
            usuarioDao user = new usuarioDao();
            Usuarios us = new Usuarios (user.consultaIdUser(),username,password,nombre,tipo);
            if(user.agregarCliente(us)!=0){
                JOptionPane.showMessageDialog(null,"Usuario registrando ");
            }else{
                JOptionPane.showMessageDialog(null,"no se registro el usuario ");
            } 
            
        }
        
    }
    
}
