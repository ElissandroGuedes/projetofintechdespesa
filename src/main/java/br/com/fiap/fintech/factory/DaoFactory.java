package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.CredoresDao;
import br.com.fiap.fintech.dao.DespesaDao;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.dao.impl.OracleCredoresDao;
import br.com.fiap.fintech.dao.impl.OracleDespesaDao;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDao;

public class DaoFactory {

    public static DespesaDao getDespesaDao() {
        return new OracleDespesaDao();
    }

    public static CredoresDao getCredoresDao() {
        return new OracleCredoresDao();
    }

    public static UsuarioDao getUsuarioDao() {
        return new OracleUsuarioDao();
    }
}
