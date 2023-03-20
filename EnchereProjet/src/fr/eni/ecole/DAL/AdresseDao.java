package fr.eni.ecole.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.BO.Adresse;
import fr.eni.ecole.BO.Role;
import fr.eni.ecole.Exceptions.CodePostalException;
import fr.eni.ecole.Exceptions.NumeroException;

public class AdresseDao 
{
	private final String SQLINSERT="insert into adresses (id_utilisateur,numero,rue,codepostal,ville)  values(?,?,?,?,?)";
	private final String SQLUPDATE="update adresses set numero=?,rue=?,codepostal=?,ville=?  where id_utilisateur=?";
	private final String SQLFINDBYID="select * from adresses where id_utilisateur=?";
	public void insert(Adresse a,Connection c)
	{
		Connection cnx;
		PreparedStatement stmt;
		if(c==null)
		{
			cnx=UtilBDD.getConnection();
		}
		else
		{
			cnx=c;
		}
		try 
		{
			stmt=cnx.prepareStatement(SQLINSERT);
			stmt.setInt(1, a.getId());
			stmt.setInt(2, a.getNumero());
			stmt.setString(3, a.getRue());
			stmt.setInt(4, a.getCodePostal());
			stmt.setString(5, a.getVille());
			stmt.executeUpdate();
			if(c==null)
			{
				cnx.close();
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	public void update(Adresse a,Connection c)
	{
		Connection cnx;
		PreparedStatement stmt;
		if(c==null)
		{
			cnx=UtilBDD.getConnection();
		}
		else
		{
			cnx=c;
		}
		try 
		{
			stmt=cnx.prepareStatement(SQLUPDATE);
			
			stmt.setInt(1, a.getNumero());
			stmt.setString(2, a.getRue());
			stmt.setInt(3, a.getCodePostal());
			stmt.setString(4, a.getVille());
			stmt.setInt(5, a.getId());
			stmt.executeUpdate();
			cnx.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public Adresse selectById(int id,Connection c)
	{
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		Adresse a=null;
		if(c==null)
		{
			cnx=UtilBDD.getConnection();
		}
		else
		{
			cnx=c;
		}
		try 
		{
			stmt=cnx.prepareStatement(SQLFINDBYID);
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				try 
				{
					a=new Adresse(rs.getInt("numero"),rs.getString("rue"),rs.getInt("codePostal"), rs.getString("ville"));
					a.setId(id);
				}
				catch (CodePostalException e) 
				{
					e.printStackTrace();
				}
				catch (NumeroException e) 
				{
					e.printStackTrace();
				}
						
			}
			cnx.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return a;
	}
}
