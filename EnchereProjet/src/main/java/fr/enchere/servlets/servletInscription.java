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

@WebServlet("/Inscription")
public class servletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Utilisateur u = null;
		String noUtilisateur = null;
		
		String errorMessage = null;
		
		noUtilisateur = request.getParameter("noUtilisateur");
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		int telephone = Integer.parseInt(request.getParameter("telephone"));
		int codePostal = Integer.parseInt(request.getParameter("codepostal"));
		String rue = request.getParameter("rue");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String ville = request.getParameter("ville");
		String MotDePasse = request.getParameter("password");
		
		Boolean checkPseudo = UtilisateurManager.getInstance().checkDoublonPseudo(pseudo);
		Boolean checkEmail = UtilisateurManager.getInstance().checkDoublonEmail(email);
			if (noUtilisateur == null) {
				if (!checkPseudo && !checkEmail) {
					try {
						Utilisateur user = new Utilisateur(pseudo, nom, prenom, MotDePasse, email, telephone, rue, ville, codePostal);
						user.setCodePostal(codePostal);
						UtilisateurManager.getInstance().ajouter(user);
						
						HttpSession session;
						session = request.getSession();
						
						user.setMotDePasse(MotDePasse);
						u = UtilisateurManager.getInstance().login(pseudo, user.getMotDePasse());
						
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
					} catch (CodePostalException e) {
						errorMessage = e.getMessage();
						request.setAttribute("errorMessage", errorMessage);
						doGet(request, response);
					}
				} else { 
					if (checkPseudo && checkEmail) {
						errorMessage = "Pseudo et Email déjà utilisé";
					} else if (!checkPseudo && checkEmail) {
						errorMessage = "Email déjà utilisé";
					} else if (checkPseudo && !checkEmail) {
						errorMessage = "Pseudo déjà utilisé";
					}
					request.setAttribute("errorMessage", errorMessage);
					doGet(request, response);
				}
			} else {
				Utilisateur user;
				try {
					user = new Utilisateur(Integer.parseInt(noUtilisateur), pseudo, nom, prenom, email, telephone, rue, ville, codePostal);
					user.setCodePostal(codePostal);
					UtilisateurManager.getInstance().modifier(user);
					response.sendRedirect("Profil");
					HttpSession session;
					session = request.getSession();
					session.setAttribute("userConnected", user);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (CodePostalException e) {
					errorMessage = e.getMessage();
					request.setAttribute("errorMessage", errorMessage);
					doGet(request, response);
				}
			}
	}	
}
	