package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.ArticleVendu;
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
	
	public void ajouter(Utilisateur utilisateur) {
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
	
	public void disable(int id) {
		utilisateurDAO.disable(id);
	}
	
	public void enable(int id) {
		utilisateurDAO.enable(id);
	}
	
	public List<Utilisateur> afficherListe() {
		return utilisateurDAO.selectAll();
	}
	
	public Utilisateur showByPseudo(String pseudo) {
		return utilisateurDAO.showByPseudo(pseudo);
	}
	
	public Utilisateur showById(int id) {
		return utilisateurDAO.showById(id);
	}

}

