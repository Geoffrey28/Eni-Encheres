package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduDAO;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.UtilisateurDAO;

public class ArticleVenduManager {
	
	private static ArticleVenduManager instance = null;
	private static ArticleVenduDAO articleVenduDAO;
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	public static void ajouter(ArticleVendu articleVendu) {
		try {
			articleVenduDAO.insert(articleVendu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ArticleVendu> afficherListe() {
		return articleVenduDAO.selectAll();
	}
	
	public ArticleVendu show(int id) {
		return ArticleVenduDAO.show(id);
	}
	
	public void supprimer(int id) {
		articleVenduDAO.deleteById(id);
	}

}
