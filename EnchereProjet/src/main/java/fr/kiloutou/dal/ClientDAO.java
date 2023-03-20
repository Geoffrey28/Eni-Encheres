package fr.kiloutou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.kiloutou.bo.Client;

public class ClientDAO {

	private final static String SQLINSERT="insert into Client (nom,prenom,telephone,email,password,typePermis) values(?,?,?,?,?,?)";
	private final static String SQLLOGIN="select id,nom,prenom,telephone,email,password,typePermis "
			+ "from Client where email=? and password=?";
	
	public ClientDAO() {
	}
	
	public void insert(Client c) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c.getNom());
			stmt.setString(2, c.getPrenom());
			stmt.setString(3, c.getTelephone());
			stmt.setString(4, c.getEmail());
			stmt.setString(5, c.getPassword());
			stmt.setString(6, c.getTypePermis());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			c.setId(rs.getInt(1));
			c.getAdresse().setId(c.getId());
			new AdresseDAO().insert(c.getAdresse(), cnx);
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
	
	public static Client login(String email, String motdepasse) {
		Client client = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLLOGIN);
			stmt.setString(1, email);
			stmt.setString(2, motdepasse);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				 client = rsToClient(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	private static Client rsToClient(ResultSet rs)
	{
		Client c = null;
		try {
			c = new Client( rs.getInt("id"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("telephone"),
							rs.getString("email"),
							rs.getString("password"),
							new AdresseDAO().selectById(rs.getInt("id"),null),
							rs.getString("typePermis"));
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return c;
	}
}
