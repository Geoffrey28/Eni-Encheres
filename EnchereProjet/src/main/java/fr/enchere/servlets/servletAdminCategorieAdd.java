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


@WebServlet("/AdminCategorieAdd")
public class servletAdminCategorieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String libelle = request.getParameter("libelle");
		
		Categorie categorie = new Categorie(libelle);
		
		CategorieManager.getInstance().ajouter(categorie);
		
		response.sendRedirect("Admin");
	}

}