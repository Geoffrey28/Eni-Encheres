package fr.eni.ecole.BO;

public abstract  class Personne implements Affichable
{
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private Adresse adresse;
	private long numSecu;
	
	
	
	/**
	 * 
	 */
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 */
	public Personne(String nom, String prenom, String email, Adresse adresse,long numsecu) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.numSecu=numsecu;
	}
	
	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 */
	public Personne(int id,String nom, String prenom, String email, Adresse adresse,long numsecu) {
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.numSecu=numsecu;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the numSecu
	 */
	public long getNumSecu() {
		return numSecu;
	}



	/**
	 * @param numSecu the numSecu to set
	 */
	public void setNumSecu(long numSecu) {
		this.numSecu = numSecu;
	}



	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Personne [id="+id +"nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + " numsecu="+numSecu+"]";
	}
	

	

}
