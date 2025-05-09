package br.com.fiap.fintech.model;

public class Credores {

    private int cdCredor;
    private String nome;

    public Credores() {
    }

    public Credores(int cdCredor, String nome) {
        this.cdCredor = cdCredor;
        this.nome = nome;
    }

    public int getCdCredor() {
        return cdCredor;
    }

    public void setCdCredor(int cdCredor) {
        this.cdCredor = cdCredor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
