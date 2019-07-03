package com.ekinoks.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Issue;
import com.ekinoks.view.AddIssueDialogView;
import com.ekinoks.view.AddUserDialogView;
import com.ekinoks.view.IssueDetailsDialogView;
import com.ekinoks.view.MainView;
import com.ekinoks.view.Messages;

public class Controller
{
	private MainView view;
	private String currentUserName;
	private DatabaseManager dbm;

	public Controller(MainView viewInput)
	{
		this.view = viewInput;
		this.currentUserName = "";
		dbm = new DatabaseManager();
	}

	public void initController()
	{
		view.getAddIssueButton().addActionListener(e -> addIssue());
		view.getAddUserButton().addActionListener(e -> addUser());
		view.getTable().addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				super.mouseClicked(e);
				if (e.getClickCount() % 2 == 0)
				{
					int rowNo = view.getTable().rowAtPoint(e.getPoint());
					String id = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 0));
					String title = (String) view.getDefaultTableModel().getValueAt(rowNo, 1);
					String type = (String) view.getDefaultTableModel().getValueAt(rowNo, 2);
					String priority = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 3));
					String author = (String) view.getDefaultTableModel().getValueAt(rowNo, 4);
					String description = (String) view.getDefaultTableModel().getValueAt(rowNo, 5);
					String status = (String) view.getDefaultTableModel().getValueAt(rowNo, 6);
					ArrayList<String> assignees = dbm.getUsersByIssueTitle(title);

					Vector<String> possibleAssignees = dbm.getPossibleAssignees(title);

					IssueDetailsDialogView issueDetailsDialogView = new IssueDetailsDialogView(currentUserName, author,
							assignees, possibleAssignees);
					issueDetailsDialogView.setIssueID(id);
					issueDetailsDialogView.setIssueTitle(title);
					issueDetailsDialogView.setIssueType(type);
					issueDetailsDialogView.setIssuePriority(priority);
					issueDetailsDialogView.setIssueAuthor(author);
					issueDetailsDialogView.setIssueDescription(description);
					issueDetailsDialogView.setIssueStatus(status);
					issueDetailsDialogView.showScreen();
					issueDetailsDialogView.setIssueAssignees(assignees);
					if (assignees.contains(currentUserName))
						issueDetailsDialogView.getAssignButton().setVisible(false);
					else
					{
						IssueDetailsDialogController issueDetailsDialogController = new IssueDetailsDialogController(
								issueDetailsDialogView, view, title, assignees);
						issueDetailsDialogController.initController();
					}
					//issueDetailsDialogView.setMinimumSize(new Dimension(800,500));;
					issueDetailsDialogView.pack();
				}
			}
		});

		ArrayList<Issue> issueList = dbm.getAllIssues();

		for (Issue issue : issueList)
		{

			view.addIssueToJTable(issue);
		}
	}

	/**
	 * Action Listener for the Add Issue Button
	 */
	public void addIssue()
	{
		AddIssueDialogView addIssueDialogView = new AddIssueDialogView();
		addIssueDialogView.showScreen();
		AddIssueDialogController addIssueDialogController = new AddIssueDialogController(addIssueDialogView, view);
		addIssueDialogController.initController();
	}

	/**
	 * Action Listener for the Add User Button
	 */
	private void addUser()
	{
		if (dbm.getUserRank(currentUserName) > 1)
		{
			JOptionPane.showOptionDialog(view.frame, Messages.getString("cannotAddUser"), "",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}
		else
		{
			AddUserDialogView addUserDialogView = new AddUserDialogView();
			addUserDialogView.showScreen();
			AddUserDialogController addUserDialogController = new AddUserDialogController(addUserDialogView);
			addUserDialogController.initController();
		}

	}

	/**
	 * Setter for current user name
	 * 
	 * @param userName
	 */
	public void setCurrentUserName(String userName)
	{
		this.currentUserName = userName;
	}

	/**
	 * Getter for current user name
	 * 
	 * @return
	 */
	public String getCurrentUserName()
	{
		return this.currentUserName;
	}

}
