package fr.enchere.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import fr.enchere.bll.RetraitManager;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierVente
 */
@WebServlet("/ModifierVente")
public class ServletModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArticleVendu a = ArticleVenduManager.getInstance().show(id);
			Retrait r = RetraitManager.getInstance().getRetraitById(id);
			List<Categorie> categories = CategorieManager.getInstance().afficherListe();
			
			String dateDebut = a.getDateDebutEncheres();
			String dateFin = a.getDateFinEncheres();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(dateDebut);
				dateDebut = sdf2.format(date);

				date = sdf.parse(dateFin);
				dateFin = sdf2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(dateDebut);
			
			
			request.setAttribute("article", a);
			request.setAttribute("categories", categories);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("retrait", r);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierVente.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
        session.getAttribute("userConnected");
        Utilisateur u = (Utilisateur) session.getAttribute("userConnected");
        
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		String nom = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String img = request.getParameter("image");
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		int miseAPrix = Integer.parseInt(request.getParameter("prix"));
		String etatVente = "etat vente";
		int noUtilisateur = u.getNoUtilisateur();
		int categorie = Integer.parseInt(request.getParameter("categorie"));

		ArticleVendu article = new ArticleVendu(noArticle, nom, description, img, 
				dateDebut, dateFin, miseAPrix, miseAPrix, etatVente, noUtilisateur, categorie);
		article.setDateDebutEncheres(dateDebut);
		article.setDateFinEncheres(dateFin);
				
		ArticleVenduManager.getInstance().miseAJourArticle(article);
		
		String rue = request.getParameter("rue");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		String ville = request.getParameter("ville");
		
		Retrait retrait = new Retrait(rue, codePostal, ville, noArticle);
		
		RetraitManager.getInstance().miseAjourRetrait(retrait);
		
		System.out.println(article.toString() + " ///// " + retrait.toString());
		
		response.sendRedirect("EnchereDetail?id=" + noArticle);
	}

}
