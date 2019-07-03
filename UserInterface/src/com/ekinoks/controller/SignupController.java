package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.Messages;
import com.ekinoks.view.SignupView;

public class SignupController
{
	private SignupView signupView;
	private DatabaseManager dbm;

	public SignupController(SignupView viewInput)
	{
		this.signupView = viewInput;
		this.dbm = new DatabaseManager();
	}

	/**
	 * initializes the controller
	 */
	public void initController()
	{
		signupView.getSignupButton().addActionListener(e -> signup());
	}

	/*
	 * Action listener for sign up button. Sends the given user name and password to
	 * the database for sign up.
	 */
	private void signup()
	{
		String userName = signupView.getUserName();
		String password = signupView.getPassword();

		String message = "";
		boolean userExists = dbm.checkUserExists(userName);
		if (userExists)
		{
			message = Messages.getString("userAlreadyExists");
		}
		else
		{
			dbm.addUser(userName, password, "Developer");
			message = Messages.getString("signupSuccessful");
		}

		int showOptionDialog = JOptionPane.showOptionDialog(signupView, message, "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (!userExists && (showOptionDialog == 0 || showOptionDialog == -1))
		{
			signupView.dispose();

		}
	}
}
