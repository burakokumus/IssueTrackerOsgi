package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.database.LogManager;
import com.ekinoks.view.LoginView;
import com.ekinoks.view.Messages;
import com.ekinoks.view.SignupView;

public class LoginController
{
	private LoginView loginView;
	private Controller mainController;

	public LoginController(Controller mainControllerInput)
	{
		this.loginView = new LoginView();
		this.mainController = mainControllerInput;
	}

	/**
	 * initializes the controller
	 */
	public void initController()
	{
		loginView.getLoginButton().addActionListener(e -> login());
		loginView.getSignUpButton().addActionListener(e -> signup());
	}

	/**
	 * Action listener for login button. Sends the given user name and password to
	 * the database manager for authentication
	 */
	private void login()
	{
		String userName = loginView.getUserName();
		String password = loginView.getPassword();

		if (DatabaseManager.getInstance().login(userName, password))
		{
			int rank = DatabaseManager.getInstance().getUserRank(userName);
			mainController.setCurrentUserName(userName);
			mainController.getCurrentView().setCurrentUserName(userName);
			loginView.dispose();
			mainController.getCurrentView().setRank(rank);
			mainController.getCurrentView().setCurrentUserName(userName);
			mainController.getCurrentView().setVisible(true);
			mainController.getCurrentView().pack();

			LogManager.getInstance().log(userName + " logged in!");
		}
		else
		{
			JOptionPane.showOptionDialog(loginView, Messages.getString("loginFailed"), "", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
			LogManager.getInstance()
					.log("Someone tried to login with username " + userName + " and password " + password);
		}

	}

	/**
	 * Action listener for sign up button. Opens the sign up dialog.
	 */
	private void signup()
	{
		SignupView signupView = new SignupView();
		signupView.showScreen();
		SignupController signupController = new SignupController(signupView);
		signupController.initController();

	}
}
