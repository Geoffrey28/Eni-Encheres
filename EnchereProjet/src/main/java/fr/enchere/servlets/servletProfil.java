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
		
		if (request.getParameter("id") != null) {
			String id = request.getParameter("id");
			
			Utilisateur u = UtilisateurManager.getInstance().show(id);
			
			request.setAttribute("userConnected", u);
			request.setAttribute("other_user", true);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
