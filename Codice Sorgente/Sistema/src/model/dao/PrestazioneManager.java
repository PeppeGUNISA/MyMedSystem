/**
 * 
 */
package model.dao;

import java.sql.SQLException;

import exception.AlreadyRegisteredException;
import model.entity.Prestazione;

/**
 * @author Cristian
 *
 */
public interface PrestazioneManager {
	
	void check(String codPrestazione, String laboratorio) throws SQLException, AlreadyRegisteredException;
	
	void save(Prestazione prestazione) throws SQLException;

}
