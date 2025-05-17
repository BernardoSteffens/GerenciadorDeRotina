/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.lang.System.Logger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import model.TarefaDiaria;
import view.TelaEditarTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaEditarTarefaDiaria {
    
    private static TarefaDiaria tarefa;
    
    public static void receberTarefaSelecionada(TarefaDiaria tarefaSelecionada){
        tarefa = tarefaSelecionada;
    }

    public static TarefaDiaria getTarefa() {
        return tarefa;
    }
    
    public static Date stringParaDate(String dataString){
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");   
        
        try {
            return sdfData.parse(dataString);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(ControllerTelaEditarTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public static Date stringParaDateHora(String horaString){
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");   
        
        try {
            return sdfHora.parse(horaString);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(ControllerTelaEditarTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void atualizarTarefaDiaria(int id, String titulo, String descricao, String data, int prioridade, String horaInicio, boolean concluida) {
        TarefaDiaria tarefa = new TarefaDiaria(id, titulo, descricao, data, prioridade, horaInicio, concluida);
        TarefaDiariaDAO dao = new TarefaDiariaDAO();
        ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
        
        try {
            dao.atualizar(tarefa);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerTelaAdicionarTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        controllerTelaPrincipal.atualizarTarefasDoDia();
        ControllerTelaTarefaDiaria.atualizarPesquisa();
    }
    
}
