package fr.kiloutou.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Personne {
	
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String password;
	private Adresse adresse;
	
	/**
	 * 
	 */
	public Personne() {
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 */
	public Personne(String nom, String prenom, String telephone, String email, String password, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		try {
			this.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.adresse = adresse;
	}
	
	public Personne(int id, String nom, String prenom, String telephone, String email, String password, Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		try {
			this.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.adresse = adresse;
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
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the password
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * @param password the password to set
	 * @throws Exception 
	 */
	public void setPassword(String password) throws Exception 
	{
		if (password.length()>4)
		{
			this.password = hashPwd(password);
		}
		else
		{
			throw new Exception("mot de passe invalide");
		}
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
	
	@Override
	public String toString() {
		return "Personne [id="+id +"nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + "]";
	}
	
	public static String hashPwd(String pwd)
	{
		MessageDigest md = null;
		StringBuffer sb = new StringBuffer();
		byte[] reponse;
		try {
			md = MessageDigest.getInstance("SHA-1");
			reponse = md.digest(pwd.getBytes());
			for(int i : reponse)
			{
				sb.append((Integer.toString((i&0xff)+0x100, 16).substring(1)));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
