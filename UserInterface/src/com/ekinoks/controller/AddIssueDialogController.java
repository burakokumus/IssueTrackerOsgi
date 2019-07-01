package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Issue;
import com.ekinoks.view.AddIssueDialogView;
import com.ekinoks.view.Messages;
import com.ekinoks.view.MainView;

public class AddIssueDialogController
{
	private AddIssueDialogView addIssueDialogView;

	private DatabaseManager dbm;

	private MainView mainView;

	public AddIssueDialogController(AddIssueDialogView viewInput, MainView mainView)
	{
		this.addIssueDialogView = viewInput;
		this.dbm = new DatabaseManager();
		this.mainView = mainView;
	}

	public void initController()
	{
		addIssueDialogView.getAddIssueButton().addActionListener(e -> addIssue());

	}

	private void addIssue()
	{
		String title = addIssueDialogView.getIssueTitle();
		String type = addIssueDialogView.getIssueType();
		int priority = addIssueDialogView.getIssuePriority();
		String description = addIssueDialogView.getIssueDescription();

		if (priority == -1 || title.trim().length() == 0)
		{
			return;
		}

		String author = mainView.getCurrentUserName();
		boolean added = dbm.addIssue(title, type, priority, author, description);
		String message = "";
		if (added)
		{
			message = Messages.getString("issueAdded");
			dbm.addRelation(mainView.getCurrentUserName(), title);
			addToJTable(title);
		}
		else
		{
			message = Messages.getString("issueAlreadyExists");
		}

		int showOptionDialog = JOptionPane.showOptionDialog(addIssueDialogView, message, "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (added && (showOptionDialog == 0 || showOptionDialog == -1))
		{
			addIssueDialogView.dispose();
		}
	}

	private void addToJTable(String title)
	{
		Issue issue = dbm.getIssue(title);
		mainView.addIssueToJTable(issue);

	}
}
