package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Comment;
import com.ekinoks.view.CommentsView;

public class CommentsController
{
	CommentsView commentsView;
	int issueID;

	public CommentsController(int id)
	{
		this.issueID = id;
		this.commentsView = new CommentsView();
		ArrayList<Comment> comments = DatabaseManager.getInstance().getAllCommentsOfIssue(issueID);
		for (Comment com : comments)
		{
			this.commentsView.addRowToTable(com);
			System.out.println(com.getComment());
		}
		this.commentsView.setVisible(true);

	}
}
