package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Destino;
import model.DestinoDAO;



@WebServlet("/LerDestinos")
public class ListarDestinosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		try {
			DestinoDAO destinoDAO = new DestinoDAO();
			
			List<Destino> destinos = destinoDAO.listarDestinos();
			
			req.setAttribute("destino", destinos);
			
			RequestDispatcher rd = req.getRequestDispatcher("cadastroDestino.jsp");
			rd.forward(req, resp);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}}