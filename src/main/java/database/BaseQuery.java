package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class BaseQuery extends BaseConnPgsql{
	private static final Logger LOGGER = Logger.getLogger(BaseQuery.class.getName());
	private final String QUERY_PEOPLE = buildQueryPeople();
				
	public String getPeople(Map<String, String> queryreq) {
		String jsonresult = null;
		if(connectToBase()) {
			jsonresult = getData(queryreq, QUERY_PEOPLE);
			closeConnections();
		}
		return jsonresult;
	}
	
	public String getLogin(Map<String, String> queryreq) {
		String stringresult = null;
		if(connectToBase()) {
			String querytobase = buildQueryLogin();
			stringresult = getDataLogin(queryreq, querytobase);
			closeConnections();
		}
		return stringresult;
	}

	private String buildQueryPeople() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.surname, p.name1, p.name2, p.city, c.car ");
		query.append("from public.peoples as p ");
		query.append("left join public.cars as c ");
		query.append("on p.id = c.id ");
		query.append("WHERE p.surname = ?");
		query.append(" AND p.name1 = ?");
		query.append(" AND p.name2 = ?");
		query.append(" AND p.city = ?");
		query.append(" AND c.car = ?");
		

		return query.toString();
	}
	
	private String buildQueryLogin() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT login, pass ");
		query.append("FROM admins ");
		query.append("WHERE login = ? ");
		query.append("AND pass = ?");
		
		return query.toString();
	}
	
	private String getData(Map<String, String> queryreq, String querytobase) {
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
            	resultmap.put("userName1", rs.getString("name1"));
            	resultmap.put("userName2", rs.getString("name2"));
            	resultmap.put("cityName", rs.getString("city"));
            	resultmap.put("autoName", rs.getString("car"));
            	arraylist.add(resultmap);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);}
		String json = new Gson().toJson(arraylist);
		
		return json;
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
            LOGGER.log(Level.SEVERE, null, ex);}
				
		return stringresult;
	}
}
