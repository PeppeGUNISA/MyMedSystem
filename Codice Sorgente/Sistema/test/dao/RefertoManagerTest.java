/**
 * 
 */
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;
import model.entity.Referto;

/**
 * @author cristian
 *
 */
class RefertoManagerTest {
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/mymedsystem");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private Connection connection;
	private Savepoint savepoint;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		connection = ds.getConnection();
		connection.setAutoCommit(false);
		savepoint = connection.setSavepoint();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		connection.rollback(savepoint);
		connection.releaseSavepoint(savepoint);
		connection.setAutoCommit(true);
		savepoint = null;
	}

	/**
	 * Test method for {@link model.dao.RefertoManagerDS#save(model.entity.Referto, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSave() {
		RefertoManager manager = new RefertoManagerDS();
		try {
			Referto referto = new Referto();
			manager.save(referto, null, null, null);
			fail();
		} catch (SQLException success) {}
		catch (IOException success) {}
		
		Referto referto = new Referto();
		referto.setFile(new File("random.txt"));
		try {
			manager.save(referto, "LabPotente", "90.62.2", "asdl");
			fail();
		} catch (SQLException | IOException success) {}
		
		try {
			manager.save(referto, "LabPotente", "90.62.2", "RNULNG62M01E506L");
			connection.commit();
		} catch (SQLException | IOException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link model.dao.RefertoManagerDS#delete(int)}.
	 */
	@Test
	void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.dao.RefertoManagerDS#getReferti(java.lang.String)}.
	 */
	@Test
	void testGetReferti() {
		fail("Not yet implemented"); // TODO
	}

}
