package controller;

import dao.SemanaDAO;
import dao.TarefaSemanalDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Semana;
import model.TarefaSemanal;
import view.TelaTarefaSemanal;

/**
 *
 * @author Bernardo
 */
public class ControllerTarefaSemanal implements AtualizacaoTarefaSemanalListener{
    
    private int idSemana;
    private SemanaDAO semanaDao = new SemanaDAO();
    private TarefaSemanalDAO tarefaSemanalDao = new TarefaSemanalDAO();
    private TelaTarefaSemanal tela;
    private ControllerPrincipal controllerPrincipal;
    private ControllerAdicionarTarefaSemanal controllerAdicionar = new ControllerAdicionarTarefaSemanal();
    private ControllerEditarTarefaSemanal controllerEditar = new ControllerEditarTarefaSemanal();

    public void exibirTela(){
        TelaTarefaSemanal telaTarefaSemanal = new TelaTarefaSemanal(this);
        telaTarefaSemanal.setVisible(true);
        this.tela = telaTarefaSemanal;
        
    }
    
    public List<TarefaSemanal> listarTarefasSemanais(int idSemana){
        this.idSemana = idSemana;
        List<TarefaSemanal> tarefasSemanais = new ArrayList<>();
        try {
            tarefasSemanais = tarefaSemanalDao.buscarPorIdSemana(idSemana);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tarefasSemanais;
    }
    
    public List<Semana> listarSemanas(){
        List<Semana> semanas = new ArrayList<>();
        
        try {
            semanas = semanaDao.listarTodos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return semanas;
    }

    public void abrirTelaPrincipal() {
        controllerPrincipal.exibirTela();
    }

    public void adicionarSemana() {
        semanaDao.adicionarSemana();
    }

    public void abrirTelaAdicionar() {
        controllerAdicionar.setListener(this);
        controllerAdicionar.receberIdSemana(idSemana);
        controllerAdicionar.exibirTela();
    }

    public void setIdSemana(int idSemana) {
        this.idSemana = idSemana;
    }

    public void setControllerPrincipal(ControllerPrincipal controllerPrincipal) {
        this.controllerPrincipal = controllerPrincipal;
    }

    public void abrirTelaEditarTarefaSemanal(TarefaSemanal tarefaSelecionada) {
        controllerEditar.setListener(this);
        controllerEditar.setTarefa(tarefaSelecionada);
        controllerEditar.exibirTela();
    }

    public void excluirEditarTarefaSemanal(TarefaSemanal tarefaSelecionada) {
        int id = tarefaSelecionada.getId();
        
        try {
            tarefaSemanalDao.deletar(id);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizarLista() {
        tela.atualizarTarefas();
    }

    public void atualizarConcluidaTarefa(TarefaSemanal tarefaAlterada, Boolean concluida) {
        tarefaAlterada.setConcluida(concluida);
        
        try {
            tarefaSemanalDao.atualizar(tarefaAlterada);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTarefaDiaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
