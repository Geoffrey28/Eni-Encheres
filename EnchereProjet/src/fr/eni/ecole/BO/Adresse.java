package fr.eni.ecole.BO;

import fr.eni.ecole.Exceptions.CodePostalException;
import fr.eni.ecole.Exceptions.NumeroException;

public class Adresse implements Affichable
{
	private int id;
	private int numero;
	private String rue;
	private int codePostal;
	private String Ville;
	/**
	 *  
	 */
	public Adresse() 
	{
		// TODO Auto-generated constructor stub
	}
	
	 /**
	 * @param numero
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @throws CodePostalException 
	 * @throws NumeroException 
	 */
	public Adresse(int numero, String rue, int codePostal, String ville) throws CodePostalException, NumeroException
	{
		this.setNumero(numero);
		this.rue = rue;
		this.setCodePostal(codePostal);
		Ville = ville;
	}




	public int getId() {
		return id;
	}

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
	 * @param numero the numero to set
	 * @throws NumeroException 
	 */
	public void setNumero(int numero) throws NumeroException 
	{
		if(numero>0)
		{
			this.numero = numero;
		}
		else
		{
			throw new NumeroException("erreur sur le numero");
		}
	}
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * @param rue the rue to set
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
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) throws CodePostalException
	{
		if(codePostal>1000 && codePostal<98999)
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
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		Ville = ville;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", rue=" + rue + ", codePostal=" + codePostal + ", Ville=" + Ville + "]";
	}




	@Override
	public void afficher() 
	{
		
		
	}
	
	

}
