/**
 * 
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

import model.entity.Medico;
import model.entity.Paziente;
import model.entity.Medico.Giorno;

/**
 * @author 
 *
 */
public class MedicoManagerDS implements MedicoManager {

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
	
	private static final String UTENTE_NAME = "utente";
	private static final String RECAPITO_NAME = "recapito";
	private static final String ORARIO_NAME = "orario";
	private static final String ASSOCIAZIONE_NAME = "associazione";
	
	public MedicoManagerDS() {
	}
	
	public MedicoManagerDS(MariaDbDataSource ds2) {
		MedicoManagerDS.ds = ds2;
	}

	@Override
	public Medico getMedico(Paziente paziente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Medico medico = null;

		String selectSQL = "SELECT u.nome, u.cognome, r.indirizzo, r.citta, r.telefono, o.orarioapertura, o.orariochiusura, o.giornoapertura FROM " + UTENTE_NAME + " u JOIN " + ASSOCIAZIONE_NAME + " a on u.username = a.usernamemedico JOIN " + RECAPITO_NAME + " r on u.username = r.username JOIN " + ORARIO_NAME + " o on u.username = o.username WHERE a.usernamepaziente = ?";

		try {
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(selectSQL);
			// TODO: in base al db
			preparedStatement.setString(1, paziente.getUsername());
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();
			rs.next();
			medico = new Medico();
			medico.setDenominazione(rs.getString("cognome") + rs.getString("nome"));
			medico.setCitta(rs.getString("citta"));
			medico.setIndirizzo(rs.getString("indirizzo"));
			medico.setTelefono(rs.getString("telefono"));
			medico.setOrarioApertura(rs.getTime("orarioapertura").toLocalTime());
			medico.setOrarioChiusura(rs.getTime("orariochiusura").toLocalTime());
			medico.setGiorniApertura(stringAsGiorni(rs.getString("giornoapertura")));
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return medico;
	}
	
	public String getGiorniAsString(List<Giorno> giorniApertura) {
		String stringa = "";
		Giorno[] giorni = Giorno.values();
		for (Giorno giorno : giorni) {
			if (giorniApertura.contains(giorno))
				stringa = stringa + "1";
			else
				stringa = stringa + "0";
		}
		return stringa;
	}
	
	public static List<Giorno> stringAsGiorni(String stringa) {
		List<Giorno> giorni = new ArrayList<>();
		char[] caratteri = stringa.toCharArray();
		for (Giorno giorno : Giorno.values()) {
			if (caratteri[giorno.ordinal()] == '1')
				giorni.add(giorno);
		}
		return giorni;
	}

}
