package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseResult extends BaseConnPgsql {
	private static final Logger LOGGER = Logger.getLogger(BaseQuery.class.getName());

	public ArrayList<Map<String, String>> getPeople(String queryToBase, Map<String, String> queryreq) {
		ArrayList<Map<String, String>> responseMap = new ArrayList<Map<String, String>>();
		if (getConnection()) {
			responseMap = getData(queryreq, queryToBase);
			closeConnections();
			return responseMap;
		} else {
			Map<String, String> error = new HashMap<>();
			error.put("error", "Нет соединения с БД");
			responseMap.add(error);
			return responseMap;
		}
	}

	public String getLogin(String querytobase, Map<String, String> queryreq) {
		String stringresult = null;
		if (getConnection()) {
			stringresult = getDataLogin(queryreq, querytobase);
			closeConnections();
			return stringresult;
		} else {
			stringresult = "503";
			return stringresult;
		}
	}

	private ArrayList<Map<String, String>> getData(Map<String, String> queryreq, String querytobase) {
		ArrayList<Map<String, String>> arraylist = new ArrayList<>();
		try {
			stmt = connection.prepareStatement(querytobase);
			stmt.setString(1, queryreq.get("surName"));
			stmt.setString(2, queryreq.get("userName1"));
			stmt.setString(3, queryreq.get("userName2"));
			stmt.setString(4, queryreq.get("cityName"));
			stmt.setString(5, queryreq.get("autoName"));

			rs = stmt.executeQuery();

			while (rs.next()) {
				Map<String, String> resultmap = new HashMap<>();
				resultmap.put("surName", rs.getString("surname"));
				resultmap.put("userName1", rs.getString("name"));
				resultmap.put("userName2", rs.getString("parentname"));
				resultmap.put("cityName", rs.getString("cityname"));
				resultmap.put("autoName", rs.getString("car"));
				arraylist.add(resultmap);
			}
		} catch (SQLException ex) {
			LOGGER.log(Level.WARNING, null, ex);
		}
		return arraylist;
	}

	private String getDataLogin(Map<String, String> queryreq, String querytobase) {
		String stringresult = null;
		try {
			stmt = connection.prepareStatement(querytobase);
			stmt.setString(1, queryreq.get("userLogin"));
			stmt.setString(2, queryreq.get("userPassword"));
			rs = stmt.executeQuery();

			while (rs.next()) {
				stringresult = rs.getString("login");
			}
		} catch (SQLException ex) {
			LOGGER.log(Level.WARNING, null, ex);
		}

		return stringresult;
	}
}
