package com.ekinoks.controller;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.IssueDetailsDialogView;
import com.ekinoks.view.VerifiedDoneView;

public class VerifiedDoneController
{
	IssueDetailsDialogView issueDetailsDialogView;
	VerifiedDoneView verifiedDoneView;
	String title;
	
	public VerifiedDoneController(String title, IssueDetailsDialogView issueDetailsDialogView)
	{
		this.title = title;
		this.issueDetailsDialogView = issueDetailsDialogView;
		this.verifiedDoneView = new VerifiedDoneView();
		this.verifiedDoneView.pack();
		this.verifiedDoneView.setVisible(true);
		this.initController();
	}
	
	private void initController()
	{
		verifiedDoneView.getConfirmButton().addActionListener(e -> confirmButtonController());
		
	}
	
	private void confirmButtonController()
	{
		int hours = verifiedDoneView.getHours() + (verifiedDoneView.getDays() * 24);
		DatabaseManager.getInstance().setFinishDate(title);
		DatabaseManager.getInstance().setTimeSpent(title, hours);
		verifiedDoneView.dispose();
		issueDetailsDialogView.dispose();
	}
}
