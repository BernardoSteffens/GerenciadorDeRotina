/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TarefaDiariaDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TarefaDiaria;
import view.TelaAdicionarTarefaDiaria;
import view.TelaPrincipal;

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
    
    public void atualizarDataDeHoje(){
        telaPrincipal.atualizarDataDeHoje(data);
    }
    
    public void atualizarTarefasDoDia(){
        String tarefas = gerarTarefasDoDia();
        telaPrincipal.atualizarTarefasDoDia(tarefas);
    }
    
    public String gerarTarefasDoDia(){
        List<TarefaDiaria> tarefas = new ArrayList<>();
        TarefaDiariaDAO tarefaDiariaDAO = new TarefaDiariaDAO();
        
        try {
            tarefas = tarefaDiariaDAO.buscarPorData(data);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        String teste = "<html>";
        
        for(TarefaDiaria tarefa : tarefas){
            teste += tarefa.getTitulo() + "<br>";
        }
        
        teste += "</html>";
        
        return teste;
    }
}
