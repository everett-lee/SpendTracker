import OptionsMenu.Menu;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Connection connection = startConnection();
        try {
            Menu menu = new Menu(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection startConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/spendchecker";
            String user = "";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database");
                return connection;
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred. Possible incorrect username or password");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
