package com.kashyap1113.mysqlsynctool.model.dto;

import java.util.List;

public class GroupAndTable {
    private TblConnectionGroups tblConnectionGroup;
    private List<TblGroupTables> groupTables;
    public GroupAndTable() {
        super();
    }
    public GroupAndTable(TblConnectionGroups tblConnectionGroup, List<TblGroupTables> groupTables) {
        super();
        this.tblConnectionGroup = tblConnectionGroup;
        this.groupTables = groupTables;
    }
    public TblConnectionGroups getTblConnectionGroup() {
        return tblConnectionGroup;
    }
    public void setTblConnectionGroup(TblConnectionGroups tblConnectionGroup) {
        this.tblConnectionGroup = tblConnectionGroup;
    }
    public List<TblGroupTables> getGroupTables() {
        return groupTables;
    }
    public void setGroupTables(List<TblGroupTables> groupTables) {
        this.groupTables = groupTables;
    }
    
}
