package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddProjectDialogView extends JDialog
{
	private JLabel projectNameLabel;
	private JTextField projectNameTextField;
	private JButton confirmButton;

	public AddProjectDialogView()
	{
		initialize();
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		projectNameLabel = new JLabel("Project Name: ");
		GridBagConstraints gbc_projectNameLabel = new GridBagConstraints();
		gbc_projectNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_projectNameLabel.anchor = GridBagConstraints.WEST;
		gbc_projectNameLabel.gridx = 0;
		gbc_projectNameLabel.gridy = 0;
		getContentPane().add(projectNameLabel, gbc_projectNameLabel);

		projectNameTextField = new JTextField();
		GridBagConstraints gbc_projectNameTextField = new GridBagConstraints();
		gbc_projectNameTextField.insets = new Insets(5, 5, 5, 0);
		gbc_projectNameTextField.anchor = GridBagConstraints.EAST;
		gbc_projectNameTextField.gridx = 1;
		gbc_projectNameTextField.gridy = 0;
		getContentPane().add(projectNameTextField, gbc_projectNameTextField);
		projectNameTextField.setColumns(10);

		confirmButton = new JButton("Confirm");
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.gridx = 1;
		gbc_confirmButton.gridy = 1;
		getContentPane().add(confirmButton, gbc_confirmButton);

		this.getRootPane().setDefaultButton(confirmButton);
		this.setModal(true);
		this.pack();
	}

	public String getProjectName()
	{
		return projectNameTextField.getText();
	}

	public JButton getConfirmButton()
	{
		return confirmButton;
	}

}
