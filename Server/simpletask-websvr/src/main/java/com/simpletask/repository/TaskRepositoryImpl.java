package com.simpletask.repository;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.simpletask.model.Task;
import com.simpletask.model.TaskGroup;

public class TaskRepositoryImpl implements TaskRepository {

	private Connection dbConn = null;
	private Logger logger = Logger.getLogger(TaskRepositoryImpl.class);
	
	@Override
	public List<Task> getAllTasksForUser(long userId) {
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
					"SELECT task_id, description, group_id, due_time, priority, labels, parent_id, created_by, created_date, owner FROM taskgroup WHERE created_by=%d", userId);
			
			rs = stmt.executeQuery(queryStr);
			
			if (rs != null) {
				List<Task> taskList = new ArrayList<Task>();

				while (rs.next()) {
					Task task = new Task();
					task.setTaskId(rs.getString(1));
					task.setDescription(rs.getString(2));
					task.setGroupId(rs.getString(3));
					task.setDueTime(rs.getDate(4));
					task.setPriority(rs.getInt(5));
					task.setLabels(rs.getString(6));
					task.setParentId(rs.getString(7));
					task.setCreatedBy(userId);
					task.setCreatedDate(rs.getDate(9));
					task.setOwner(rs.getLong(10));
					
					taskList.add(task);
				}

				return taskList;
			}
		} catch (SQLException ex) {
			logger.error(String.format("getAllTasksForUser failed, SQLException: %s SQLState:%s VendorError: %s",
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
	public List<Task> getAllTasksForUserAndGroup(long userId, String groupId) {
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
					"SELECT task_id, description, group_id, due_time, priority, labels, parent_id, created_by, created_date, owner FROM taskgroup WHERE created_by=%d and group_id=%s", 
					userId, groupId);
			
			rs = stmt.executeQuery(queryStr);
			
			if (rs != null) {
				List<Task> taskList = new ArrayList<Task>();

				while (rs.next()) {
					Task task = new Task();
					task.setTaskId(rs.getString(1));
					task.setDescription(rs.getString(2));
					task.setGroupId(rs.getString(3));
					task.setDueTime(rs.getDate(4));
					task.setPriority(rs.getInt(5));
					task.setLabels(rs.getString(6));
					task.setParentId(rs.getString(7));
					task.setCreatedBy(userId);
					task.setCreatedDate(rs.getDate(9));
					task.setOwner(rs.getLong(10));
					
					taskList.add(task);
				}

				return taskList;
			}
		} catch (SQLException ex) {
			logger.error(String.format("getAllTasksForUserAndGroup failed, SQLException: %s SQLState:%s VendorError: %s",
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
	public Task createTask(long userId, String description, String groupId, String dueTimeStr, int priority,
			String labels, String parentId, long owner) {
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
			Task task = new Task();
			Date createdDate = new Date();
			String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
			String dateSqlStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdDate);
						
			task.setTaskId(String.format("%d-task-%s", userId, createdDateStr));
			task.setDescription(description);
			task.setGroupId(groupId);
			try {
				task.setDueTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dueTimeStr));
			} catch (ParseException e) {
				logger.error(String.format("parse time string failed, ParseException: %s",
						e.getMessage()));
			}
			
			task.setPriority(priority);
			task.setLabels(labels);
			task.setParentId(parentId);
			task.setCreatedBy(userId);
			task.setCreatedDate(createdDate);
			task.setOwner(owner);

			stmt = dbConn.createStatement();
			String insertStr = String.format(
					"Insert into task (task_id, description, group_id, due_time, priority, labels, parent_id, created_by, created_date, owner) values('%s', '%s', '%s', '%s', %d, '%s', %d, '%s', %d)",
					task.getTaskId(), task.getDescription(), dueTimeStr, task.getPriority(), task.getLabels(), task.getParentId(), task.getCreatedBy(), dateSqlStr, task.getOwner());

			stmt.executeUpdate(insertStr);
			return task;
		} catch (SQLException ex) {
			// handle any errors
			logger.error(String.format("createTask failed, SQLException: %s SQLState:%s VendorError: %s",
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
	public boolean deleteTask(long userId, String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

}
