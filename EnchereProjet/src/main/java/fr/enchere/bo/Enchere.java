package fr.enchere.bo;

public class Enchere {
	
	private String dateEnchere;
	private int montant;
	private int noUtilisateur;
	private int noArticle;
	
	public Enchere() {
	}

	public Enchere(String dateEnchere, int montant, int noUtilisateur, int noArticle) {
		this.dateEnchere = dateEnchere;
		this.montant = montant;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
	}

	public String getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(String dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
}
