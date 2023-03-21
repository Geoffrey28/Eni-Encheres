package fr.kiloutou.bll;

import java.util.List;

import fr.kiloutou.bo.ArticleVendu;
import fr.kiloutou.bo.Utilisateur;
import fr.kiloutou.dal.UtilisateurDAO;
import fr.kiloutou.dal.ArticleVenduDAO;
import fr.kiloutou.dal.DAOFactory;

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
	
	public void supprimer(int id) {
		articleVenduDAO.deleteById(id);
	}

}
