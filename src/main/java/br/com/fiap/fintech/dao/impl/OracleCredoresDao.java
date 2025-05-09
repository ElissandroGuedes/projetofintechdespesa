package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.CredoresDao;
import br.com.fiap.fintech.model.Credores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCredoresDao implements CredoresDao {

    private Connection conexao;

    @Override
    public List<Credores> listar() {
        List<Credores> lista = new ArrayList<Credores>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_CREDORES";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                int codigo = rs.getInt("cod_credor");
                String nome = rs.getString("nome");
                Credores credores = new Credores(codigo,nome);
                lista.add(credores);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
                }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return lista;
    }


}
