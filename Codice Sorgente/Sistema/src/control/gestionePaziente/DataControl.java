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

import model.dao.PrenotazioneManager;
import model.dao.PrenotazioneManagerDS;

/**
 * Servlet implementation class DataControl
 */
@WebServlet(asyncSupported = true, description = "Ritrova le date di prenotazione disponibili", urlPatterns = { "/DataControl" })
public class DataControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataControl() {
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
		List<Integer> giorni = null;
		try {
			giorni = ds.getDateLaboratorio(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Gson().toJson(giorni));
		response.getWriter().write(new Gson().toJson(giorni));
	}

}
