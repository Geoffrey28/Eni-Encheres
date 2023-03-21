package fr.kiloutou.dal;

public abstract class DAOFactory {

	public static ClientDAO getClientDAO() {
		return new ClientDAO();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAO();
	}
	
	public static VoitureDAO getVoitureDAO() {
		return new VoitureDAO();
	}
}
