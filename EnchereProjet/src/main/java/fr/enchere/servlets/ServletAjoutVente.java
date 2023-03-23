package fr.enchere.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjoutVente
 */
@WebServlet("/AjoutVente")
public class ServletAjoutVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
        session.getAttribute("userConnected");
        Utilisateur u = (Utilisateur) session.getAttribute("userConnected");
       
		Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Converted String: " + strDate);
		
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		int codePostal = Integer.parseInt(request.getParameter("codepostal"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		String dateDebutEncheres = request.getParameter("dateDebut");
	    String dateFinEncheres = request.getParameter("dateFin");

	    Date dateDebut = null;
	    if(dateDebutEncheres != null && !dateDebutEncheres.isEmpty()) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            dateDebut = formatter.parse(dateDebutEncheres);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }

	    Date dateFin = null;
	    if(dateFinEncheres != null && !dateFinEncheres.isEmpty()) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            dateFin = formatter.parse(dateFinEncheres);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    
	    }
		ArticleVendu ArticleVendu = new ArticleVendu (nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, u.getNoUtilisateur(), categorie);
		ArticleVendu.toString();
		request.setAttribute("utilisateur", ArticleVendu);
	}
}