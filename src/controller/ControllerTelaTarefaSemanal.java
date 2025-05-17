/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SemanaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Semana;
import view.TelaPrincipal;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaTarefaSemanal {
    
    SemanaDAO semanaDao = new SemanaDAO();
    
    public List<Semana> listarSemanas(){
        
        List<Semana> semanas = new ArrayList<>();
        
        try {
            semanas = semanaDao.listarTodos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return semanas;
    }

    public void abrirTelaPrincipal() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
}
