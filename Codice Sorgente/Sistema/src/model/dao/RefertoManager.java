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

	void check(String codiceFiscale) throws SQLException, NotRegisteredException;

	void save(Referto referto, String usernameLaboratorio, String idPrestazione, String codiceFiscale)
			throws SQLException, IOException;

	void delete(int codiceReferto) throws SQLException;

	List<Referto> getReferti(String usernameLaboratorio) throws SQLException;

}