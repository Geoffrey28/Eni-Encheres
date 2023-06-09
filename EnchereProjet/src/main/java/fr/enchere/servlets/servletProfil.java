package fr.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enchere.bll.UtilisateurManager;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class servletProfil
 */
@WebServlet("/Profil")
public class servletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("pseudo") != null) {
			String pseudo = request.getParameter("pseudo");
			
			Utilisateur u = UtilisateurManager.getInstance().showByPseudo(pseudo);
			
			request.setAttribute("user", u);
		}
		
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Utilisateur u = UtilisateurManager.getInstance().showById(id);
			
			request.setAttribute("user", u);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
