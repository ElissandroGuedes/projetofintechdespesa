package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.DespesaDao;
import br.com.fiap.fintech.execption.DBExecption;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Despesa;

import java.time.LocalDate;
import java.util.List;

public class DespesaDaoTeste {

    public static void main(String[] args) {

        //Cadastar uma despesa

        DespesaDao dao = DaoFactory.getDespesaDao();

        Despesa despesa = new Despesa(0,"Aluguel da casa",1000,LocalDate.of(2024,03,01),"Moradia");

//        try {
//
//            dao.cadastrar(despesa);
//
//        } catch (DBExecption e) {
//            throw new RuntimeException(e);
//        }

        //Buscar uma despesa

//        despesa = dao.buscar(1);
//        despesa.setDescricao("mensalidade do curso");
//        despesa.setValor(1129.00);
//
//        try {
//            dao.atualizar(despesa);
//        }catch (DBExecption e) {
//            e.printStackTrace();
//        }

        //Listar uma despesa

//        List<Despesa> lista = dao.listar();
//        for (Despesa item : lista) {
//            System.out.println( item.getDescricao() + " - " + item.getValor() + " - " + item.getDataDespesa() + " - " + item.getCategoria() );
//        }

        //Remover uma despesa

        try {
            dao.remover(1);
        } catch (DBExecption e) {
            e.printStackTrace();
        }
    }
}
