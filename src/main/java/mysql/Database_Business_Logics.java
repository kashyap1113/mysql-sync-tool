package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mysql.model.Tables;

public class Database_Business_Logics {
    Gson gson;
    
	public Database_Business_Logics() {
        super();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String get_all_database_names()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String jsonResponse = null;
		// Your db name here
		String sDatabaseName = "practice";
		String sTableName = "";
		List<String>  alTables = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + sDatabaseName, "root", "root");

			Statement st = con.createStatement();
			String sql = ("show tables;");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				sTableName = rs.getString("Tables_in_" + sDatabaseName);
				alTables.add(sTableName);
				System.out.println(sTableName);
			}			
			con.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		Tables tables = new Tables(alTables);
		jsonResponse = gson.toJson(tables);
		return jsonResponse;
	}

}
