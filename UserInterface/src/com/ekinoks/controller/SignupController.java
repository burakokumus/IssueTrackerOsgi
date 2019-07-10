package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.database.LogManager;
import com.ekinoks.view.Messages;
import com.ekinoks.view.SignupView;

public class SignupController
{
	private SignupView signupView;

	public SignupController(SignupView viewInput)
	{
		this.signupView = viewInput;
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
		boolean userExists = DatabaseManager.getInstance().checkUserExists(userName);
		if (userExists)
		{
			message = Messages.getString("userAlreadyExists");
			LogManager.getInstance().log("someone tried to sign up with already existing user name " + userName);
		}
		else
		{
			DatabaseManager.getInstance().addSignUpRequest(userName, password);
			message = Messages.getString("requestSent");
			LogManager.getInstance().log(userName + " signed up!");
		}

		int showOptionDialog = JOptionPane.showOptionDialog(signupView, message, "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (!userExists && (showOptionDialog == 0 || showOptionDialog == -1))
		{
			signupView.dispose();

		}
	}
}
