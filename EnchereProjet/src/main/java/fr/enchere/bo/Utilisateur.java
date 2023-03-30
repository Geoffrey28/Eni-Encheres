package fr.enchere.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.enchere.bo.exceptions.CodePostalException;
import fr.enchere.dal.UtilisateurDAO;

public class Utilisateur {
	
	private int NoUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String motDePasse;
	private String email;
	private String telephone;
	private String rue;
	private String ville;
	private String codePostal;
	private int credit;
	private boolean administrateur;
	private boolean disabled;
	
	public Utilisateur() {
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String motDePasse, String email, String telephone,
			String rue, String ville, String codePostal) throws CodePostalException {
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
			String telephone, String rue, String ville, String codePostal) throws CodePostalException {
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
			String telephone, String rue, String ville, String codePostal, int credit, boolean administrateur) throws CodePostalException {
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
	
	
	
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String motDePasse, String email,
			String telephone, String rue, String ville, String codePostal, int credit, boolean administrateur,
			boolean disabled) {
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
		this.disabled = disabled;
	}



	public boolean isDisabled() {
		return disabled;
	}



	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String ville, String codePostal) throws CodePostalException {
		super();
		NoUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
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
		this.motDePasse = hashPwd(motDePasse);
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) throws CodePostalException {
		if(Integer.parseInt(codePostal) > 10000 && Integer.parseInt(codePostal) < 98999) {
			this.codePostal = codePostal;
		} else {
			throw new CodePostalException("Le code postal doit être composé de 5 chiffres et être compris entre 10000 et 98999.");
		}
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



	public static String hashPwd(String pwd) {
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
