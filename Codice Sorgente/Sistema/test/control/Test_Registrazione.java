package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import control.gestioneAutenticazione.FirstFormControl;
import control.gestioneAutenticazione.SecondFormControl;

class Test_Registrazione extends Mockito {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;

	private Map<String, Object> attributes;

	@BeforeEach
	void setUp() throws Exception {
		attributes = new HashMap<>();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);

		// Quando la servlet inserisce un oggetto nella sessione, mettilo nell'HashMap
		Mockito.doAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				String key = (String) aInvocation.getArguments()[0];
				Object value = aInvocation.getArguments()[1];
				attributes.put(key, value);
				return null;
			}
		}).when(session).setAttribute(anyString(), anyObject());

		// Quando la servlet chiede l'oggetto paziente, prelevalo dall'HashMap verificando col parametro stringa "paziente"
		when(session.getAttribute(anyString())).thenAnswer(new Answer<Object>() {
			/**
			 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
			 */
			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				String key = (String) aInvocation.getArguments()[0];
				return attributes.get(key);
			}
		});
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws ServletException, IOException {
		when(request.getParameter("username")).thenReturn("adgbnae2");
		when(request.getParameter("password")).thenReturn("daslfnSA23");
		when(request.getParameter("confirmpassword")).thenReturn("daslfnSA23");
		when(request.getParameter("cf")).thenReturn("LDOSMC08P10D612Q");
		FirstFormControl ffc = new FirstFormControl();
		ffc.doPost(request, response);
		Mockito.verify(session).setAttribute(Mockito.eq("paziente"), Mockito.any());		
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

}
