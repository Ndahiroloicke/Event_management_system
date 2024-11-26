package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db{
    private static final String URL = "jdbc:postgresql://localhost:5432/event_management";
    private static final String User = "postgres";
    private static final String password = "buburine";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, password);
    }

}
