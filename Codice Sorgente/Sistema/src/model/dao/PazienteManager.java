/**
 * 
 */
package model.dao;

import java.sql.SQLException;

import exception.AlreadyRegisteredException;
import model.entity.Paziente;

/**
 * @author 
 *
 */
public interface PazienteManager {
	
	/**
	 * Registra un paziente
	 * @param paziente
	 * @return true se l'operazione è riuscita
	 * @throws SQLException 
	 */
	boolean save(Paziente paziente) throws SQLException;

	void check(String username, String cf) throws SQLException, AlreadyRegisteredException;
	
	// TODO: il resto dei metodi

}
