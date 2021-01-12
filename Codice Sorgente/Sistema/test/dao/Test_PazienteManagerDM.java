/**
 * 
 */
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.dao.PazienteManager;
import model.dao.PazienteManagerDM;
import model.entity.Paziente;

/**
 * @author Cristian
 *
 */
class Test_PazienteManagerDM {

	private PazienteManager dm;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dm = new PazienteManagerDM();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testCheck() throws SQLException {
		boolean flag = dm.check("blabla20", "RNULNG62M01E506L");
		assertTrue(flag);
	}

	@Test
	void testRegistrazioneFake1() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("bla");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRegistrazioneFake2() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("RNULNG62M01E506L");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}

	@Test
	void testRegistrazioneFake3() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake4() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getClass().getCanonicalName());
		}
	}
	
	@Test
	void testRegistrazioneFake5() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake6() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake7() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake8() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(2100, 05, 05));
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException  e) {}
	}
	
	@Test
	void testRegistrazioneFake9() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake10() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException  e) {}
	}

	@Test
	void testRegistrazioneFake11() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException  e) {}
	}
	
	@Test
	void testRegistrazioneFake12() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("bla");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException  e) {}
	}
	
	@Test
	void testRegistrazioneFake13() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake14() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("nonsonounamail");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake15() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake16() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake17() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake18() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake19() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake20() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("1");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake21() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake22() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("@");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake23() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("Via Piazza qualcosa, 92");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci NullPointerException");
		} catch (NullPointerException e) {}
	}
	
	@Test
	void testRegistrazioneFake24() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("Via Piazza qualcosa, 92");
		paziente.setCap("bla");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneFake25() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("Via Piazza qualcosa, 92");
		paziente.setCap("02020");
		paziente.setCellulare("bla");
		try {
			dm.save(paziente);
			fail("Dovrebbe esserci IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	void testRegistrazioneTrue1() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("Via Piazza qualcosa, 92");
		paziente.setCap("02020");
		dm.save(paziente);
	}
	
	@Test
	void testRegistrazioneTrue2() throws SQLException {
		Paziente paziente = new Paziente();
		paziente.setUsername("blabla20");
		paziente.setPassword("imBlue280");
		paziente.setCodiceFiscale("LGHGPP51B01E506T");
		paziente.setNome("Giuseppe");
		paziente.setCognome("Alighieri");
		paziente.setDataNascita(new GregorianCalendar(1990, 05, 05));
		paziente.setLuogoNascita("Lecce");
		paziente.setTelefono("020015465");
		paziente.setEmail("g.ali20@dominio.it");
		paziente.setStato("Italia");
		paziente.setProvincia("Lecce");
		paziente.setCitta("Lecce");
		paziente.setIndirizzo("Via Piazza qualcosa, 92");
		paziente.setCap("02020");
		paziente.setCellulare("3331234567");
		dm.save(paziente);
	}
}