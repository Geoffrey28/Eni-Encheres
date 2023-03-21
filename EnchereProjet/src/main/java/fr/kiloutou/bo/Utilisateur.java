package fr.kiloutou.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilisateur {
	
	private int NoUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String motDePasse;
	
	/**
	 * 
	 */
	public Utilisateur() {
	}

	/**
	 * @param nom
	 * @param prenom
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String motDePasse) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
	}
	
	public Utilisateur(int NoUtilisateur, String pseudo, String nom, String prenom, String motDePasse) {
		this.NoUtilisateur = NoUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return NoUtilisateur;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.NoUtilisateur = id;
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
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() 
	{
		return motDePasse;
	}
	
	/**
	 * @param password the password to set
	 * @throws Exception 
	 */
	public void setPassword(String password) throws Exception 
	{
		if (password.length()>4)
		{
			this.motDePasse = hashPwd(password);
		}
		else
		{
			throw new Exception("mot de passe invalide");
		}
	}
	
	@Override
	public String toString() {
		return "Personne [id="+NoUtilisateur + "pseudo=" + pseudo + "nom=" + nom + ", prenom=" + prenom + "]";
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
