/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerTarefaDiaria implements AtualizacaoTarefaDiariaListener{
        
    private TelaTarefaDiaria tela; 
    private TarefaDiariaDAO dao = new TarefaDiariaDAO();
    private ControllerPrincipal controllerPrincipal;
    private AtualizacaoTarefaDiariaListener listener;
    private ControllerAdicionarTarefaDiaria controllerAdicionar;
    private ControllerEditarTarefaDiaria controllerEditar;

    public void exibirTela(){
        TelaTarefaDiaria telaTarefaDiaria = new TelaTarefaDiaria(this);
        telaTarefaDiaria.setVisible(true);
        this.tela = telaTarefaDiaria;
    }
    
    public void apagarTarefaSelecionada(TarefaDiaria tarefaSelecionada) {
        int id = tarefaSelecionada.getId();
        
        try {
            dao.deletar(id);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listener.atualizarLista();
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

    public List<TarefaDiaria> pesquisar(String titulo, String data, int prioridade, int concluida){
        
        List<TarefaDiaria> tarefas = null;
        
        try {
            tarefas = dao.buscarPorFiltros(titulo, data, prioridade, concluida);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tarefas;
    }
    
    public void abrirTelaPrincipal() {
        controllerPrincipal.exibirTela();
    }
    
    public void abrirTelaAdicionarTarefaDiaria() {
        controllerAdicionar = new ControllerAdicionarTarefaDiaria();
        controllerAdicionar.setAtualizacaoListener(this);
        controllerAdicionar.exibirTela();
    }
    
    public void abrirTelaEditarTarefaDiaria(TarefaDiaria tarefaSelecionada) {
        controllerEditar.exibirTela();
        ControllerEditarTarefaDiaria.receberTarefaSelecionada(tarefaSelecionada);
    }

    @Override
    public void atualizarLista() {
        tela.pesquisar();
    }

    public void setControllerPrincipal(ControllerPrincipal controllerPrincipal) {
        this.controllerPrincipal = controllerPrincipal;
    }
}
