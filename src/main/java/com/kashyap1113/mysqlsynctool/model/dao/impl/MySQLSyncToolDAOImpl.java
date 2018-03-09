package com.kashyap1113.mysqlsynctool.model.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kashyap1113.mysqlsynctool.model.ConnectionParams;
import com.kashyap1113.mysqlsynctool.model.dao.MySQLSyncToolDAO;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnectionGroups;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnections;
import com.kashyap1113.mysqlsynctool.model.dto.TblGroupTables;

public class MySQLSyncToolDAOImpl extends BaseDAOImpl implements MySQLSyncToolDAO {
    private String sql;
    public MySQLSyncToolDAOImpl(ConnectionParams connectionParams) {
        super(connectionParams);
    }
    public List<TblConnections> getAllConnections() {
        List<TblConnections> list = new ArrayList<TblConnections>();
        sql = "SELECT id, connection_name, hostname, port, username, password, connection_type FROM tbl_connections";        
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblConnections(
                        resultSet.getInt("id"), 
                        resultSet.getString("connection_name"), 
                        resultSet.getString("hostname"), 
                        resultSet.getInt("port"), 
                        resultSet.getString("username"), 
                        resultSet.getString("password"), 
                        resultSet.getString("connection_type")
                                )
                        );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return list;
    }

    public TblConnections getConnectionById(int id) {
        TblConnections tblConnections = new TblConnections();
        sql = "SELECT id, connection_name, hostname, port, username, password, connection_type FROM tbl_connections WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tblConnections = new TblConnections(
                        resultSet.getInt("id"), 
                        resultSet.getString("connection_name"), 
                        resultSet.getString("hostname"), 
                        resultSet.getInt("port"), 
                        resultSet.getString("username"), 
                        resultSet.getString("password"), 
                        resultSet.getString("connection_type")
                                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        
        return tblConnections;
    }

    public boolean updateConnection(TblConnections tblConnections) {
        /*
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        */
        return false;
    }

    public boolean deleteConnection(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<TblConnectionGroups> getAllConnectionGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    public TblConnectionGroups getConnectionGroupById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean updateConnectionGroup(TblConnectionGroups tblConnectionGroups) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteConnectionGroup(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<TblGroupTables> getAllGroupTables() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TblGroupTables> getAllGroupTablesByGroupId(int groupId) {
        // TODO Auto-generated method stub
        return null;
    }

    public TblGroupTables getGroupTablesById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateGroupTablesList(List<TblGroupTables> tblGroupTablesList) {
        // TODO Auto-generated method stub
        
    }

    public boolean updateGroupTable(TblGroupTables tblGroupTables) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteGroupTable(int id) {
        // TODO Auto-generated method stub
        return false;
    }

}
