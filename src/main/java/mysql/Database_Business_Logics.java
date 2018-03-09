package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mysql.model.ApiResponse;

public class Database_Business_Logics {
	Gson gson;
	ApiResponse jsonResult;
	public Database_Business_Logics() {
		super();
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public String get_all_database_names()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String jsonResponse = null;
		// Your db name here
		String sDatabaseName = "mysqlsync";
		String sTableName = "";
		List<String> alTables = new ArrayList<String>();
		ApiResponse<List<String>> jsonResult = new ApiResponse<List<String>>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sDatabaseName, "root",
					"root");

			Statement st = con.createStatement();
			String sql = ("show tables;");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				sTableName = rs.getString("Tables_in_" + sDatabaseName);
				alTables.add(sTableName);
			}
			rs.close();
			con.close();
			
			if (alTables.size() == 0) {
			    jsonResult.setResult(ApiResponse.RESULT_NO_DATA);
			    jsonResult.setData(null);
			} else {    			    	        
    	        jsonResult.setResult(ApiResponse.RESULT_SUCCESS);
    	        jsonResult.setData(alTables);
			}
		} catch (SQLException ex) {
		    jsonResult.setResult(ApiResponse.RESULT_FAIL);
            jsonResult.setData(null);
			ex.printStackTrace();
		}
		
		jsonResponse = gson.toJson(jsonResult);
		return jsonResponse;
	}

	public String view_all_local_connections()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	    ApiResponse<List<String>> jsonResult = new ApiResponse<List<String>>();
		String jsonResponse = null;

		// Your db name here
		String sDatabaseName = "mysqlsync";
		String sConnectionName = "";
		List<String> alTables = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sDatabaseName, "root",
					"root");
			
			Statement st = con.createStatement();
			String sql = ("SELECT connection_name FROM tbl_local_connections;");
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				sConnectionName = rs.getString("connection_name");
				alTables.add(sConnectionName);
			}
			rs.close();
			con.close();
			if (alTables.size() == 0) {
                jsonResult.setResult(ApiResponse.RESULT_NO_DATA);
                jsonResult.setData(null);
            } else {                               
                jsonResult.setResult(ApiResponse.RESULT_SUCCESS);
                jsonResult.setData(alTables);
            }
		} catch (SQLException ex) {
		    jsonResult.setResult(ApiResponse.RESULT_FAIL);
            jsonResult.setData(null);
			ex.printStackTrace();
		}		
		jsonResponse = gson.toJson(jsonResult);
		return jsonResponse;

	}

	public String insert_local_connection(String sConnectionName,
			String sHost,String sPort,String sUsername,String sPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String jsonResponse = null;
		ApiResponse<Map<String, String>> jsonResult = new ApiResponse<Map<String, String>>();
		Map<String, String> mapData = new HashMap<String, String>();
		// Your db name here
		String sDatabaseName = "mysqlsync";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sDatabaseName, "root",
					"root");

			Statement st = con.createStatement();
			String sql = ("insert into tbl_local_connections (connection_name,host,port,username,password) values "
					+ "('"+ sConnectionName +"','"+ sHost +"','"+ sPort +"','"+ sUsername +"','"+ sPassword +"')");
			int iUpdatedRecords = st.executeUpdate(sql);
			con.close();
			
		    jsonResult.setResult(ApiResponse.RESULT_SUCCESS);
		    mapData.put("updated_records", "" + iUpdatedRecords);
		    jsonResult.setData(mapData);
			

		} catch (SQLException ex) {
			ex.printStackTrace();
			jsonResult.setResult(ApiResponse.RESULT_FAIL);
			jsonResult.setData(null);
		}
		jsonResponse = gson.toJson(jsonResult);
		return jsonResponse;
	}

	public String getAllClientConnection() {
	    
	}
	
	
	
	
}
