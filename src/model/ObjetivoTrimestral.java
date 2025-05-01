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
public class ObjetivoTrimestral {
    
    private int id;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private int progresso; // 0 a 100 (porcentagem)
    private boolean concluido;
    private int categoriaId;

    public ObjetivoTrimestral() {
    }

    public ObjetivoTrimestral(String titulo, Date dataInicio, Date dataFim) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public ObjetivoTrimestral(String titulo, String descricao, Date dataInicio, Date dataFim, int progresso, boolean concluido, int categoriaId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.progresso = progresso;
        this.concluido = concluido;
        this.categoriaId = categoriaId;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = progresso;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public long getDiasRestantes() {
        Date hoje = new Date();
        if (dataFim == null) {
            return 0;
        }
        
        long diferenca = dataFim.getTime() - hoje.getTime();
        
        return diferenca / (24 * 60 * 60 * 1000);
    }
   
    public boolean isNoPrazo() {
        return getDiasRestantes() >= 0;
    }
    
    @Override
    public String toString() {
        return "ObjetivoTrimestral{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", progresso=" + progresso + "%" +
                ", diasRestantes=" + getDiasRestantes() +
                ", concluido=" + concluido +
                '}';
    }
}
