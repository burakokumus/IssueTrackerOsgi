package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//sdfsf
@SuppressWarnings("serial")
public class LoginView extends JDialog
{
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signUpButton;

	public LoginView()
	{
		initialize();
	}

	private void initialize()
	{
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblId = new JLabel("ID: ");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(5, 5, 5, 5);
		gbc_lblId.gridx = 4;
		gbc_lblId.gridy = 3;
		getContentPane().add(lblId, gbc_lblId);

		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.insets = new Insets(5, 5, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 5;
		gbc_idTextField.gridy = 3;
		getContentPane().add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		idTextField.setMinimumSize(idTextField.getSize());

		JLabel lblNewLabel = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 5, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 5;
		gbc_passwordField.gridy = 4;
		getContentPane().add(passwordField, gbc_passwordField);
		passwordField.setMinimumSize(passwordField.getSize());

		loginButton = new JButton("Login");
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.EAST;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 5;
		gbc_loginButton.gridy = 5;
		getContentPane().add(loginButton, gbc_loginButton);

		this.getRootPane().setDefaultButton(loginButton);

		signUpButton = new JButton("Sign up");
		signUpButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_signUpButton = new GridBagConstraints();
		gbc_signUpButton.anchor = GridBagConstraints.EAST;
		gbc_signUpButton.insets = new Insets(5, 5, 5, 5);
		gbc_signUpButton.gridx = 5;
		gbc_signUpButton.gridy = 6;
		getContentPane().add(signUpButton, gbc_signUpButton);
		this.setVisible(true);
		this.pack();
	}

	public void hide()
	{
		// this.dispose();
	}

	public String getUserName()
	{
		return idTextField.getText();
	}

	public String getPassword()
	{
		String result = new String(passwordField.getPassword());
		return result;
	}

	public JButton getLoginButton()
	{
		return loginButton;
	}

	public JButton getSignUpButton()
	{
		return signUpButton;
	}

}
