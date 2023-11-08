package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DestinoDAO;

@WebServlet("/excluirDestino")
public class ExcluirDestinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			DestinoDAO destinoDAO = new DestinoDAO();
			destinoDAO.excluirDestinos(id);
			
			resp.sendRedirect("index.html");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}