package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.gestioneAutenticazione.SecondFormControl;
import model.entity.Paziente;

class Test_SecondFormControl extends Mockito {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;

	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		
		Paziente paziente = new Paziente();
		paziente.setUsername("adgbnae2");
		paziente.setPassword("daslfnSA23");
		paziente.setCodiceFiscale("LDOSMC08P10D612Q");
		
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("paziente")).thenReturn(paziente);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoPostSuccesful() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("Pino");
		when(request.getParameter("cognome")).thenReturn("Giuseppino");
		when(request.getParameter("email")).thenReturn("giuseppe@outlook.it");
		when(request.getParameter("telefono")).thenReturn("0818887799");
		when(request.getParameter("provincia")).thenReturn("Avellino");
		when(request.getParameter("comune")).thenReturn("Avellino");
		when(request.getParameter("indirizzo")).thenReturn("Via Garibaldi 94");
		when(request.getParameter("cap")).thenReturn("83100");
		when(request.getParameter("stato")).thenReturn("Italia");
		when(request.getParameter("luogonascita")).thenReturn("Poggiomarino");
		when(request.getParameter("datanascita")).thenReturn("1980-04-01");
		SecondFormControl sfc = new SecondFormControl();
		sfc.doPost(request, response);
		Mockito.verify(response).sendRedirect("/index.jsp");
	}

	@Test
	void testDoPostFailure() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("Pino");
		when(request.getParameter("cognome")).thenReturn("Giuseppino");
		when(request.getParameter("email")).thenReturn("giuseppeoutlook.it");
		when(request.getParameter("telefono")).thenReturn("0818887799");
		when(request.getParameter("provincia")).thenReturn("Avellino");
		when(request.getParameter("comune")).thenReturn("Avellino");
		when(request.getParameter("indirizzo")).thenReturn("Via Garibaldi 94");
		when(request.getParameter("cap")).thenReturn("83100");
		when(request.getParameter("stato")).thenReturn("Italia");
		when(request.getParameter("luogonascita")).thenReturn("Poggiomarino");
		when(request.getParameter("datanascita")).thenReturn("1980-04-01");
		SecondFormControl sfc = new SecondFormControl();
		sfc.doPost(request, response);
		Mockito.verify(response).sendError(Response.SC_FORBIDDEN, "Formato dei campi errato! Abilita JavaScript.");
	}
}
