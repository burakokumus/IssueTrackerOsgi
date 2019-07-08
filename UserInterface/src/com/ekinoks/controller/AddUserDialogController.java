package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.AddUserDialogView;
import com.ekinokssoftware.tropic.zemin.Messages;

public class AddUserDialogController
{
	private AddUserDialogView addUserDialogView;

	public AddUserDialogController(AddUserDialogView viewInput)
	{
		this.addUserDialogView = viewInput;
	}

	/**
	 * initializes the controller
	 */
	public void initController()
	{
		addUserDialogView.getAcceptButton().addActionListener(e -> addUser());
		addUserDialogView.getRejectButton().addActionListener(e -> rejectUser());
		addUserDialogView.getRemoveButton().addActionListener(e -> removeUser());

	}

	/**
	 * Action listener for the add issue button. Tries to add the user into the
	 * database
	 */
	private void addUser()
	{
		String userName = addUserDialogView.getSelectedUser();
		String password = DatabaseManager.getInstance().getSignUpRequestPassword(userName);
		String rank = addUserDialogView.getSelectedRank();
		DatabaseManager.getInstance().addUser(userName, password, rank);
		DatabaseManager.getInstance().removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userAdded"), "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}

	private void rejectUser()
	{

		String userName = addUserDialogView.getSelectedUser();
		DatabaseManager.getInstance().removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userRemoved"), "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}

	private void removeUser()
	{
		String userName = addUserDialogView.getRemoveName();
		DatabaseManager.getInstance().removeUser(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userRemoved"), "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}
}
