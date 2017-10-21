package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseConnPgsql implements BaseConnInterface {

	public Connection connection = null;
	public PreparedStatement stmt = null;
	public ResultSet rs = null;
	private InitialContext cxt = null;
	private static final Logger LOGGER = Logger.getLogger(BaseConnPgsql.class.getName());

	/**
	 * @return Connection to database
	 */
	public boolean connectToBase() {
		try {
			cxt = new InitialContext();
			if (cxt == null) {
				throw new Exception("NOT CONTEXT");
			}
			DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
			if (ds == null) {
				throw new Exception("NOT DATASOURCE");
			}
			connection = ds.getConnection();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, null, e);
		}
		return connection != null;
	}

	/**
	 * Close connection
	 */
	public void closeConnections() {
		try {
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			connection.close();
			connection = null;
		} catch (SQLException ex) {
			LOGGER.log(Level.WARNING, null, ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					LOGGER.log(Level.WARNING, null, ex);
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					LOGGER.log(Level.WARNING, null, ex);
				}
				stmt = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					LOGGER.log(Level.WARNING, null, ex);
				}
				connection = null;
			}
		}
	}
}
