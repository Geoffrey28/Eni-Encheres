package fr.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enchere.bll.UtilisateurManager;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class servletSupprimer
 */
@WebServlet("/Supprimer")
public class servletSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("userConnected");
		int id = u.getNoUtilisateur();
		System.out.println(id);
		UtilisateurManager.getInstance().deleteById(id);
		request.getSession().invalidate();
		response.sendRedirect("Accueil");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
