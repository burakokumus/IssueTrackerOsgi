package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Comment;
import com.ekinoks.view.CommentsView;

public class CommentsController
{
	private CommentsView commentsView;
	private int issueID;
	private String currentUserName;

	public CommentsController(int id, String currentUserName)
	{
		this.issueID = id;
		this.currentUserName = currentUserName;
		this.commentsView = new CommentsView();
		this.addAllCommentsToJTable();
		this.commentsView.setVisible(true);
		this.initController();
	}

	private void initController()
	{
		commentsView.getNewCommentButton().addActionListener(e -> addComment());
	}

	private void addComment()
	{
		String issueTitle = DatabaseManager.getInstance().getIssueNameById(issueID);
		@SuppressWarnings("unused")
		AddCommentDialogController addCommentDialogController = new AddCommentDialogController(issueTitle,
				currentUserName, this);
	}

	public void addAllCommentsToJTable()
	{
		ArrayList<Comment> comments = DatabaseManager.getInstance().getAllCommentsOfIssue(issueID);
		for (Comment com : comments)
		{
			this.commentsView.addRowToTable(com);
		}
	}

	public void clearJTable()
	{
		commentsView.clearJTable();
	}
}
