/**
 * 
 */
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

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
class Test_UtenteManager {
	private static MariaDbDataSource ds;
	
	private Connection connection;
	private Savepoint savepoint;

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
	void testRetrieveFake() {
		UtenteManager um = new UtenteManagerDS(ds);
		Utente user = null;
		try {
			user = um.retrieve("gepp", "Gepp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail();
	}
	
	@Test
	void testRetrieveWrongPassword() {
		UtenteManager um = new UtenteManagerDS(ds);
		Utente user = null;
		try {
			user = um.retrieve("LabPotente", "Gepp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null)
			fail();
	}
	
	@Test
	void testRetrieve() {
		UtenteManager um = new UtenteManagerDS(ds);
		Utente user = null;
		try {
			user = um.retrieve("LabPotente", "panskjwk2S");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null)
			fail();
	}

	/**
	 * Test method for {@link model.dao.UtenteManagerDS#containsIdentificativo(java.lang.String)}.
	 */
	@Test
	void testContainsIdentificativoFake() {
		UtenteManager um = new UtenteManagerDS(ds);
		boolean flag = false;
		try {
			flag = um.containsIdentificativo("asd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag != false)
			fail();
	}
	
	@Test
	void testContainsIdentificativo() {
		UtenteManager um = new UtenteManagerDS(ds);
		boolean flag = false;
		try {
			flag = um.containsIdentificativo("10002938453");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag != true)
			fail();
	}

}
