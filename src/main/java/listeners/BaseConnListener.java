package listeners;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class BaseListener
 *
 */
@WebListener
public class BaseConnListener implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(BaseConnListener.class.getName());

	/**
	 * Default constructor.
	 */
	public BaseConnListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			database.BaseConnPgsql.ds.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, String.format("CLOSE DATASOURCE"));
			e.printStackTrace();
		}

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				LOGGER.log(Level.SEVERE, String.format("deregistering jdbc driver: %s", driver));
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, String.format("Error deregistering driver %s", driver), e);
			}
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		database.BaseConnPgsql.connectToBase();
	}
}
