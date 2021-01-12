/**
 * 
 */
package control;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.gestioneAutenticazione.LogoutControl;

/**
 * @author Cristian
 *
 */
class Test_Logout extends Mockito {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("utente")).thenReturn(new Object());
	}

	/**
	 * Test method for
	 * {@link control.gestioneAutenticazione.LogoutControl#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@Test
	void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		LogoutControl logout = new LogoutControl();
		logout.doGet(request, response);

		Mockito.verify(session).invalidate();
		assertNull(request.getSession(false));
		Mockito.verify(response).sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
}
