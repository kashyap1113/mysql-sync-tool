package mysql;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            String sRequestCategory = request.getParameter("type");
            String sConnectionName = null;
            String sHost = null;
            String sPort = null;
            String sUsername = null;
            String sPassword = null;
            String sLocalConnectionId = null;
            String sClientConnectionId = null;

            PrintWriter writer = response.getWriter();
            Database_Business_Logics dbl = new Database_Business_Logics();
            String sJsonResponse = "";

            if (sRequestCategory.equals("getalldatabase")) {

                sJsonResponse = dbl.get_all_database_names();
                writer.print(sJsonResponse);

            } else if (sRequestCategory.equals("view_all_local_connections")) {

                sJsonResponse = dbl.view_all_local_connections();
                

            } else if (sRequestCategory.equals("insert_local_connection")) {

                sConnectionName = request.getParameter("connection_name");
                sHost = request.getParameter("host");
                sPort = request.getParameter("port");
                sUsername = request.getParameter("username");
                sPassword = request.getParameter("password");

                sJsonResponse = dbl.insert_local_connection(sConnectionName, sHost, sPort, sUsername, sPassword);

            } else if (sRequestCategory.equals("view_local_connection")) {

                sLocalConnectionId = request.getParameter("local_connection_id");

            } else if (sRequestCategory.equals("edit_local_connection")) {

                sLocalConnectionId = request.getParameter("local_connection_id");
                sConnectionName = request.getParameter("connection_name");
                sHost = request.getParameter("host");
                sPort = request.getParameter("port");
                sUsername = request.getParameter("username");
                sPassword = request.getParameter("password");

            } else if (sRequestCategory.equals("delete_local_connection")) {

                sLocalConnectionId = request.getParameter("local_connection_id");

            } else if (sRequestCategory.equals("view_all_clients_connection")) {

            } else if (sRequestCategory.equals("insert_client_connection")) {

                sConnectionName = request.getParameter("connection_name");
                sHost = request.getParameter("host");
                sPort = request.getParameter("port");
                sUsername = request.getParameter("username");
                sPassword = request.getParameter("password");

            } else if (sRequestCategory.equals("view_client_connection")) {

                sClientConnectionId = request.getParameter("client_connection_id");

            } else if (sRequestCategory.equals("edit_client_connection")) {

                sClientConnectionId = request.getParameter("client_connection_id");
                sConnectionName = request.getParameter("connection_name");
                sHost = request.getParameter("host");
                sPort = request.getParameter("port");
                sUsername = request.getParameter("username");
                sPassword = request.getParameter("password");

            } else if (sRequestCategory.equals("delete_client_connection")) {

                sClientConnectionId = request.getParameter("client_connection_id");

            }
            writer.print(sJsonResponse);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

}
