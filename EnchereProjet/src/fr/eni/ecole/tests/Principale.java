package fr.eni.ecole.tests;


import java.time.LocalDate;

import fr.eni.ecole.BO.*;
import fr.eni.ecole.Bll.UtilisateurManager;
import fr.eni.ecole.DAL.AdresseDao;
import fr.eni.ecole.DAL.RoleDao;

import fr.eni.ecole.DAL.UtilisateurDao;
import fr.eni.ecole.Exceptions.CodePostalException;
import fr.eni.ecole.Exceptions.NumeroException;
import fr.eni.ecole.ihmSwing.FenetrePrincipale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Principale {

	public static void main(String[] args) 
	{
		System.out.println(UtilisateurManager.getInstance().login("bob@leponge.fr", Utilisateur.hashPwd("123456")));
		
		
		FenetrePrincipale f;
		f=new FenetrePrincipale();
		f.setVisible(true);
		
		
		
		
		Utilisateur u1,u2,u3;
		Adresse a1,a2,a3;
		Role r1,r2;
		RoleDao rDao;
		UtilisateurDao uDao;
		AdresseDao aDao;
		UtilisateurManager um;
		
		um=UtilisateurManager.getInstance();
		System.out.println(um.login("bob@leponge.fr", Utilisateur.hashPwd("123456")));
		
	/*		rDao=new RoleDao();
			r1=new Role("admin");
			r2=new Role("stagiaire");
			rDao.insert(r1);
			rDao.insert(r2);
			
			
			a1=new Adresse(42,"rue de paris",79000,"Niort");
			a2=new Adresse(25,"rue des bois",17000,"La Rochelle");
			a3=new Adresse(17,"rue des pinsons",17190,"virson");
			uDao=new UtilisateurDao();
			u1=new Utilisateur("leponge", "bob", "bob@leponge.fr", "123456", LocalDate.of(2000, 5, 31), a1, r1, 12346);
			u2=new Utilisateur("Naudin","Fernand","f@naudin.fr","tonton",LocalDate.of(1985,11, 18),a2,r2,14524);
			u3=new Utilisateur("Volfoni","Raoul","r@volfoni.fr","grisbi",LocalDate.of(1975, 9, 24),a3,r2,12486);
			uDao.insert(u1);
			uDao.insert(u2);
			uDao.insert(u3);
			
			uDao=new UtilisateurDao();
			uDao.delete(30);
			rDao=new RoleDao();
			rDao.delete(10);*/
			
			
			
		

		
		
	
		
	}
	
	public static void afficherObjet(Affichable o)
	{
		o.afficher();
		Map<Role,String> m;
		m=new TreeMap<>();
		m.put(new Role("comptable"),"quatre");
		m.put(new Role("admin"), "un");
		m.put(new Role("prof"), "trois");
		m.put(new Role("retraité"), "deux");
	
		Iterator<Role> it=m.keySet().iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		for(Map.Entry<Role, String> entry:m.entrySet())
		{
			Role key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + value);
		}
	}

}
