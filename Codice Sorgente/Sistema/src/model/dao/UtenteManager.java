package model.dao;

import java.sql.SQLException;

import model.entity.Utente;

public interface UtenteManager {
	
	public Utente retrieve(String username, String password) throws SQLException;
	
}
