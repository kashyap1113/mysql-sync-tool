package mysql;

import java.sql.*;

public class Database_Business_Logics {

	public static String get_all_database_names() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String jsonResponse = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");

			Statement st = con.createStatement();
			String sql = ("show tables;");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String sTableName = rs.getString("Tables_in_mysqlsync");
			}
			con.close();
		} catch (SQLException ex) {

		}

		return jsonResponse;
	}

}
