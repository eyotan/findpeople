package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class FindPeople
 */
public class FindPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = -2825991113825143494L;
	private static String queryFromPeople = new database.BaseQuery().buildQueryPeople();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> queryreq = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		ArrayList<Map<String, String>> responseMap;
		Gson json = new Gson();
		String stringJson;

		try {
			if (request.getParameter("surName") != null && !request.getParameter("surName").equals("")) {
				queryreq.put("surName", request.getParameter("surName").trim());
			}
			if (request.getParameter("userName1") != null && !request.getParameter("userName1").equals("")) {
				queryreq.put("userName1", request.getParameter("userName1").trim());
			}
			if (request.getParameter("userName2") != null && !request.getParameter("userName2").equals("")) {
				queryreq.put("userName2", request.getParameter("userName2").trim());
			}
			if (request.getParameter("cityName") != null && !request.getParameter("cityName").equals("")) {
				queryreq.put("cityName", request.getParameter("cityName").trim());
			}
			if (request.getParameter("autoName") != null && !request.getParameter("autoName").equals("")) {
				queryreq.put("autoName", request.getParameter("autoName").trim());
			}

		} catch (NullPointerException ex) {
			Logger.getLogger(FindPeopleServlet.class.getName()).log(Level.WARNING, null, ex);
			response.sendError(500, "ERROR PARAMETERS");
		}

		database.BaseResult db = new database.BaseResult();
		responseMap = db.getPeople(queryFromPeople, queryreq);

		if (responseMap.isEmpty()) {
			error.put("error", "Совпадений не найдено");
			stringJson = json.toJson(error);
			response.getWriter().write(stringJson);
		}
		if (!responseMap.isEmpty() && responseMap.get(0).size() > 4) {
			stringJson = json.toJson(responseMap);
			response.getWriter().write(stringJson);
			}
		if (!responseMap.isEmpty() && responseMap.get(0).size() < 4) {
			error = responseMap.get(0);
			stringJson = json.toJson(error);
			response.getWriter().write(stringJson);
		}
	}
}
