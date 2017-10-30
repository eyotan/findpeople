package database;

public interface BaseConnInterface {
    /**
     * Connect to database 
     * @return boolean
     */
    static void connectToBase() {
	}
    /**
     * Get connection 
     * @return Connection to database
     */ 
    boolean getConnection();
    /**
     * Close connection with database
     */
    void closeConnections();
}
