package com.ekinoks.model;

public class User
{
	private int id;
	private String user_name;
	private String password;
	private int rank;

	public User(int id, String user_name, String password, int rank)
	{
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.rank = rank;
	}

	public int getId()
	{
		return id;
	}

	public String getUser_name()
	{
		return user_name;
	}

	public String getPassword()
	{
		return password;
	}

	public int getRank()
	{
		return rank;
	}
	
	

}
