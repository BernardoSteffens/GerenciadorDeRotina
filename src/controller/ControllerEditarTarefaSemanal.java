/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaSemanalDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaSemanal;
import view.TelaEditarTarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class ControllerEditarTarefaSemanal {

    private TarefaSemanalDAO dao = new TarefaSemanalDAO();
    private TelaEditarTarefaSemanal tela;
    private TarefaSemanal tarefa;
    private AtualizacaoTarefaSemanalListener listener;

    
    public void exibirTela(){
        tela = new TelaEditarTarefaSemanal(this, tarefa);
        tela.setVisible(true);
        
    }

    public void setTarefa(TarefaSemanal tarefa) {
        this.tarefa = tarefa;
    }

    public void setListener(AtualizacaoTarefaSemanalListener listener) {
        this.listener = listener;
    }

    public void editarTarefaSemanal(int id, String titulo, String descricao, int prioridade, boolean concluida, int idSemana) {
        TarefaSemanal tarefaEditada = new TarefaSemanal(id, titulo, descricao, prioridade, concluida, idSemana);
                
        try {
            dao.atualizar(tarefaEditada);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdicionarTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        listener.atualizarLista();
    }
}
