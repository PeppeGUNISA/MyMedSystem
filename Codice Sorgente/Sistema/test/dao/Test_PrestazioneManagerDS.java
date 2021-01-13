/**
 * 
 */
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
		prestazione.setId("bla");
		try {
			dm.save(prestazione);
			fail();
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testSaveWrong2() throws SQLException {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("90.62.2");
		try {
			dm.save(prestazione);
			fail();
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testSaveWrong3() throws SQLException {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("4000");
		try {
			dm.save(prestazione);
			fail();
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testSaveWrong4() throws SQLException {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("4000");
		prestazione.setLaboratorio("a");
		try {
			dm.save(prestazione);
			fail();
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testSaveWrong5() {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("4000");
		prestazione.setLaboratorio("afgreag21");
		try {
			dm.save(prestazione);
			fail();
		} catch (SQLException e) {}
	}
	
	@Test
	void testSaveWrong6() {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("4000");
		prestazione.setLaboratorio("LabPotente");
		try {
			dm.save(prestazione);
			fail();
		} catch (SQLException e) {}
	}

	@Test
	void testSaveOk() {
		Prestazione prestazione = new Prestazione();
		prestazione.setId("90.62.2");
		prestazione.setLaboratorio("LabPotente");
		try {
			dm.save(prestazione);
		} catch (SQLException e) {}
	}
	
	@Test
	void testGetPrestazioni() throws SQLException {
		List<Prestazione> prestazioni = null;
		prestazioni = dm.getPrestazioni();
		assertNotNull(prestazioni);
	}
	
	@Test
	void testGetPrestazioniLaboratorioFake() {
		List<Prestazione> prestazioni = null;
		try {
			prestazioni = dm.getPrestazioni("asdona202");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		assert(prestazioni.size() == 0);
	}
	
	@Test
	void testGetPrestazioniLaboratorioTrue() {
		List<Prestazione> prestazioni = null;
		try {
			prestazioni = dm.getPrestazioni("LabPotente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		assert(prestazioni.size() != 0);
	}
}
