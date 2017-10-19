package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindPeople
 */
public class FindPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = -2825991113825143494L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("index.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> queryreq = new HashMap<>();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
						
		try {
				
			if(request.getParameter("surName") != null && !request.getParameter("surName").equals("")) {
				queryreq.put("surName", request.getParameter("surName").trim());
			}
			if(request.getParameter("userName1") != null && !request.getParameter("userName1").equals("")) {
				queryreq.put("userName1", request.getParameter("userName1").trim());
			}
			if(request.getParameter("userName2") != null && !request.getParameter("userName2").equals("")) {
				queryreq.put("userName2", request.getParameter("userName2").trim());
			}
			if(request.getParameter("cityName") != null && !request.getParameter("cityName").equals("")) {
				queryreq.put("cityName", request.getParameter("cityName").trim());
			}
			if(request.getParameter("autoName") != null && !request.getParameter("autoName").equals("")) {
				queryreq.put("autoName", request.getParameter("autoName").trim());
			}
			
		}catch (NullPointerException ex) {
            Logger.getLogger(FindPeopleServlet.class.getName()).log(Level.WARNING, null, ex);
            response.sendError(500, "ERROR PARAMETERS");
        }

		database.BaseQuery db = new database.BaseQuery();
		String json = db.getPeople(queryreq);

		response.getWriter().write(json);
		}

}
