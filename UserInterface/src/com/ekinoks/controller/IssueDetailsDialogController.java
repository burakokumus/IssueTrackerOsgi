package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.database.LogManager;
import com.ekinoks.model.IssueState;
import com.ekinoks.view.IssueDetailsDialogView;
import com.ekinoks.view.MainView;

public class IssueDetailsDialogController
{
	private IssueDetailsDialogView issueDetailsDialogView;
	private String title;
	private ArrayList<String> assignees;
	private MainView mainView;
	private String currentUserName;

	public IssueDetailsDialogController(IssueDetailsDialogView issueDetailsDialogView, MainView mainView, String title,
			String currentUserName, ArrayList<String> assignees)
	{
		this.issueDetailsDialogView = issueDetailsDialogView;
		this.title = title;
		this.assignees = assignees;
		this.mainView = mainView;
		this.currentUserName = currentUserName;
	}

	public void initController()
	{
		issueDetailsDialogView.getAssignButton().addActionListener(e -> assignButtonController());
		issueDetailsDialogView.getStateSetButton().addActionListener(e -> stateSetButtonController());
	}

	private void assignButtonController()
	{
		String selectedUserName = issueDetailsDialogView.getSelectedUserName();
		DatabaseManager.getInstance().addRelation(selectedUserName, title);
		issueDetailsDialogView.getPossibleAssignees().remove(selectedUserName);
		assignees.add(selectedUserName);
		issueDetailsDialogView.setIssueAssignees(assignees);
		LogManager.getInstance().log("Issue " + title + "is assigned to " + selectedUserName + " by " + mainView.getCurrentUserName());
	}

	private void stateSetButtonController()
	{
		String selectedState = issueDetailsDialogView.getSelectedState();
		DatabaseManager.getInstance().updateIssueState(title, selectedState);
		issueDetailsDialogView.setIssueState(selectedState);
		if (selectedState.equals(IssueState.InProgress.toString()))
		{
			DatabaseManager.getInstance().setProgressUser(title, currentUserName);
		}
		else
		{
			DatabaseManager.getInstance().setProgressUser(title, null);
		}
		if (selectedState.equals(IssueState.VerifiedDone.toString()))
		{
			DatabaseManager.getInstance().setFinishDate(title);
		}
		mainView.updateIssueStateOnJTable(title, selectedState);

		issueDetailsDialogView.setPossibleStates();
		LogManager.getInstance().log("State of the issue " + title + "is set to " + selectedState + " by " + mainView.getCurrentUserName());

	}
}
