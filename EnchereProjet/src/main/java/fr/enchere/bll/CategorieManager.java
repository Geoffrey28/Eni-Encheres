package fr.enchere.bll;

import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduDAO;
import fr.enchere.dal.CategorieDAO;
import fr.enchere.dal.DAOFactory;
import fr.enchere.dal.UtilisateurDAO;

public class CategorieManager {
	
	private static CategorieManager instance = null;
	private static CategorieDAO categorieDAO;
	
	private CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public static void ajouter(Categorie categorie) {
		try {
			categorieDAO.insert(categorie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(Categorie categorie) {
		try {
			categorieDAO.update(categorie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Categorie> afficherListe() {
		return categorieDAO.selectAll();
	}
	
	public Categorie selectById(int id) {
		return CategorieDAO.selectById(id);
	}
	
	public void supprimer(int id) {
		categorieDAO.deleteById(id);
	}

}
