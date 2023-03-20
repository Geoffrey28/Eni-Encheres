package fr.kiloutou.bo;

public class Voiture extends Vehicule {
	
	private String motorisation;
	private int nbrPortes;
	
	/**
	 * 
	 */
	public Voiture() {
	}

	/**
	 * @param motorisation
	 * @param nbrPortes
	 */
	public Voiture(String modele, String marque, String immatriculation, int puissance, 
				String statut, int prixLocation, String motorisation, int nbrPortes) {
		super(modele, marque, immatriculation, puissance, statut, prixLocation);
		this.motorisation = motorisation;
		this.nbrPortes = nbrPortes;
	}
	
	public Voiture(int id, String modele, String marque, String immatriculation, int puissance, 
				String statut, int prixLocation, String motorisation, int nbrPortes) {
		super(id, modele, marque, immatriculation, puissance, statut, prixLocation);
		this.motorisation = motorisation;
		this.nbrPortes = nbrPortes;
	}

	/**
	 * @return the motorisation
	 */
	public String getMotorisation() {
		return motorisation;
	}

	/**
	 * @param motorisation the motorisation to set
	 */
	public void setMotorisation(String motorisation) {
		this.motorisation = motorisation;
	}

	/**
	 * @return the nbrPortes
	 */
	public int getNbrPortes() {
		return nbrPortes;
	}

	/**
	 * @param nbrPortes the nbrPortes to set
	 */
	public void setNbrPortes(int nbrPortes) {
		this.nbrPortes = nbrPortes;
	}
	
	public String toString() {
		return "Voiture [" + super.toString() + ", motorisation=" + motorisation + ", nbrPortes= " + nbrPortes + "]";
	}
	
	
}
