package fr.kiloutou.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.kiloutou.bll.ClientManager;
import fr.kiloutou.bo.Client;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/Connection")
public class servletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String email, motdepasse;
		Client c = null;
		Boolean check = false;
		email = request.getParameter("email");
		motdepasse = request.getParameter("motdepasse");
		HttpSession session;
		session = request.getSession();
		
		c = ClientManager.getInstance().login(email, motdepasse);

		if (c == null) {
			check = true;
		}
		session.setAttribute("check", check);
		if(c != null) {
			session.setAttribute("clientConnecte", c);
			Cookie connectionMemo;
			connectionMemo = new Cookie("lastLogin", c.getEmail());
			connectionMemo.setMaxAge(60*60*24*7);
			response.addCookie(connectionMemo);
			response.sendRedirect("Accueil");
		} else {
			doGet(request, response);
		}*/
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}

