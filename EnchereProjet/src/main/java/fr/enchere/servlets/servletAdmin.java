package fr.enchere.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enchere.bll.ArticleVenduManager;
import fr.enchere.bll.CategorieManager;
import fr.enchere.bll.UtilisateurManager;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Utilisateur;


@WebServlet("/Admin")
public class servletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Utilisateur> lstUtilisateur;
		lstUtilisateur = UtilisateurManager.getInstance().afficherListe();
		request.setAttribute("listeUtilisateur", lstUtilisateur);
		
		List<Categorie> lstCategorie;
		lstCategorie = CategorieManager.getInstance().afficherListe();
		request.setAttribute("listeCategorie", lstCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin.jsp");
		rd.forward(request, response);
	}

}