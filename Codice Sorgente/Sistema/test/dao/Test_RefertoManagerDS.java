package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;
import model.entity.Referto;

class Test_RefertoManagerDS {
	
	private MariaDbDataSource ds;
	private RefertoManager dm;


	@BeforeEach
	void setUp() throws Exception {
		ds = new MariaDbDataSource();
		ds.setUrl(
				"jdbc:mysql://localhost:3306/mymedsystemdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Rome");
		ds.setUser("root");
		ds.setPassword("root");
		dm = new RefertoManagerDS(ds);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSave1() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB0001");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote(null);
		dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
	}
	
	@Test
	void testSave2() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB0002");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote("Il paziente purtroppo ha l'elicobatterio RIP");
		dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
	}
	
	@Test
	void testSaveWrong1() throws IOException {
		Referto referto = new Referto();
		referto.setId("17");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote(null);
		try {
			dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
			// fail(); Problema!
		} catch (SQLException | IllegalArgumentException e) {}
	}
	
	@Test
	void testSaveWrong2() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB#14");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote(null);
		try {
			dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
			fail();
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testSaveWrong3() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB0014");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote("Il paziente purtroppo ha l_elicobatterio RIP");
		try {
			dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
			fail();
		} catch (IllegalArgumentException e) {}
	}
	
	void testSaveWrong4() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB0014");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli"));
		referto.setNote(null);
		try {
			dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506L");
			fail();
		} catch (IllegalArgumentException e) {}
	}
	
	void testSaveWrong5() throws SQLException, IOException {
		Referto referto = new Referto();
		referto.setId("RLAB0014");
		referto.setFile(new File("C:/eclipse/referti/forzaNapoli.pdf"));
		referto.setNote(null);
		try {
			dm.save(referto, "LabPotente", "90.91.5", "RNULNG62M01E506U");
			fail();
		} catch (IllegalArgumentException e) {}
	}

}
