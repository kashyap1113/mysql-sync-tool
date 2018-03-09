package com.kashyap1113.mysqlsynctool.model.dto;

public class TblConnectionGroups {
    private int groupId;
    private String groupName;
    public TblConnectionGroups() {
        super();
    }
    public TblConnectionGroups(int groupId, String groupName) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }    
}
