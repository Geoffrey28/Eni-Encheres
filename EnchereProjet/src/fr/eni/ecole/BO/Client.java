package fr.eni.ecole.BO;

import java.util.Comparator;

public class Client extends Personne implements Comparable<Client>,Comparator<Client>
{

	private int numero;
		

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 * @param numero
	 */
	public Client(String nom, String prenom, String email, Adresse adresse,int numero,long numSecu) {
		super(nom, prenom, email, adresse,numSecu);
		this.setNumero(numero);
	
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [numero=" + numero + "," + super.toString() + "]";
	}

	@Override
	public void afficher() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Client o) 
	{
		if(this.numero>o.numero)
			return 1;
		if(this.numero<o.numero)
			return -1;
		return 0;
	}

	@Override
	public int compare(Client o1, Client o2) 
	{
			return o1.getNom().compareTo(o2.getNom());
	}
	
	
	

}
