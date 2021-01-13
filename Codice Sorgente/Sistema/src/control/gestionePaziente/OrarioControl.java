package control.gestionePaziente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.PrenotazioneManager;
import model.dao.PrenotazioneManagerDS;
import model.entity.Laboratorio;

/**
 * Servlet implementation class OrarioControl
 */
@WebServlet(asyncSupported = true, description = "Ritrova gli orari disponibili", urlPatterns = { "/OrarioControl" })
public class OrarioControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrarioControl() {
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
		PrenotazioneManager ds = new PrenotazioneManagerDS();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		Laboratorio laboratorio = null;
		try {
			laboratorio = ds.getOrari(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, String> mappa = new HashMap<>();
		mappa.put("apertura", laboratorio.getOrarioApertura().toString());
		mappa.put("chiusura", laboratorio.getOrarioChiusura().toString());
		System.out.println(new Gson().toJson(mappa));
		response.getWriter().write(new Gson().toJson(mappa));
	}

}
