package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.ManageUsersDialogView;
import com.ekinokssoftware.tropic.zemin.Messages;

public class ManageUsersDialogController
{
	private ManageUsersDialogView manageUsersDialogView;

	public ManageUsersDialogController(ManageUsersDialogView viewInput)
	{
		this.manageUsersDialogView = viewInput;
		initController();
	}

	/**
	 * initializes the controller
	 */
	public void initController()
	{
		manageUsersDialogView.getAcceptButton().addActionListener(e -> addUser());
		manageUsersDialogView.getRejectButton().addActionListener(e -> rejectUser());
		manageUsersDialogView.getRemoveButton().addActionListener(e -> removeUser());
		manageUsersDialogView.getSetButton().addActionListener(e -> setProjectVisible());

	}

	/**
	 * Action listener for the add issue button. Tries to add the user into the
	 * database
	 */
	private void addUser()
	{
		String userName = manageUsersDialogView.getSelectedAcceptRejectUser();
		String password = DatabaseManager.getInstance().getSignUpRequestPassword(userName);
		String rank = manageUsersDialogView.getSelectedRank();
		DatabaseManager.getInstance().addUser(userName, password, rank);
		DatabaseManager.getInstance().removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(manageUsersDialogView, Messages.getString("userAdded"), "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			manageUsersDialogView.dispose();
		}
	}

	private void rejectUser()
	{

		String userName = manageUsersDialogView.getSelectedAcceptRejectUser();
		DatabaseManager.getInstance().removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(manageUsersDialogView, Messages.getString("userRemoved"),
				"", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			manageUsersDialogView.dispose();
		}
	}

	private void removeUser()
	{
		String userName = manageUsersDialogView.getRemoveName();
		DatabaseManager.getInstance().removeUser(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(manageUsersDialogView, Messages.getString("userRemoved"),
				"", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			manageUsersDialogView.dispose();
		}
	}

	private void setProjectVisible()
	{
		String userName = manageUsersDialogView.getSelectedSetProjectUser();

		@SuppressWarnings("unused")
		ProjectAvailabilityController projectVisibilityController = new ProjectAvailabilityController(userName,
				manageUsersDialogView, DatabaseManager.getInstance().getAllProjectNames());
	}
}
