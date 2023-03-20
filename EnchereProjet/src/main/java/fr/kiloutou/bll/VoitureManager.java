package fr.kiloutou.bll;

import java.util.List;

import fr.kiloutou.bo.Voiture;
import fr.kiloutou.dal.DAOFactory;
import fr.kiloutou.dal.VoitureDAO;

public class VoitureManager {
	
	private static VoitureManager instance = null;
	private VoitureDAO voitureDAO;
	
	private VoitureManager() {
		this.voitureDAO = DAOFactory.getVoitureDAO();
	}
	
	public static VoitureManager getInstance() {
		if (instance == null) {
			instance = new VoitureManager();
		}
		return instance;
	}
	
	public void ajouter(Voiture voiture) {
		voitureDAO.insert(voiture);
	}
	
	public List<Voiture> afficherListe() {
		return voitureDAO.selectAll();
	}
	
	public void supprimer(int id) {
		voitureDAO.deleteById(id);
	}
}
