/**
 * 
 */
package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exception.NotRegisteredException;
import model.entity.Referto;

/**
 * @author Cristian
 *
 */

public class RefertoManagerDS implements RefertoManager {

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

	private static final String REFERTO_NAME = "referto";
	private static final String UTENTE_NAME = "utente";

	@Override
	public void check(String codiceFiscale) throws SQLException, NotRegisteredException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT COUNT(*) AS totale from " + UTENTE_NAME + " WHERE CFPIVA = ?";

		try {
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codiceFiscale);
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();
			rs.next();
			if (rs.getInt("totale") == 0)
				throw new NotRegisteredException();
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
	public void save(Referto referto, String usernameLaboratorio, String idPrestazione, String codiceFiscale)
			throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ds.getConnection();

			String destinationPath = "C:" + File.separator + "referti" + File.separator + usernameLaboratorio + File.separator + referto.getFile().getName() + ".pdf";

			String insertSQL = "INSERT INTO " + REFERTO_NAME
					+ " (IDprestazione, usernamelaboratorio, usernamepaziente, file, note) VALUES (?, ?, ?, ?, ?)";
			String selectSQL = "SELECT username FROM " + UTENTE_NAME + " WHERE CFPIVA = ?";

			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codiceFiscale);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			String usernamePaziente = rs.getString("username");
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, idPrestazione);
			preparedStatement.setString(2, usernameLaboratorio);
			preparedStatement.setString(3, usernamePaziente);
			preparedStatement.setString(4, destinationPath);
			preparedStatement.setString(5, referto.getNote());
			preparedStatement.executeUpdate();

			rs = preparedStatement.getGeneratedKeys();

			rs.next();

			int id = rs.getInt(1);

			preparedStatement.close();
			
			destinationPath = "C:" + File.separator + "referti" + File.separator + usernameLaboratorio + File.separator + id + ".pdf";

			FileInputStream fileOrig = new FileInputStream(referto.getFile().getAbsolutePath());
			File file = new File(destinationPath);
			file.getParentFile().mkdirs();
			FileOutputStream fileDest = new FileOutputStream(file);

			byte[] buffer = new byte[1024];

			int length;
			/*
			 * copying the contents from input stream to output stream using read and write
			 * methods
			 */
			while ((length = fileOrig.read(buffer)) > 0) {
				fileDest.write(buffer, 0, length);
			}

			fileOrig.close();
			fileDest.close();

			String updateSQL = "UPDATE " + REFERTO_NAME + " SET file = ? WHERE IDreferto = ?";

			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, destinationPath);
			preparedStatement.setInt(2, id);
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
