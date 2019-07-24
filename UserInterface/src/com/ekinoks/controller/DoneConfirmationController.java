package com.ekinoks.controller;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.IssueState;
import com.ekinoks.view.DoneConfirmationView;
import com.ekinoks.view.MainView;

public class DoneConfirmationController
{
	MainView view;
	DoneConfirmationView verifiedDoneView;
	String title;

	public DoneConfirmationController(String title, MainView view)
	{
		this.title = title;
		this.view = view;
		this.verifiedDoneView = new DoneConfirmationView();
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
		DatabaseManager.getInstance().updateIssueState(title, IssueState.Done);
		DatabaseManager.getInstance().setProgressUser(title, null);
		view.updateIssueStateOnJTable(title, IssueState.Done);
		view.setIssueState(IssueState.Done);
		view.setPossibleStates();
		verifiedDoneView.dispose();

	}
}
