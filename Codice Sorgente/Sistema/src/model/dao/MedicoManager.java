/**
 * 
 */
package model.dao;

import java.sql.SQLException;

import model.entity.Medico;
import model.entity.Paziente;

/**
 * @author 
 *
 */
public interface MedicoManager {

	/**
	 * Restituisce il medico associato al paziente
	 * @param paziente
	 * @return il medico associato
	 * @throws SQLException 
	 */
	Medico getMedico(Paziente paziente) throws SQLException;

}
