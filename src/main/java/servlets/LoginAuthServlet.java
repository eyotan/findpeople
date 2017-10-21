package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.*;

/**
 * Servlet implementation class LoginAuth
 */
public class LoginAuthServlet extends HttpServlet {
	private static final long serialVersionUID = -884698119470790521L;
	private static String queryFromLogin = new database.BaseQuery().buildQueryLogin();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> queryreq = new HashMap<>();
		String attributeLogin = "user";
		Map<String, String> dataForResponse = new HashMap<>();
		Gson json = new Gson();
		String stringJson;

		try {

			if (request.getParameter("userLogin") != null && !request.getParameter("userLogin").equals("")) {
				queryreq.put("userLogin", request.getParameter("userLogin").trim());
			}
			if (request.getParameter("userPassword") != null && !request.getParameter("userPassword").equals("")) {
				String md5 = DigestUtils.md5Hex(request.getParameter("userPassword").trim());
				queryreq.put("userPassword", md5);
			}
			if (request.getParameter("userCheckbox") != null && !request.getParameter("userCheckbox").equals("")) {
				queryreq.put("userCheckbox", request.getParameter("userCheckbox").toLowerCase());
			}

		} catch (NullPointerException ex) {
			Logger.getLogger(LoginAuthServlet.class.getName()).log(Level.WARNING, null, ex);
			response.sendError(500, "ERROR PARAMETERS");
		}

		database.BaseResult baseGetLogin = new database.BaseResult();
		String login = baseGetLogin.getLogin(queryFromLogin, queryreq);

		if (login == null) {
			dataForResponse.put("error", "Неверный логин или пароль");
			stringJson = json.toJson(dataForResponse);
			response.getWriter().write(stringJson);
		}

		if (queryreq.get("userLogin").equals(login)) {
			request.getSession().setAttribute(attributeLogin, login);
			if (queryreq.get("userCheckbox").equals("true")) {
				Cookie userlogincookie = new Cookie("userLogin", login);
				userlogincookie.setMaxAge(604800);
				response.addCookie(userlogincookie);
				Cookie userpasscookie = new Cookie("userPassword", request.getParameter("userPassword"));
				userpasscookie.setMaxAge(604800);
				response.addCookie(userpasscookie);
			}
			Cookie userlogincookie = new Cookie("userLoginUnbox", login);
			userlogincookie.setMaxAge(604800);
			response.addCookie(userlogincookie);

			dataForResponse.put("redirect", "findpeople.jsp");
			stringJson = json.toJson(dataForResponse);
			response.getWriter().write(stringJson);
		}

		if (login != null && login.equals("503")) {
			dataForResponse.put("error", "Нет соединения с БД");
			stringJson = json.toJson(dataForResponse);
			response.getWriter().write(stringJson);
		}
	}
}
