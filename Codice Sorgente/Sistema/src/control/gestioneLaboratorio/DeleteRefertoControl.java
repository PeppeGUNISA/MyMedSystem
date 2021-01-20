package control.gestioneLaboratorio;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;

/**
 * Servlet implementation class DeleteControl
 */
@WebServlet(description = "Gestisce la cancellazione delle prestazioni", urlPatterns = { "/DeleteRefertoControl" })
public class DeleteRefertoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static RefertoManager ds = new RefertoManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRefertoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceReferto = request.getParameter("referto");
		try {
			ds.delete(codiceReferto);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova pi� tardi.");
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
