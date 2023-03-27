package fr.enchere.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.enchere.bll.ArticleVenduManager;
import fr.enchere.bll.RetraitManager;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjoutVente
 */
@WebServlet("/AjoutVente")
public class ServletAjoutVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateDuJour = sdf.format(new Date());
		System.out.println(dateDuJour);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
        session.getAttribute("userConnected");
        Utilisateur u = (Utilisateur) session.getAttribute("userConnected");
		
		String nom = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String img = request.getParameter("image");
		int prix = Integer.parseInt(request.getParameter("prix"));
		String etatVente = "etat vente";
        		
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = sdf.parse(dateDebut);
			dateDebut = sdf2.format(date);

			date = sdf.parse(dateFin);
			dateFin = sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	    int noUtilisateur = u.getNoUtilisateur();
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		
		ArticleVendu articleVendu = new ArticleVendu (nom, description, img, dateDebut, dateFin, prix, prix, etatVente, noUtilisateur, categorie);
		ArticleVenduManager.ajouter(articleVendu);
		
		System.out.println(articleVendu.toString());
		
		String rue = request.getParameter("rue");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		String ville = request.getParameter("ville");
		
		Retrait retrait = new Retrait(rue, codePostal, ville, articleVendu.getNoArticle());
		RetraitManager.ajouterRetrait(retrait);
		
		response.sendRedirect("Accueil");
	}
}