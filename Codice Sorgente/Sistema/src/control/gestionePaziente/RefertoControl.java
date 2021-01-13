package control.gestionePaziente;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.RefertoManager;
import model.dao.RefertoManagerDS;
import model.entity.Referto;

/**
 * Servlet implementation class RefertoControl
 */
@WebServlet("/RefertoControl")
public class RefertoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static RefertoManager ds = new RefertoManagerDS();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefertoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Referto referto = null;
		try {
			referto = ds.retrieve(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + referto.getFile().getName());
        File file = referto.getFile();
        FileInputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        
        out.close();
        in.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
