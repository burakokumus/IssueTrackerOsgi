package com.ekinoks.controller;

import javax.swing.JOptionPane;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.view.LoginView;
import com.ekinoks.view.SignupView;
import com.ekinoks.view.MainView;
import com.ekinoks.view.Messages;

public class LoginController
{
	private MainView mainView;
	private LoginView loginView;
	private Controller mainController;

	public LoginController(LoginView loginViewInput, MainView mainViewInput, Controller mainControllerInput)
	{
		this.loginView = loginViewInput;
		this.mainView = mainViewInput;
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
			mainController.setCurrentUserName(userName);
			mainView.setCurrentUserName(userName);
			loginView.dispose();
			int rank = DatabaseManager.getInstance().getUserRank(userName);
			mainView.setRank(rank);
			mainView.setCurrentUserName(userName);
			mainView.setVisible(true);
			mainView.pack();
		}
		else
		{
			JOptionPane.showOptionDialog(loginView, Messages.getString("loginFailed"), "", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
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
