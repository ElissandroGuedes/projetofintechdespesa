package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.CredoresDao;
import br.com.fiap.fintech.dao.DespesaDao;
import br.com.fiap.fintech.execption.DBExecption;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Credores;
import br.com.fiap.fintech.model.Despesa;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {

    private DespesaDao dao;

    private CredoresDao credoresDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getDespesaDao();
        credoresDao = DaoFactory.getCredoresDao();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }


    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));

        try {
            dao.remover(codigo);
            req.setAttribute("mensagem", "Despesa removida com sucesso!");
        }catch (DBExecption e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("erro","Erro ao excluir despesa!");
        }

        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String descricao = req.getParameter("descricao");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("dtDespesa"));
            String categoria = req.getParameter("categoria");

            int codigoCredor = Integer.parseInt(req.getParameter("codigo"));

            Credores credores = new Credores();

            credores.setCdCredor(codigoCredor);

            Despesa despesa = new Despesa(0, descricao, valor, data, categoria);

            despesa.setCredores(credores);

            dao.cadastrar(despesa);

            req.setAttribute("mensagem", "Despesa cadastrada com sucesso!");
        } catch (DBExecption db) {
            db.printStackTrace();
            req.setAttribute("mensagem", "Erro ao cadastrar despesa");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados e tente novamente!");
        }

        //req.getRequestDispatcher("cadastro-despesa.jsp").forward(req, resp);

        abrirFormCadastro(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoDespesa = Integer.parseInt(req.getParameter("codigoDespesa"));
            String descricao = req.getParameter("descricao");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate dataDespesa = LocalDate
                    .parse(req.getParameter("dtDespesa"));
            String categoria = req.getParameter("categoria");

            int codigoCredor = Integer.parseInt(req.getParameter("codigoCredor"));
            Credores credores = new Credores();
            credores.setCdCredor(codigoCredor);

            Despesa despesa = new Despesa(codigoDespesa, descricao, valor, dataDespesa, categoria);
            despesa.setCredores(credores);

            dao.atualizar(despesa);

            req.setAttribute("mensagem", "Despesa atualizado!");
        } catch (DBExecption db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao editar despesa");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados e tente novamente!");
        }

        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;
                case "abrir-form-cadastro":
                    abrirFormCadastro(req, resp);
                    break;
        }


    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carregarOpcoesCredores(req);
        req.getRequestDispatcher("cadastro-despesa.jsp").forward(req, resp);
    }

    private void carregarOpcoesCredores(HttpServletRequest req) {
        List<Credores>lista = credoresDao.listar();
        req.setAttribute("credores", lista);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Despesa despesa = dao.buscar(id);
        req.setAttribute("despesa", despesa);
        carregarOpcoesCredores(req);
        req.getRequestDispatcher("editar-despesa.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Despesa> lista = dao.listar();
        req.setAttribute("despesas", lista);
        req.getRequestDispatcher("lista-despesa.jsp").forward(req, resp);
    }
}
