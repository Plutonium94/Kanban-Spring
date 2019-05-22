package fr.unice.kanban.entities;

import org.springframework.data.annotation.Id;
import java.math.BigInteger;
import java.time.*;
import java.io.Serializable;

public class Task implements Serializable {

	// For Id generation to succeed use String, ObjectId or BigInteger
	@Id
	private BigInteger id;

	private String title;
	private String details;
	private Duration expectedDuration;
	private TaskStatus status = TaskStatus.OPEN;

	private KUser reporter;
	private KUser assignee;

	public Task() {

	}

	public Task(String title, String details) {
		this.title = title;
		this.details = details;
	}

	public Task(String title, String details, int hours) {
		this(title, details);
		setExpectedDuration(hours);
	}

	public Task(String title, String details, KUser reporter) {
		this.title = title;
		this.details = details;
		this.reporter = reporter;
	}

	public Task(String title, String details, KUser reporter, KUser assignee) {
		this(title, details, reporter);
		this.assignee = assignee;
	}

	public Task(String title, String details, KUser reporter, KUser assignee, int hours) {
		this(title, details, reporter, assignee);
		setExpectedDuration(hours);
	}

	public Task(String title, KUser reporter, KUser assignee, int hours) {
		this(title, title, reporter, assignee);
		setExpectedDuration(hours);
	}

	public Task(String title, String details, KUser reporter, int hours) {
		this(title, details, reporter);
		setExpectedDuration(hours);
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public int getExpectedDuration() {
		return (expectedDuration == null)? -1: (int)expectedDuration.toHours();
	}

	public void setExpectedDuration(int hours) {
		expectedDuration = (hours == -1)? null: Duration.ofHours(hours);
	}

	public KUser getAssignee() {
		return assignee;
	}

	public void setAssignee(KUser u) {
		assignee = u;
	}

	public KUser getReporter() {
		return reporter;
	}

	public void setReporter(KUser u) {
		reporter = u;
	}

	public void setStatus(TaskStatus status) {
		this.status	= status;
	}

	public TaskStatus getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		return id.hashCode() + title.hashCode() 
			+ details.hashCode() + reporter.hashCode()
			+ status.hashCode()
			+ assignee.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) { return false; }
		if(o instanceof Task) {
			Task t = (Task)o;
			return id.equals(t.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Task[title: " + title + ", details: " + details 
			+ ", expectedDuration: " + getExpectedDuration()
			+ ", status: " + status
			+ ", reporter: " + ((reporter==null)?"null":reporter.getUsername())
			+ ", assignee: " + ((assignee ==null)?"null":assignee.getUsername())
			+ ", id: " +id+"]";
	}
}