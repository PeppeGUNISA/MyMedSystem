package control.gestionePaziente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.MedicoManager;
import model.dao.MedicoManagerDS;
import model.entity.Medico;
import model.entity.Paziente;

/**
 * Servlet implementation class MedicoControl
 */
@WebServlet(description = "Controlla le info del medico di base del paziente", urlPatterns = { "/MedicoControl" })
public class MedicoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static MedicoManager medManager = new MedicoManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Medico medico = null;
		try {
			medico = medManager.getMedico((Paziente) request.getSession().getAttribute("utente"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (medico != null) {
        	// Invio asincrono in json
        	response.getWriter().write(new Gson().toJson(medico));
        } else
        	return;
	}

}
