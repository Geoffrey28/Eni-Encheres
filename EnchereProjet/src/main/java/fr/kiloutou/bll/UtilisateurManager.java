package fr.kiloutou.bll;

import fr.kiloutou.bo.Utilisateur;
import fr.kiloutou.dal.UtilisateurDAO;
import fr.kiloutou.dal.DAOFactory;

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
		return utilisateurDAO.login(pseudo, Utilisateur.hashPwd(motdepasse));
	}

}
