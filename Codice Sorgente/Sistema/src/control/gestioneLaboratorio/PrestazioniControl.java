package control.gestioneLaboratorio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PrestazioneManager;
import model.dao.PrestazioneManagerDS;
import model.entity.Laboratorio;
import model.entity.Prestazione;

/**
 * Servlet implementation class PrestazioniControl
 */
@WebServlet(description = "Gestisce la visualizzazione delle prestazioni", urlPatterns = { "/PrestazioniControl" })
public class PrestazioniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static PrestazioneManager ds = new PrestazioneManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrestazioniControl() {
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
        List<Prestazione> prestazioni = null;
        if (tipoRichiesta.equals("tutte")) {
        	try {
				prestazioni = ds.getPrestazioni();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (tipoRichiesta.equals("laboratorio")) {
        	// TODO: implementare per laboratorio
        	Laboratorio laboratorio = (Laboratorio) request.getSession().getAttribute("utente");
        	try {
				prestazioni = ds.getPrestazioni(laboratorio.getUsername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		response.getWriter().write(new Gson().toJson(prestazioni));
	}

}
