package com.ekinoks.model;

public class Comment
{
	private int commentID;
	private int issueID;
	private String userName;
	private String comment;
	private String date;

	public Comment()
	{

	}

	public Comment(int commentID, int issueID, String userName, String comment, String date)
	{
		super();
		this.commentID = commentID;
		this.issueID = issueID;
		this.userName = userName;
		this.comment = comment;
		this.date = date;
	}

	public int getCommentID()
	{
		return commentID;
	}

	public int getIssueID()
	{
		return issueID;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getComment()
	{
		return comment;
	}

	public String getDate()
	{
		return date;
	}

}
