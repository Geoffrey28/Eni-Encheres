package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;
import fr.enchere.bo.exceptions.CodePostalException;

public class UtilisateurDAO {

	private final String SQLINSERT="insert into users (pseudo,nom,prenom,MotDePasse,email,telephone,"
			+ "rue,codePostal,ville) values(?,?,?,?,?,?,?,?,?)";
	private final String SQLLOGINWITHPSEUDO="select * "
			+ "from users where pseudo=? and MotDePasse=?";
	private final String SQLLOGINWITHEMAIL="select * "
			+ "from users where email=? and MotDePasse=?";
	private final String SQLDELETE="delete from users where noUtilisateur=?";
	private final String SQLDISABLE="update users set disabled=1 where noUtilisateur=?";
	private final String SQLENABLE="update users set disabled=0 where noUtilisateur=?";
	private final static String SQLSHOW="select * from users where pseudo=?";
	private final static String SQLSELECTALL="select * from users where true";
	private final static String SQLSHOWWITHPSEUDO="select * from users where pseudo=?";
	private final static String SQLSHOWWITHEMAIL="select * from users where email=?";
	private final static String SQLSHOWID="select * from users where noUtilisateur=?";
	private final static String SQLUPDATE="update users set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, ville=?, codePostal=? where noUtilisateur=?";
	private final static String SQLUPDATEPOINTS = "update users set credit=? where noUtilisateur=?";
	
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
			stmt.setString(6, u.getTelephone());
			stmt.setString(7, u.getRue());
			stmt.setString(8, u.getCodePostal());
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
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getRue());
			stmt.setString(7, u.getVille());
			stmt.setString(8, u.getCodePostal());
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
		} catch (CodePostalException e) {
			// TODO Auto-generated catch block
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
		} catch (CodePostalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Utilisateur;
	}
	
	public List<Utilisateur> selectAll() {
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		ArrayList<Utilisateur> listeUtilisateur = null;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQLSELECTALL);
			listeUtilisateur = new ArrayList<>();
			
			while (rs.next()) {
				listeUtilisateur.add(rsToUser(rs));
			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUtilisateur;
	}
	
	public static Utilisateur showByPseudo(String pseudo) {
		Utilisateur Utilisateur = null;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOWWITHPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Utilisateur = rsToUser(rs);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CodePostalException e) {
			// TODO Auto-generated catch block
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
		} catch (CodePostalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Utilisateur;
	}
	
	public void disable(int id) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		try {
			stmt = cnx.prepareStatement(SQLDISABLE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void enable(int id) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		try {
			stmt = cnx.prepareStatement(SQLENABLE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCredit(int noArticle) {
		Enchere e = EnchereDAO.getBestEnchere(noArticle);
		if (e != null) {
			Connection cnx;
			PreparedStatement stmt;
			cnx = UtilBDD.getConnection();
			Utilisateur u = UtilisateurDAO.showById(e.getNoUtilisateur());
			u.setCredit(u.getCredit() + e.getMontant());
			try {
				stmt = cnx.prepareStatement(SQLUPDATEPOINTS);
				stmt.setInt(1, u.getCredit());
				stmt.setInt(2, u.getNoUtilisateur());
				stmt.executeUpdate();
				cnx.close();
				System.out.println("(add)Nouveau credit de " + u.getPseudo() + " = " + u.getCredit());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void removeCredit(Utilisateur user, int montant) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		user.setCredit(user.getCredit() - montant);
		try {
			stmt = cnx.prepareStatement(SQLUPDATEPOINTS);
			stmt.setInt(1, user.getCredit());
			stmt.setInt(2, user.getNoUtilisateur());
			stmt.executeUpdate();
			cnx.close();
			System.out.println("(remove)Nouveau credit de " + user.getPseudo() + " = " + user.getCredit());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Utilisateur rsToUser(ResultSet rs) throws CodePostalException {
		Utilisateur u = null;
		try {
			u = new Utilisateur( rs.getInt("NoUtilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("motDePasse"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("ville"),
							rs.getString("codePostal"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur"),
							rs.getBoolean("disabled"));
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public static Boolean checkDoublonPseudo(String pseudo) {
		Boolean check = false;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOWWITHPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public static Boolean checkDoublonEmail(String email) {
		Boolean check = false;
		try {
			Connection cnx = UtilBDD.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SQLSHOWWITHEMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
}

