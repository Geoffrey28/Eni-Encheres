package fr.kiloutou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.kiloutou.bo.Utilisateur;

public class UtilisateurDAO {

	private final static String SQLINSERT="insert into users (pseudo,nom,prenom,MotDePasse,email,telephone,"
			+ "rue,codePostal,ville) values(?,?,?,?,?,?,?,?,?)";
	private final static String SQLLOGIN="select * "
			+ "from users where pseudo=? and MotDePasse=?";
	
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
			stmt.setString(4, u.getMotDePasse());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getTelephone());
			stmt.setString(7, u.getRue());
			stmt.setInt(8, u.getCodePostal());
			stmt.setString(9, u.getVille());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			u.setNoUtilisateur(rs.getInt(1));
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
							rs.getString("motDePasse"),
							rs.getString("email"),
							rs.getInt("telephone"),
							rs.getString("rue"),
							rs.getString("ville"),
							rs.getInt("codePostal"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur"));
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return u;
	}
}
