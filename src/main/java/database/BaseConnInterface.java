package database;

public interface BaseConnInterface {
    /**
     * Connect to database 
     * @return boolean
     */
    boolean connectToBase();

    /**
     * Close connection with database
     */
    void closeConnections();
}
