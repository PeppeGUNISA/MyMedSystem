/**
 * 
 */
package model.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import model.entity.Laboratorio;

/**
 * @author cristian
 *
 */
public interface PrenotazioneManager {
	
	List<String> getProvince(String prestazione) throws SQLException;

	List<Laboratorio> getDistretti(String prestazione, String provincia) throws SQLException;	

	List<Integer> getDateLaboratorio(String usernameLaboratorio) throws SQLException;
	
	Laboratorio getOrari(String usernameLaboratorio) throws SQLException;
}
