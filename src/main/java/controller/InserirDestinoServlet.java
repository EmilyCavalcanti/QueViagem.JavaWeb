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

@WebServlet("/inserirDestino")
public class InserirDestinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		Destino destino = new Destino();
	
	destino.setCidade(req.getParameter("cidade"));
	destino.setPais(req.getParameter("pais"));
	destino.setValor(Double.parseDouble(req.getParameter ("valor")));

	
	
	try {
		DestinoDAO destinoDAO = new DestinoDAO();
		destinoDAO.inserirDestino(destino);
		
		
		
		System.out.println(destino.getCidade());
		System.out.println(destino.getPais());
		System.out.println(destino.getValor());

		req.setAttribute("destino", destino);
		RequestDispatcher dispatcher = req.getRequestDispatcher("lerCliente.jsp");
		dispatcher.forward(req, res);

	} catch(Exception e) {
		e.printStackTrace();
	}
	}

}
