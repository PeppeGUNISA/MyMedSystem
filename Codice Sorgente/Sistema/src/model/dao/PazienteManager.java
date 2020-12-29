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
	 * @param paziente
	 * @return true se l'operazione è riuscita
	 * @throws SQLException 
	 */
	boolean save(Paziente paziente) throws SQLException;
	
	// TODO: il resto dei metodi

}
