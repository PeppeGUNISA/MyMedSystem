package control.gestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UtenteManager;
import model.dao.UtenteManagerDS;
import model.entity.Utente;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet(description = "Controlla il login", urlPatterns = { "/LoginControl" })
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectedPage = null;
		try {
			Utente bean = checkLogin(username, password);
			request.setAttribute("utente", bean);

			switch (bean.getRuolo()) {
			case medico:
				redirectedPage = "/homemedico.jsp";
				break;
			case paziente:
				redirectedPage = "/homepaziente.jsp";
				break;
			case laboratorio:
				redirectedPage = "/homelaboratorio.jsp";
				break;
			case operatoreAsl:
				redirectedPage = "/homeasl.jsp";
				break;
			}

		} catch (Exception e) {
			redirectedPage = "/index.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	private Utente checkLogin(String username, String password) throws Exception {
		UtenteManager ds = new UtenteManagerDS();
		Utente user = null;
		try {
			user = ds.retrieve(username, password);
		} catch (SQLException e) {
			throw new SQLException("Errore di connessione al database");
		}
		if (user != null)
			return user;
		else
			throw new Exception("Invalid login and password");
	}

}
