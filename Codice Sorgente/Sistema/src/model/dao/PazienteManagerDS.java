package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Paziente;

public class PazienteManagerDS implements PazienteManager {
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/mymedsystem");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "utente";

	@Override
	public boolean save(Paziente paziente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean result = false;
		
		// TODO: in base al db
		String insertSQL = "INSERT INTO " + PazienteManagerDS.TABLE_NAME + "(column_names) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			// TODO: in base al db
			preparedStatement.setString(1, paziente.getUsername());
			preparedStatement.executeUpdate();
			result = true;

		} catch (SQLException e) {
			result = false;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return result;
	}

}
