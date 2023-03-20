package fr.eni.ecole.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.BO.Utilisateur;

public class UtilisateurDao 
{
	private final String SQLINSERT="insert into utilisateur (nom,prenom,email,password,dateNaissance,id_role)  values(?,?,?,?,?,?)";
	private final String SQLDELETE="delete from utilisateur where id=";
	private final String SQLUPDATE="update utilisateur set nom=?,prenom=?,email=?,password=?,dateNaissance=?,id_role=? where id=?";
	private final String SQLSELECTALL="select id,nom,prenom,email,password,dateNaissance,id_role from utilisateur";
	private final String SQLSELECTBYID="select id,nom,prenom,email,password,dateNaissance,id_role from utilisateur where id=?";
	private final String SQLSELECTUSERBYROLE="select id,nom,prenom,email,password,dateNaissance,id_role from utilisateur where id_role=?";
	private final String SQLLOGIN="select id,nom,prenom,email,password,dateNaissance,id_role from utilisateur where email=? and password=?";
	//!!!!!!!!!!!!!!!!!!!!! ne pas faire comme ça !!!!!!!!!!!!!!!!!!!!!!!!!
	private final String SQLDELETEBYEMAIL="delete from utilisateur where email=";
	
	
	public UtilisateurDao() 
	{
		
	}
	
	
	
	public Utilisateur login(String email,String pwd) 
	{
		Utilisateur utilisateur=null;
		try {
			Connection con = UtilBDD.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQLLOGIN);
			pstmt.setString(1,email);
			pstmt.setString(2,pwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				 utilisateur= rsToUtilisateur(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return utilisateur;
	}
	
	public void delete(int u)
	{
		Connection cnx;
		Statement stmt;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.createStatement();
			stmt.executeUpdate(SQLDELETE+u);
			cnx.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//!!!!!!!!!!!!!!!!!!!!! ne pas faire comme ça !!!!!!!!!!!!!!!!!!!!!!!!!!
	public void deleteByEmail(String email)
	{
		Connection cnx;
		Statement stmt;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.createStatement();
			stmt.executeUpdate(SQLDELETEBYEMAIL+"'"+email+"'");
			cnx.close();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	
	
	public void insert(Utilisateur u)
	{
		Connection cnx=null;
		PreparedStatement stmt;
		cnx=UtilBDD.getConnection();
		
		try 
		{
			cnx.setAutoCommit(false);
			stmt=cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getNom());
			stmt.setString(2, u.getPrenom());
			stmt.setString(3,u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setDate(5,new Date(u.getDateNaissance().toEpochDay()*24*3600*1000));
			stmt.setInt(6,u.getRole().getId());
			stmt.executeUpdate();
			ResultSet rs;
			rs=stmt.getGeneratedKeys();
			rs.next();
			u.setId(rs.getInt(1));
			u.getAdresse().setId(u.getId());
			new AdresseDao().insert(u.getAdresse(),cnx);
			cnx.commit();
			
			cnx.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try 
			{
				cnx.rollback();
				cnx.close();
			}
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public void update(Utilisateur u)
	{
		Connection cnx;
		PreparedStatement stmt;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.prepareStatement(SQLUPDATE);
			stmt.setString(1, u.getNom());
			if(u.getPrenom()!=null)
			{
				stmt.setString(2, u.getPrenom());
			}
			else
			{
				stmt.setNull(2, Types.VARCHAR);
			}
			stmt.setString(3,u.getEmail());
			stmt.setString(4, u.getPassword());
			if(u.getDateNaissance()!=null)
			{	
				stmt.setDate(5,new Date(u.getDateNaissance().toEpochDay()*24*3600*1000));
			}
			else
			{
				stmt.setNull(5,Types.DATE);
			}
			stmt.setInt(6, u.getRole().getId());
			stmt.setInt(7, u.getId());
			stmt.executeUpdate();
			new AdresseDao().update(u.getAdresse(), cnx);
			cnx.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Utilisateur> selectAll()
	{
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		ArrayList<Utilisateur> lst=null;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.createStatement();
			rs=stmt.executeQuery(SQLSELECTALL);
			lst=new ArrayList<>();
			
			while(rs.next())
			{
				lst.add(rsToUtilisateur(rs));
			}
			cnx.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lst;
	}
	
	public Utilisateur selectById(int id)
	{
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		cnx=UtilBDD.getConnection();
		Utilisateur u=null;
		try 
		{
			stmt=cnx.prepareStatement(SQLSELECTBYID);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				u=rsToUtilisateur(rs);
			}
			cnx.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return u;
	}
	public List<Utilisateur> selectByRole(int id)
	{
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		cnx=UtilBDD.getConnection();
		List<Utilisateur> lst=null;
		try 
		{
			stmt=cnx.prepareStatement(SQLSELECTUSERBYROLE);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				if(lst==null)
				{ 
					lst=new ArrayList<>();
				}
				
				lst.add(rsToUtilisateur(rs));
			}
			cnx.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lst;
	}
	
	private Utilisateur rsToUtilisateur(ResultSet rs)
	{
		Date dt;
		LocalDate ld;
		Utilisateur u=null;
		try 
		{
			dt=rs.getDate("dateNaissance");
		
		if(!rs.wasNull())
		{
			ld=LocalDate.ofEpochDay(dt.getTime()/24/3600/1000);
		}
		else
		{
			ld=null;
		}
		u=new Utilisateur(rs.getInt("id"),
						  rs.getString("nom"),
						  rs.getString("prenom"),
						  rs.getString("email"),
						  rs.getString("password"),
						  ld,
						  new AdresseDao().selectById(rs.getInt("id"),null),
						  new RoleDao().getRoleById(rs.getInt("id_role")),
						  0);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
}
