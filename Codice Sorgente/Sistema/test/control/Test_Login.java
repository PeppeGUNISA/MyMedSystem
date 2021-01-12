/**
 * 
 */
package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.gestioneAutenticazione.LoginControl;
import model.entity.Utente;

/**
 * @author Cristian
 *
 */
class Test_Login extends Mockito {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDoPostHttpServletRequestHttpServletResponse() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		HttpSession session = mock(HttpSession.class);

		LoginControl login = new LoginControl();

		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("LabPotente");
		when(request.getParameter("password")).thenReturn("panskjwk2S");

		login.doPost(request, response);

		Mockito.verify(session).setAttribute(Mockito.eq("utente"), Mockito.any(Utente.class));
		Mockito.verify(response).sendRedirect(request.getContextPath() + "/laboratorio/homelaboratorio.jsp");

	}

}
