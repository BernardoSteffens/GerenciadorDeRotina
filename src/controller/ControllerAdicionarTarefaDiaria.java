/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaAdicionarTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerAdicionarTarefaDiaria {

    private AtualizacaoTarefaDiariaListener listener;

    public void exibirTela(){
        new TelaAdicionarTarefaDiaria(this).setVisible(true);
    }

    public void setAtualizacaoListener(AtualizacaoTarefaDiariaListener listener) {
        this.listener = listener;
    }
    
    public void adicionarTarefaDiaria(String titulo, String descricao, String data, int prioridade, String horaInicio) {
        TarefaDiaria tarefa = new TarefaDiaria(titulo, descricao, data, prioridade, horaInicio);
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        
        try {
            dao.inserir(tarefa);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdicionarTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        listener.atualizarLista();
    }
    
    
}
