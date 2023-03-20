package fr.eni.ecole.controler;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.BO.Role;
import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.Bll.UtilisateurManager;
import fr.eni.ecole.DAL.RoleDao;
import fr.eni.ecole.Exceptions.CodePostalException;
import fr.eni.ecole.Exceptions.NumeroException;
import fr.eni.ecole.ihmSwing.FenetrePrincipale;

public class CtrlUtilisateur 
{
	List<Utilisateur> data;
	int index;
	FenetrePrincipale fenetre;
	boolean ajout;
	
	
	public CtrlUtilisateur(FenetrePrincipale f)
	{
		data=UtilisateurManager.getInstance().findAll("", "");
		fenetre=f;
		index=0;
		ajout=false;
		//afficherPremier();
	}
	public List<Role> getListeRole()
	{
		return new RoleDao().selectAll();
	}
	
	public void afficherPremier()
	{
		index=0;
		fenetre.afficherUtilisateur(data.get(index));
	}
	public void afficherPrecedent()
	{
		if(index>0)
		{
			index--;
		}
		fenetre.afficherUtilisateur(data.get(index));
	}
	public void afficherSuivant()
	{
		if(index<data.size()-1)
		{
			index++;
		}
		fenetre.afficherUtilisateur(data.get(index));
	}
	public void afficherDernier()
	{
		index=data.size()-1;
		fenetre.afficherUtilisateur(data.get(index));
		
	}
	public void ajouter()
	{
		fenetre.raz();
		ajout=true;
	}
	public void supprimer()
	{
		
	}
	public void enregistrer()
	{
		Utilisateur u;
		if(ajout)
		{
			u=fenetre.getFromSaisie();
			try 
			{
				u.setPassword(Utilisateur.hashPwd(u.getPassword()));
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u.setDateNaissance(LocalDate.now());
			UtilisateurManager.getInstance().save(u);
			data.add(u);
			ajout=false;
		}
		else
		{
			u=fenetre.getFromSaisie();
			data.get(index).setNom(u.getNom());
			data.get(index).setPrenom(u.getPrenom());
			data.get(index).setEmail(u.getEmail());
			data.get(index).setRole(u.getRole());
			try 
			{
				if(!data.get(index).getPassword().equals(u.getPassword()))
				{	
					data.get(index).setPassword(Utilisateur.hashPwd(u.getPassword()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				data.get(index).getAdresse().setNumero(u.getAdresse().getNumero());
				data.get(index).getAdresse().setRue(u.getAdresse().getRue());
				data.get(index).getAdresse().setCodePostal(u.getAdresse().getCodePostal());
				data.get(index).getAdresse().setVille(u.getAdresse().getVille());
			} catch (NumeroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CodePostalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UtilisateurManager.getInstance().modifier(data.get(index));
		}
		
	}
	public void nouveau()
	{
		
	}
}
