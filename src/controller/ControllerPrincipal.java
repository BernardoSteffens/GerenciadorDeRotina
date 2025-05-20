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
public class ControllerPrincipal {

    private Date hoje = new Date();
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private String data = formato.format(hoje);
    
    public static void exibirTela(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    
    public void abrirTelaTarefaDiaira(){
        ControllerTarefaDiaria.exibirTela();
    }

    public void abrirTelaTarefaSemanal() {
        ControllerTarefaSemanal.exibirTela();
    }
    
    public void atualizarConcluidaTarefa(TarefaDiaria tarefaAlterada, boolean estadoConcluida){
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        tarefaAlterada.setConcluida(estadoConcluida);
        
        try {
            dao.atualizar(tarefaAlterada);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public List<TarefaDiaria> gerarTarefasDoDia(){
        List<TarefaDiaria> tarefas = new ArrayList<>();
        TarefaDiariaDAO tarefaDiariaDAO = new TarefaDiariaDAO();
        
        try {
            tarefas = tarefaDiariaDAO.buscarPorData(data);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefas;
    }

    public String getData() {
        return data;
    }
}
