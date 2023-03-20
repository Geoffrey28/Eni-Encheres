package fr.kiloutou.bll;

import fr.kiloutou.bo.Client;
import fr.kiloutou.dal.ClientDAO;
import fr.kiloutou.dal.DAOFactory;

public class ClientManager {
	
	private static ClientManager instance = null;
	private ClientDAO clientDAO;
	
	private ClientManager() {
		this.clientDAO = DAOFactory.getClientDAO();
	}
	
	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}
	
	public void ajouter(Client client) {
		client.setNom(client.getNom().toUpperCase());
		clientDAO.insert(client);
	}
	
	public Client login(String email,String motdepasse) {
		return ClientDAO.login(email, Client.hashPwd(motdepasse));
	}

}
