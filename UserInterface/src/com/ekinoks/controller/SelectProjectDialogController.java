package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Issue;
import com.ekinoks.model.IssueConfiguration;
import com.ekinoks.view.MainView;
import com.ekinoks.view.SelectProjectDialogView;

public class SelectProjectDialogController
{
	private SelectProjectDialogView selectProjectDialogView;
	private MainView mainView;
	private Controller mainController;
	private IssueConfiguration config;

	public SelectProjectDialogController(MainView mainViewInput, Controller mainControllerInput, String[] allProjects,
			IssueConfiguration config)
	{
		this.selectProjectDialogView = new SelectProjectDialogView(allProjects);
		this.config = config;
		this.selectProjectDialogView.setVisible(true);
		this.mainView = mainViewInput;
		this.mainController = mainControllerInput;
		this.initController();
	}

	private void initController()
	{
		this.selectProjectDialogView.getSelectButton().addActionListener(e -> selectProject());
	}

	private void selectProject()
	{
		String newProject = this.selectProjectDialogView.getSelectedProject();
		mainView.setCurrentProjectName(newProject);
		mainController.setCurrentProjectName(newProject);
		config.currentProject = newProject;
		config.save();

		mainView.clearJTable();
		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssuesOfProject(newProject);

		for (Issue issue : issueList)
		{
			mainView.addIssueToJTable(issue);
		}
		selectProjectDialogView.dispose();
	}
}
