/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaPrincipal;
import view.TelaTarefaDiaria;
import view.TelaTarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaPrincipal {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static TelaPrincipal telaPrincipal;

    Date hoje = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String data = formato.format(hoje);
    
    public ControllerTelaPrincipal() {
    }

    public ControllerTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }
    
    public static void abrirTelaTarefaDiaira(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTarefaDiaria().setVisible(true);
            }
        });
    }

    public static void abrirTelaTarefaSemanal() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTarefaSemanal().setVisible(true);
            }
        });
    }
    
    public static void atualizarConcluidaTarefa(TarefaDiaria tarefaAlterada, boolean estadoConcluida){
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        tarefaAlterada.setConcluida(estadoConcluida);
        
        try {
            dao.atualizar(tarefaAlterada);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    public void atualizarDataDeHoje(){
        telaPrincipal.atualizarDataDeHoje(data);
    }
    
    public void atualizarTarefasDoDia(){
        List<TarefaDiaria> tarefas = gerarTarefasDoDia();
        telaPrincipal.atualizarTarefasDoDia(tarefas);
    }
    
    public List<TarefaDiaria> gerarTarefasDoDia(){
        List<TarefaDiaria> tarefas = new ArrayList<>();
        TarefaDiariaDAO tarefaDiariaDAO = new TarefaDiariaDAO();
        
        try {
            tarefas = tarefaDiariaDAO.buscarPorData(data);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefas;
    }
}
