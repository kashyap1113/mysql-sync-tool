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
        List<TblConnectionGroups> list = new ArrayList<TblConnectionGroups>();
        sql = "SELECT group_id, group_name FROM tbl_connection_groups";
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(new TblConnectionGroups(resultSet.getInt("group_id"), resultSet.getString("group_name")));
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
        sql = "SELECT group_id, group_name FROM tbl_connection_groups WHERE group_id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tblConnectionGroups = new TblConnectionGroups(resultSet.getInt("group_id"), resultSet.getString("group_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (Exception ex) {}
            try { connection.close(); } catch (Exception ex) {}
        }
        return tblConnectionGroups;
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
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteGroupTable(int id) {
        // TODO Auto-generated method stub
        return false;
    }

}
