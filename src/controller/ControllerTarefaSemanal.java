/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SemanaDAO;
import dao.TarefaSemanalDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Semana;
import model.TarefaSemanal;
import view.TelaAdicionarTarefaSemanal;
import view.TelaPrincipal;
import view.TelaTarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class ControllerTarefaSemanal {
    
    private int idSemana;
    private SemanaDAO semanaDao = new SemanaDAO();
    private TarefaSemanalDAO tarefaSemanalDao = new TarefaSemanalDAO();
    private ControllerAdicionarTarefaSemanal controller = new ControllerAdicionarTarefaSemanal();
    
    public static void exibirTela(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTarefaSemanal().setVisible(true);
            }
        });
    }
    
    public List<TarefaSemanal> listarTarefasSemanais(int idSemana){
        this.idSemana = idSemana;
        List<TarefaSemanal> tarefasSemanais = new ArrayList<>();
        try {
            tarefasSemanais = tarefaSemanalDao.buscarPorIdSemana(idSemana);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tarefasSemanais;
    }
    
    public List<Semana> listarSemanas(){
        List<Semana> semanas = new ArrayList<>();
        
        try {
            semanas = semanaDao.listarTodos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return semanas;
    }

    public void abrirTelaPrincipal() {
        ControllerPrincipal.exibirTela();
    }

    public void adicionarSemana() {
        semanaDao.adicionarSemana();
    }

    public void abrirTelaAdicionar() {
        controller.receberIdSemana(idSemana);
        ControllerAdicionarTarefaSemanal.exibirTela();
    }

    public void setIdSemana(int idSemana) {
        this.idSemana = idSemana;
    }
    
    
}
