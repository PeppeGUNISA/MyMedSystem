package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Laboratorio;
import model.entity.Paziente;
import model.entity.Utente;
import model.entity.Utente.Ruolo;

public class UtenteManagerDS implements UtenteManager {
	
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
	private static final String RECAPITO_NAME = "recapito";


	@Override
	public Utente retrieve(String username, String password) throws SQLException {
		
		Utente user = null;
		if (!username.matches("^[a-zA-Z0-9]*$") ||
				username.length() < 6 || username.length() > 24
				|| !password.matches("\\d")
				|| password.length() < 8 || password.length() > 64)
			return user;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ds.getConnection();
			
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " JOIN " + RECAPITO_NAME + " WHERE username = ? AND password = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()) {
		    	Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));

				//TODO: Implementare in base al db
				switch(ruolo) {
					case paziente:
						Paziente paziente = new Paziente();
						// TODO: implementare stringhe, dati dummy per ora
						paziente.setNome(rs.getString("nome"));
						paziente.setCognome(rs.getString("cognome"));
						paziente.setEmail(rs.getString("email"));
						paziente.setPassword(rs.getString("password"));
						paziente.setCap(rs.getString("cap"));
						paziente.setCellulare(rs.getString("cellulare"));
						paziente.setCitta(rs.getString("citta"));
						paziente.setCodiceFiscale(rs.getString("CFPIVA"));
						GregorianCalendar datanascita = new GregorianCalendar();
						datanascita.setTime(rs.getDate("datanascita"));
						paziente.setDataNascita(datanascita);
						paziente.setIndirizzo(rs.getString("indirizzo"));
						paziente.setStato(rs.getString("stato"));
						paziente.setTelefono(rs.getString("telefono"));
						paziente.setLuogoNascita(rs.getString("luogonascita"));
						paziente.setProvincia(rs.getString("provincia"));
						paziente.setUsername(rs.getString("username"));
						user = paziente;
						break;
					case medico:
						//
						break;
					case laboratorio:
						Laboratorio laboratorio = new Laboratorio();
						laboratorio.setUsername(rs.getString("username"));
						laboratorio.setPassword(rs.getString("password"));
						laboratorio.setEmail(rs.getString("email"));
						laboratorio.setTelefono(rs.getString("telefono"));
						laboratorio.setCellulare(rs.getString("cellulare"));
						laboratorio.setCap(rs.getString("cap"));
						laboratorio.setCitta(rs.getString("citta"));
						laboratorio.setpIva(rs.getString("CFPIVA"));
						laboratorio.setProvincia(rs.getString("provincia"));
						laboratorio.setDenominazione(rs.getString("nome"));
						laboratorio.setOrarioApertura(LocalTime.of(Integer.parseInt(rs.getString("oraApertura")), 0));
						laboratorio.setOrarioChiusura(LocalTime.of(Integer.parseInt(rs.getString("oraApertura")), 0));
						laboratorio.setGiorniApertura(Laboratorio.stringAsGiorni(rs.getString("giorniApertura")));
						user = laboratorio;
						break;
					case operatoreAsl:
						//
						break;
				}
				
		        
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
		return user;
	}
	
	/**
	 * Si potrebbe inserire questa operazione nei Manager di Medico e Laboratorio,
	 * in modo da rendere piu' chiaro il codice
	 */
	@Override
	public boolean containsIdentificativo(String identificativo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean presente;
		
		String selectSQL = "SELECT 1 FROM MEDICO WHERE CF = ? UNION SELECT 1 FROM LABORATORIO WHERE PARTITAIVA = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, identificativo);
			preparedStatement.setString(2, identificativo);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.isBeforeFirst())
				presente = true;
			else presente = false;
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}		
		return presente;
	}
	
	/**
	 * Il control dovrebbe fare due save? Poiché vanno scritti i dati su
	 * due tabelle, una su Utente, l'altra su Medico o Laboratorio
	 */
	@Override
	public void saveUser(Utente utente) throws SQLException {
		/*
			 	if (utente instanceof Medico) {
			 		E riempi
				}
				if (utente instanceof Laboratorio) {
			 		E riempi
				}
		*/
		
	}

}
