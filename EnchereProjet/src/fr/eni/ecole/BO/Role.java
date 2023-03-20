package fr.eni.ecole.BO;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.DAL.UtilisateurDao;

public class Role implements Comparable<Role>
{
	private int id;
	private String nom;
	private List<Utilisateur> users;
	/**
	 * 
	 */
	public Role() 
	{
		//users=new ArrayList<Utilisateur>();
	}
	

	/**
	 * @param nom
	 */
	public Role(String nom) 
	{
		this();
		this.nom = nom;
	}
	
	public Role(int id,String nom) 
	{
		this(nom);
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
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Utilisateur> getUsers() 
	{
		if(users==null)
		{
			users=new UtilisateurDao().selectByRole(id);
		}
		return users;
	}


	public void ajoutUtilisateur(Utilisateur u)
	{
		if(users!=null && ! users.contains(u))
		{
		users.add(u);
		}
		if (u.getRole()!=this)
		{
			u.setRole(this);
		}
		
	}
	
	public void removeUtilisateur(Utilisateur u)
	{
		users.remove(u);
		u.setRole(null);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nom;
	}


	@Override
	public int compareTo(Role o) 
	{
		return nom.compareTo(o.getNom());
	}


	
	
	
}
