package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		
		String selectSQL = "SELECT * FROM " + UtenteManagerDS.TABLE_NAME + " WHERE username = ? AND password = ?";

		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()) {
		    	Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));
				selectSQL = "SELECT * FROM ? WHERE username = ?";
				rs.close();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, ruolo.toString());
				preparedStatement.setString(2, username);
				rs = preparedStatement.executeQuery();
				
				//TODO: Implementare in base al db
				switch(ruolo) {
					case paziente:
						Paziente paziente = new Paziente();
						// TODO: implementare stringhe, dati dummy per ora
						paziente.setNome("");
						paziente.setCognome("");
						paziente.setEmail("");
						paziente.setPassword("");
						paziente.setCap(0);
						paziente.setCellulare("");
						paziente.setcitta("");
						paziente.setCodiceFiscale("");
						paziente.setDataNascita(new GregorianCalendar());
						paziente.setIndirizzo("");
						paziente.setStato("");
						paziente.setTelefono("");
						paziente.setLuogoNascita("");
						paziente.setProvincia("");
						paziente.setUsername("");
						user = paziente;
						break;
					case medico:
						//
						break;
					case laboratorio:
						//
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
