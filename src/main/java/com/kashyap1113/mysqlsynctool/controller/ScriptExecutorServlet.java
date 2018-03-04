package com.kashyap1113.mysqlsynctool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kashyap1113.mysqlsynctool.PythonScriptExecutor;

/**
 * Servlet implementation class Test
 */
public class ScriptExecutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScriptExecutorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String scriptPath = request.getParameter("scriptPath");
	    String arguments = request.getParameter("arguments") == null ? "" : request.getParameter("arguments");
	    String result = new PythonScriptExecutor().executeScript(scriptPath, arguments.split("\\s+"));
		PrintWriter writer = response.getWriter();
		writer.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
