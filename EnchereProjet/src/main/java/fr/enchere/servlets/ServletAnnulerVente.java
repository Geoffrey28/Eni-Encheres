package fr.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enchere.bll.ArticleVenduManager;
import fr.enchere.bll.RetraitManager;

/**
 * Servlet implementation class ServletAnnulerVente
 */
@WebServlet("/AnnulerVente")
public class ServletAnnulerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));   		
			System.out.println(id);
			ArticleVenduManager.getInstance().supprimer(id);
			RetraitManager.getInstance().supprimer(id);
			response.sendRedirect("Accueil");
   		}
   	}
   		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
