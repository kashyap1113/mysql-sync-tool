package com.kashyap1113.mysqlsynctool.model;

public class ConnectionParams {
    private String databaseName;
    private String hostname;
    private int portNo;
    private String username;
    private String password;
    public ConnectionParams() {
        super();
    }
    public ConnectionParams(String databaseName, String hostname, int portNo, String username, String password) {
        super();
        this.databaseName = databaseName;
        this.hostname = hostname;
        this.portNo = portNo;
        this.username = username;
        this.password = password;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public int getPortNo() {
        return portNo;
    }
    public void setPortNo(int portNo) {
        this.portNo = portNo;
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
}
