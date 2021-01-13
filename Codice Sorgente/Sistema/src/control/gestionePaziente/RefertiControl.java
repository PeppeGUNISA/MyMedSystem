package control.gestionePaziente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;
import model.entity.Paziente;
import model.entity.Referto;

/**
 * Servlet implementation class RefertiControl
 */
@WebServlet(asyncSupported = true, name = "RefertiPazienteControl", urlPatterns = { "/RefertiPazienteControl" })
public class RefertiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static RefertoManager ds = new RefertoManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefertiControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String tipoRichiesta = request.getParameter("tipo");
        List<Referto> referti = null;
        if (tipoRichiesta.equals("paziente")) {
        	Paziente paziente = (Paziente) request.getSession().getAttribute("utente");
        	try {
				referti = ds.getRefertiPaziente(paziente.getUsername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		response.getWriter().write(new Gson().toJson(referti));
	}

}
