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
import view.TelaAdicionarTarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class ControllerAdicionarTarefaSemanal {
    
    private TarefaSemanalDAO dao = new TarefaSemanalDAO();
    private static int idSemana;
    private TelaAdicionarTarefaSemanal tela;
    private AtualizacaoTarefaSemanalListener listener;

    
    public void exibirTela(){
        new TelaAdicionarTarefaSemanal(this).setVisible(true);
    }
    
    public void receberIdSemana(int id){
        idSemana = id;
    }
    
    public void adicionarTarefaSemanal(String titulo, String descricao, int prioridade, Boolean concluida){
        TarefaSemanal tarefa = new TarefaSemanal(titulo, descricao, prioridade, concluida, idSemana);
        
        try {
            dao.inserir(tarefa);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAdicionarTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        listener.atualizarLista();
    }

    public void setListener(AtualizacaoTarefaSemanalListener listener) {
        this.listener = listener;
    }
}