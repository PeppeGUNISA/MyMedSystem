package control;

import static org.junit.jupiter.api.Assertions.*;

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

import control.gestioneAutenticazione.FirstFormControl;
import model.entity.Utente;

class Test_FirstFormControl extends Mockito {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;

	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoPostSuccesful() throws ServletException, IOException {
		when(request.getParameter("username")).thenReturn("adgbnae2");
		when(request.getParameter("password")).thenReturn("daslfnSA23");
		when(request.getParameter("confirmpassword")).thenReturn("daslfnSA23");
		when(request.getParameter("cf")).thenReturn("LDOSMC08P10D612Q");
		FirstFormControl ffc = new FirstFormControl();
		ffc.doPost(request, response);
		Mockito.verify(session).setAttribute(Mockito.eq("paziente"), Mockito.any());
		Mockito.verify(response).sendRedirect("./paziente/registrazione2.jsp");
	}

	@Test
	void testDoPostFailure() throws IOException, ServletException {
		FirstFormControl ffc = new FirstFormControl();
		ffc.doPost(request, response);
		Mockito.verify(response).sendError(Response.SC_FORBIDDEN, "Formato dei campi errato! Abilita JavaScript.");
	}
	
}
