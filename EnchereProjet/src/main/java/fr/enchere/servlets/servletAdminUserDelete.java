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


@WebServlet("/AdminUserDelete")
public class servletAdminUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		
		UtilisateurManager.getInstance().deleteById(noUtilisateur);
		
		response.sendRedirect("Admin");
	}

}