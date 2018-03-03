package mysql;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.Database_Business_Logics;
/**
 * Servlet implementation class database_requests
 */
public class database_requests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public database_requests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String scategory=request.getParameter("type");
		
		if(scategory.equals("getalldatabase")) {
			try {
				String sJsonResponse=Database_Business_Logics.get_all_database_names();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else if(scategory.equals("insert_local_connection")) {
			String sConnectionName=request.getParameter("connection_name");
			String sHost=request.getParameter("host");
			String sPort=request.getParameter("port");
			String sUsername=request.getParameter("username");
			String sPassword=request.getParameter("password");
			
		}
		else if(scategory.equals("insert_client_connection"))
		{
			String sConnectionName=request.getParameter("connection_name");
			String sHost=request.getParameter("host");
			String sPort=request.getParameter("port");
			String sUsername=request.getParameter("username");
			String sPassword=request.getParameter("password");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
