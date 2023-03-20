package fr.eni.ecole.controler.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.BO.Adresse;
import fr.eni.ecole.BO.Role;
import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.Bll.UtilisateurManager;
import fr.eni.ecole.DAL.RoleDao;
import fr.eni.ecole.Exceptions.CodePostalException;
import fr.eni.ecole.Exceptions.NumeroException;

/**
 * Servlet implementation class AjouterUtilisateur
 */
@WebServlet("/ajouterutilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("roles", new RoleDao().selectAll());
		getServletContext().getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request, response);
		
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
					request.getParameter("motDePasse"),
					LocalDate.of(2000, 12, 25), 
						new Adresse(Integer.parseInt(request.getParameter("numero")),
								     request.getParameter("rue"),
									 Integer.parseInt(request.getParameter("codePostal")),
									 request.getParameter("ville")),
						new RoleDao().getRoleById(Integer.parseInt(request.getParameter("role"))), 12332234l);
			UtilisateurManager.getInstance().save(u);
			
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CodePostalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
		
	}

}
