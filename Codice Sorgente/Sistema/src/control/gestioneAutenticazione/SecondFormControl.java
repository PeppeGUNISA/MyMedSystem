package control.gestioneAutenticazione;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import model.dao.PazienteManager;
import model.dao.PazienteManagerDM;
import model.entity.Paziente;

/**
 * Servlet implementation class SecondFormControl
 */
@WebServlet(description = "Gestisce il secondo form di registrazione", urlPatterns = { "/SecondFormControl" })
public class SecondFormControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static PazienteManager ds = new PazienteManagerDM();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecondFormControl() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nome, cognome, email, telefono, cellulare, provincia, comune, indirizzo, cap, stato, luogoNascita;
			GregorianCalendar dataNascita = new GregorianCalendar();
			nome = request.getParameter("nome");
			cognome = request.getParameter("cognome");
			email = request.getParameter("email");
			telefono = request.getParameter("telefono");
			cellulare = request.getParameter("cellulare");
			provincia = request.getParameter("provincia");
			comune = request.getParameter("comune");
			indirizzo = request.getParameter("indirizzo");
			cap = request.getParameter("cap");
			stato = request.getParameter("stato");
			luogoNascita = request.getParameter("luogonascita");
			dataNascita.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datanascita")));
			
			Paziente user = (Paziente) request.getSession().getAttribute("paziente");
			user.setNome(nome);
			user.setCognome(cognome);
			user.setEmail(email);
			user.setTelefono(telefono);
			user.setCellulare(cellulare);
			user.setProvincia(provincia);
			user.setCitta(comune);
			user.setIndirizzo(indirizzo);
			user.setCap(cap);
			user.setStato(stato);
			user.setLuogoNascita(luogoNascita);
			user.setDataNascita(dataNascita);
			
			ds.save(user);
			
			response.sendRedirect("./index.jsp");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("paziente", null);
			response.sendError(Response.SC_FORBIDDEN, "Formato dei campi errato! Abilita JavaScript.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
