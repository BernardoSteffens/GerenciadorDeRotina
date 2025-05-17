/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bernardo
 */
public class Semana {
    private int id;
    private String diaComeco;
    private String diaFim;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaComeco() {
        return diaComeco;
    }

    public void setDiaComeco(String diaComeco) {
        this.diaComeco = diaComeco;
    }

    public String getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(String diaFim) {
        this.diaFim = diaFim;
    }
    
    @Override
    public String toString() {
        return diaComeco + " - " + diaFim;
    }
    
}
