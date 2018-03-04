package com.kashyap1113.mysqlsynctool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kashyap1113.mysqlsynctool.DatabaseOperations;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String scategory=request.getParameter("type");
		PrintWriter writer = response.getWriter();
		DatabaseOperations dbl = new DatabaseOperations();
		String sJsonResponse = "";
		String sDatabaseName = "practice";
		if(scategory.equals("getalldatabase")) {
			try {
				sJsonResponse=dbl.getAllTables(sDatabaseName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.print(sJsonResponse);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
