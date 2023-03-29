package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.enchere.bo.Enchere;

public class EnchereDAO {
	
	private final String SQLINSERT= "insert into enchere (dateEnchere, montant_enchere, "
			+ "noUtilisateur, noArticle) values(?,?,?,?)";
	private final String SQLDELETE= "delete * from enchere where noArticle=?";
	private final static String SQLSELECTBYNOARTICLE = "select * from enchere where noArticle=?";
	private final String SQLSELECTALLBYNOUTILISATEUR = "select * from enchere where noUtilisateur=?";
	
	public void insert(Enchere e) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT);
			stmt.setString(1, e.getDateEnchere());
			stmt.setInt(2, e.getMontant());
			stmt.setInt(3, e.getNoUtilisateur());
			stmt.setInt(4, e.getNoArticle());
			stmt.executeUpdate();
			cnx.close();
			System.out.println("enchere ajoute, " + e.toString());
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	
	public static List<Enchere> selectAllByNoArticle(int noArticle) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		ArrayList<Enchere> encheres = null;
		
		try {
			stmt = cnx.prepareStatement(SQLSELECTBYNOARTICLE);
			encheres = new ArrayList<>();
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	
				encheres.add(rsToEnchere(rs));
			}
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return encheres;
	}
	
	public void deleteByNoArticle(int noArticle) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		try {
			stmt = cnx.prepareStatement(SQLDELETE);
			stmt.setInt(1, noArticle);
			stmt.executeUpdate();
			cnx.close();
			System.out.println("Enchere supprimé avec succès");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Enchere> selectAllByNoUtilisateur() {
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<Enchere> listeEnchere = null;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLSELECTALLBYNOUTILISATEUR);
			rs = stmt.executeQuery();
			listeEnchere = new ArrayList<>();
			
			while (rs.next()) {
				listeEnchere.add(rsToEnchere(rs));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeEnchere;
	}
	
	public static Enchere getBestEnchere(int id) {
		List<Enchere> encheres = new ArrayList<Enchere>();
		Enchere enchere = null;
		int montant = 0;
		try {
			encheres = selectAllByNoArticle(id);
			for (Enchere e : encheres) {
				if (montant < e.getMontant()) {
					enchere = e;
					montant = enchere.getMontant();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enchere;
	}
	
	private static Enchere rsToEnchere(ResultSet rs) {
		Enchere enchere = null;
		
		String date;
		try {
			date = rs.getString("dateEnchere");
			int montant = rs.getInt("montant_enchere");
			int noUtilisateur = rs.getInt("noUtilisateur");
			int noArticle = rs.getInt("noArticle");
			enchere = new Enchere(date, montant, noUtilisateur, noArticle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}
}
