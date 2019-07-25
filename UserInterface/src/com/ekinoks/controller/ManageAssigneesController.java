package com.ekinoks.controller;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.ManageAssigneesView;

public class ManageAssigneesController
{
	@SuppressWarnings("unused")
	private int issueID;
	private ManageAssigneesView manageAssigneesView;
	private String issueTitle;

	public ManageAssigneesController(int id)
	{
		issueTitle = DatabaseManager.getInstance().getIssueNameById(id);
		this.manageAssigneesView = new ManageAssigneesView(issueTitle);
		this.issueID = id;

		this.manageAssigneesView.setVisible(true);
		this.initController();
	}

	private void initController()
	{
		this.manageAssigneesView.getRemoveAssigneeButton().addActionListener(e -> removeAssignee());
		this.manageAssigneesView.getInviteUserButton().addActionListener(e -> inviteUser());
	}

	private void removeAssignee()
	{
		String name = this.manageAssigneesView.getRemoveAssigneeName();
		DatabaseManager.getInstance().removeRelation(name, issueTitle);
		manageAssigneesView.removeAssigneeVector.remove(name);
	}

	private void inviteUser()
	{
		String name = this.manageAssigneesView.getInviteUserName();
		DatabaseManager.getInstance().addInvitation(name, issueTitle);
		manageAssigneesView.inviteUserVector.remove(name);
	}
}
