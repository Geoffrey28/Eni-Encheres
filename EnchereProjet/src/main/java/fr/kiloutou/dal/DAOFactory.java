package fr.kiloutou.dal;

public abstract class DAOFactory {

	public static ClientDAO getClientDAO() {
		return new ClientDAO();
	}
	
	public static VoitureDAO getVoitureDAO() {
		return new VoitureDAO();
	}
}
