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
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class servletAccueil
 */
@WebServlet("/EnchereDetail")
public class servletEnchereDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArticleVendu a = ArticleVenduManager.getInstance().show(id);
			Retrait r = ArticleVenduManager.getInstance().getRetraitById(id);
			Utilisateur u = UtilisateurManager.getInstance().showById(a.getNoUtilisateur());
			Categorie c = CategorieManager.getInstance().selectById(a.getNoCategorie());
			
			request.setAttribute("user", u);
			
			request.setAttribute("categorie", c);
			request.setAttribute("article", a);
			request.setAttribute("user", u);
			request.setAttribute("retrait", r);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/enchereDetail.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
