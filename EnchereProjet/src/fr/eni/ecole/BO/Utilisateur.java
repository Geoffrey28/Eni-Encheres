package fr.eni.ecole.BO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;

import javax.management.relation.RelationServiceNotRegisteredException;

public class Utilisateur extends Personne 
{
	private String password;
	private LocalDate dateNaissance;
	private Role role;
	
	
	/**
	 * 
	 */
	public Utilisateur() 
	{
		
	}
	/**
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 * @param dateNaissance
	 * @throws Exception 
	 */
	public Utilisateur(String nom, String prenom, String email, String password, LocalDate dateNaissance,Adresse adresse,Role role,long numSecu) 
	{
	super(nom,prenom,email,adresse,numSecu);
		try {
			this.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dateNaissance = dateNaissance;
		this.setRole(role);
		
	}
	
	
	public Utilisateur(int id,String nom, String prenom, String email, String password, LocalDate dateNaissance,Adresse adresse,Role role,long numSecu) 
	{
	super(id,nom,prenom,email,adresse,numSecu);
		try {
			this.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dateNaissance = dateNaissance;
		this.setRole(role);
		
	}


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
			this.password = password;//hashPwd(password);
		}
		else
		{
			throw new Exception("mot de passe invalide");
		}
	}
	/**
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public int getAge()
	{
		LocalDate today;
		int age;
		today=LocalDate.now();
		age=Period.between(dateNaissance,today).getYears();
		return age;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) 
	{
			this.role=role;
			if(role!=null)
			{
				role.ajoutUtilisateur(this);
			}
			else
			{
				if (this.role!=null)
				{
					role.removeUtilisateur(this);
				}
			}
				
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Utilisateur ["+ super.toString() + ", password=" + password
				+ ", dateNaissance=" + dateNaissance + ", role=" + (role!=null?role.getNom():"rien") + "]";
	}
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	public static String hashPwd(String pwd)
	{
		MessageDigest md=null;
		StringBuffer sb=new StringBuffer();
		byte[] reponse;
		try {
			md=MessageDigest.getInstance("SHA");
			reponse=md.digest(pwd.getBytes());
			for(int i:reponse)
			{
				sb.append((Integer.toString((i&0xff)+0x100, 16).substring(1)));
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	

}
