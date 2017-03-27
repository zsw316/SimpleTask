package com.simpletask.repository;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private static DataSource uniqueInstance = null;
	static String DS_PROPERTIES_CONF_NAME = "c3p0.properties";
	private ComboPooledDataSource cpds;
	
	private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        
        // Load configuration from properties file
        Properties pps = new Properties();
        pps.load(new FileInputStream(DS_PROPERTIES_CONF_NAME));
        
        if(pps.containsKey("c3p0.driverClass") && pps.containsKey("c3p0.jdbcUrl") 
        		&& pps.containsKey("c3p0.user") && pps.containsKey("c3p0.password")) { // Mandatory configure items
            cpds.setDriverClass(pps.getProperty("c3p0.driverClass")); //loads the jdbc driver
            cpds.setJdbcUrl(pps.getProperty("c3p0.jdbcUrl"));
            cpds.setUser(pps.getProperty("c3p0.user"));
            cpds.setPassword(pps.getProperty("c3p0.password"));
            
            if (pps.containsKey("c3p0.acquireIncrement")) {
            	cpds.setAcquireIncrement(Integer.parseInt(pps.getProperty("c3p0.driverClass")));
            }
            
            if (pps.containsKey("c3p0.minPoolSize")) {
            	cpds.setMinPoolSize(Integer.parseInt(pps.getProperty("c3p0.minPoolSize")));
            } 
            
            if (pps.containsKey("c3p0.maxPoolSize")) {
            	cpds.setMaxPoolSize(Integer.parseInt(pps.getProperty("c3p0.maxPoolSize")));
            } 
            
            if (pps.containsKey("c3p0.maxStatements")) {
            	cpds.setMaxStatements(Integer.parseInt(pps.getProperty("c3p0.maxStatements")));
            } 
        } 
        else { // Set default config
        	cpds.setDriverClass("om.mysql.jdbc.Driver "); //loads the jdbc driver
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/easy_todo?useSSL=false&serverTimezone=UTC");
            cpds.setUser("dbuser");
            cpds.setPassword("shwei.zhang2011");
            
            // the settings below are optional -- c3p0 can work with defaults
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        }
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
