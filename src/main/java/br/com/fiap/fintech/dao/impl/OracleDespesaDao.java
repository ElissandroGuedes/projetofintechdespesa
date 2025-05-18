package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.DespesaDao;
import br.com.fiap.fintech.execption.DBExecption;
import br.com.fiap.fintech.model.Credores;
import br.com.fiap.fintech.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleDespesaDao implements DespesaDao {

    Connection conexao;

    @Override
    public void cadastrar(Despesa despesa) throws DBExecption {

        PreparedStatement stmt = null;
        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO TB_DESPESA " + "(cod_despesa, descricao, valor, dt_despesa, categoria, cod_credor)" + " VALUES (SQ_TB_DESPESA.NEXTVAL, ?, ?, ?,?,?) ";

           try {
               stmt = conexao.prepareStatement(sql);
               stmt.setString(1, despesa.getDescricao());
               stmt.setDouble(2, despesa.getValor());
               stmt.setDate(3, Date.valueOf(despesa.getDataDespesa()));
               stmt.setString(4, despesa.getCategoria());
               stmt.setInt(5,despesa.getCredores().getCdCredor());
               stmt.executeUpdate();
               System.out.println("Despesa cadastrada com sucesso!");
        } catch (SQLException e) {
               throw new DBExecption("Erro ao cadastrar despesa", e);
           }finally{
               try {
                   conexao.close();
                   stmt.close();
               }catch (SQLException e){
                   e.printStackTrace();
               }
           }
    }

    @Override
    public void atualizar(Despesa despesa) throws DBExecption {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = " UPDATE TB_DESPESA SET " +
                    " descricao = ?, " +
                    " valor = ?, " +
                    " dt_despesa = ?," +
                    " categoria = ?, " +
                    " cod_credor = ? " +
                    " WHERE cod_despesa = ? ";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2,despesa.getValor());
            stmt.setDate(3,Date.valueOf(despesa.getDataDespesa()));
            stmt.setString(4, despesa.getCategoria());
            stmt.setInt(5,despesa.getCredores().getCdCredor());
            stmt.setInt(6,despesa.getCdDespesa());
            stmt.executeUpdate();

            System.out.println("Despesa atualizada com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBExecption("Erro ao atualizar despesa", e);
        }finally {
            try {
                conexao.close();
                stmt.close();
                }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int codigo) throws DBExecption {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = " DELETE FROM TB_DESPESA WHERE cod_despesa = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Despesa removida com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
            throw new DBExecption("Erro ao remover despesa", e);
        }finally {
            try {
                stmt.close();
                conexao.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Despesa buscar(int codigo) {

        Despesa despesa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conexao = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = " SELECT * FROM TB_DESPESA INNER JOIN TB_CREDORES ON TB_DESPESA.cod_credor = TB_CREDORES.cod_credor " + " WHERE TB_DESPESA.cod_despesa = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()){
                codigo = rs.getInt("cod_despesa");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                java.sql.Date data = rs.getDate("dt_despesa");
                LocalDate dtDespesa = rs.getDate("dt_despesa").toLocalDate();
                String categoria = rs.getString("categoria");
                int codigoCredor = rs.getInt("cod_credor");
                String nomeCredor = rs.getString("nome");

                Credores credores = new Credores(codigoCredor,nomeCredor);

                despesa = new Despesa(codigo, descricao, valor, dtDespesa, categoria);

                despesa.setCredores(credores);


            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conexao != null) conexao.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return despesa;
    }

    @Override
    public List<Despesa> listar() {
        List<Despesa> lista = new ArrayList<Despesa>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = " SELECT * FROM TB_DESPESA" +
                    " INNER JOIN TB_CREDORES " +
                    " ON TB_DESPESA.cod_credor = TB_CREDORES.cod_credor";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                int codigo = rs.getInt("cod_despesa");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                java.sql.Date data = rs.getDate("dt_despesa");
                LocalDate dtDespesa = rs.getDate("dt_despesa").toLocalDate();
                String categoria = rs.getString("categoria");
                int codigoCredor = rs.getInt("cod_credor");
                String nomeCredor = rs.getString("nome");

                Credores credores = new Credores(codigoCredor,nomeCredor);

                Despesa despesa = new Despesa(codigo, descricao, valor, dtDespesa, categoria);

                despesa.setCredores(credores);

                lista.add(despesa);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public List<Despesa> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {

            List<Despesa> lista = new ArrayList<>();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conexao = null;

            try {
                conexao = ConnectionManager.getInstance().getConnection();

                String sql = "SELECT * FROM TB_DESPESA INNER JOIN TB_CREDORES ON TB_DESPESA.cod_credor = TB_CREDORES.cod_credor WHERE dt_despesa BETWEEN ? AND ?";

                stmt = conexao.prepareStatement(sql);

                stmt.setDate(1, java.sql.Date.valueOf(inicio));
                stmt.setDate(2, java.sql.Date.valueOf(fim));

                rs = stmt.executeQuery();

                while (rs.next()) {
                    int codigo = rs.getInt("cod_despesa");
                    String descricao = rs.getString("descricao");
                    double valor = rs.getDouble("valor");
                    LocalDate dtDespesa = rs.getDate("dt_despesa").toLocalDate();
                    String categoria = rs.getString("categoria");
                    int codigoCredor = rs.getInt("cod_credor");
                    String nomeCredor = rs.getString("nome");

                    Credores credores = new Credores(codigoCredor,nomeCredor);
                    Despesa despesa = new Despesa(codigo, descricao, valor, dtDespesa, categoria);
                    despesa.setCredores(credores);

                    lista.add(despesa);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (conexao != null) conexao.close(); } catch (Exception e) { e.printStackTrace(); }
            }

            return lista;
        }

    }

