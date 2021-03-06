package control.gestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import exception.AlreadyRegisteredException;
import model.dao.PazienteManager;
import model.dao.PazienteManagerDM;
import model.entity.Paziente;

/**
 * Servlet implementation class FirstFormControl
 */
@WebServlet(description = "Gestisce il primo form di registrazione", urlPatterns = { "/FirstFormControl" })
public class FirstFormControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static PazienteManager ds = new PazienteManagerDM();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstFormControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null, password = null, confirmPassword, cf = null;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			confirmPassword = request.getParameter("confirmpassword");
			cf = request.getParameter("cf");

			if (!password.equals(confirmPassword))
				response.sendError(Response.SC_FORBIDDEN, "Le password non corrispondono.");
			checkFormat(username, password, cf);
			if (checkSignUp(username, cf))
				throw new AlreadyRegisteredException();
			Paziente user = new Paziente();
			user.setUsername(username);
			user.setPassword(password);
			user.setCodiceFiscale(cf);
			request.getSession().setAttribute("paziente", user);

			response.sendRedirect("./registrazione2.jsp");
		} catch (AlreadyRegisteredException e) {
			e.printStackTrace();
			response.sendError(Response.SC_FORBIDDEN, "Username o CF gi� registrati!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
			response.sendError(Response.SC_FORBIDDEN, "Formato dei campi errato! Abilita JavaScript.");
		}
	}

	private void checkFormat(String username, String password, String cf) {
		if (!(username.matches("^[A-Za-z0-9]{6,25}$")
				&& password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+){8,64}$") && cf.matches(
						"^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")))
			throw new IllegalArgumentException("Uno dei campi non � valido.");
	}

	private boolean checkSignUp(String username, String cf) throws SQLException, AlreadyRegisteredException {
		return ds.check(username, cf);
	}

}
