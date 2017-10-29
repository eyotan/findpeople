package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LogoutAuthServlet
 */
@WebServlet(name = "logoutAuthServlet", urlPatterns = { "/logoutAuthServlet" })
public class LogoutAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 854394614655135925L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (request.getParameter("userLogout") != null && !request.getParameter("userLogout").equals("")) {
				request.getSession().removeAttribute("user");
				String redirectURL = "index.jsp";
				Map<String, String> data = new HashMap<>();
				data.put("redirect", redirectURL);
				String json = new Gson().toJson(data);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}

		} catch (NullPointerException ex) {
			Logger.getLogger(LogoutAuthServlet.class.getName()).log(Level.WARNING, null, ex);
			response.sendError(500, "ERROR PARAMETERS");
		}

	}

}
