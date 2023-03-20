package fr.kiloutou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.kiloutou.bll.VoitureManager;
import fr.kiloutou.bo.Voiture;

@WebServlet("/ListeVehicules")
public class servletListeVehicules extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Voiture> lstVoitures;
		lstVoitures = VoitureManager.getInstance().afficherListe();
		
		request.setAttribute("listeVoitures", lstVoitures);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listeVehicules.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String modele = request.getParameter("modele");
		String marque = request.getParameter("marque");
		String immatriculation = request.getParameter("immatriculation");
		int puissance = Integer.parseInt(request.getParameter("puissance"));
		String statut = request.getParameter("statut");
		int prixLocation = Integer.parseInt(request.getParameter("prixLocation"));
		String motorisation = request.getParameter("motorisation");
		int nbrPortes = Integer.parseInt(request.getParameter("nbrPortes"));
		
		VoitureManager voitureManager = VoitureManager.getInstance();
		Voiture voiture = new Voiture(modele, marque, immatriculation, puissance, statut, prixLocation, motorisation, nbrPortes);
		voiture.toString();
		voitureManager.ajouter(voiture);
		request.setAttribute("voiture", voiture);
		doGet(request, response);
	}

}
