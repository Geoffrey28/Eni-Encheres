package fr.kiloutou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.kiloutou.bo.Adresse;
import fr.kiloutou.bo.exceptions.CodePostalException;
import fr.kiloutou.bo.exceptions.NumeroException;

public class AdresseDAO {
	
	private final String SQLINSERT="insert into Adresse (id_Personne,numero,rue,codepostal,ville) values(?,?,?,?,?)";
	private final String SQLSELECTBYID="select * from Adresse where id_Personne=?";

	public void insert(Adresse a, Connection c) {
		Connection cnx;
		PreparedStatement stmt;
		
		if (c == null) {
			cnx = UtilBDD.getConnection();
		} else {
			cnx = c;
		}
		
		try {
			stmt = cnx.prepareStatement(SQLINSERT);
			stmt.setInt(1, a.getId());
			stmt.setInt(2, a.getNumero());
			stmt.setString(3, a.getRue());
			stmt.setInt(4, a.getCodePostal());
			stmt.setString(5, a.getVille());
			stmt.executeUpdate();
			if (c == null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Adresse selectById(int id, Connection c)
	{
		Connection cnx;
		PreparedStatement stmt;
		ResultSet rs;
		Adresse a=null;
		
		if(c == null) {
			cnx=UtilBDD.getConnection();
		} else {
			cnx=c;
		}
		
		try {
			stmt = cnx.prepareStatement(SQLSELECTBYID);
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs.next()) {
				try {
					a=new Adresse(rs.getInt("numero"),rs.getString("rue"),rs.getInt("codePostal"), rs.getString("ville"));
					a.setId(id);
				} catch (CodePostalException e) {
					e.printStackTrace();
				} catch (NumeroException e) {
					e.printStackTrace();
				}
						
			}
			cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return a;
	}
}
