package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.enchere.bo.Utilisateur;

public class UtilisateurDAO {

	private final String SQLINSERT="insert into users (pseudo,nom,prenom,MotDePasse,email,telephone,"
			+ "rue,codePostal,ville) values(?,?,?,?,?,?,?,?,?)";
	private final String SQLLOGINWITHPSEUDO="select * "
			+ "from users where pseudo=? and MotDePasse=?";
	private final String SQLLOGINWITHEMAIL="select * "
			+ "from users where email=? and MotDePasse=?";
	private final String SQLDELETE="delete from users where noUtilisateur=?";
	private final static String SQLSHOW="select * from users where pseudo=?";
	private final static String SQLSHOWID="select * from users where noUtilisateur=?";
	private final static String SQLUPDATE="update users set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, ville=?, codePostal=? where noUtilisateur=?";
	
	public UtilisateurDAO() {
	}
	
	public void insert(Utilisateur u) {
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
	
	public void update(Utilisateur u) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLUPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getPseudo());
			stmt.setString(2, u.getNom());
			stmt.setString(3, u.getPrenom());
			stmt.setString(4, u.getEmail());
			stmt.setInt(5, u.getTelephone());
			stmt.setString(6, u.getRue());
			stmt.setString(7, u.getVille());
			stmt.setInt(8, u.getCodePostal());
			stmt.setInt(9, u.getNoUtilisateur());
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
	public void delete(int id)
	{
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		try {
			stmt = cnx.prepareStatement(SQLDELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Utilisateur loginPseudo(String pseudo, String motDePasse) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLLOGINWITHPSEUDO);
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
	
	public Utilisateur loginEmail(String email, String motDePasse) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLLOGINWITHEMAIL);
			stmt.setString(1, email);
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
	
	public static Utilisateur showByPseudo(String pseudo) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOW);
			stmt.setString(1, pseudo);
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
	
	public static Utilisateur showById(int id) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOWID);
			stmt.setInt(1, id);
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
