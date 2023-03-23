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
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;

/**
 * Servlet implementation class servletAccueil
 */
@WebServlet("/Accueil")
public class servletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ArticleVendu> lstArticleVendu;
		lstArticleVendu = ArticleVenduManager.getInstance().afficherListe();
		request.setAttribute("listeArticleVendu", lstArticleVendu);
		
		List<Categorie> lstCategorie;
		lstCategorie = CategorieManager.getInstance().afficherListe();
		request.setAttribute("listeCategorie", lstCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int type = Integer.parseInt(request.getParameter("achat-vente"));
		int checked = Integer.parseInt(request.getParameter("checked-info"));
		
		List<ArticleVendu> lstArticleVendu;
		lstArticleVendu = ArticleVenduManager.getInstance().afficherListeWithFilter(name, categorie, type, checked);
		request.setAttribute("listeArticleVendu", lstArticleVendu);
		
		List<Categorie> lstCategorie;
		lstCategorie = CategorieManager.getInstance().afficherListe();
		request.setAttribute("listeCategorie", lstCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
