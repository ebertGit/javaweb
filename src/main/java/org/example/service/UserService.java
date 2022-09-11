package org.example.service;

import org.example.db.DbConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserService {

    // TODO use singleton.
    private final DbConnectionManager dbConnectionManager;

    public UserService(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    /**
     * Authenticate The name and password.<br>
     * When the password is empty, the authenticate result will be false.
     * @param name user name
     * @param password user password
     * @return the result of authentication. {@code true}:passed, {@code false}:failed.
     */
    public boolean userAuthentication(String name, String password){
        // If password is empty, then return false.
        if (password == null || password == ""){
            return false;
        }

        // Get DB connection.
        Connection conn = dbConnectionManager.getConnection();

        // Get password use the given name.
        String resultPassword = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT PASSWORD FROM USER_AUTH WHERE NAME = ?;");
            // Set SQL parameter.
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                // Get password with search result in the first row.
                resultPassword = resultSet.getString("PASSWORD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Compare password and return the compare result.
        return Objects.equals(password, resultPassword);
    }

    public int userRegistration(String name, String password){
        // Get DB connection.
        Connection conn = dbConnectionManager.getConnection();

        int count = 0;
        try {
            conn.setAutoCommit(true);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO USER_AUTH (NAME, PASSWORD) VALUES(?, ?);");
            // Set SQL parameter.
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            count = preparedStatement.executeUpdate();
            // conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
