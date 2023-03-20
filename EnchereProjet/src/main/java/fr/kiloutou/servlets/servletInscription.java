package fr.kiloutou.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.kiloutou.bll.ClientManager;
import fr.kiloutou.bo.Adresse;
import fr.kiloutou.bo.Client;
import fr.kiloutou.bo.exceptions.CodePostalException;
import fr.kiloutou.bo.exceptions.NumeroException;

@WebServlet("/Inscription")
public class servletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// CLIENT
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String motdepasse = request.getParameter("motdepasse");
		String typePermis = request.getParameter("typePermis");
		// ADRESSE
		int numero = Integer.parseInt(request.getParameter("numero"));
		String rue = request.getParameter("rue");
		int codePostal = Integer.parseInt(request.getParameter("codepostal"));
		String ville = request.getParameter("ville");
		Adresse adresse = null;
		try {
			adresse = new Adresse(numero, rue, codePostal, ville);
		} catch (CodePostalException e) {
			e.getMessage();
		} catch (NumeroException e) {
			e.getMessage();
		}
		
		ClientManager clientManager = ClientManager.getInstance();
		Client client = new Client(nom, prenom, telephone, email, motdepasse, adresse, typePermis);
		client.toString();
		clientManager.ajouter(client);
		request.setAttribute("client", client);
		response.sendRedirect("Connection");
	}

}
