package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseConnPgsql implements BaseConnInterface {

   public Connection connection;
   public PreparedStatement stmt;
   public ResultSet rs;
   private static final Logger LOGGER = Logger.getLogger(BaseConnPgsql.class.getName());

   /**
    * @return Connection to database
    */
  public boolean connectToBase() {
       try {
           Class.forName("org.postgresql.Driver");
           InitialContext cxt = new InitialContext();
           DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
           connection = ds.getConnection();
       } catch (NamingException | ClassNotFoundException | SQLException e) {
           LOGGER.log(Level.SEVERE, null, e);
       }
       return connection != null;
   }

   /**
    * Close connection
    */
   public void closeConnections() {
       try {
           rs.close();
           stmt.close();
           connection.close();
       } catch (SQLException ex) {
           LOGGER.log(Level.SEVERE, null, ex);
       }
   }
}
