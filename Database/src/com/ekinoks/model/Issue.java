package com.ekinoks.model;

public class Issue
{
	private String projectName;
	private int id;
	private String title;
	private String type;
	private int priority;
	private String author;
	private String description;
	private String state;

	public Issue()
	{

	}

	public Issue(String projectName, int id, String title, String type, int priority, String author, String description,
			String state)
	{
		this.projectName = projectName;
		this.id = id;
		this.title = title;
		this.type = type;
		this.priority = priority;
		this.author = author;
		this.description = description;
		this.state = state;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public int getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getType()
	{
		return type;
	}

	public int getPriority()
	{
		return priority;
	}

	public String getAuthor()
	{
		return author;
	}

	public String getDescription()
	{
		return description;
	}

	public String getState()
	{
		return state;
	}

}
