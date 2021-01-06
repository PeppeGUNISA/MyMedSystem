package control.gestioneLaboratorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import exception.NotRegisteredException;
import model.dao.PazienteManager;
import model.dao.PazienteManagerDS;
import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;
import model.entity.Referto;
import model.entity.Utente;

/**
 * Servlet implementation class CaricamentoControl
 */
@WebServlet(description = "Gestisce il caricamento del referto", urlPatterns = { "/CaricamentoControl" })
@MultipartConfig
public class CaricamentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static RefertoManager ds = new RefertoManagerDS();
	static PazienteManager uds = new PazienteManagerDS();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaricamentoControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		File file = new File(fileName);
		FileOutputStream os = new FileOutputStream(file);
		try (OutputStream outputStream = new FileOutputStream(file)) {
			IOUtils.copy(fileContent, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		}
		
		String codiceFiscale = request.getParameter("codice");

		try {
			if (!uds.check(null, codiceFiscale))
				throw new NotRegisteredException();
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		} catch (NotRegisteredException e) {
			e.printStackTrace();
			response.sendError(Response.SC_FORBIDDEN, "Codice Fiscale non presente nel sistema!");
		}

		Referto referto = new Referto();
		referto.setNote(request.getParameter("note"));
		referto.setFile(file);
		
		Utente laboratorio = (Utente) request.getSession().getAttribute("utente");
		String usernameLab = laboratorio.getUsername();
		
		try {
			ds.save(referto, usernameLab, request.getParameter("prestazione"), codiceFiscale);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		} catch (IOException e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR, "Problema temporaneo del server, riprova più tardi.");
		}
		
		String redirectedPage = "/laboratorio/homelaboratorio.jsp";
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

}
