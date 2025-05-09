package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.execption.DBExecption;
import br.com.fiap.fintech.model.Despesa;

import java.util.List;

public interface DespesaDao {

    void cadastrar(Despesa despesa) throws DBExecption;
    void atualizar(Despesa despesa) throws DBExecption;
    void remover(int codigo) throws DBExecption;
    Despesa buscar(int codigo);
    List<Despesa> listar();
}
