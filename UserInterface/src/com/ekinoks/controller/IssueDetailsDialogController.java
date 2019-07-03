package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.IssueDetailsDialogView;
import com.ekinoks.view.MainView;

public class IssueDetailsDialogController
{
	private IssueDetailsDialogView issueDetailsDialogView;
	private DatabaseManager dbm;
	private String title;
	private ArrayList<String> assignees;
	private MainView mainView;

	public IssueDetailsDialogController(IssueDetailsDialogView issueDetailsDialogView, MainView mainView, String title,
			ArrayList<String> assignees)
	{
		this.dbm = new DatabaseManager();
		this.issueDetailsDialogView = issueDetailsDialogView;
		this.title = title;
		this.assignees = assignees;
		this.mainView = mainView;
	}

	public void initController()
	{
		issueDetailsDialogView.getAssignButton().addActionListener(e -> assignButtonController());
		issueDetailsDialogView.getStateSetButton().addActionListener(e -> stateSetButtonController());
	}

	private void assignButtonController()
	{
		String selectedUserName = issueDetailsDialogView.getSelectedUserName();
		dbm.addRelation(selectedUserName, title);
		issueDetailsDialogView.getPossibleAssignees().remove(selectedUserName);
		assignees.add(selectedUserName);
		issueDetailsDialogView.setIssueAssignees(assignees);
	}
	
	private void stateSetButtonController()
	{
		String selectedState = issueDetailsDialogView.getSelectedState();
		dbm.updateIssueState(title, selectedState);
		issueDetailsDialogView.setIssueStatus(selectedState);
		mainView.updateIssueStateOnJTable(title, selectedState);
		
	}
}
