package com.kashyap1113.mysqlsynctool.model.dto;

import java.util.List;

public class ConnectionAndGroups {
    private int connectionId;
    private String connectionName;
    private List<TblConnectionGroups> groups;
    public ConnectionAndGroups() {
        super();
    }
    public ConnectionAndGroups(int connectionId, String connectionName, List<TblConnectionGroups> groups) {
        super();
        this.connectionId = connectionId;
        this.connectionName = connectionName;
        this.groups = groups;
    }
    public int getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
    public String getConnectionName() {
        return connectionName;
    }
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }
    public List<TblConnectionGroups> getGroups() {
        return groups;
    }
    public void setGroups(List<TblConnectionGroups> groups) {
        this.groups = groups;
    }
    
}
