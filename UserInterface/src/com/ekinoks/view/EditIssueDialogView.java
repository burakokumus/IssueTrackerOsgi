package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EditIssueDialogView extends JDialog
{
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbc_projectNameLabel;
	private GridBagConstraints gbc_issueIDLabel;
	private GridBagConstraints gbc_titleLabel;
	private GridBagConstraints gbc_priorityLabel;

	private JLabel projectNameLabel;
	private JLabel issueIDLabel;
	private JLabel titleLabel;
	private JLabel priorityLabel;
	private JLabel lblAuthor;
	private JLabel lblDescription;
	private JLabel stateLabel;
	private JTextField projectNameTextField;
	private JTextField issueIDTextField;
	private JTextField titleTextField;
	private JTextField priorityTextField;
	private JTextField authorTextField;
	private JTextField descriptionTextField;
	private JTextField stateTextfield;
	private JButton applyButton;

	public EditIssueDialogView()
	{

		initialize();
		this.setTitle("Edit Issue");
	}

	private void initialize()
	{
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		projectNameLabel = new JLabel("Project Name");
		gbc_projectNameLabel = new GridBagConstraints();
		gbc_projectNameLabel.anchor = GridBagConstraints.EAST;
		gbc_projectNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_projectNameLabel.gridx = 0;
		gbc_projectNameLabel.gridy = 0;
		getContentPane().add(projectNameLabel, gbc_projectNameLabel);

		projectNameTextField = new JTextField();
		projectNameTextField.setEditable(false);
		GridBagConstraints gbc_projectNameTextField = new GridBagConstraints();
		gbc_projectNameTextField.anchor = GridBagConstraints.EAST;
		gbc_projectNameTextField.insets = new Insets(0, 5, 5, 5);
		gbc_projectNameTextField.gridx = 1;
		gbc_projectNameTextField.gridy = 0;
		getContentPane().add(projectNameTextField, gbc_projectNameTextField);
		projectNameTextField.setColumns(10);

		lblAuthor = new JLabel("Author");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(5, 5, 5, 5);
		gbc_lblAuthor.gridx = 3;
		gbc_lblAuthor.gridy = 0;
		getContentPane().add(lblAuthor, gbc_lblAuthor);

		authorTextField = new JTextField();
		authorTextField.setEditable(false);
		GridBagConstraints gbc_authorTextField = new GridBagConstraints();
		gbc_authorTextField.anchor = GridBagConstraints.EAST;
		gbc_authorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_authorTextField.gridx = 4;
		gbc_authorTextField.gridy = 0;
		getContentPane().add(authorTextField, gbc_authorTextField);
		authorTextField.setColumns(10);

		issueIDLabel = new JLabel("Issue Id");
		gbc_issueIDLabel = new GridBagConstraints();
		gbc_issueIDLabel.insets = new Insets(5, 5, 5, 5);
		gbc_issueIDLabel.anchor = GridBagConstraints.WEST;
		gbc_issueIDLabel.gridx = 0;
		gbc_issueIDLabel.gridy = 1;
		getContentPane().add(issueIDLabel, gbc_issueIDLabel);

		issueIDTextField = new JTextField();
		issueIDTextField.setEditable(false);
		GridBagConstraints gbc_issueIDTextField = new GridBagConstraints();
		gbc_issueIDTextField.anchor = GridBagConstraints.EAST;
		gbc_issueIDTextField.insets = new Insets(0, 5, 5, 5);
		gbc_issueIDTextField.gridx = 1;
		gbc_issueIDTextField.gridy = 1;
		getContentPane().add(issueIDTextField, gbc_issueIDTextField);
		issueIDTextField.setColumns(10);

		lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(5, 5, 5, 5);
		gbc_lblDescription.gridx = 3;
		gbc_lblDescription.gridy = 1;
		getContentPane().add(lblDescription, gbc_lblDescription);

		descriptionTextField = new JTextField();
		GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
		gbc_descriptionTextField.anchor = GridBagConstraints.EAST;
		gbc_descriptionTextField.insets = new Insets(0, 0, 5, 0);
		gbc_descriptionTextField.gridx = 4;
		gbc_descriptionTextField.gridy = 1;
		getContentPane().add(descriptionTextField, gbc_descriptionTextField);
		descriptionTextField.setColumns(10);

		titleLabel = new JLabel("Title");
		gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(5, 5, 5, 5);
		gbc_titleLabel.anchor = GridBagConstraints.WEST;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 2;
		getContentPane().add(titleLabel, gbc_titleLabel);

		titleTextField = new JTextField();
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.anchor = GridBagConstraints.EAST;
		gbc_titleTextField.insets = new Insets(0, 5, 5, 5);
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 2;
		getContentPane().add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);

		stateLabel = new JLabel("State");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.WEST;
		gbc_stateLabel.insets = new Insets(5, 5, 5, 5);
		gbc_stateLabel.gridx = 3;
		gbc_stateLabel.gridy = 2;
		getContentPane().add(stateLabel, gbc_stateLabel);

		stateTextfield = new JTextField();
		GridBagConstraints gbc_stateTextfield = new GridBagConstraints();
		gbc_stateTextfield.anchor = GridBagConstraints.EAST;
		gbc_stateTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_stateTextfield.gridx = 4;
		gbc_stateTextfield.gridy = 2;
		getContentPane().add(stateTextfield, gbc_stateTextfield);
		stateTextfield.setColumns(10);

		priorityLabel = new JLabel("Priority");
		gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.anchor = GridBagConstraints.WEST;
		gbc_priorityLabel.insets = new Insets(5, 5, 0, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 3;
		getContentPane().add(priorityLabel, gbc_priorityLabel);

		priorityTextField = new JTextField();
		GridBagConstraints gbc_priorityTextField = new GridBagConstraints();
		gbc_priorityTextField.anchor = GridBagConstraints.EAST;
		gbc_priorityTextField.insets = new Insets(5, 5, 0, 5);
		gbc_priorityTextField.gridx = 1;
		gbc_priorityTextField.gridy = 3;
		getContentPane().add(priorityTextField, gbc_priorityTextField);
		priorityTextField.setColumns(10);

		applyButton = new JButton("Apply");
		GridBagConstraints gbc_applyButton = new GridBagConstraints();
		gbc_applyButton.gridx = 4;
		gbc_applyButton.gridy = 3;
		getContentPane().add(applyButton, gbc_applyButton);

		this.pack();
	}

	public JTextField getProjectNameTextField()
	{
		return projectNameTextField;
	}

	public JTextField getIssueIDTextField()
	{
		return issueIDTextField;
	}

	public JTextField getTitleTextField()
	{
		return titleTextField;
	}

	public JTextField getPriorityTextField()
	{
		return priorityTextField;
	}

	public JTextField getAuthorTextField()
	{
		return authorTextField;
	}

	public JTextField getDescriptionTextField()
	{
		return descriptionTextField;
	}

	public JTextField getStateTextfield()
	{
		return stateTextfield;
	}

	public JButton getApplyButton()
	{
		return applyButton;
	}

}
