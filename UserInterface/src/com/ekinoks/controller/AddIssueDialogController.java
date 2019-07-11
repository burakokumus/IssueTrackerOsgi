package com.ekinoks.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.database.LogManager;
import com.ekinoks.model.Issue;
import com.ekinoks.view.AddIssueDialogView;
import com.ekinoks.view.MainView;
import com.ekinoks.view.Messages;

public class AddIssueDialogController
{
	private AddIssueDialogView addIssueDialogView;

	private MainView mainView;

	public AddIssueDialogController(AddIssueDialogView viewInput, MainView mainView)
	{
		this.addIssueDialogView = viewInput;
		this.mainView = mainView;
	}

	/**
	 * initializes the controller
	 */
	public void initController()
	{
		addIssueDialogView.getAddIssueButton().addActionListener(e -> addIssue());
		addIssueDialogView.getPriorityTextField().addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char input = e.getKeyChar();
				if ((input < '0' || input > '9') && input != '\b')
				{
					e.consume();
					JOptionPane.showOptionDialog(addIssueDialogView, Messages.getString("Priority has to be integer!"), "", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
			}
		});
	}

	/**
	 * Action listener for the add issue button. Tries to add the issue to the
	 * database. If the issue is added, addToJTable method is called
	 */
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
		boolean added = DatabaseManager.getInstance().addIssue(title, type, priority, author, description);
		String message = "";
		if (added)
		{
			message = Messages.getString("issueAdded");
			addToJTable(title);
			LogManager.getInstance().log("Issue " + title + "is added by " + mainView.getCurrentUserName());
		}
		else
		{
			LogManager.getInstance()
					.log(mainView.getCurrentUserName() + "tried to add issue " + title + " but it already exists");
			message = Messages.getString("issueAlreadyExists");
		}

		int showOptionDialog = JOptionPane.showOptionDialog(addIssueDialogView, message, "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (added && (showOptionDialog == 0 || showOptionDialog == -1))
		{
			addIssueDialogView.dispose();
		}
	}

	/**
	 * Adds the issue with the given title to the JTable
	 * 
	 * @param title
	 */
	private void addToJTable(String title)
	{
		Issue issue = DatabaseManager.getInstance().getIssue(title);
		mainView.addIssueToJTable(issue);

	}

}
