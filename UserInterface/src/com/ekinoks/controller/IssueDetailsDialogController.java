package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.IssueDetailsDialogView;

public class IssueDetailsDialogController
{
	private IssueDetailsDialogView issueDetailsDialogView;
	private DatabaseManager dbm;
	private String title;
	private ArrayList<String> assignees;

	public IssueDetailsDialogController(IssueDetailsDialogView issueDetailsDialogView, int ID,
			String title, String author, String type, ArrayList<String> assignees)
	{
		this.dbm = new DatabaseManager();
		this.issueDetailsDialogView = issueDetailsDialogView;
		this.title = title;
		this.assignees = assignees;
	}
	
	public void initController()
	{
		issueDetailsDialogView.getAssignButton().addActionListener(e -> assignButtonController());
	}
	
	private void assignButtonController()
	{
		//TODO
//		String curName = issueDetailsDialogView.getCurrentUserName();
//		dbm.addRelation(curName, title);
//		assignees.add(curName);
//		issueDetailsDialogView.setIssueAssignees(assignees);
//		issueDetailsDialogView.getAssignButton().setVisible(false);
	}
}
