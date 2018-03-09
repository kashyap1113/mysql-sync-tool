package com.kashyap1113.mysqlsynctool.model.dao;

import java.sql.Connection;
import java.util.List;

import com.kashyap1113.mysqlsynctool.model.ConnectionParams;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnectionGroups;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnections;
import com.kashyap1113.mysqlsynctool.model.dto.TblGroupTables;

public interface MySQLSyncToolDAO {
    public List<TblConnections> getAllConnections();
    public TblConnections getConnectionById(int id);
    public boolean updateConnection(TblConnections tblConnections);
    public boolean deleteConnection(int id);
    
    public List<TblConnectionGroups> getAllConnectionGroups();
    public TblConnectionGroups getConnectionGroupById(int id);
    public boolean updateConnectionGroup(TblConnectionGroups tblConnectionGroups);
    public boolean deleteConnectionGroup(int id);
    
    public List<TblGroupTables> getAllGroupTables();
    public List<TblGroupTables> getAllGroupTablesByGroupId(int groupId);    
    public TblGroupTables getGroupTablesById(int id);
    public void updateGroupTablesList(List<TblGroupTables> tblGroupTablesList);
    public boolean updateGroupTable(TblGroupTables tblGroupTables);
    public boolean deleteGroupTable(int id);
    
}
