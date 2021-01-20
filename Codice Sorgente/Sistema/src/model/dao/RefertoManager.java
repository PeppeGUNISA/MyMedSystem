/**
 * 
 */
package model.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import exception.NotRegisteredException;
import model.entity.Referto;

/**
 * @author
 *
 */
public interface RefertoManager {

	void save(Referto referto, String usernameLaboratorio, String idPrestazione, String codiceFiscale)
			throws SQLException, IOException;

	void delete(String string) throws SQLException;

	List<Referto> getRefertiLaboratorio(String usernameLaboratorio) throws SQLException;

	List<Referto> getRefertiPaziente(String username) throws SQLException;
	
	Referto retrieve(String id) throws SQLException;

}
