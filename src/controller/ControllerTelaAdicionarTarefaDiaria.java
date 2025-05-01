/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaAdicionarTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaAdicionarTarefaDiaria {

    private static TelaAdicionarTarefaDiaria tela = new TelaAdicionarTarefaDiaria();

    public ControllerTelaAdicionarTarefaDiaria(TelaAdicionarTarefaDiaria tela) {
        this.tela = tela;
        atualizarData();
    }
    
    public void atualizarData(){
        tela.atualizarData();
    }
    
    
    public static void adicionarTarefaDiaria(String titulo, String descricao, String data, int prioridade, String horaInicio) {
        TarefaDiaria tarefa = new TarefaDiaria(titulo, descricao, data, prioridade, horaInicio);
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
        
        try {
            dao.inserir(tarefa);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaAdicionarTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        controllerTelaPrincipal.atualizarTarefasDoDia();
    }
    
}
