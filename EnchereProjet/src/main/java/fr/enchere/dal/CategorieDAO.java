package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Utilisateur;

public class CategorieDAO {

	private final static String SQLINSERT="insert into Categorie (libelle) values(?)";
	private final static String SQLSELECTALL="select * "
			+ "from Categorie where true";
	private final static String SQLDELETEBYID = "DELETE FROM Categorie WHERE noCategorie=?";
	private final static String SQLUPDATE="update Categorie set libelle=? where noCategorie=?";
	private final static String SQLSELECTBYID="select * from Categorie where noCategorie=?";
	
	public CategorieDAO() {
	}
	
	public static void insert(Categorie c) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c.getLibelle());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			c.setNoCategorie(rs.getInt(1));
			cnx.close();
			System.out.println("Catégorie ajouté.");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				cnx.rollback();
				cnx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void update(Categorie c) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLUPDATE);
			stmt.setString(1, c.getLibelle());
			stmt.setInt(2, c.getNoCategorie());
			stmt.executeUpdate();
			cnx.close();
			System.out.println("Catégorie modifié.");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				cnx.rollback();
				cnx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Categorie> selectAll() {
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		ArrayList<Categorie> listeCategorie = null;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQLSELECTALL);
			listeCategorie = new ArrayList<>();
			
			while (rs.next()) {
				listeCategorie.add(rsToCategorie(rs));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeCategorie;
	}
	
	private static Categorie rsToCategorie(ResultSet rs)
	{
		Categorie c = null;
		try {
			c = new Categorie( rs.getInt("NoCategorie"),
							rs.getString("libelle"));
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	
	public static Categorie selectById(int id) {
		Categorie categorie = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				categorie = rsToCategorie(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	public void deleteById(int id) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLDELETEBYID);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
