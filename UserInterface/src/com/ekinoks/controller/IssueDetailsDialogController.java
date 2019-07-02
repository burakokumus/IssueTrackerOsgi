package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.IssueDetailsDialogView;

public class IssueDetailsDialogController
{
	private IssueDetailsDialogView issueDetailsDialogView;
	private DatabaseManager dbm;
//	private int ID;
	private String title;
//	private String author;
//	private String type;
	private ArrayList<String> assignees;

	public IssueDetailsDialogController(IssueDetailsDialogView issueDetailsDialogView, int ID,
			String title, String author, String type, ArrayList<String> assignees)
	{
		this.dbm = new DatabaseManager();
		this.issueDetailsDialogView = issueDetailsDialogView;
//		this.ID = ID;
		this.title = title;
//		this.author = author;
//		this.type = type;
		this.assignees = assignees;
	}
	
	public void initController()
	{
		issueDetailsDialogView.getAssignButton().addActionListener(e -> assignButtonController());
	}
	
	private void assignButtonController()
	{
		String curName = issueDetailsDialogView.getCurrentUserName();
		System.out.println("cur: " + curName);
		dbm.addRelation(curName, title);
		assignees.add(curName);
		issueDetailsDialogView.setIssueAssignees(assignees);
		issueDetailsDialogView.getAssignButton().setVisible(false);
	}
}
