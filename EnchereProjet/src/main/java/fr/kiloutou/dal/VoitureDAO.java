package fr.kiloutou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.kiloutou.bo.Voiture;

public class VoitureDAO {

	private final String SQLINSERT = "insert into Voiture (modele, marque, immatriculation, puissance, statut, prixLocation,"
								+ " motorisation, nbrPortes) values(?,?,?,?,?,?,?,?)";
	private final String SQLSELECTALL = "SELECT id, modele, marque, immatriculation, puissance, statut, prixLocation,"
								+ " motorisation, nbrPortes FROM Voiture";
	private final String SQLDELETEBYID = "DELETE FROM Voiture WHERE id=?";
	
	public VoitureDAO() {
	}
	
	public void insert(Voiture v) {
		Connection cnx = null;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, v.getModele());
			stmt.setString(2, v.getMarque());
			stmt.setString(3, v.getImmatriculation());
			stmt.setInt(4, v.getPuissance());
			stmt.setString(5, v.getStatut());
			stmt.setInt(6, v.getPrixLocation());
			stmt.setString(7, v.getMotorisation());
			stmt.setInt(8, v.getNbrPortes());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			v.setId(rs.getInt(1));
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				cnx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Voiture> selectAll() {
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		ArrayList<Voiture> listeVoitures = null;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQLSELECTALL);
			listeVoitures = new ArrayList<>();
			
			while (rs.next()) {
				listeVoitures.add(rsToVoiture(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeVoitures;
	}
	
	private Voiture rsToVoiture(ResultSet rs) {
		Voiture v = null;
		try {
			v = new Voiture(rs.getInt("id"),
					rs.getString("modele"),
					rs.getString("marque"),
					rs.getString("immatriculation"),
					rs.getInt("puissance"),
					rs.getString("statut"),
					rs.getInt("prixLocation"),
					rs.getString("motorisation"),
					rs.getInt("nbrPortes"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
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
