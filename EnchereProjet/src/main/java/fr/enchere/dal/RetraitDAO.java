package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Retrait;

public class RetraitDAO {

	private final String SQLINSERT="insert into Retrait (rue,code_postal,ville,noArticle) values(?,?,?,?)";
	private final String SQLUPDATE="update Retrait set rue=?,code_postal=?,ville=? where noArticle=?";
	private final String SQLSELECTBYID="select * from Retrait where noArticle=?";
	private final String SQLDELETEBYID = "DELETE FROM Retrait WHERE noArticle=?";

	public void insert(Retrait r) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT);
			stmt.setString(1, r.getRue());
			stmt.setInt(2, r.getCodePostal());
			stmt.setString(3, r.getVille());
			stmt.setInt(4, r.getNoArticle());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Retrait selectByNoArticle(int noArticle) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		Retrait r = null;
		
		try {
			stmt = cnx.prepareStatement(SQLSELECTBYID);
			stmt.setInt(1,noArticle);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				try {
					String rue = rs.getString("rue");
					int cp = rs.getInt("code_postal");
					String ville = rs.getString("ville");
					r = new Retrait(rue, cp, ville, noArticle);
				} catch (SQLException e) {
					e.printStackTrace();
				}		
			}
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return r;
	}
	
	public void update(Retrait retrait) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLUPDATE);
			stmt.setInt(4, retrait.getNoArticle());

			stmt.setString(1, retrait.getRue());
			stmt.setInt(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
