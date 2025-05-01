/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Bernardo
 */
public class TarefaDiaria {
    
    private int id;
    private String titulo;
    private String descricao;
    private String data;
    private String horaInicio;
    private String horaFim;
    private int prioridade;
    private boolean concluida;
    private int categoriaId;
    private int tarefaSemanalId;

    public TarefaDiaria() {
    }

    public TarefaDiaria(String titulo, String data) {
        this.titulo = titulo;
        this.data = data;
    }

    public TarefaDiaria(String titulo, String descricao, String data, int prioridade, String horaInicio) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
        this.horaInicio = horaInicio;
    }
    
    public TarefaDiaria(String titulo, String descricao, String data, String horaInicio, String horaFim, int prioridade, boolean concluida, int categoriaId, int tarefaSemanalId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.prioridade = prioridade;
        this.concluida = concluida;
        this.categoriaId = categoriaId;
        this.tarefaSemanalId = tarefaSemanalId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getTarefaSemanalId() {
        return tarefaSemanalId;
    }

    public void setTarefaSemanalId(int tarefaSemanalId) {
        this.tarefaSemanalId = tarefaSemanalId;
    }

    @Override
    public String toString() {
        return titulo;   
    }
}
