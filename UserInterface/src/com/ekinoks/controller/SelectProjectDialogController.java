package com.ekinoks.controller;

import java.util.ArrayList;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Issue;
import com.ekinoks.view.MainView;
import com.ekinoks.view.SelectProjectDialogView;

public class SelectProjectDialogController
{
	private SelectProjectDialogView selectProjectDialogView;
	private MainView mainView;
	private Controller mainController;

	public SelectProjectDialogController(MainView mainViewInput, Controller mainControllerInput, String[] allProjects)
	{
		this.selectProjectDialogView = new SelectProjectDialogView(allProjects);
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
		mainView.clearJTable();
		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssuesOfProject(newProject);

		for (Issue issue : issueList)
		{
			mainView.addIssueToJTable(issue);
		}
		selectProjectDialogView.dispose();
	}
}
