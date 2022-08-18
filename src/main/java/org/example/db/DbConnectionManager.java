package org.example.db;

import java.sql.*;

public class DbConnectionManager {
    /** @see <a href="https://www.h2database.com">H2 Database Engine</a> */
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:./db/hello";
    static final String DB_USER = "admin";
    static final String DB_PASSWORD = "admin123";

    static final String CREATE_USER_AUTH_TABLE = "CREATE TABLE IF NOT EXISTS USER_AUTH(" +
            "NAME VARCHAR(100) PRIMARY KEY, " +
            "PASSWORD VARCHAR(50) NOT NULL);";

    private Connection connection;

    public Connection getConnection() {
        if (this.connection == null){
            try {
                Class.forName(JDBC_DRIVER);
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                this.connection = null;
            }
        }
        return this.connection;
    }

    public void releaseConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Load JDBC driver.
            Class.forName(JDBC_DRIVER);
            // The other way to load JDBC driver.
            // DriverManager.registerDriver(new org.h2.Driver());

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.execute(CREATE_USER_AUTH_TABLE);

            try {
                String insertAdminUser = "INSERT INTO USER_AUTH (NAME, PASSWORD) VALUES('admin', 'admin123');";
                stmt.executeUpdate(insertAdminUser);
            } catch (SQLException e) {
                System.out.println("user[admin] was already existed.");
            }

            String selectAdminUser = "SELECT * FROM USER_AUTH WHERE NAME = 'admin';";
            ResultSet rs = stmt.executeQuery(selectAdminUser);
            while (rs.next()) {
                String userName = rs.getString("NAME");
                String password = rs.getString("PASSWORD");
                System.out.printf("name[%s], password[%s].", userName, password);
            }
            rs.close();

            stmt.execute("SHUTDOWN");
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
