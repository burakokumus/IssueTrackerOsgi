package com.ekinoks.model;


public class Issue
{
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
	
	public Issue(int id, String title, String type, int priority, String author, String description, String state)
	{
		this.id = id;
		this.title = title;
		this.type = type;
		this.priority = priority;
		this.author = author;
		this.description = description;
		this.state = state;
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
