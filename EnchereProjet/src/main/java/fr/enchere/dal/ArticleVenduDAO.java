package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;

public class ArticleVenduDAO {

	private final static String SQLINSERT="insert into ArticleVendu (nomArticle,description, dateDebutEncheres,"
			+ "dateFinEncheres, miseAPrix, prixVente) values(?,?,?,?,?,?)";
	private final static String SQLSELECTALL="select * "
			+ "from ArticleVendu where true";
	private final String SQLDELETEBYID = "DELETE FROM ArticleVendu WHERE noArticle=?";
	private final static String SQLSHOW="select * from ArticleVendu where noArticle=?";
	
	public ArticleVenduDAO() {
	}
	
	public static void insert(ArticleVendu a) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, a.getNomArticle());
			stmt.setString(2, a.getDescription());
			stmt.setString(3, a.getDateDebutEncheres());
			stmt.setString(4, a.getDateFinEncheres());
			stmt.setInt(5, a.getMiseAPrix());
			stmt.setInt(6, a.getPrixVente());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			a.setNoArticle(rs.getInt(1));
			cnx.close();
			System.out.println("Article mise en vente avec succès.");
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
	
	public List<ArticleVendu> selectAll() {
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		ArrayList<ArticleVendu> listeArticleVendu = null;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQLSELECTALL);
			listeArticleVendu = new ArrayList<>();
			
			while (rs.next()) {
				listeArticleVendu.add(rsToArticleVendu(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticleVendu;
	}
	
	private static ArticleVendu rsToArticleVendu(ResultSet rs)
	{
		ArticleVendu a = null;
		try {
			a = new ArticleVendu( rs.getInt("NoArticle"),
							rs.getString("nomArticle"),
							rs.getString("description"),
							rs.getString("dateDebutEncheres"),
							rs.getString("dateFinEncheres"),
							rs.getInt("miseAPrix"),
							rs.getInt("prixVente"),
							rs.getString("etatVente"),
							rs.getInt("noUtilisateur"),
							rs.getInt("noCategorie"));
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return a;
	}
	
	public static ArticleVendu show(int id) {
		ArticleVendu ArticleVendu = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOW);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				ArticleVendu = rsToArticleVendu(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArticleVendu;
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
