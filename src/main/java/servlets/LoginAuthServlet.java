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
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");   
		
		Map<String, String> queryreq = new HashMap<>();

		try {
			
			if(request.getParameter("userLogin") != null && !request.getParameter("userLogin").equals("")) {
				queryreq.put("userLogin", request.getParameter("userLogin").trim());
			}
			if(request.getParameter("userPassword") != null && !request.getParameter("userPassword").equals("")) {
				String md5 = DigestUtils.md5Hex(request.getParameter("userPassword").trim());
				queryreq.put("userPassword", md5);
			}
			if(request.getParameter("userCheckbox") != null && !request.getParameter("userCheckbox").equals("")) {
				queryreq.put("userCheckbox", request.getParameter("userCheckbox").toLowerCase());
			}
			
		}catch (NullPointerException ex) {
            Logger.getLogger(LoginAuthServlet.class.getName()).log(Level.WARNING, null, ex);
            response.sendError(500, "ERROR PARAMETERS");
        }

		database.BaseQuery baseGetLogin = new database.BaseQuery(); 
		String login = baseGetLogin.getLogin(queryreq);
		
		
		
		if(queryreq.get("userLogin").equals(login)) {
			request.getSession().setAttribute("user", login);
			if(queryreq.get("userCheckbox").equals("true")) {
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
			
			String redirectURL = "findpeople.jsp";
			Map<String, String> data = new HashMap<>();
			data.put("redirect", redirectURL);
			String json = new Gson().toJson(data);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		
	}
}
