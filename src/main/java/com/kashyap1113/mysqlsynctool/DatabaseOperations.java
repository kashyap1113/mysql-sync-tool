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

public class DatabaseOperations {
    private Gson gson;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Connection connection;
    
	public DatabaseOperations() {
        super();
        gson = new GsonBuilder().setPrettyPrinting().create();        
    }

    public String getAllTables(String sDatabaseName) {
        sDatabaseName = "practice";
        connection = new ConnectionManager("localhost", "root", "root", 3306, sDatabaseName).getConnection();		
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
    
    public String getAllLocalConnections(String sDatabaseName) {
        sDatabaseName = "practice";
        connection = new ConnectionManager("localhost", "root", "root", 3306, sDatabaseName).getConnection();
        String sql = "SELECT connection_name FROM tbl_local_connections";
        List<String> alConnections = new ArrayList<String>();
        ApiResponse<List<String>> apiResponse = new ApiResponse<List<String>>();
        try {
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                alConnections.add(resultSet.getString("connection_name"));
            }
            if (alConnections.size() == 0) {
                apiResponse.setResult(ApiResponse.RESULT_NO_DATA);
                apiResponse.setData(null);
            } else {                               
                apiResponse.setResult(ApiResponse.RESULT_SUCCESS);
                apiResponse.setData(alConnections);
            }
        } catch (SQLException e) {
            apiResponse.setResult(ApiResponse.RESULT_FAIL);
            apiResponse.setData(null);
            e.printStackTrace();
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

}