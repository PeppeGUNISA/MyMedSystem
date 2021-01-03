package model.dao;

import java.sql.SQLException;

import model.entity.Utente;

public interface UtenteManager {
	/**
	 * Ricerca e restituisce l'utente
	 * @param username
	 * @param password
	 * @return Utente
	 * @throws SQLException
	 */
	public Utente retrieve(String username, String password) throws SQLException;
	
	/**
	 * Determina se l'identificativo è già presente
	 * @param codiceFiscale
	 * @return vero se già presente
	 * @throws SQLException
	 */
	public boolean containsIdentificativo(String identificativo) throws SQLException;
	
	public void saveUser(Utente utente) throws SQLException;
}
