package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.IssueDetailsDialogView;

//TODO
public class IssueDetailsDialogController
{
	private IssueDetailsDialogView issueDetailsDialogView;
	private DatabaseManager dbm;
	private String title;
	private ArrayList<String> assignees;

	public IssueDetailsDialogController(IssueDetailsDialogView issueDetailsDialogView, String title,
			ArrayList<String> assignees)
	{
		this.dbm = new DatabaseManager();
		this.issueDetailsDialogView = issueDetailsDialogView;
		this.title = title;
//		this.assignees = assignees;
	}

	public void initController()
	{
		issueDetailsDialogView.getAssignButton().addActionListener(e -> assignButtonController());
	}

	private void assignButtonController()
	{
		// TODO
		String selectedUserName = issueDetailsDialogView.getSelectedUserName();
//		System.out.println(selectedUserName);
		dbm.addRelation(selectedUserName, title);
		issueDetailsDialogView.getPossibleAssignees().remove(selectedUserName);
		assignees.add(selectedUserName);
		issueDetailsDialogView.setIssueAssignees(assignees);
	}
}
