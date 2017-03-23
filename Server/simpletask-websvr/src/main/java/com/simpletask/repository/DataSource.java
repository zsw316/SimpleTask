package com.simpletask.repository;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private static DataSource uniqueInstance = null;
	private ComboPooledDataSource cpds;
	
	private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/easy_todo?useSSL=false&serverTimezone=UTC");
        cpds.setUser("dbuser");
        cpds.setPassword("shwei.zhang2011");

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (uniqueInstance == null) {
        	uniqueInstance = new DataSource();
        } 
        return uniqueInstance;
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
