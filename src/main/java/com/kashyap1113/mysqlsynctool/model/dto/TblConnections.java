package com.kashyap1113.mysqlsynctool.model.dto;

public class TblConnections {    
    private int id;
    private String connectionName;
    private String hostname;
    private int portNo;
    private String username;
    private String password;
    private String connectionType;
    public TblConnections() {
        super();
    }
    public TblConnections(int id, String connectionName, String hostname, int portNo, String username, String password,
            String connectionType) {
        super();
        this.id = id;
        this.connectionName = connectionName;
        this.hostname = hostname;
        this.portNo = portNo;
        this.username = username;
        this.password = password;
        this.connectionType = connectionType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getConnectionName() {
        return connectionName;
    }
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
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
    public String getConnectionType() {
        return connectionType;
    }
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
    
}
