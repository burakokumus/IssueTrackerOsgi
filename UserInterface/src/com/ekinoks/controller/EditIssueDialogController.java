package com.ekinoks.controller;

import com.ekinoks.view.EditIssueDialogView;
import com.ekinoks.view.MainView;

public class EditIssueDialogController
{
	String title;
	EditIssueDialogView editIssueDialogView;

	public EditIssueDialogController(MainView mainView, String title)
	{
		this.title = title;
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

	}
}
