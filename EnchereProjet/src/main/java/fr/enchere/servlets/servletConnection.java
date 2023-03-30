package fr.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.enchere.bll.UtilisateurManager;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/Connection")
public class servletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo, motdepasse;
		Utilisateur u = new Utilisateur();
		Boolean check = false;
		pseudo = request.getParameter("pseudo");
		motdepasse = request.getParameter("motdepasse");
		HttpSession session;
		session = request.getSession();
		
		u.setMotDePasse(motdepasse);
		u = UtilisateurManager.getInstance().login(pseudo, u.getMotDePasse());

		if (u == null) {
			check = true;
		}
		session.setAttribute("check", check);
		if(u != null) {
			session.setAttribute("userConnected", u);
			Cookie connectionMemo;
			connectionMemo = new Cookie("lastLogin", u.getPseudo());
			connectionMemo.setMaxAge(60*60*24*7);
			response.addCookie(connectionMemo);
			response.sendRedirect("Accueil");
		} else {
			doGet(request, response);
		}
	}

}

