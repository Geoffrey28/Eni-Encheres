package fr.kiloutou.bo;

public class Client extends Personne {
	
	private String typePermis;
	
	/**
	 * 
	 */
	public Client() {
	}

	/**
	 * @param typePermis
	 * @param voitureLouees
	 */
	public Client(String nom, String prenom, String telephone, String email, String password, 
					Adresse adresse, String typePermis) {
		super(nom, prenom, telephone, email, password, adresse);
		this.typePermis = typePermis;
	}
	
	public Client(int id, String nom, String prenom, String telephone, String email, String password, 
					Adresse adresse, String typePermis) {
		super(id, nom, prenom, telephone, email, password, adresse);
		this.typePermis = typePermis;
	}

	/**
	 * @return the typePermis
	 */
	public String getTypePermis() {
		return typePermis;
	}

	/**
	 * @param typePermis the typePermis to set
	 */
	public void setTypePermis(String typePermis) {
		this.typePermis = typePermis;
	}
	
	public String toString() {
		return "Client [" + super.toString() + ", typePermis= " + typePermis + "]";
	}
}
