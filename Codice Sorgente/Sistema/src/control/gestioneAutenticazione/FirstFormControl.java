package control.gestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import exception.AlreadyRegisteredException;
import model.dao.PazienteManager;
import model.dao.PazienteManagerDS;
import model.entity.Paziente;

/**
 * Servlet implementation class FirstFormControl
 */
@WebServlet(description = "Gestisce il primo form di registrazione", urlPatterns = { "/FirstFormControl" })
public class FirstFormControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static PazienteManager ds = new PazienteManagerDS();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstFormControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null, password = null, confirmPassword, cf = null;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			confirmPassword = request.getParameter("confirmPassword");
			cf = request.getParameter("cf");
			
			if (!password.equals(confirmPassword))
				throw new IllegalArgumentException("Le password non corrispondono.");
			checkFormat(username, password, cf);
			checkSignUp(username, cf);
		
		} catch (AlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			response.sendError(Response.SC_FORBIDDEN, "Username o email già registrati!");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			response.sendError(Response.SC_FORBIDDEN, "Formato dei campi errato! Abilita JavaScript.");
		}
		Paziente user = new Paziente();
		user.setUsername(username);
		user.setPassword(password);
		user.setCodiceFiscale(cf);
		request.getSession().setAttribute("paziente", user);
		
		response.sendRedirect("/SecondFormControl");
	}

	private void checkFormat(String username, String password, String cf) {
		if (!(username.matches("^[a-zA-Z0-9]*$") && username.length() >= 6 && username.length() <= 24
				&& password.matches("\\d") && password.matches("[A-Za-z]") && password.length() >= 8
				&& password.length() <= 64 && cf.matches(
						"^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")))
			throw new IllegalArgumentException("Uno dei campi non è valido.");
	}

	private void checkSignUp(String username, String cf) throws SQLException, AlreadyRegisteredException {
		ds.check(username, cf);
	}

}
