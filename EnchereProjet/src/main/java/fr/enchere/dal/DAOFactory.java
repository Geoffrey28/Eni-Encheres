package fr.enchere.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAO();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAO();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAO();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAO();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAO();
	}
}
