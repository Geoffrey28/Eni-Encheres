package fr.kiloutou.bo;

import fr.kiloutou.bo.exceptions.CodePostalException;
import fr.kiloutou.bo.exceptions.NumeroException;

public class Adresse {
	private int id;
	private int numero;
	private String rue;
	private int codePostal;
	private String Ville;
	
	/**
	 *  
	 */
	public Adresse() {	
	}
	
	 /**
	 * @param numero
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @throws CodePostalException 
	 * @throws NumeroException 
	 */
	public Adresse(int numero, String rue, int codePostal, String ville) throws CodePostalException, NumeroException {
		this.setNumero(numero);
		this.rue = rue;
		this.setCodePostal(codePostal);
		Ville = ville;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * @param the numero
	 * @throws NumeroException 
	 */
	public void setNumero(int numero) throws NumeroException {
		if(numero > 0)
		{
			this.numero = numero;
		}
		else
		{
			throw new NumeroException("Le numéro doit être supérieur à 0.");
		}
	}
	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * @param the rue
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param the codePostal
	 */
	public void setCodePostal(int codePostal) throws CodePostalException {
		if(codePostal > 1000 && codePostal < 98999)
		{
			this.codePostal = codePostal;
		}
		else
		{
			throw new CodePostalException("erreur sur le code postal");
		}
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return Ville;
	}
	
	/**
	 * @param the ville
	 */
	public void setVille(String ville) {
		Ville = ville;
	}
	
	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", rue=" + rue + ", codePostal=" + codePostal + ", Ville=" + Ville + "]";
	}
}
