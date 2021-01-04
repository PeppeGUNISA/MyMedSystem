/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exception.AlreadyRegisteredException;
import model.entity.Prestazione;

/**
 * @author Cristian
 *
 */
public class PrestazioneManagerDS implements PrestazioneManager {

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
	
	private static final String PRESTAZIONE_NAME = "prestazione";
	private static final String OFFERTA_NAME = "offerta";
	
	@Override
	public void check(String codPrestazione, String laboratorio) throws SQLException, AlreadyRegisteredException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT COUNT(*) AS totale FROM " + OFFERTA_NAME + " WHERE IDprestazione = ? AND usernamelaboratorio = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codPrestazione);
			preparedStatement.setString(2, laboratorio);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if (rs.getInt("totale") != 0)
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

	@Override
	public void save(Prestazione prestazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ds.getConnection();
			String insertSQL = "INSERT INTO " + OFFERTA_NAME + " (IDprestazione, usernamelaboratorio) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prestazione.getId());
			preparedStatement.setString(2, prestazione.getLaboratorio());
			preparedStatement.executeUpdate();
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
