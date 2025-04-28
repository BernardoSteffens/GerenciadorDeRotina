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
    private int diaSemana; // 1 (domingo) a 7 (sábado)
    private String semanaAno; // Formato: "YYYY-WW" (ano-número da semana)
    private int prioridade; // 1 (baixa) a 5 (alta)
    private boolean concluida;
    private int categoriaId;
    private int objetivoId;

    public TarefaSemanal() {
    }

    public TarefaSemanal(String titulo, String semanaAno) {
        this.titulo = titulo;
        this.semanaAno = semanaAno;
    }

    public TarefaSemanal(String titulo, String descricao, int diaSemana, String semanaAno, int prioridade, boolean concluida, int categoriaId, int objetivoId) {       
        this.titulo = titulo;
        this.descricao = descricao;
        this.diaSemana = diaSemana;
        this.semanaAno = semanaAno;
        this.prioridade = prioridade;
        this.concluida = concluida;
        this.categoriaId = categoriaId;
        this.objetivoId = objetivoId;
    }

    
    
    public int getId() {
        return id;
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

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getSemanaAno() {
        return semanaAno;
    }

    public void setSemanaAno(String semanaAno) {
        this.semanaAno = semanaAno;
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
    
    public String getNomeDiaSemana() {
        switch (diaSemana) {
            case 1: return "Domingo";
            case 2: return "Segunda-feira";
            case 3: return "Terça-feira";
            case 4: return "Quarta-feira";
            case 5: return "Quinta-feira";
            case 6: return "Sexta-feira";
            case 7: return "Sábado";
            default: return "Dia inválido";
        }
    }
    
    @Override
    public String toString() {
        return "TarefaSemanal{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", diaSemana=" + getNomeDiaSemana() +
                ", semana=" + semanaAno +
                ", concluida=" + concluida +
                '}';
    }
}
