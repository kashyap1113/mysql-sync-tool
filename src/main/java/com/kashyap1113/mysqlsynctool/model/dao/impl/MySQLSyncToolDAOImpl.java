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
    
    public int insertConnection(TblConnections tblConnections) {
        int id = 0;
        sql = "INSERT INTO tbl_connections (connection_name, hostname, port, username, password, connection_type) VALUES (?,?,?,?,?,?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tblConnections.getConnectionName());
            pstmt.setString(2, tblConnections.getHostname());
            pstmt.setInt(3, tblConnections.getPortNo());
            pstmt.setString(4, tblConnections.getUsername());
            pstmt.setString(5, tblConnections.getPassword());
            pstmt.setString(6, tblConnections.getConnectionType());
            id = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return id;
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
        boolean result = false;
        sql = "UPDATE tbl_connections SET connection_name=?, hostname=?, port=?, username=?, password=?, connection_type=? WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);            
            pstmt.setString(1, tblConnections.getConnectionName());
            pstmt.setString(2, tblConnections.getHostname());
            pstmt.setInt(3, tblConnections.getPortNo());
            pstmt.setString(4, tblConnections.getUsername());
            pstmt.setString(5, tblConnections.getPassword());
            pstmt.setString(6, tblConnections.getConnectionName());
            pstmt.setInt(7, tblConnections.getId());
            pstmt.executeUpdate();  
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();            
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }

    public int insertConnectionGroup(TblConnectionGroups tblConnectionGroups) {
        int id = 0;
        sql = "INSERT INTO tbl_connection_groups (connection_id, group_name) VALUES (?,?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tblConnectionGroups.getConnectionId());
            pstmt.setString(2, tblConnectionGroups.getGroupName());
            id = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return id;
    }
    
    public boolean deleteConnection(int id) {
        boolean result = false;
        sql = "DELETE FROM tbl_connections WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);            
            pstmt.setInt(1, id);            
            pstmt.executeUpdate();  
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();            
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }

    public List<TblConnectionGroups> getAllConnectionGroups() {
        List<TblConnectionGroups> list = new ArrayList<TblConnectionGroups>();
        sql = "SELECT group_id, connection_id, group_name FROM tbl_connection_groups";
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblConnectionGroups(resultSet.getInt("group_id"), resultSet.getInt("connection_id"), resultSet.getString("group_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return list;
    }

    public TblConnectionGroups getConnectionGroupById(int id) {
        TblConnectionGroups tblConnectionGroups = new TblConnectionGroups();
        sql = "SELECT group_id, connection_id, group_name FROM tbl_connection_groups WHERE group_id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tblConnectionGroups = new TblConnectionGroups(resultSet.getInt("group_id"), resultSet.getInt("connection_id"), resultSet.getString("group_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return tblConnectionGroups;
    }

    public List<TblConnectionGroups> getAllConnectionGroupsByConnectionId() {
        List<TblConnectionGroups> list = new ArrayList<TblConnectionGroups>();
        sql = "SELECT group_id, connection_id, group_name FROM tbl_connection_groups WHERE connection_id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblConnectionGroups(resultSet.getInt("group_id"), resultSet.getInt("connection_id"), resultSet.getString("group_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return list;
    }

    public boolean updateConnectionGroup(TblConnectionGroups tblConnectionGroups) {
        boolean result = false;
        sql = "UPDATE tbl_connection_groups SET connection_id = ?, group_name=? WHERE group_id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tblConnectionGroups.getConnectionId());
            pstmt.setString(1, tblConnectionGroups.getGroupName());
            pstmt.setInt(2, tblConnectionGroups.getGroupId());
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }

    public boolean deleteConnectionGroup(int id) {
        boolean result = false;
        sql = "DELETE FROM tbl_connection_groups WHERE group_id=?";
        try {
            pstmt = connection.prepareStatement(sql);            
            pstmt.setInt(1, id);
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }

    public int insertGroupTable(TblGroupTables tblGroupTables) {
        int id = 0;
        sql = "INSERT INTO tbl_connection_groups (group_id, table_name, is_schema, is_data) VALUES (?,?,?,?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tblGroupTables.getGroupId());
            pstmt.setString(2, tblGroupTables.getTableName());
            pstmt.setString(3, tblGroupTables.getIsSchema());
            pstmt.setString(4, tblGroupTables.getIsData());
            id = pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return id;
    }
    
    public List<TblGroupTables> getAllGroupTables() {
        List<TblGroupTables> list = new ArrayList<TblGroupTables>();
        sql = "SELECT id, group_id, table_name, is_schema, is_data FROM tbl_group_tables";
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblGroupTables(
                        resultSet.getInt("id"), 
                        resultSet.getInt("group_id"), 
                        resultSet.getString("table_name"), 
                        resultSet.getString("is_schema"), 
                        resultSet.getString("is_data")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return list;
    }

    public List<TblGroupTables> getAllGroupTablesByGroupId(int groupId) {
        List<TblGroupTables> list = new ArrayList<TblGroupTables>();
        sql = "SELECT id, group_id, table_name, is_schema, is_data FROM tbl_group_tables WHERE group_id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, groupId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblGroupTables(
                        resultSet.getInt("id"), 
                        resultSet.getInt("group_id"), 
                        resultSet.getString("table_name"), 
                        resultSet.getString("is_schema"), 
                        resultSet.getString("is_data")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return list;        
    }

    public TblGroupTables getGroupTablesById(int id) {
        TblGroupTables tblGroupTables = new TblGroupTables();
        sql = "SELECT id, group_id, table_name, is_schema, is_data FROM tbl_group_tables WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tblGroupTables = new TblGroupTables(
                    resultSet.getInt("id"), 
                    resultSet.getInt("group_id"), 
                    resultSet.getString("table_name"), 
                    resultSet.getString("is_schema"), 
                    resultSet.getString("is_data")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return tblGroupTables;
    }

    public void updateGroupTablesList(List<TblGroupTables> tblGroupTablesList) {
        // TODO Auto-generated method stub
        
    }

    public boolean updateGroupTable(TblGroupTables tblGroupTables) {
        boolean result = false;
        sql = "UPDATE tbl_connection_groups SET group_id=?, table_name=?, is_schema=?, is_data=? WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tblGroupTables.getGroupId());
            pstmt.setString(2, tblGroupTables.getTableName());
            pstmt.setString(3, tblGroupTables.getIsSchema());
            pstmt.setString(4, tblGroupTables.getIsData());
            pstmt.setInt(5, tblGroupTables.getId());
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }

    public boolean deleteGroupTable(int id) {
        boolean result = false;
        sql = "DELETE FROM tbl_group_tables WHERE id=?";
        try {
            pstmt = connection.prepareStatement(sql);            
            pstmt.setInt(1, id);
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        } finally {            
            try { connection.close(); } catch (Exception ex) {}
        }
        return result;
    }
    
    
    

}
