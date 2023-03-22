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
import fr.enchere.bo.exceptions.CodePostalException;
import fr.enchere.bo.exceptions.NumeroException;

@WebServlet("/Inscription")
public class servletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// CLIENT
		
		Utilisateur u = null;
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		int telephone = Integer.parseInt(request.getParameter("telephone"));
		int codePostal = Integer.parseInt(request.getParameter("codepostal"));
		String rue = request.getParameter("rue");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String ville = request.getParameter("ville");
		String MotDePasse = request.getParameter("password");
				
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, MotDePasse, email, telephone, rue, ville, codePostal);
		UtilisateurManager.getInstance().ajouter(user);
		System.out.println(user);
		HttpSession session;
		session = request.getSession();
		
		u = UtilisateurManager.getInstance().login(pseudo, MotDePasse);

		
		if(u != null) {
			System.out.println(u);
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