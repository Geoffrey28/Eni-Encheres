package fr.enchere.bll;

import fr.enchere.bo.Retrait;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.RetraitDAO;

public class RetraitManager {
	private static RetraitManager instance = null;
	private static RetraitDAO retraitDAO;
	
	private RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public static RetraitManager getInstance() {
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	public Retrait getRetraitById(int noArticle) {
		return retraitDAO.selectByNoArticle(noArticle);
	}
	
	public void ajouterRetrait(Retrait retrait) {
		try {
			retraitDAO.insert(retrait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void miseAjourRetrait(Retrait retrait) {
		retraitDAO.update(retrait);
	}
	
	public void supprimer(int id) {
		retraitDAO.deleteById(id);
	}
}
