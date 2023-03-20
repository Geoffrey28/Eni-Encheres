package fr.eni.ecole.controler.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.BO.Adresse;
import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.Bll.UtilisateurManager;
import fr.eni.ecole.DAL.RoleDao;

/**
 * Servlet implementation class ModifierUtilisateur
 */
@WebServlet("/modifier")
public class ModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id;
		Utilisateur u;
		id=Integer.parseInt(request.getParameter("id"));
		u=UtilisateurManager.getInstance().findById(id);
		request.setAttribute("user", u);
		request.setAttribute("roles", new RoleDao().selectAll());
		getServletContext().getRequestDispatcher("/WEB-INF/modifier.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utilisateur u;
		try {
			u=new Utilisateur(request.getParameter("nom"),
					request.getParameter("prenom"), 
					request.getParameter("email"),
					UtilisateurManager.getInstance().findById(Integer.parseInt(request.getParameter("id"))).getPassword(),
					LocalDate.of(2000, 12, 25), 
						new Adresse(Integer.parseInt(request.getParameter("numero")),
								     request.getParameter("rue"),
									 Integer.parseInt(request.getParameter("codePostal")),
									 request.getParameter("ville")),
						new RoleDao().getRoleById(Integer.parseInt(request.getParameter("role"))), 12332234l);
						u.setId(Integer.parseInt(request.getParameter("id")));
			UtilisateurManager.getInstance().modifier(u);
			response.sendRedirect("listerutilisateurs");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
