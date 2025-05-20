/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bernardo
 */
public class TarefaSemanal {
    
    private int id;
    private String titulo;
    private String descricao;
    private int prioridade;
    private boolean concluida;
    private int categoriaId;
    private int objetivoId;
    private int semanaId;

    public TarefaSemanal() {
    }

    public TarefaSemanal(String titulo, String semanaAno) {
        this.titulo = titulo;
    }
    
    public TarefaSemanal(String titulo, String descricao, int prioridade, boolean concluida, int semanaId) {       
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = concluida;
        this.semanaId = semanaId;
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

    public int getObjetivoId() {
        return objetivoId;
    }

    public void setObjetivoId(int objetivoId) {
        this.objetivoId = objetivoId;
    }

    public int getSemanaId() {
        return semanaId;
    }

    public void setSemanaId(int semanaId) {
        this.semanaId = semanaId;
    }
    
    
    
    @Override
    public String toString() {
        return "TarefaSemanal{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", concluida=" + concluida +
                '}';
    }
}
