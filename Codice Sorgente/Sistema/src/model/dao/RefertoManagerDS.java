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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

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
	private static final String PRESTAZIONE_NAME = "prestazione";
	private static final String RECAPITO_NAME = "recapito";
	
	public RefertoManagerDS() {
	}

	public RefertoManagerDS(MariaDbDataSource ds2) {
		RefertoManagerDS.ds = ds2;
	}

	@Override
	public void save(Referto referto, String usernameLaboratorio, String idPrestazione, String codiceFiscale)
			throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ds.getConnection();

			String destinationPath = "referti" + File.separator + usernameLaboratorio
					+ File.separator + referto.getFile().getName() + ".pdf";

			String insertSQL = "INSERT INTO " + REFERTO_NAME
					+ " (IDprestazione, usernamelaboratorio, usernamepaziente, file, note, IDreferto) VALUES (?, ?, ?, ?, ?, ?)";
			String selectSQL = "SELECT username FROM " + UTENTE_NAME + " WHERE CFPIVA = ?";
			if ( !codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$") 
					|| (referto.getNote().length() != 0 && !referto.getNote().matches("^[A-Za-z',.\\s]+$"))
					|| !referto.getId().matches("^[A-Za-z0-9]+$")
					|| !referto.getFile().getAbsolutePath().contains("pdf"))
				throw new IllegalArgumentException();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codiceFiscale);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			String usernamePaziente = rs.getString("username");
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, idPrestazione);
			preparedStatement.setString(2, usernameLaboratorio);
			preparedStatement.setString(3, usernamePaziente);
			preparedStatement.setString(4, destinationPath);
			preparedStatement.setString(5, referto.getNote());
			preparedStatement.setString(6, referto.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();

			destinationPath = "referti" + File.separator + usernameLaboratorio + File.separator
					+ referto.getId() + ".pdf";

			FileInputStream fileOrig = new FileInputStream(referto.getFile().getAbsolutePath());
			File file = new File(destinationPath);
			file.getParentFile().mkdirs();
			System.out.println(file.getAbsolutePath());
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
			preparedStatement.setString(2, referto.getId());
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

	@Override
	public void delete(String codiceReferto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "DELETE FROM " + REFERTO_NAME + " WHERE IDreferto = ?";

		try {
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codiceReferto);
			preparedStatement.executeQuery();
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
	public List<Referto> getRefertiLaboratorio(String usernameLaboratorio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Referto> referti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT r.IDreferto, r.note, r.file, r.datainserimento, u.CFPIVA, p.descrizione FROM "
					+ REFERTO_NAME + " r JOIN " + UTENTE_NAME + " u on u.username = r.usernamepaziente JOIN "
					+ PRESTAZIONE_NAME + " p on r.IDprestazione = p.IDprestazione WHERE usernamelaboratorio = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usernameLaboratorio);
			ResultSet rs = preparedStatement.executeQuery();
			Referto referto = null;
			while (rs.next()) {
				referto = new Referto();
				referto.setId(rs.getString("IDreferto"));
				referto.setNote(rs.getString("note"));
				referto.setFile(new File(rs.getString("file")));
				referto.setPaziente(rs.getString("CFPIVA"));
				referto.setPrestazione(rs.getString("descrizione"));
				referto.setDataInserimento(new Date(rs.getDate("dataInserimento").getTime()));
				referti.add(referto);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return referti;
	}

	@Override
	public List<Referto> getRefertiPaziente(String usernamePaziente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Referto> referti = new ArrayList<>();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT r.IDreferto, p.descrizione, r.file, u.citta, r.datainserimento FROM "
					+ REFERTO_NAME + " r JOIN " + RECAPITO_NAME + " u on u.username = r.usernamelaboratorio JOIN "
					+ PRESTAZIONE_NAME + " p on r.IDprestazione = p.IDprestazione WHERE usernamepaziente = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usernamePaziente);
			ResultSet rs = preparedStatement.executeQuery();
			Referto referto = null;
			while (rs.next()) {
				referto = new Referto();
				referto.setId(rs.getString("IDreferto"));
				referto.setFile(new File(rs.getString("file")));
				referto.setLaboratorio(rs.getString("citta"));
				referto.setPrestazione(rs.getString("descrizione"));
				referto.setDataInserimento(new Date(rs.getDate("dataInserimento").getTime()));
				referti.add(referto);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return referti;
	}

	@Override
	public Referto retrieve(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Referto referto = null;
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT * FROM " + REFERTO_NAME + " WHERE IDreferto = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				referto = new Referto();
				referto.setId(rs.getString("IDreferto"));
				referto.setFile(new File(rs.getString("file")));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return referto;
	}

}
