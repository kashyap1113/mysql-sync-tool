package com.kashyap1113.mysqlsynctool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kashyap1113.mysqlsynctool.DatabaseOperations;
import com.kashyap1113.mysqlsynctool.model.ConnectionParams;

/**
 * Servlet implementation class database_requests
 */
public class DatabaseOperationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseOperationsServlet() {
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
        String type = request.getParameter("type");
        PrintWriter writer = response.getWriter();
        
        ConnectionParams connectionParams = new ConnectionParams();
        connectionParams.setHostname("localhost");
        connectionParams.setPortNo(3306);
        connectionParams.setUsername("root");
        connectionParams.setPassword("root");
        DatabaseOperations dbo = new DatabaseOperations(connectionParams);
        String sJsonResponse = "";
        String sDatabaseName = "mysqlsync";

        try {
            if (type.equals("getAllTables")) {
                sJsonResponse = dbo.getAllTables(sDatabaseName);
            } else if (type.equals("getAllConnections")) {
                sJsonResponse = dbo.getAllConnections2(sDatabaseName);
            } else if (type.equals("getAllConnectionGroups")) {
                sJsonResponse = dbo.getAllConnectionGroups(sDatabaseName);
            } else if (type.equals("getAllGroupTables")) {
                sJsonResponse = dbo.getAllGroupTables(sDatabaseName);
            } else if (type.equals("getAllConnectionGroups")) {
                sJsonResponse = dbo.getAllConnectionGroups(sDatabaseName);
            } else if (type.equals("getAllConnectionGroups")) {
                sJsonResponse = dbo.getAllConnectionGroups(sDatabaseName);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        writer.print(sJsonResponse);
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
