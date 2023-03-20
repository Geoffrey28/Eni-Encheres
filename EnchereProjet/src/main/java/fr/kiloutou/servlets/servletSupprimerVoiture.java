package fr.kiloutou.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.kiloutou.bll.VoitureManager;

/**
 * Servlet implementation class servletSupprimerVoiture
 */
@WebServlet("/SupprimerVoiture")
public class servletSupprimerVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id;
    	id = Integer.parseInt(request.getParameter("id"));
    	VoitureManager.getInstance().supprimer(id);
    	response.sendRedirect("ListeVehicules");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
