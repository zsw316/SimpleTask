package com.simpletask.model;

import java.util.Date;

/*
 * 
 * CREATE TABLE `taskgroup` (
  `group_id` varchar(45) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `created_by` bigint(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `importance` int(11) DEFAULT NULL,
  `labels` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class TaskGroup {
	/*
	 * Primary key, group Id
	 */
	private String groupId;
	
	/*
	 * Title to describe what the group is about
	 */
	private String title;
	
	/*
	 * The user id who created this group
	 */
	private long createdBy;
	
	/*
	 * The date when this group is created
	 */
	private Date createdDate;
	
	/*
	 * The importance of this group, 0 is the highest, bigger is less important
	 */
	private int importance;
	
	/*
	 * Multiple labels splitted by ','
	 */
	private String labels;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
}
