package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduDAO;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.RetraitDAO;
import fr.enchere.dal.UtilisateurDAO;

public class ArticleVenduManager {
	
	private static ArticleVenduManager instance = null;
	private static ArticleVenduDAO articleVenduDAO;
	private static RetraitDAO retraitDAO;
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
		this.retraitDAO = DAOFactory.getRetraitDAO();
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
			e.printStackTrace();
		}
	}

	public List<ArticleVendu> afficherListe() {
		return articleVenduDAO.selectAll();
	}
	
	public List<ArticleVendu> afficherListeWithFilter(String name, int categorie, int type, int checked) {
		return articleVenduDAO.selectAllWithFilter(name,categorie,type,checked);
	}
	
	public ArticleVendu show(int id) {
		return ArticleVenduDAO.show(id);
	}
	
	public void supprimer(int id) {
		articleVenduDAO.deleteById(id);
	}
	
	public Retrait getRetraitById(int noArticle) {
		return retraitDAO.selectByNoArticle(noArticle);
	}
	
	public static void ajouterRetrait(Retrait retrait) {
		try {
			retraitDAO.insert(retrait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void miseAJourArticle(int id) {
		articleVenduDAO.updateById(id);
	}
	
	public void miseAJourPrix(int id) {
		articleVenduDAO.updatePrixVenteById(id);
	}

}
