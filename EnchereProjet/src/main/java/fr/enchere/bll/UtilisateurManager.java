package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private static UtilisateurManager instance = null;
	private static UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public static void ajouter(Utilisateur utilisateur) {
		utilisateur.setNom(utilisateur.getNom().toUpperCase());
		try {
			utilisateurDAO.insert(utilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Utilisateur login(String pseudo,String motdepasse) {
		return utilisateurDAO.login(pseudo, motdepasse);
	}
	
	public void deleteById(int id) {
		utilisateurDAO.delete(id);
	}

}
