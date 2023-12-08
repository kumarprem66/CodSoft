package studentmanagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBUtils {

     static Connection getConnection() throws ClassNotFoundException, SQLException {

        //step-1 load the driver class
        Class.forName("com.mysql.cj.jdbc.Driver");
        //step-2
        ResourceBundle rb = ResourceBundle.getBundle("dbu", Locale.getDefault());
        return DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
    }

    static void closeConnection(Connection conn) throws SQLException {
        if(conn != null)
            conn.close();
    }

    static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
        return !rs.isBeforeFirst() && rs.getRow() == 0;
    }

}
