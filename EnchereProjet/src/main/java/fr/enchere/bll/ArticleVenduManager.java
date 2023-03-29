package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduDAO;
import fr.enchere.dal.DAOFactory;

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
			e.printStackTrace();
		}
	}

	public List<ArticleVendu> afficherListe() {
		return articleVenduDAO.selectAll();
	}
	
	public List<ArticleVendu> afficherListeWithFilter(String name, int categorie, int type, int filterType, int filterValue[], Utilisateur u) {
		return articleVenduDAO.selectAllWithFilter(name,categorie,type,filterType,filterValue, u);
	}
	
	public ArticleVendu show(int id) {
		return ArticleVenduDAO.show(id);
	}
	
	public void supprimer(int id) {
		articleVenduDAO.deleteById(id);
	}
	
	public void miseAJourArticle(ArticleVendu article) {
		articleVenduDAO.update(article);
	}
	
	public void miseAJourPrix(int id) {
		articleVenduDAO.updatePrixVenteById(id);
	}
	
	public void miseAJourEtat(ArticleVendu a) {
		articleVenduDAO.updateEtatVente(a);
	}

}
