// Disconnect MySQL

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Close {
	public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
