package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.CredoresDao;
import br.com.fiap.fintech.dao.impl.OracleCredoresDao;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Credores;
import br.com.fiap.fintech.model.Despesa;

import java.util.List;

public class CredoresDaoTeste {
    public static void main(String[] args) {

        CredoresDao dao = DaoFactory.getCredoresDao();

        List<Credores> lista = dao.listar();
        for (Credores item : lista) {
           System.out.println( item.getCdCredor() + " " + item.getNome());
       }
    }
}
