package com.kashyap1113.mysqlsynctool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kashyap1113.mysqlsynctool.model.ApiResponse;
import com.kashyap1113.mysqlsynctool.model.ConnectionParams;
import com.kashyap1113.mysqlsynctool.model.dao.MySQLSyncToolDAO;
import com.kashyap1113.mysqlsynctool.model.dao.impl.MySQLSyncToolDAOImpl;
import com.kashyap1113.mysqlsynctool.model.dto.ConnectionAndGroups;
import com.kashyap1113.mysqlsynctool.model.dto.GroupAndTable;
import com.kashyap1113.mysqlsynctool.model.dto.IdValue;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnectionGroups;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnections;
import com.kashyap1113.mysqlsynctool.model.dto.TblGroupTables;

public class DatabaseOperations {
    private Gson gson;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Connection connection;
    private ConnectionParams connectionParams;
    private MySQLSyncToolDAO dao;
    
	public DatabaseOperations() {
        super();
        gson = new GsonBuilder().setPrettyPrinting().create();  
        connectionParams = new ConnectionParams();        
    }
	
	public DatabaseOperations(ConnectionParams connectionParams) {
	    this();
	    this.connectionParams = connectionParams;
	    dao = new MySQLSyncToolDAOImpl(connectionParams);
	}

    public String getAllTables(String sDatabaseName) {        
        connection = new ConnectionManager(connectionParams).getConnection();		
		String sTableName = "";
		List<String> alTables = new ArrayList<String>();
		ApiResponse<List<String>> apiResponse = new ApiResponse<List<String>>();
		try {			
			String sql = "SELECT TABLE_NAME AS tables_list FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, sDatabaseName);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				sTableName = resultSet.getString("tables_list");
				alTables.add(sTableName);
				System.out.println(sTableName);
			}
			if (alTables.size() == 0) {
                apiResponse.setResult(ApiResponse.RESULT_NO_DATA);
                apiResponse.setData(null);
            } else {                               
                apiResponse.setResult(ApiResponse.RESULT_SUCCESS);
                apiResponse.setData(alTables);
            }
		} catch (SQLException ex) {
		    apiResponse.setResult(ApiResponse.RESULT_FAIL);
            apiResponse.setData(null);
			ex.printStackTrace();
		} finally {
		    if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) {}
		    }
		    if (connection != null) {
		        try { connection.close(); } catch (SQLException e) {}
		    }
		}
		return gson.toJson(apiResponse);
	}
        
    public String getAllConnections() {        
        ApiResponse<List<TblConnections>> apiResponse = new ApiResponse<List<TblConnections>>();        
        List<TblConnections> list = dao.getAllConnections();        
        if (list.size() > 0) {
            apiResponse.setResultSuccess();
            apiResponse.setData(list);
        } else {
            apiResponse.setResultNoData();
        }
        return gson.toJson(apiResponse);
    }
    
    
    
    public String getAllConnectionGroups() {
        ApiResponse<List<TblConnectionGroups>> apiResponse = new ApiResponse<List<TblConnectionGroups>>();        
        List<TblConnectionGroups> list = dao.getAllConnectionGroups();        
        if (list.size() > 0) {
            apiResponse.setResultSuccess();
            apiResponse.setData(list);
        } else {
            apiResponse.setResultNoData();
        }
        return gson.toJson(apiResponse);
    }
    
    public String getAllGroupTables() {
        ApiResponse<List<TblGroupTables>> apiResponse = new ApiResponse<List<TblGroupTables>>();
        List<TblGroupTables> list = dao.getAllGroupTables();        
        if (list.size() > 0) {
            apiResponse.setResultSuccess();
            apiResponse.setData(list);
        } else {
            apiResponse.setResultNoData();
        }
        return gson.toJson(apiResponse);
    }

    public String insertConnection(TblConnections tblConnections) {
        String result = "";
        int id = dao.insertConnection(tblConnections);
        if (id > 0) {
            result = "success";
        } else {
            result = "fail";
        }
        return gson.toJson(result);
    }
    
    public String insertConnectionGroup(TblConnectionGroups tblConnectionGroups) {
        String result = "";
        int id = dao.insertConnectionGroup(tblConnectionGroups);
        if (id > 0) {
            result = "success";
        } else {
            result = "fail";
        }
        return gson.toJson(result);
    }
    
    public String insertGroupTable(TblGroupTables tblGroupTables) {
        String result = "";
        int id = dao.insertGroupTable(tblGroupTables);
        if (id > 0) {
            result = "success";
        } else {
            result = "fail";
        }
        return gson.toJson(result);
    }
    
    public String getConnectionList() {
        ApiResponse<List<IdValue>> apiResponse = new ApiResponse<List<IdValue>>();
        List<TblConnections> list = dao.getAllConnections();
        List<IdValue> connectionsList = new ArrayList<IdValue>();
        for (TblConnections tblConnection : list) {
            connectionsList.add(new IdValue(tblConnection.getId(), tblConnection.getConnectionName()));
        }
        if (connectionsList.size() > 0) {
            apiResponse.setResultSuccess();
            apiResponse.setData(connectionsList);
        } else {
            apiResponse.setResultNoData();
        }
        return gson.toJson(apiResponse);
    }
    
    public String getGroupsByConnection() {
        ApiResponse<List<ConnectionAndGroups>> apiResponse = new ApiResponse<List<ConnectionAndGroups>>();
        List<ConnectionAndGroups> list = new ArrayList<ConnectionAndGroups>();
        List<TblConnections> tblConnectionList = dao.getAllConnections();
        for (TblConnections connection : tblConnectionList) {
            List<TblConnectionGroups> tblConnectionGroupsList = dao.getAllConnectionGroupsByConnectionId(connection.getId());
            list.add(new ConnectionAndGroups(connection.getId(), connection.getConnectionName(), tblConnectionGroupsList));
        }
        if (list.size() > 0) {
            apiResponse.setResultSuccess();
            apiResponse.setData(list);
        } else {
            apiResponse.setResultNoData();
        }
        return gson.toJson(apiResponse);
    }
    
    public String getGroupTablesByGroupId(int groupId) {
        ApiResponse<GroupAndTable> apiResponse = new ApiResponse<GroupAndTable>();
        GroupAndTable groupAndTable = null;
        try {
            TblConnectionGroups tblConnectionGroup = dao.getConnectionGroupById(groupId);
            List<TblGroupTables> groupTablesList = dao.getAllGroupTablesByGroupId(tblConnectionGroup.getGroupId());
            groupAndTable = new GroupAndTable(tblConnectionGroup, groupTablesList);
            apiResponse.setResultSuccess();
            apiResponse.setData(groupAndTable);
        } catch (Exception ex) {
            apiResponse.setResultFail();
        }
        return gson.toJson(apiResponse);
    }
    
    public String getConnectionById(int connectionId) {
        ApiResponse<TblConnections> apiResponse = new ApiResponse<TblConnections>();
        TblConnections tblConnection = dao.getConnectionById(connectionId);
        if (tblConnection != null) {
            apiResponse.setResultSuccess();
            apiResponse.setData(tblConnection);
        } else {
            apiResponse.setResultFail();
        }
        return gson.toJson(apiResponse);
    }
    
    public String deleteConnection(int connectionId) {
        String result = "";
        if (dao.deleteConnection(connectionId)) {
            result = "success";
        } else {
            result = "fail";
        }
        return gson.toJson(result);        
    }
    
    public String updateConnection(TblConnections tblConnection) {
        String result = "";
        if (dao.updateConnection(tblConnection)) {
            result = "success";
        } else {
            result = "fail";
        }
        return gson.toJson(result);
    }
}
