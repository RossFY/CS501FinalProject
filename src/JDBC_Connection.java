// This file is used to connect to MySQL

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection {
	
	// MySQL JDBC Driver
    public static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    
    // connect to MySQL
    public static final String URL = "jdbc:mysql://localhost:3306/FinalProject?"
            + "user=root&password=38015293&useUnicode=true&verifyServerCertificate=false&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
    
    public static Statement statement;
    
    public static Statement getconnection() {
        Connection connection = null;
        
        try {
            Class.forName(DRIVER_MYSQL);     // Load JDBC Driver

            connection = DriverManager.getConnection(URL);    // Create connection object
            statement = connection.createStatement();       // Create statement object
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return statement;
    }
}
