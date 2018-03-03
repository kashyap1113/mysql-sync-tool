package mysql;

import java.sql.*;

public class Database_Business_Logics {

	public static String get_all_database_names()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String jsonResponse = null;
		String sTableName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlsync", "root", "root");

			Statement st = con.createStatement();
			String sql = ("show tables;");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				sTableName = rs.getString("Tables_in_mysqlsync");
				
				/** Kashyap give me all table names in jsonResponse **/
				
			}
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return jsonResponse;
	}

}
