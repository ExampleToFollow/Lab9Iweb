package com.example.lab9iweb.Daos;

public abstract class  DaoBase {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String username = "root";
        String password = "root";
        String database = "lab_9";
        String url = "jdbc:mysql://localhost:3306/" + database;

        return DriverManager.getConnection(url, username, password);
    }

}
