package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String userID = "sa";
    private static String password = "1234567890";
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://DESKTOP-EBUN5JD:1433;databaseName=BaiGiangAPI;";
	public static Connection getConnection() throws SQLException, Exception {

		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(url, userID, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    public static void main(String[] args) {
        new DBConnection();
		try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLServerDriver not found. Please check your driver dependency.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database. Please check the connection details.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
        }
    }
}
