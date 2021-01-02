/**
 * 
 */
package model.dao;

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
	 */
	Medico getMedico(Paziente paziente);

}
