package control.gestioneLaboratorio;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import model.dao.PrestazioneManager;
import model.dao.PrestazioneManagerDS;
import model.entity.Laboratorio;

/**
 * Servlet implementation class DeleteControl
 */
@WebServlet(description = "Gestisce la cancellazione delle prestazioni", urlPatterns = { "/DeleteControl" })
public class DeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static PrestazioneManager ds = new PrestazioneManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codicePrestazione = (String) request.getParameter("prestazione");
		Laboratorio laboratorio = (Laboratorio) request.getSession().getAttribute("utente");
		String usernameLaboratorio = laboratorio.getUsername();
		try {
			ds.delete(codicePrestazione, usernameLaboratorio);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		}
		String redirectedPage = "/laboratorio/homelaboratorio.jsp";
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
