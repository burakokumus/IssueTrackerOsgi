package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.AddUserDialogView;
import com.ekinokssoftware.tropic.zemin.Messages;

public class AddUserDialogController
{
	private AddUserDialogView addUserDialogView;

	private DatabaseManager dbm;

	public AddUserDialogController(AddUserDialogView viewInput)
	{
		this.addUserDialogView = viewInput;
		this.dbm = new DatabaseManager();
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
		String password = dbm.getSignUpRequestPassword(userName);
		String rank = addUserDialogView.getSelectedRank();
		dbm.addUser(userName, password, rank);
		dbm.removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userAdded"), "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}
		
	private void rejectUser()
	{

		String userName = addUserDialogView.getSelectedUser();
		dbm.removeRequest(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userRemoved"), "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}
	
	private void removeUser()
	{
		String userName = addUserDialogView.getRemoveName();
		dbm.removeUser(userName);
		int showOptionDialog = JOptionPane.showOptionDialog(addUserDialogView, Messages.getString("userRemoved"), "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (showOptionDialog == 0 || showOptionDialog == -1)
		{
			addUserDialogView.dispose();
		}
	}
}
