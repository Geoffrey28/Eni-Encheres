package fr.enchere.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.enchere.bll.ArticleVenduManager;
import fr.enchere.bll.CategorieManager;
import fr.enchere.bll.EnchereManager;
import fr.enchere.bll.RetraitManager;
import fr.enchere.bll.UtilisateurManager;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Enchere;
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
			Retrait r = RetraitManager.getInstance().getRetraitById(id);
			Utilisateur u = UtilisateurManager.getInstance().showById(a.getNoUtilisateur());
			Categorie c = CategorieManager.getInstance().selectById(a.getNoCategorie());
			Enchere e = EnchereManager.getInstance().getBestEnchere(a.getNoArticle());
			
			if (e != null) {
				Utilisateur encherisseur = UtilisateurManager.getInstance().showById(e.getNoUtilisateur());
				request.setAttribute("encherisseur", encherisseur);	
			}
			
			ArticleVenduManager.getInstance().miseAJourEtat(a);
			
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
	    String date = sdf.format(new Date()).toString();
		int montant = Integer.parseInt(request.getParameter("montant"));
		Utilisateur userConnected = (Utilisateur) request.getSession().getAttribute("userConnected");
		int noUser = userConnected.getNoUtilisateur();
		int noArticle = Integer.parseInt(request.getParameter("id"));
		Enchere e = EnchereManager.getInstance().getBestEnchere(noArticle);

		Boolean checkCreditUser = false;
		Boolean checkMontantEnchere = false;
		
		if (userConnected.getCredit() >= montant) {
			UtilisateurManager.getInstance().enleverCredit(userConnected, montant);
			UtilisateurManager.getInstance().ajouterCredit(noArticle);
			
			Enchere enchere = new Enchere(date, montant, noUser, noArticle);
			EnchereManager.getInstance().ajouter(enchere);
			
			ArticleVenduManager.getInstance().miseAJourPrix(noArticle);
		} else {
			checkCreditUser = true;
			request.setAttribute("checkCreditUser", checkCreditUser);
		}
		doGet(request, response);
	}

}
