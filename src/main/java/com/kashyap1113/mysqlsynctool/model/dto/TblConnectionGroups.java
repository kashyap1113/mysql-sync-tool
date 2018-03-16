package com.kashyap1113.mysqlsynctool.model.dto;

public class TblConnectionGroups {
    private int groupId;
    private int connectionId;
    private String groupName;    
    public TblConnectionGroups() {
        super();
    }
    
    public TblConnectionGroups(int groupId, int connectionId, String groupName) {
        super();
        this.groupId = groupId;
        this.connectionId = connectionId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }    
}
