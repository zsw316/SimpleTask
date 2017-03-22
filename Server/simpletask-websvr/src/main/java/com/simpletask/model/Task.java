package com.simpletask.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * CREATE TABLE `task` (
  `task_id` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `group_id` varchar(45) DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `labels` varchar(255) DEFAULT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `owner` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 */
@XmlRootElement
public class Task {
	/*
	 * Primary key, the task Id 
	 */
	private String taskId;
	
	/*
	 * Describe what this task is about
	 */
	private String description;
	
	/*
	 * The id of associated group
	 */
	private String groupId;
	
	/*
	 * The due time of this task
	 */
	private Date dueTime;
	
	/*
	 * The priority of this task
	 */
	private int priority;
	
	/*
	 * Multiple labels splitted by ','
	 */
	private String labels;
	
	/*
	 * The id of parent task
	 */
	private String parentId;
	
	/*
	 * The user id who created this task
	 */
	private long createdBy;
	
	/*
	 * The date when this task created
	 */
	private Date createdDate;
	
	/*
	 * The current owner of this task
	 */
	private long owner;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}
}
