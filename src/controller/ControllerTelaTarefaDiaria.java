/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaAdicionarTarefaDiaria;
import view.TelaEditarTarefaDiaria;
import view.TelaPrincipal;
import view.TelaTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaTarefaDiaria {
    
    private static TelaTarefaDiaria tela = new TelaTarefaDiaria();
    
    public static void apagarTarefaSelecionada(TarefaDiaria tarefaSelecionada) {
        int id = tarefaSelecionada.getId();
        ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        
        try {
            dao.deletar(id);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        controllerTelaPrincipal.atualizarTarefasDoDia();
        ControllerTelaTarefaDiaria.atualizarPesquisa();
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

    private TarefaDiariaDAO dao = new TarefaDiariaDAO();
    
    public ControllerTelaTarefaDiaria(TelaTarefaDiaria tela) {
        this.tela = tela;
    }

   public static void atualizarPesquisa(){
       tela.atualizarPesquisa();
   }
    
    public List<TarefaDiaria> pesquisar(String titulo, String data, int prioridade, int concluida){
        
        List<TarefaDiaria> tarefas = null;
        
        try {
            tarefas = dao.buscarPorFiltros(titulo, data, prioridade, concluida);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefas;
    }
    
    public void abrirTelaPrincipal() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
        
    }
    
    public static void abrirTelaAdicionarTarefaDiaria() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdicionarTarefaDiaria().setVisible(true);
            }
        });
    }
    
    public static void abrirTelaEditarTarefaDiaria(TarefaDiaria tarefaSelecionada) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEditarTarefaDiaria().setVisible(true);
            }
        });
        ControllerTelaEditarTarefaDiaria.receberTarefaSelecionada(tarefaSelecionada);
    }
}
