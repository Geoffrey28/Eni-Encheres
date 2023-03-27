package fr.enchere.bo;

import fr.enchere.bo.exceptions.CodePostalException;

public class Retrait {
	
	private String rue;
	private int codePostal;
	private String ville;
	private int noArticle;
	
	public Retrait() {
	}

	public Retrait(String rue, int codePostal, String ville, int noArticle) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.noArticle = noArticle;
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

	public void setCodePostal(int codePostal) throws CodePostalException {
		if(codePostal > 1000 && codePostal < 98999) {
			this.codePostal = codePostal;
		}
		else {
			throw new CodePostalException("erreur sur le code postal");
		}
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", Ville=" + ville + ", noArticle" + noArticle + "]";
	}
	
}
