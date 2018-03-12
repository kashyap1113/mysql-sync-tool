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
                sJsonResponse = dbo.getAllTables(connectionParams.getDatabaseName());
            } else if (type.equals("getConnectionList")) {
                sJsonResponse = dbo.getConnectionList();
            }else if (type.equals("getAllConnections")) {
                sJsonResponse = dbo.getAllConnections();
            } else if (type.equals("getAllConnectionGroups")) {
                sJsonResponse = dbo.getAllConnectionGroups();
            } else if (type.equals("getAllGroupTables")) {
                sJsonResponse = dbo.getAllGroupTables();
            } else if (type.equals("insertGroupTable")) {
                TblGroupTables tblGroupTables = gson.fromJson(values, TblGroupTables.class);
                sJsonResponse = dbo.insertGroupTable(tblGroupTables);
            } else if (type.equals("insertConnectionGroup")) {
                TblConnectionGroups tblConnectionGroups = gson.fromJson(values, TblConnectionGroups.class);
                sJsonResponse = dbo.insertConnectionGroup(tblConnectionGroups);
            } else if (type.equals("insertConnection")) {
                TblConnections tblConnections = gson.fromJson(values, TblConnections.class);
                sJsonResponse = dbo.insertConnection(tblConnections);
            } else if (type.equals("getGroupsByConnection")) {                
                sJsonResponse = dbo.getGroupsByConnection();
            } else if (type.equals("getGroupTablesByGroupId")) {
                int groupId = Integer.parseInt(request.getParameter("groupId"));
                sJsonResponse = dbo.getGroupTablesByGroupId(groupId);
            } else if (type.equals("getConnectionById")) {
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.getConnectionById(connectionId);
            } else if (type.equals("updateConnection")) {
                TblConnections tblConnection = gson.fromJson(values, TblConnections.class);
                sJsonResponse = dbo.updateConnection(tblConnection);
            } else if (type.equals("deleteConnection")) {
                int connectionId = request.getParameter("values") == null ? 
                        0 : Integer.parseInt(request.getParameter("values"));
                sJsonResponse = dbo.deleteConnection(connectionId);
            } else if (type.equals("")) {
                
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
