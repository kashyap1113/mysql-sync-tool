package com.kashyap1113.mysqlsynctool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kashyap1113.mysqlsynctool.model.dto.Tables;

public class DatabaseOperations {
    private Gson gson;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Connection connection;
    
	public DatabaseOperations() {
        super();
        gson = new GsonBuilder().setPrettyPrinting().create();        
    }

    public String getAllTables(String sDatabaseName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        sDatabaseName = "practice";
        connection = new ConnectionManager("localhost", "root", "root", 3306, sDatabaseName).getConnection();
		String jsonResponse = "";
		String sTableName = "";
		List<String> alTables = new ArrayList<String>();
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
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
		    if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) {}
		    }
		    if (connection != null) {
		        try { connection.close(); } catch (SQLException e) {}
		    }
		}
		Tables tables = new Tables(alTables);
		jsonResponse = gson.toJson(tables);
		return jsonResponse;
	}

}
