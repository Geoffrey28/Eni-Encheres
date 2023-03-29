package fr.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;

public class ArticleVenduDAO {

	private final static String SQLINSERT = "insert into ArticleVendu (nomArticle,description,img,dateDebutEncheres,"
			+ "dateFinEncheres, miseAPrix, prixVente, etatVente, noUtilisateur, noCategorie) values(?,?,?,?,?,?,?,?,?,?)";
	private final static String SQLSELECTALLWITHFILTER="select * "
			+ "from ArticleVendu where nomArticle like CONCAT( '%',?,'%') and noCategorie = ? ";
	private final static String SQLSELECTALLWITHFILTER_without_categorie="select * "
			+ "from ArticleVendu where nomArticle like CONCAT( '%',?,'%') ";
	private final static String SQLSELECTALLWITHFILTER_without_name="select * "
			+ "from ArticleVendu where noCategorie = ? ";
	private final static String SQLSELECTALL = "select * from ArticleVendu where true";
	private final String SQLDELETEBYID = "DELETE FROM ArticleVendu WHERE noArticle=?";
	private final static String SQLSHOW = "select * from ArticleVendu where noArticle=?";
	private final static String SQLUPDATE = "update ArticleVendu set nomArticle=?,description=?,img=?,dateDebutEncheres=?,"
			+ "dateFinEncheres=?,miseAPrix=?,prixVente=?,noCategorie=? where noArticle=?";
	private final static String SQLUPDATEPRIXVENTE = "update ArticleVendu set prixVente=? where noArticle=?";
	private final static String SQLUPDATEETATVENTE = "update ArticleVendu set etatVente=? where noArticle=?";
	
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
			stmt.setString(3, a.getImg());
			stmt.setString(4, a.getDateDebutEncheres());
			stmt.setString(5, a.getDateFinEncheres());
			stmt.setInt(6, a.getMiseAPrix());
			stmt.setInt(7, a.getPrixVente());
			stmt.setString(8, a.getEtatVente());
			stmt.setInt(9, a.getNoUtilisateur());
			stmt.setInt(10, a.getNoCategorie());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			a.setNoArticle(rs.getInt(1));
			cnx.close();
			System.out.println("Article mise en vente avec succès.");
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
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeArticleVendu;
	}
	
	public List<ArticleVendu> selectAllWithFilter(String name, int categorie, int type, int filterType, int filterValue[], Utilisateur u) {
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<ArticleVendu> listeArticleVendu = null;
		cnx = UtilBDD.getConnection();
		
		try {
			
			String CustomSql = "select * from ArticleVendu where ";
			
			if(name != "" && categorie == -1) {
				CustomSql = CustomSql + "nomArticle like '%"+ name +"%' && ";
			} else if(name == "" && categorie != -1) {
				CustomSql = CustomSql + "noCategorie = "+ categorie +" && ";
			} else if(name != "" && categorie != -1) {
				CustomSql = CustomSql + "nomArticle like '%"+ name +"%' && noCategorie = "+ categorie +" && ";
			}
			
			if (filterType == 0) {
				
				if (filterValue[0] == 1) {
					CustomSql = CustomSql + "etatVente = 'Ec' || ";
				}
				if (filterValue[1] == 1) {
					CustomSql = CustomSql + "noArticle = (select noArticle from enchere where noUtilisateur = "+ u.getNoUtilisateur() +" && enchere.noArticle = ArticleVendu.noArticle) || ";
				}
				if (filterValue[2] == 1) {
					CustomSql = CustomSql + "noAcquereur = "+ u.getNoUtilisateur() +" && ";
				}
				
			} else {
				
				if (filterValue[0] == 1) {
					CustomSql = CustomSql + "noUtilisateur = "+ u.getNoUtilisateur() +" || ";
				}
				if (filterValue[1] == 1) {
					CustomSql = CustomSql + "etatVente = 'Cr' || ";
				}
				if (filterValue[2] == 1) {
					CustomSql = CustomSql + "etatVente = 'Et' || ";
				}
				
			}
			
			String cut = CustomSql.substring(CustomSql.length() - 3, CustomSql.length() - 1).trim();
			
			if (!cut.equals("re") ) {
				CustomSql = CustomSql.substring(0, CustomSql.length() - 4);
			} else {
				CustomSql = CustomSql.substring(0, CustomSql.length() - 7);
			}
			
			stmt = cnx.prepareStatement(CustomSql);
			rs = stmt.executeQuery();
			
			listeArticleVendu = new ArrayList<>();
			
			while (rs.next()) {
				listeArticleVendu.add(rsToArticleVendu(rs));
			}
			cnx.close();
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
							rs.getString("img"),
							rs.getString("dateDebutEncheres"),
							rs.getString("dateFinEncheres"),
							rs.getInt("miseAPrix"),
							rs.getInt("prixVente"),
							rs.getString("etatvente"),
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
	
	public void update(ArticleVendu article) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		try {
			stmt = cnx.prepareStatement(SQLUPDATE);
			stmt.setInt(9, article.getNoArticle());
		
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setString(3, article.getImg());
			stmt.setString(4, article.getDateDebutEncheres());
			stmt.setString(5, article.getDateFinEncheres());
			stmt.setInt(6, article.getMiseAPrix());
			stmt.setInt(7, article.getPrixVente());
			stmt.setInt(8,  article.getNoCategorie());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrixVenteById(int id) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		try {
			stmt = cnx.prepareStatement(SQLUPDATEPRIXVENTE);
			stmt.setInt(2, id);

			Enchere enchere = EnchereDAO.getBestEnchere(id);
			stmt.setInt(1, enchere.getMontant());
			stmt.executeUpdate();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateEtatVente(ArticleVendu a) {
		Connection cnx;
		PreparedStatement stmt;
		cnx = UtilBDD.getConnection();
		
		String etat = a.getEtatVente();
		System.out.println(etat);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date dateDuJour = new Date();
		System.out.println(dateDuJour);
		try {
			if (sdf.parse(a.getDateDebutEncheres()).after(dateDuJour)) {
				a.setEtatVente("Cr");
			} else if (sdf.parse(a.getDateFinEncheres()).before(dateDuJour)) {
				a.setEtatVente("Et");
			} else {
				a.setEtatVente("Ec");
			}

			System.out.println(a.getEtatVente());
			if (etat.equals(a.getEtatVente())) {
				cnx.close();
			} else {
				stmt = cnx.prepareStatement(SQLUPDATEETATVENTE);
				stmt.setString(1, a.getEtatVente());
				stmt.setInt(2, a.getNoArticle());
				stmt.executeUpdate();
				cnx.close();
				System.out.println("update");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
