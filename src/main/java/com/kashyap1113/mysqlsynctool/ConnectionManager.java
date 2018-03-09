package com.kashyap1113.mysqlsynctool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kashyap1113.mysqlsynctool.model.ConnectionParams;

public class ConnectionManager {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    private String host;
    private String username;
    private String password;
    private int port;
    private String database;
    private Connection connection;    

    public ConnectionManager() {
        super();
        host = "localhost";
        username = "root";
        password = "root";
        port = 3306;
    }

    public ConnectionManager(String host, String username, String password, int port, String database) {
        super();
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.database = database;
    }
    
    public ConnectionManager(ConnectionParams params) {
        this.database = params.getDatabaseName();
        this.host = params.getHostname();
        this.port = params.getPortNo();
        this.username = params.getUsername();
        this.password = params.getPassword();
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            ex.printStackTrace();
        }
        return connection;
    }
    
}
