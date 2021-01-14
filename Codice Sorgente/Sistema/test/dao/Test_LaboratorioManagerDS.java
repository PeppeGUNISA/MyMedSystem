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
 * @author Anna
 *
 */

public class Test_LaboratorioManagerDS {
	
	private MariaDbDataSource ds;
	private LaboratorioManager dm;
	
	private Connection connection;
	
	/**
	 * @throws java.lang.Exception
	 */
	
	@BeforeEach
	void setUp() throws Exception{
		
		ds = new MariaDbDataSource();
		ds.setUrl(
				"jdbc:mysql://localhost:3306/mymedsystemdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome"
				);
		
		ds.setUser("root");
		ds.setPassword("root");
		connection = ds.getConnection();
		dm = new LaboratorioManagerDS(ds);
					
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception{
		
		connection.rollback();
		connection.close();
	}
	
	@Test
	void Test() {
		
		fail("Not yet implemented");
	}

}
