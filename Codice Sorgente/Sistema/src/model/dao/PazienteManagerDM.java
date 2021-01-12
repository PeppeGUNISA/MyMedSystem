package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import model.entity.Paziente;

public class PazienteManagerDM implements PazienteManager {

	private static final String TABLE_NAME = "utente";
	private static final String RECAPITO_NAME = "recapito";

	@Override
	public boolean save(Paziente paziente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		boolean result = false;
		
		if ( Pattern.compile("\\d").matcher(paziente.getNome()).find()
				|| Pattern.compile("\\d").matcher(paziente.getCognome()).find()
				|| paziente.getNome().length() == 0
				|| paziente.getCognome().length() == 0
				|| !paziente.getEmail().matches("\\S+@\\S+\\.\\S+")
				|| !paziente.getTelefono().matches("^[0-9]+$")
				|| (paziente.getCellulare() == null || !paziente.getCellulare().matches("^[0-9]+$"))
				|| paziente.getProvincia().length() == 0
				|| paziente.getProvincia().matches("^[A-Za-z- ]+$")
				|| paziente.getcitta().length() == 0
				|| paziente.getcitta().matches("^[A-Za-z- ]+$")
				|| paziente.getIndirizzo().length() == 0
				|| paziente.getIndirizzo().matches("^[A-Za-z-, ]+$")
				|| !paziente.getCap().matches("^[0-9]+$")
				|| paziente.getStato().length() == 0
				|| paziente.getLuogoNascita().length() == 0
				|| paziente.getDataNascita().compareTo(new GregorianCalendar()) > 0) {
				throw new IllegalArgumentException();
		}

		// TODO: in base al db
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (username, password, nome, cognome, email, CFPIVA, ruolo, luogonascita, datanascita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String insertSQL2 = "INSERT INTO " + RECAPITO_NAME
				+ " (username, stato, provincia, citta, cap, indirizzo, telefono, cellulare) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getDBConnection();

			preparedStatement1 = connection.prepareStatement(insertSQL);
			preparedStatement2 = connection.prepareStatement(insertSQL2);

			preparedStatement1.setString(1, paziente.getUsername());
			preparedStatement1.setString(2, paziente.getPassword());
			preparedStatement1.setString(3, paziente.getNome());
			preparedStatement1.setString(4, paziente.getCognome());
			preparedStatement1.setString(5, paziente.getEmail());
			preparedStatement1.setString(6, paziente.getCodiceFiscale());
			preparedStatement1.setString(7, paziente.getRuolo().toString().toLowerCase());
			preparedStatement1.setString(8, paziente.getLuogoNascita());
			preparedStatement1.setDate(9, new java.sql.Date(paziente.getDataNascita().getTimeInMillis()));
			preparedStatement2.setString(1, paziente.getUsername());
			preparedStatement2.setString(2, paziente.getStato());
			preparedStatement2.setString(3, paziente.getProvincia());
			preparedStatement2.setString(4, paziente.getcitta());
			preparedStatement2.setString(5, paziente.getCap());
			preparedStatement2.setString(6, paziente.getIndirizzo());
			preparedStatement2.setString(7, paziente.getTelefono());
			preparedStatement2.setString(8, paziente.getCellulare());
			connection.setAutoCommit(false);
			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			connection.commit();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
			try {
				if (preparedStatement1 != null)
					preparedStatement1.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return result;
	}

	@Override
	public boolean check(String username, String cf) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// TODO: in base al db
		String selectSQL = "SELECT COUNT(*) AS totale from " + TABLE_NAME + " WHERE username = ? OR CFPIVA = ?";

		try {
			connection = DBConnectionPool.getDBConnection();

			preparedStatement = connection.prepareStatement(selectSQL);
			// TODO: in base al db
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, cf);
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();
			rs.next();
			if (rs.getInt("totale") != 0)
				return true;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return false;
	}

}
