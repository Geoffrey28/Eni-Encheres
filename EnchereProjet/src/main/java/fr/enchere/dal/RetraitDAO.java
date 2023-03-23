package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.enchere.bo.Retrait;

public class RetraitDAO {

	private final String SQLINSERT="insert into Retrait (rue,code_postal,ville,noArticle) values(?,?,?,?)";
	private final String SQLSELECTBYID="select * from Retrait where noArticle=?";

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
	
}
