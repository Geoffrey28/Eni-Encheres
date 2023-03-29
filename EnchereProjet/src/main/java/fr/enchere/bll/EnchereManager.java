package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.Enchere;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.EnchereDAO;

public class EnchereManager {
	
	private static EnchereManager instance = null;
	private EnchereDAO enchereDAO;
	
	private EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void ajouter(Enchere enchere) {
		try {
			enchereDAO.insert(enchere);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Enchere> showByNoArticle(int id) {
		return enchereDAO.selectAllByNoArticle(id);
	}
	
	public Enchere getBestEnchere(int id) {
		return enchereDAO.getBestEnchere(id);
	}
	
	public void supprimer(int noArticle) {
		enchereDAO.deleteByNoArticle(noArticle);
	}
}
