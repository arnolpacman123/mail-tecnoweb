package Datos;

import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static Statement statement;
    private static final String HOST = "jdbc:postgresql://mail.tecnoweb.org.bo:5432/db_grupo23sc";
    private static final String USER = "grupo23sc";
    private static final String PASS = "grup023grup023";

    public DBConnection() {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(HOST, USER, PASS);
            System.out.println("Connected");
            return true;
        } catch (SQLException e) {
            System.err.println("Connect error: " + e.getMessage());
            return false;
        }
    }

    public boolean close() {
        try {
            if (connection != null) {
                System.out.println("Disconnected");
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Disconnect error: " + e.getMessage());
            return false;
        }
    }

    public ResultSet select(String query) throws SQLException {
        statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Select send");
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Select error " + e.getMessage());
            return null;
        }
    }

    public int insert(String query) throws SQLException {
        statement = connection.createStatement();
        try {
            statement.executeUpdate(query);
            System.out.println("Insert send");
        } catch (SQLException e) {
            System.err.println("Insert error " + e.getMessage());
        }
        return 0;
    }

    public int update(String query) throws SQLException {
        statement = connection.createStatement();
        try {
            statement.executeUpdate(query);
            System.out.println("Update send");

        } catch (SQLException e) {
            System.err.println("Update error " + e.getMessage());
        }
        return 0;
    }

    public int delete(String query) throws SQLException {
        statement = connection.createStatement();
        try {
            statement.executeUpdate(query);
            System.out.println("Delete send");
        } catch (SQLException e) {
            System.err.println("Delete error " + e.getMessage());
        }
        return 0;
    }

    
}
