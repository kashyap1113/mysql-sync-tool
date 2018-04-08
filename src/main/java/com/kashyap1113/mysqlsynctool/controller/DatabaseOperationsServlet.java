package com.kashyap1113.mysqlsynctool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kashyap1113.mysqlsynctool.DatabaseOperations;
import com.kashyap1113.mysqlsynctool.model.ConnectionParams;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnectionGroups;
import com.kashyap1113.mysqlsynctool.model.dto.TblConnections;
import com.kashyap1113.mysqlsynctool.model.dto.TblGroupTables;

/**
 * Servlet implementation class database_requests
 */
public class DatabaseOperationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
        String connectionParamsJson = request.getParameter("connectionParams");
        String values = request.getParameter("values");
        
        PrintWriter writer = response.getWriter();
        
//        ConnectionParams connectionParams = new Gson().fromJson(connectionParamsJson, ConnectionParams.class);
        ConnectionParams connectionParams = new ConnectionParams("mysqlsync", "localhost", 3306, "root", "root");
        
        DatabaseOperations dbo = new DatabaseOperations(connectionParams);
        String sJsonResponse = "";
        
        try {
            if (type.equals("getAllTables")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllTables&values={databaseName:"mysql",hostname:"localhost",portNo:3306,username:"root",password:"root"}
                ConnectionParams remoteConnectionParams = gson.fromJson(values, ConnectionParams.class);
                if (remoteConnectionParams != null) {
                    connectionParams = remoteConnectionParams;
                    dbo = new DatabaseOperations(connectionParams);
                }
                sJsonResponse = dbo.getAllTables(connectionParams.getDatabaseName());
            } else if (type.equals("getConnectionList")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getConnectionList
                sJsonResponse = dbo.getConnectionList();
            }else if (type.equals("getAllConnections")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllConnections
                sJsonResponse = dbo.getAllConnections();
            } else if (type.equals("getAllConnectionGroups")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllConnectionGroups
                sJsonResponse = dbo.getAllConnectionGroups();
            } else if (type.equals("getAllGroupTables")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllGroupTables
                sJsonResponse = dbo.getAllGroupTables();
            } else if (type.equals("insertGroupTable")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=insertGroupTable&values={"groupId":1,"tableName":"test_table","isSchema":"TRUE","isData":"FALSE"}
                TblGroupTables tblGroupTables = gson.fromJson(values, TblGroupTables.class);
                sJsonResponse = dbo.insertGroupTable(tblGroupTables);
            } else if (type.equals("insertConnectionGroup")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=insertConnectionGroup&values={"connectionId":"2","groupName":"test_group2"}
                TblConnectionGroups tblConnectionGroups = gson.fromJson(values, TblConnectionGroups.class);
                sJsonResponse = dbo.insertConnectionGroup(tblConnectionGroups);
            } else if (type.equals("insertConnection")) {
             // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=insertConnectionGroup&values={"connectionName":"conn1","hostname":"local1","portNo":3308,"username":"root","password":"toor","connectionType":"local"}
                TblConnections tblConnections = gson.fromJson(values, TblConnections.class);
                sJsonResponse = dbo.insertConnection(tblConnections);
            } else if (type.equals("getGroupsByConnection")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getGroupsByConnection
                sJsonResponse = dbo.getGroupsByConnection();
            } else if (type.equals("getGroupTablesByGroupId")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getGroupTablesByGroupId&values=1
                int groupId = Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getGroupTablesByGroupId(groupId);
            } else if (type.equals("getConnectionById")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getConnectionById&values=3
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getConnectionById(connectionId);
            } else if (type.equals("updateConnection")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=updateConnection&values={"id":2,"connectionName":"conn1","hostname":"local1","portNo":3308,"username":"root","password":"toor","connectionType":"local"}
                TblConnections tblConnection = gson.fromJson(values, TblConnections.class);
                sJsonResponse = dbo.updateConnection(tblConnection);
            } else if (type.equals("deleteConnection")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=deleteConnection&values=2
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.deleteConnection(connectionId);
            } else if (type.equals("getConnectionGroupById")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getConnectionGroupById&values=2
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getConnectionGroupById(connectionId);
            } else if (type.equals("getAllConnectionGroupsByConnectionId")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllConnectionGroupsByConnectionId&values=2
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getAllConnectionGroupsByConnectionId(connectionId);
            } else if (type.equals("updateConnectionGroup")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=updateConnectionGroup&values={"groupId":2,"connectionId":2,"groupName":"test_group5"}
                TblConnectionGroups tblConnectionGroup = gson.fromJson(values, TblConnectionGroups.class);
                sJsonResponse = dbo.updateConnectionGroup(tblConnectionGroup);
            } else if (type.equals("deleteConnectionGroup")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=deleteConnectionGroup&values=3
                int id = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.deleteConnectionGroup(id);
            } else if (type.equals("getAllGroupTablesByGroupId")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=getAllGroupTablesByGroupId&values=2
                int id = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getAllGroupTablesByGroupId(id);
            } else if (type.equals("deleteGroupTable")) {
                // http://localhost:8080/mysqlsynctool/databaseoperations.do?type=deleteGroupTable&values=3
                int id = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.deleteGroupTable(id);
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
