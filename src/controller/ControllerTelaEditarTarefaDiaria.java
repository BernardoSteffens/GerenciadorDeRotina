/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.TarefaDiaria;

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
    
    
}
