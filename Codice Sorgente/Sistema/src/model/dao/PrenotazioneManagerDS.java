/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Laboratorio;
import model.entity.Laboratorio.Giorno;
import model.entity.Prestazione;

/**
 * @author cristian
 *
 */
public class PrenotazioneManagerDS implements PrenotazioneManager {

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
	private static final String RECAPITO_NAME = "recapito";
	private static final String OFFERTA_NAME = "offerta";
	private static final String UTENTE_NAME = "utente";
	private static final String ORARIO_NAME = "orario";

	@Override
	public List<String> getProvince(String prestazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<String> province = new ArrayList<>();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT r.provincia FROM " + OFFERTA_NAME + " o JOIN " + RECAPITO_NAME
					+ " r on r.username = o.usernamelaboratorio WHERE o.IDprestazione = ? GROUP BY r.provincia";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, prestazione);
			ResultSet rs = preparedStatement.executeQuery();
			String provincia = null;
			while (rs.next()) {
				provincia = rs.getString("provincia");
				province.add(provincia);
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
		return province;
	}

	@Override
	public List<Laboratorio> getDistretti(String prestazione, String provincia) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Laboratorio> laboratori = new ArrayList<>();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT u.username, r.citta, u.nome FROM " + OFFERTA_NAME + " o JOIN " + RECAPITO_NAME
					+ " r on r.username = o.usernamelaboratorio JOIN " + UTENTE_NAME + " u on u.username = o.usernamelaboratorio WHERE o.IDprestazione = ? AND r.provincia = ? GROUP BY r.citta";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, prestazione);
			preparedStatement.setString(2, provincia);
			ResultSet rs = preparedStatement.executeQuery();
			Laboratorio laboratorio = null;
			while (rs.next()) {
				laboratorio = new Laboratorio();
				laboratorio.setUsername(rs.getString("username"));
				laboratorio.setCitta(rs.getString("citta"));
				laboratorio.setDenominazione(rs.getString("nome"));
				laboratori.add(laboratorio);
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
		return laboratori;
	}

	@Override
	public List<Integer> getDateLaboratorio(String usernameLaboratorio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Integer> giorni = new ArrayList<>();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT giornoapertura FROM " + ORARIO_NAME + " WHERE username = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usernameLaboratorio);
			ResultSet rs = preparedStatement.executeQuery();
			String giorniStringa = null;
			while (rs.next()) {
				giorniStringa = rs.getString("giornoapertura");
			}
			List<Giorno> weekdays = Laboratorio.stringAsGiorni(giorniStringa);
			for (Giorno giorno : weekdays) {
				giorni.add(giorno.ordinal() + 1);
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
		return giorni;
	}

	@Override
	public Laboratorio getOrari(String usernameLaboratorio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Laboratorio laboratorio = new Laboratorio();
		try {
			connection = ds.getConnection();
			String selectSQL = "SELECT orarioapertura, orariochiusura FROM " + ORARIO_NAME + " WHERE username = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usernameLaboratorio);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				laboratorio.setOrarioApertura(rs.getTime("orarioapertura").toLocalTime());
				laboratorio.setOrarioChiusura(rs.getTime("orariochiusura").toLocalTime());
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
		return laboratorio;
	}

}
