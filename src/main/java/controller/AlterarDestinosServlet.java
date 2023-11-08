package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Destino;
import model.DestinoDAO;


@WebServlet("/editarDestino")
public class AlterarDestinosServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idDestino = Integer.parseInt(req.getParameter("id"));
		DestinoDAO cdao = new DestinoDAO();
		Destino destinoSelecionado = cdao.getDestinoById(idDestino);

		req.setAttribute("destino", destinoSelecionado);
	
		RequestDispatcher rd = req.getRequestDispatcher("/alterarDestino.jsp");

        rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Destino destinoAlterado = new Destino();		
		destinoAlterado.setCidade(req.getParameter("cidade"));
		destinoAlterado.setPais(req.getParameter("pais"));
		destinoAlterado.setValor(Double.parseDouble(req.getParameter("valor")));
		destinoAlterado.setId(Integer.parseInt(req.getParameter("id")));
		
		DestinoDAO cd = new DestinoDAO();
		cd.update(destinoAlterado);	
		resp.sendRedirect("index.html");
	}

}
	