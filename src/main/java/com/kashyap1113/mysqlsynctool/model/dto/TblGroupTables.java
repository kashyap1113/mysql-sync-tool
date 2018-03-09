package com.kashyap1113.mysqlsynctool.model.dto;

public class TblGroupTables {    
    private int id;
    private int groupId;
    private String tableName;
    private String isSchema;
    private String isData;
    public TblGroupTables() {
        super();
    }
    public TblGroupTables(int id, int groupId, String tableName, String isSchema, String isData) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.tableName = tableName;
        this.isSchema = isSchema;
        this.isData = isData;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getIsSchema() {
        return isSchema;
    }
    public void setIsSchema(String isSchema) {
        this.isSchema = isSchema;
    }
    public String getIsData() {
        return isData;
    }
    public void setIsData(String isData) {
        this.isData = isData;
    }
    
}
