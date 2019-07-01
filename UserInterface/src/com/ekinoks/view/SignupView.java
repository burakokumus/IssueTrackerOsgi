package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignupView extends JDialog
{
	private GridBagLayout gridBagLayout;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private JButton signUpButton;

	public SignupView()
	{
		initialize();
	}

	private void initialize()
	{
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel userNameLabel = new JLabel("Username: ");
		GridBagConstraints gbc_userNameLabel = new GridBagConstraints();
		gbc_userNameLabel.anchor = GridBagConstraints.EAST;
		gbc_userNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_userNameLabel.gridx = 0;
		gbc_userNameLabel.gridy = 0;
		getContentPane().add(userNameLabel, gbc_userNameLabel);

		userNameTextField = new JTextField();
		GridBagConstraints gbc_userNameTextField = new GridBagConstraints();
		gbc_userNameTextField.insets = new Insets(5, 5, 5, 5);
		gbc_userNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userNameTextField.gridx = 1;
		gbc_userNameTextField.gridy = 0;
		getContentPane().add(userNameTextField, gbc_userNameTextField);
		userNameTextField.setColumns(10);

		JLabel lblPassword = new JLabel("Password: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(5, 5, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		getContentPane().add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 5, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		getContentPane().add(passwordField, gbc_passwordField);

		signUpButton = new JButton("Sign Up");
		GridBagConstraints gbc_signUpButton = new GridBagConstraints();
		gbc_signUpButton.insets = new Insets(5, 5, 5, 5);
		gbc_signUpButton.anchor = GridBagConstraints.EAST;
		gbc_signUpButton.gridx = 1;
		gbc_signUpButton.gridy = 2;
		getContentPane().add(signUpButton, gbc_signUpButton);

		this.getRootPane().setDefaultButton(signUpButton);
		this.pack();
	}

	public void showScreen()
	{
		this.setVisible(true);
	}

	public String getUserName()
	{
		return userNameTextField.getText();
	}

	public String getPassword()
	{
		String result = new String(passwordField.getPassword());
		return result;
	}

	public JButton getSignupButton()
	{
		return signUpButton;
	}

}
