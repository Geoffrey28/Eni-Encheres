package fr.eni.ecole.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.BO.Role;
import fr.eni.ecole.BO.Utilisateur;

public class RoleDao 
{
	private final String SQLSELECTBYID="select id,nom from role where id=?";
	private final String SQLSELECTALL="select id,nom from role";
	private final String SQLINSERT="insert into role (nom) values(?)";
	private final String SQLDELETE="delete from role where id=?";
	
	
	public void insert(Role r)
	{
		Connection cnx;
		PreparedStatement stmt;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.prepareStatement(SQLINSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, r.getNom());
			stmt.executeUpdate();
			ResultSet rs;
			rs=stmt.getGeneratedKeys();
			rs.next();
			r.setId(rs.getInt(1));
			cnx.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void delete(int id)
	{
		Connection cnx;
		PreparedStatement stmt;
		cnx=UtilBDD.getConnection();
		try 
		{
			stmt=cnx.prepareStatement(SQLDELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			cnx.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	public Role getRoleById(int id)
	{
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		cnx=UtilBDD.getConnection();
		Role r=null;
		try 
		{
			stmt=cnx.prepareStatement(SQLSELECTBYID);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				r=new Role(id,rs.getString("nom"));
			}
			cnx.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	public List<Role> selectAll()
	{
		Connection cnx;
		Statement stmt;
		ResultSet rs;
		cnx=UtilBDD.getConnection();
		Role r=null;
		ArrayList<Role> lst=null;
		try 
		{
			
			stmt=cnx.createStatement();
			rs=stmt.executeQuery(SQLSELECTALL);
			while(rs.next())
			{
				if(lst==null)
				{
					lst=new ArrayList<>();
				}
				lst.add(new Role(rs.getInt("id"),rs.getString("nom")));
			}
			cnx.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lst;
	}
}
