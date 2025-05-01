/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.TelaAdicionarTarefaDiaria;
import view.TelaTarefaDiaria;

/**
 *
 * @author Bernardo
 */
public class ControllerTelaTarefaDiaria {
    
    private static TelaTarefaDiaria tela = new TelaTarefaDiaria();

    public ControllerTelaTarefaDiaria(TelaTarefaDiaria tela) {
        this.tela = tela;
    }

    public static void abrirTelaAdicionarTarefaDiaria() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdicionarTarefaDiaria().setVisible(true);
            }
        });
    }       
}
