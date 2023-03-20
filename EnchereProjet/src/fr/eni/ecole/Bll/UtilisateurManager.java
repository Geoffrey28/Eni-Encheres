package fr.eni.ecole.Bll;



import java.util.List;

import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.DAL.UtilisateurDao;



public class UtilisateurManager {

	private static UtilisateurManager instance = null;
	private UtilisateurDao utilisateurDAO;

	private UtilisateurManager(UtilisateurDao utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	// SINGLETON
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager(new UtilisateurDao());
		}
		return instance;
	}
	public Utilisateur login(String email,String pwd)
	{
		return utilisateurDAO.login(email,Utilisateur.hashPwd(pwd));
	}
	
	public void save(Utilisateur utilisateur) {
		utilisateur.setNom(utilisateur.getNom().toUpperCase());
		utilisateurDAO.insert(utilisateur);
	}

	
	public void deleteById(int id) {
		utilisateurDAO.delete(id);
	}
	
	public void modifier(Utilisateur c) {
		utilisateurDAO.update(c);
	}

	public List<Utilisateur> findAll(String field,String sens) {
		return utilisateurDAO.selectAll();

	}
	
	public Utilisateur findById(int id) {
		return utilisateurDAO.selectById(id);

	}

	public boolean verificationEmail(String email) {
		if (email.contains("@")) {
			return true;
		}

		return false;
	}

	public boolean verificationPassword(String password) {
		char mdp; 
		boolean numberOk = false; 
		boolean letterOk = false;
		
		for (int i = 0; i< password.length(); i++) {
			mdp = password.charAt(i);
			if (Character.isDigit(mdp)) {
				numberOk = true;
			}else if (Character.isLetter(mdp)) {
				letterOk=true;
			}
			
		}

		return numberOk && letterOk;
}


	
	
}
