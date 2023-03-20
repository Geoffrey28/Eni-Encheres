package fr.kiloutou.bo;

public class Vehicule {
	
	private int id;
	private String modele;
	private String marque;
	private String immatriculation;
	private int puissance;
	private String statut;
	private int prixLocation;
	
	/**
	 * 
	 */
	public Vehicule() {
	}

	/**
	 * @param immatriculation
	 * @param puissance
	 * @param statut
	 */
	public Vehicule(String modele, String marque, String immatriculation, int puissance, String statut, int prixLocation) {
		this.modele = modele;
		this.marque = marque;
		this.immatriculation = immatriculation;
		this.puissance = puissance;
		this.statut = statut;
		this.prixLocation = prixLocation;
	}
	
	public Vehicule(int id, String modele, String marque, String immatriculation, int puissance, String statut, int prixLocation) {
		this.id = id;
		this.modele = modele;
		this.marque = marque;
		this.immatriculation = immatriculation;
		this.puissance = puissance;
		this.statut = statut;
		this.prixLocation = prixLocation;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @param immatriculation the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @return the puissance
	 */
	public int getPuissance() {
		return puissance;
	}

	/**
	 * @param puissance the puissance to set
	 */
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	/**
	 * @return the prixLocation
	 */
	public int getPrixLocation() {
		return prixLocation;
	}

	/**
	 * @param prixLocation the prixLocation to set
	 */
	public void setPrixLocation(int prixLocation) {
		this.prixLocation = prixLocation;
	}

	public String toString() {
		return "Vehicule [id= " + id + ", modele= " + modele + ", marque= " + marque + ", immatriculation= " + immatriculation +
				"puissance= " + puissance + ", statut= " + statut + "]";
	}
	
	
}
