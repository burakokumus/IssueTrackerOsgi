package com.ekinoks.controller;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.ManageUsersDialogView;
import com.ekinoks.view.ProjectVisibilityView;

public class ProjectVisibilityController
{
	private String userName;
	private ProjectVisibilityView projectVisibilityView;

	public ProjectVisibilityController(String userName, ManageUsersDialogView manageUsersDialogView,
			ArrayList<String> allProjects)
	{
		this.userName = userName;
		this.projectVisibilityView = new ProjectVisibilityView(userName, manageUsersDialogView, allProjects);
		this.projectVisibilityView.setVisible(true);
		this.initController();
	}

	private void initController()
	{
		projectVisibilityView.getSetButton().addActionListener(e -> setAvailability());
	}

	private void setAvailability()
	{
		for (JCheckBox checkBox : projectVisibilityView.getAllCheckBoxes())
		{
			if (DatabaseManager.getInstance().isProjectAvailableToUser(userName, checkBox.getText()))
			{
				if (!checkBox.isSelected())
				{
					DatabaseManager.getInstance().removeProjectAvailabilityFromUser(userName, checkBox.getText());
				}
			}
			else
			{
				if (checkBox.isSelected())
				{
					DatabaseManager.getInstance().addProjectAvailabilityToUser(userName, checkBox.getText());
				}
			}
		}

		String message = "Update successful!";
		int showOptionDialog = JOptionPane.showOptionDialog(projectVisibilityView, message, "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if ((showOptionDialog == 0 || showOptionDialog == -1))
		{
			projectVisibilityView.dispose();
		}

	}
}
