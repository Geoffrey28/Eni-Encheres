package fr.eni.ecole.controler.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.Bll.UtilisateurManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email,password;
		Utilisateur u=null;
		email=request.getParameter("email");
		password=request.getParameter("password");
		HttpSession ses;
		ses=request.getSession();
		
		u=UtilisateurManager.getInstance().login(email, password);
		
		if(u!=null)
		{
			ses.setAttribute("userConnected", u);
			Cookie gato;
			gato=new Cookie("lastLogin", u.getEmail());
			gato.setMaxAge(60*60*24*7);
			response.addCookie(gato);
			response.sendRedirect("listerutilisateurs");
		}
		else
		{
			doGet(request, response);
		}
			
			
		
	}

}
