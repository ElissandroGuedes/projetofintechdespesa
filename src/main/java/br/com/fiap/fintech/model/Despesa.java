package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Despesa {
    private int cdDespesa;
    private String descricao;
    private double valor;
    private LocalDate dataDespesa;
    private String categoria;
    private Credores credores;

    public Despesa() {
    }

    public Despesa(int cdDespesa, String descricao, double valor, LocalDate dataDespesa, String categoria) {
        this.cdDespesa = cdDespesa;
        this.descricao = descricao;
        this.valor = valor;
        this.dataDespesa = dataDespesa;
        this.categoria = categoria;

    }


    public int getCdDespesa() {
        return cdDespesa;
    }

    public void setCdDespesa(int cdespesa) {
        this.cdDespesa = cdDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public java.util.Date getDataDespesaAsDate() {
        if (this.dataDespesa != null) {
            return java.util.Date.from(this.dataDespesa.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        }
        return null;
    }


    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Credores getCredores() {
        return credores;
    }

    public void setCredores(Credores credores) {
        this.credores = credores;
    }
}
