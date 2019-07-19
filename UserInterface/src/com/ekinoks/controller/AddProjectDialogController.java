package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.AddProjectDialogView;

public class AddProjectDialogController
{
	private AddProjectDialogView addProjectDialogView;

	public AddProjectDialogController()
	{
		this.addProjectDialogView = new AddProjectDialogView();
		this.addProjectDialogView.setVisible(true);
		this.initController();
	}

	private void initController()
	{
		this.addProjectDialogView.getConfirmButton().addActionListener(e -> addProject());
	}

	private void addProject()
	{
		String projectName = this.addProjectDialogView.getProjectName();

		boolean added = DatabaseManager.getInstance().addProject(projectName);
		if (added)
		{
			int showOptionDialog = JOptionPane.showOptionDialog(addProjectDialogView, "Project Added", "",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (showOptionDialog == 0 || showOptionDialog == -1)
				addProjectDialogView.dispose();
		}
		else
		{
			JOptionPane.showOptionDialog(addProjectDialogView, "Project Already Exists", "", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}

	}
}
