package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exception.AlreadyRegisteredException;
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
		String insertSQL = "INSERT INTO " + PazienteManagerDS.TABLE_NAME + "(username, password, nome, cognome, email, CF-PIVA, ruolo, luogonascita, datanascita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			// TODO: in base al db
			preparedStatement.setString(1, paziente.getUsername());
			preparedStatement.setString(2, paziente.getPassword());
			preparedStatement.setString(3, paziente.getNome());
			preparedStatement.setString(4, paziente.getCognome());
			preparedStatement.setString(5, paziente.getEmail());
			preparedStatement.setString(6, paziente.getCodiceFiscale());
			preparedStatement.setInt(7, paziente.getRuolo().ordinal());
			preparedStatement.setString(8, paziente.getLuogoNascita());
			preparedStatement.setDate(9, (java.sql.Date) Date.from(paziente.getDataNascita().toInstant()));
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

	@Override
	public void check(String username, String cf) throws SQLException, AlreadyRegisteredException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// TODO: in base al db
		String selectSQL = "SELECT COUNT(*) AS totale from " + TABLE_NAME + " WHERE username = ? OR CF-PIVA = ?";

		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			// TODO: in base al db
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, cf);
			if (preparedStatement.getResultSet().getInt("totale") != 0)
				throw new AlreadyRegisteredException();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}		
	}

}
