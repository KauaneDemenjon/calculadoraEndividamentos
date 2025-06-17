package com.unicuritiba.plataforma_endividamento.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuarioFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double rendaMensal;
    private Double despesasFixas;
    private Double totalDividas;

    // Construtores
    public UsuarioFinanceiro() {}

    public UsuarioFinanceiro(String nome, Double rendaMensal, Double despesasFixas, Double totalDividas) {
        this.nome = nome;
        this.rendaMensal = rendaMensal;
        this.despesasFixas = despesasFixas;
        this.totalDividas = totalDividas;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getRendaMensal() { return rendaMensal; }
    public void setRendaMensal(Double rendaMensal) { this.rendaMensal = rendaMensal; }

    public Double getDespesasFixas() { return despesasFixas; }
    public void setDespesasFixas(Double despesasFixas) { this.despesasFixas = despesasFixas; }

    public Double getTotalDividas() { return totalDividas; }
    public void setTotalDividas(Double totalDividas) { this.totalDividas = totalDividas; }
}
