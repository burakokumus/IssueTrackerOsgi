package com.ekinoks.controller;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.PendingInvitationsView;

public class PendingInvitationsController
{
	private PendingInvitationsView pendingInvitationsView;
	private String userName;

	public PendingInvitationsController(String userName)
	{
		this.pendingInvitationsView = new PendingInvitationsView(userName);
		this.pendingInvitationsView.setVisible(true);
		this.userName = userName;
		this.initController();
	}

	public void initController()
	{
		pendingInvitationsView.getAcceptButton().addActionListener(e -> acceptInvitation());
		pendingInvitationsView.getRejectButton().addActionListener(e -> rejectInvitation());
	}

	private void acceptInvitation()
	{
		String issueTitle = pendingInvitationsView.getSelectedIssue();
		DatabaseManager.getInstance().addRelation(userName, issueTitle);
		DatabaseManager.getInstance().removeInvitation(userName, issueTitle);
		pendingInvitationsView.invitationsVector.remove(issueTitle);
		pendingInvitationsView.setPendingInvitations();
	}

	private void rejectInvitation()
	{
		String issueTitle = pendingInvitationsView.getSelectedIssue();
		DatabaseManager.getInstance().removeInvitation(userName, issueTitle);
		pendingInvitationsView.invitationsVector.remove(issueTitle);
		pendingInvitationsView.setPendingInvitations();
	}
}
