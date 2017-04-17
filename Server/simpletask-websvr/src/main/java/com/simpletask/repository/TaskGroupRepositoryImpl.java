package com.simpletask.repository;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.simpletask.model.TaskGroup;

public class TaskGroupRepositoryImpl implements TaskGroupRepository {

	private Connection dbConn = null;
	private Logger logger = Logger.getLogger(TaskGroupRepositoryImpl.class);
	
	@Override
	public List<TaskGroup> getAllTaskGroupsForUser(long userId) {
		
		try {
			dbConn = DataSource.getInstance().getConnection();
		} catch (SQLException e) {
			logger.error(String.format("getConnection failed, SQLException: %s",
					e.getMessage()));
			return null;
		} catch (IOException e) {
			logger.error(String.format("getConnection failed, IOException: %s",
					e.getMessage()));
			return null;
		} catch (PropertyVetoException e) {
			logger.error(String.format("getConnection failed, PropertyVetoException: %s",
					e.getMessage()));
			return null;
		}
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = dbConn.createStatement();
			String queryStr = String.format(
					"SELECT group_id, title, created_by, created_date, importance, labels FROM taskgroup WHERE created_by=%d", userId);
			
			logger.error(String.format("Exec sql: %s", queryStr));
			rs = stmt.executeQuery(queryStr);
			if (rs != null) {
				List<TaskGroup> groupList = new ArrayList<TaskGroup>();

				while (rs.next()) {
					TaskGroup group = new TaskGroup();
					group.setGroupId(rs.getString(1));
					group.setTitle(rs.getString(2));
					group.setCreatedBy(rs.getInt(3));
					group.setCreatedDate(rs.getDate(4));
					group.setImportance(rs.getInt(5));
					group.setLabels(rs.getString(6));
					groupList.add(group);
					
					logger.error(String.format("Group info: Id: %s, Title: %s, CreatedBy: %d, CreatedDate: %s, Importance: %d, Labels: %s", 
							group.getGroupId(), group.getTitle(), group.getCreatedBy(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(group.getCreatedDate()), 
							group.getImportance(), group.getLabels()));
				}

				return groupList;
			}
		} catch (SQLException ex) {
			logger.error(String.format("getAllTaskGroupsForUser failed, SQLException: %s SQLState:%s VendorError: %s",
					ex.getMessage(), ex.getSQLState(), ex.getErrorCode()));
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				
				stmt = null;
			}
		}

		return null;
	}

	@Override
	public TaskGroup createTaskGroup(long userId, String title, int importance, String labels) {
		try {
			dbConn = DataSource.getInstance().getConnection();
		} catch (SQLException e) {
			logger.error(String.format("getConnection failed, SQLException: %s",
					e.getMessage()));
			return null;
		} catch (IOException e) {
			logger.error(String.format("getConnection failed, IOException: %s",
					e.getMessage()));
			return null;
		} catch (PropertyVetoException e) {
			logger.error(String.format("getConnection failed, PropertyVetoException: %s",
					e.getMessage()));
			return null;
		}
		
		Statement stmt = null;
		
		try {
			TaskGroup group = new TaskGroup();
			Date createdDate = new Date();
			String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
			String dateSqlStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdDate);
			
			group.setGroupId(String.format("%d-proj-%s", userId, createdDateStr));
			group.setTitle(title);
			group.setCreatedBy(userId);
			group.setCreatedDate(createdDate);
			group.setImportance(importance);
			group.setLabels(labels);

			stmt = dbConn.createStatement();
			String insertStr = String.format(
					"Insert into taskgroup (group_id, title, created_by, created_date, importance, labels) values('%s', '%s', %d, '%s', %d, '%s')",
					group.getGroupId(), group.getTitle(), group.getCreatedBy(), dateSqlStr, group.getImportance(), group.getLabels());

			stmt.executeUpdate(insertStr);
			return group;
		} catch (SQLException ex) {
			// handle any errors
			logger.error(String.format("createTaskGroup failed, SQLException: %s SQLState:%s VendorError: %s",
					ex.getMessage(), ex.getSQLState(), ex.getErrorCode()));
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}

		return null;
	}

	@Override
	public boolean deleteTaskGroup(long userId, String groupId) {
		try {
			dbConn = DataSource.getInstance().getConnection();
		} catch (SQLException e) {
			logger.error(String.format("getConnection failed, SQLException: %s",
					e.getMessage()));
			return false;
		} catch (IOException e) {
			logger.error(String.format("getConnection failed, IOException: %s",
					e.getMessage()));
			return false;
		} catch (PropertyVetoException e) {
			logger.error(String.format("getConnection failed, PropertyVetoException: %s",
					e.getMessage()));
			return false;
		}
		
		Statement stmt = null;
		
		try {
			stmt = dbConn.createStatement();
			String deleteStr = String.format(
					"DELETE From taskgroup WHERE group_id='%s' and created_by=%d",
					groupId, userId);

			stmt.executeUpdate(deleteStr);
			return true;
		} catch (SQLException ex) {
			// handle any errors
			logger.error(String.format("createTaskGroup failed, SQLException: %s SQLState:%s VendorError: %s",
					ex.getMessage(), ex.getSQLState(), ex.getErrorCode()));
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
		
		return false;
	}
}
