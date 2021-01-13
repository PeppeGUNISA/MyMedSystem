package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import model.dao.MedicoManager;
import model.dao.MedicoManagerDS;

class Test_MedicoManagerDS {

	private MariaDbDataSource ds;
	private MedicoManager dm;

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
		dm = new MedicoManagerDS(ds);
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
	void test() {
		fail("Not yet implemented");
	}

}
