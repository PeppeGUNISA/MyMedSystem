/**
 * 
 */
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;


import model.dao.PrestazioneManager;
import model.dao.PrestazioneManagerDS;
import model.entity.Prestazione;

/**
 * @author Cristian
 *
 */
class Test_PrestazioneManagerDS {
	
	private MariaDbDataSource ds;
	private PrestazioneManager dm;

	private Connection connection;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ds = new MariaDbDataSource();
		ds.setUrl(
				"jdbc:mysql://localhost:3306/mymedsystemdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome");
		ds.setUser("root");
		ds.setPassword("root");
		connection = ds.getConnection();
		dm = new PrestazioneManagerDS(ds);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		connection.close();
	}

	@Test
	void testSaveWrong1() throws SQLException {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("90 90 6");
		prestazione.setLaboratorio("LabPotente");
		try {
			dm.save(prestazione);
		} catch (IllegalArgumentException e) {}
	}

	@Test
	void testSaveOk() {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("90.90.2");
		prestazione.setLaboratorio("LabPotente");
		try {
			dm.save(prestazione);
		} catch (SQLException e) {}
	}

	@Test
	void testDelete() {
		try {
			dm.delete("90.90", "LabPotente");
		} catch (SQLException e) {
			fail();
		}
	}
}
