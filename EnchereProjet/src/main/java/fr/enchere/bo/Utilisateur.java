package fr.enchere.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.enchere.dal.UtilisateurDAO;

public class Utilisateur {
	
	private int NoUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String motDePasse;
	private String email;
	private int telephone;
	private String rue;
	private String ville;
	private int codePostal;
	private int credit;
	private boolean administrateur;
	
	
	public Utilisateur(String pseudo, String nom, String prenom, String motDePasse, String email, int telephone,
			String rue, String ville, int codePostal) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}



	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String motDePasse, String email,
			int telephone, String rue, String ville, int codePostal) {
		super();
		NoUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String motDePasse, String email,
			int telephone, String rue, String ville, int codePostal, int credit, boolean administrateur) {
		super();
		NoUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	
	
	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public int getNoUtilisateur() {
		return NoUtilisateur;
	}



	public void setNoUtilisateur(int noUtilisateur) {
		NoUtilisateur = noUtilisateur;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getMotDePasse() {
		return motDePasse;
	}



	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getTelephone() {
		return telephone;
	}



	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public int getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}



	public int getCredit() {
		return credit;
	}



	public void setCredit(int credit) {
		this.credit = credit;
	}



	public boolean isAdministrateur() {
		return administrateur;
	}



	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
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
