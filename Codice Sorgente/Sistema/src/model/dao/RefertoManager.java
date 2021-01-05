/**
 * 
 */
package model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import exception.AlreadyRegisteredException;
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

}
