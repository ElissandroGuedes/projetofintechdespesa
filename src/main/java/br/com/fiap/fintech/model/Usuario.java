package br.com.fiap.fintech.model;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {

    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            this.senha = CriptografiaUtils.criptografar(getSenha());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
