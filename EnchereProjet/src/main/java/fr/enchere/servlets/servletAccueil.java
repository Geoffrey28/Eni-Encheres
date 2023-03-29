package fr.enchere.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.enchere.bll.ArticleVenduManager;
import fr.enchere.bll.CategorieManager;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Utilisateur;

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
		
		int countArticle = ArticleVenduManager.getInstance().countArticles();
		
		List<Categorie> lstCategorie;
		lstCategorie = CategorieManager.getInstance().afficherListe();
		request.setAttribute("listeCategorie", lstCategorie);
		
		request.setAttribute("nbPages", (int) Math.ceil(countArticle / 8.0));
		
		request.setAttribute("page", 1);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("article-name");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int type = Integer.parseInt(request.getParameter("achat-vente"));
		int filterType = Integer.parseInt(request.getParameter("achat-vente"));
		int[] filterValue;
		filterValue = new int[3];
		
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		HttpSession session=request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("userConnected");
		
		if (filterType == 0) {
			
			if(request.getParameter("achat-1") != null) {
				filterValue[0] = 1;
			} else {
				filterValue[0] = 0;
			}
			
			if(request.getParameter("achat-2") != null) {
				filterValue[1] = 1;
			} else {
				filterValue[1] = 0;
			}
			
			if(request.getParameter("achat-3") != null) {
				filterValue[2] = 1;
			} else {
				filterValue[2] = 0;
			}
			
		} else {
			
			if(request.getParameter("vente-1") != null) {
				filterValue[0] = 1;
			} else {
				filterValue[0] = 0;
			}
			
			if(request.getParameter("vente-2") != null) {
				filterValue[1] = 1;
			} else {
				filterValue[1] = 0;
			}
			
			if(request.getParameter("vente-3") != null) {
				filterValue[2] = 1;
			} else {
				filterValue[2] = 0;
			}
			
		}
		
		request.setAttribute("filterType", filterType);
		request.setAttribute("filterValue", filterValue);
		
		List<ArticleVendu> lstArticleVendu;
		int nbArticles = ArticleVenduManager.getInstance().afficherListeWithFilter(name, categorie, type, filterType, filterValue, u, 0).size();
		
		if (nbArticles <= 8) {
			page = 1;
		}
		
		lstArticleVendu = ArticleVenduManager.getInstance().afficherListeWithFilter(name, categorie, type, filterType, filterValue, u, page);
		request.setAttribute("listeArticleVendu", lstArticleVendu);
		
		request.setAttribute("nbPages", (int) Math.ceil(nbArticles / 8.0));
		request.setAttribute("page", page);
		
		List<Categorie> lstCategorie;
		lstCategorie = CategorieManager.getInstance().afficherListe();
		request.setAttribute("listeCategorie", lstCategorie);
		
		request.setAttribute("categorie", categorie);
		request.setAttribute("name", name);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
