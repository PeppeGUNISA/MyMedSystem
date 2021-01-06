/**
 * 
 */
package model.dao;

import java.sql.SQLException;

import model.entity.Paziente;

/**
 * @author
 *
 */
public interface PazienteManager {

	/**
	 * Registra un paziente
	 * 
	 * @param paziente
	 * @return true se l'operazione � riuscita
	 * @throws SQLException
	 */
	boolean save(Paziente paziente) throws SQLException;

	boolean check(String username, String cf) throws SQLException;

	// TODO: il resto dei metodi

}
