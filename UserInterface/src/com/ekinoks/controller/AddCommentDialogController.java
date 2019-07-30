package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.AddCommentDialogView;
import com.ekinokssoftware.tropic.zemin.Messages;

public class AddCommentDialogController
{
	private AddCommentDialogView addCommentDialogView;
	private String issueTitle;
	private String currentUserName;
	private CommentsController commentsController;

	public AddCommentDialogController(String issueTitle, String currentUserName, CommentsController commentsController)
	{
		addCommentDialogView = new AddCommentDialogView(issueTitle, commentsController.getCommentsView());
		this.issueTitle = issueTitle;
		this.currentUserName = currentUserName;
		this.commentsController = commentsController;
		initController();
		addCommentDialogView.setVisible(true);
	}

	private void initController()
	{
		addCommentDialogView.getAddButton().addActionListener(e -> addComment());
	}

	private void addComment()
	{

		int issueID = DatabaseManager.getInstance().getIssueID(issueTitle);
		String comment = addCommentDialogView.getComment();
		DatabaseManager.getInstance().addComment(issueID, currentUserName, comment);
		int showOptionDialog = JOptionPane.showOptionDialog(addCommentDialogView, Messages.getString("commentAdded"),
				"", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			commentsController.clearJTable();
			commentsController.addAllCommentsToJTable();
			addCommentDialogView.dispose();
		}

	}
}
