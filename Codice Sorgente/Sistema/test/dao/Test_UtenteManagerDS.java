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

import model.dao.UtenteManager;
import model.dao.UtenteManagerDS;
import model.entity.Utente;

/**
 * @author Cristian
 *
 */
class Test_UtenteManagerDS {
	
	private MariaDbDataSource ds;
	private UtenteManager um;
	
	private Connection connection;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ds = new MariaDbDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/mymedsystemdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome");
		ds.setUser("root");
		ds.setPassword("root");
		connection = ds.getConnection();
		um = new UtenteManagerDS(ds);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		connection.close();
	}

	/**
	 * Test method for {@link model.dao.UtenteManagerDS#retrieve(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testRetrieveFake1() {
		Utente user = null;
		try {
			user = um.retrieve("bla", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail("L'utente non dovrebbe esistere");
	}
	
	/**
	 * Test method for {@link model.dao.UtenteManagerDS#retrieve(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testRetrieveFake2() {
		Utente user = null;
		try {
			user = um.retrieve("blabla20", null);
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail("L'utente non dovrebbe esistere");
	}
	
	@Test
	void testRetrieveFake3() {
		Utente user = null;
		try {
			user = um.retrieve("LabPotente", "bla");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail("L'utente non dovrebbe esistere");
	}
	
	@Test
	void testRetrieveFake4() {
		Utente user = null;
		try {
			user = um.retrieve("LabPotente", "blabla20");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail("L'utente non dovrebbe esistere");
	}
	
	@Test
	void testRetrieveFake5() {
		Utente user = null;
		try {
			user = um.retrieve("LabPotente", "panskjwk2S");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(user);
	}

	/**
	 * Test method for {@link model.dao.UtenteManagerDS#containsIdentificativo(java.lang.String)}.
	 */
	@Test
	void testContainsIdentificativoFake() {
		boolean flag = false;
		try {
			flag = um.containsIdentificativo("asd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(flag);
	}
	
	@Test
	void testContainsIdentificativo() {
		boolean flag = false;
		try {
			flag = um.containsIdentificativo("10002938453");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(flag);
	}

}
