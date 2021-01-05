package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Utente;

/**
 * Servlet Filter implementation class AuthFilter
 */

/**
 * 
 * @WebFilter(urlPatterns = { "/laboratorio/*", "/medico/*", "/paziente/*", "/operatoreAsl/*" }, filterName = "AdminFilter", description = "Filter all admin URLs")
 *	Da riattivare
 */

public class AuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;

		String loginRequest = hrequest.getRequestURI().toLowerCase();

		HttpSession session = hrequest.getSession(false);
		boolean loggedIn = session != null;
		if (!loggedIn) {
			hresponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			Utente utente = (Utente) session.getAttribute("utente");
			if (loginRequest.contains(utente.getRuolo().name().toLowerCase()))
				chain.doFilter(request, response);
			else
				hresponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
