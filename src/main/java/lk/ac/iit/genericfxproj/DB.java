package lk.ac.iit.genericfxproj;

import javafx.scene.control.Alert;
import java.sql.*;

public class DB {
    Connection connection = null;
    Statement statement = null;
    private static DB instance;
    private  DB () {
        try {
            // Register SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");

            // Create a new table
            statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "first_name TEXT, " +
                    "last_name TEXT, " +
                    "age INTEGER, " +
                    "birthday TEXT, " +
                    "gender TEXT, " +
                    "civil_status TEXT, " +
                    "country TEXT, " +
                    "email TEXT, " +
                    "mobile TEXT, " +
                    "password TEXT" +
                    ")";
            statement.execute(createTableQuery);
            System.out.println("Table initialized successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        instance = this;
    }

    public static DB getInstance() {
        if (instance == null) {
            new DB();
        }

        return instance;
    }

    boolean addUser(User usr) {
        String query;
        PreparedStatement preparedStatement;
        // checking if username already exists
        try {
            query = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usr.username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username in use");
                alert.setContentText(
                        "Username is already in use. " +
                        "Please try another unique username"
                );

                alert.showAndWait();
                return false;
            }
            else {
                query = "INSERT INTO users (" +
                        "first_name, " +
                        "last_name, " +
                        "age, " +
                        "birthday, " +
                        "gender, " +
                        "civil_status, " +
                        "country, " +
                        "email, " +
                        "mobile, " +
                        "username, " +
                        "password" +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, usr.firstName);
                    preparedStatement.setString(2, usr.lastName);
                    preparedStatement.setInt(3, usr.age);
                    preparedStatement.setString(4, usr.birthday);
                    preparedStatement.setString(5, usr.gender.toString());
                    preparedStatement.setString(6, usr.civilStatus);
                    preparedStatement.setString(7, usr.country);
                    preparedStatement.setString(8, usr.email);
                    preparedStatement.setString(9, usr.mobile);
                    preparedStatement.setString(10, usr.username);
                    preparedStatement.setString(11, usr.password);

                    preparedStatement.executeUpdate();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    void printDB () {
        String query = "SELECT * FROM users";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

            resultSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean validateCredentials (String name, String pw) {
        String query = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                if (password.equals(pw)) {
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        DB.getInstance().printDB();
    }
}
