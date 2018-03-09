package com.kashyap1113.mysqlsynctool.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kashyap1113.mysqlsynctool.ConnectionManager;
import com.kashyap1113.mysqlsynctool.model.ConnectionParams;

public class BaseDAOImpl {
    ConnectionParams connectionParams;
    Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public BaseDAOImpl() {
        super();
    }
    public BaseDAOImpl(ConnectionParams connectionParams) {
        super();
        this.connectionParams = connectionParams; 
        connection = new ConnectionManager(connectionParams).getConnection();
    }    
}
