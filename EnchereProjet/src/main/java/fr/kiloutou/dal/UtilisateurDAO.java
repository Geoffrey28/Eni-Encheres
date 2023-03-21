package fr.kiloutou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.kiloutou.bo.Client;
import fr.kiloutou.bo.Utilisateur;

public class UtilisateurDAO {

	private final static String SQLINSERT="insert into users (pseudo,nom,prenom,motDePasse) values(?,?,?,?)";
	private final static String SQLLOGIN="select * "
			+ "from users where email=? and password=?";
	
	public UtilisateurDAO() {
	}
	
	public static void insert(Utilisateur u) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getPseudo());
			stmt.setString(2, u.getPrenom());
			stmt.setString(3, u.getNom());
			stmt.setString(4, u.getPassword());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			u.setId(rs.getInt(1));
			cnx.close();
			System.out.println("Inscription réussi.");
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
	
	public static Utilisateur login(String pseudo, String motDePasse) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLLOGIN);
			stmt.setString(1, pseudo);
			stmt.setString(2, motDePasse);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Utilisateur = rsToUser(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Utilisateur;
	}
	
	private static Utilisateur rsToUser(ResultSet rs)
	{
		Utilisateur u = null;
		try {
			u = new Utilisateur( rs.getInt("NoUtilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("motDePasse"));
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return u;
	}
}
