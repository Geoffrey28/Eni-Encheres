package fr.eni.ecole.DAL;

import java.sql.Connection;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class UtilBDD 
{
	public static Connection getConnection()
	{
		Connection cnx=null;
		InitialContext ctx;
		DataSource ds;
		
		try 
		{
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/pool_cnx");
			cnx=ds.getConnection();
			
			
			
			
			//// *********** pour SQL Server ************ //
		//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//	cnx=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Annuaire;encrypt=false;trustServerCertificate=true", "sa","Pa$$w0rd");
			
			//  *********** pour Mysql **************  //
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/Annuaire", "root","Pa$$w0rd");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnx;
	}

}
