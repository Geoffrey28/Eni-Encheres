package fr.eni.ecole.controler.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.Bll.UtilisateurManager;

/**
 * Servlet implementation class ListerUtilisateurs
 */
@WebServlet("/listerutilisateurs")
public class ListerUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession ses;
		ses=request.getSession();
		if(ses.getAttribute("userConnected")!=null)
		{
		List<Utilisateur> lst;
		lst=UtilisateurManager.getInstance().findAll(null, null);
		request.setAttribute("liste", lst);
		getServletContext().getRequestDispatcher("/WEB-INF/lister.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("login");
		}
	}

}
