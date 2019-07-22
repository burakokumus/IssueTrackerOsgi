package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Issue;
import com.ekinoks.view.EditIssueDialogView;
import com.ekinoks.view.MainView;

public class EditIssueDialogController
{
	String title;
	EditIssueDialogView editIssueDialogView;
	MainView mainView;

	public EditIssueDialogController(MainView mainView, String title)
	{
		this.title = title;
		this.mainView = mainView;
		editIssueDialogView = new EditIssueDialogView();
		editIssueDialogView.getProjectNameTextField()
				.setText(mainView.getIssueProjectNameLabel().getText().substring(14));
		editIssueDialogView.getIssueIDTextField().setText(mainView.getIssueIDLabel().getText().substring(4));
		editIssueDialogView.getTitleTextField().setText(mainView.getIssueTitleLabel().getText().substring(7));
		editIssueDialogView.getPriorityTextField().setText(mainView.getIssuePriorityLabel().getText().substring(10));
		editIssueDialogView.getAuthorTextField().setText(mainView.getIssueAuthorLabel().getText().substring(8));
		editIssueDialogView.getDescriptionTextField()
				.setText(mainView.getIssueDescriptionLabel().getText().substring(13));
		editIssueDialogView.getStateTextfield().setText(mainView.getIssueStatusLabel().getText().substring(7));
		editIssueDialogView.setVisible(true);
		initController();
	}

	private void initController()
	{
		editIssueDialogView.getApplyButton().addActionListener(e -> apply());
	}

	private void apply()
	{
		int id = Integer.parseInt(editIssueDialogView.getIssueIDTextField().getText());
		String description = editIssueDialogView.getDescriptionTextField().getText();
		String state = editIssueDialogView.getStateTextfield().getText();
		title = editIssueDialogView.getTitleTextField().getText();
		System.out.println(title);

		DatabaseManager.getInstance().editIssue(id, description, title, state);
		mainView.clearJTable();
		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssuesOfProject(mainView.getCurrentProject());

		for (Issue issue : issueList)
		{
			mainView.addIssueToJTable(issue);
		}

	}
}
