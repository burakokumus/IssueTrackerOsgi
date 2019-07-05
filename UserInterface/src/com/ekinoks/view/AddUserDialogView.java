package com.ekinoks.view;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AddUserDialogView extends JDialog
{
	private JComboBox<String> requestsComboBox;
	private JComboBox<String> ranksComboBox;
	private String[] usersArr;
	private String[] ranks;
	private String[] allUsers;
	private JButton acceptButton;
	private JButton rejectButton;
	private JLabel removeLabel;
	private JComboBox<String> removeComboBox;
	private JButton removeButton;

	public AddUserDialogView(ArrayList<String> requests, ArrayList<String> allUsers)
	{
		ranks = new String[]
		{ "Manager", "Analyst", "Tester", "Developer" };
		this.usersArr = new String[requests.size()];
		for (int i = 0; i < requests.size(); i++)
			usersArr[i] = requests.get(i);
		this.allUsers = new String[allUsers.size()];
		for(int i = 0; i < allUsers.size(); i++)
			this.allUsers[i] = allUsers.get(i);
		initialize();
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		requestsComboBox = new JComboBox<>(usersArr);
		GridBagConstraints gbc_requestsComboBox = new GridBagConstraints();
		gbc_requestsComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_requestsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_requestsComboBox.gridx = 1;
		gbc_requestsComboBox.gridy = 1;
		getContentPane().add(requestsComboBox, gbc_requestsComboBox);

		ranksComboBox = new JComboBox<>(ranks);
		GridBagConstraints gbc_ranksComboBox = new GridBagConstraints();
		gbc_ranksComboBox.insets = new Insets(5, 5, 5, 0);
		gbc_ranksComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_ranksComboBox.gridx = 2;
		gbc_ranksComboBox.gridy = 1;
		getContentPane().add(ranksComboBox, gbc_ranksComboBox);

		acceptButton = new JButton("Accept");
		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.insets = new Insets(5, 5, 5, 5);
		gbc_acceptButton.gridx = 1;
		gbc_acceptButton.gridy = 2;
		getContentPane().add(acceptButton, gbc_acceptButton);

		rejectButton = new JButton("Reject");
		GridBagConstraints gbc_rejectButton = new GridBagConstraints();
		gbc_rejectButton.insets = new Insets(5, 5, 5, 0);
		gbc_rejectButton.gridx = 2;
		gbc_rejectButton.gridy = 2;
		getContentPane().add(rejectButton, gbc_rejectButton);
		
		removeLabel = new JLabel("Remove an existing user");
		GridBagConstraints gbc_removeLabel = new GridBagConstraints();
		gbc_removeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_removeLabel.gridx = 1;
		gbc_removeLabel.gridy = 5;
		getContentPane().add(removeLabel, gbc_removeLabel);
		
		removeComboBox = new JComboBox<>(allUsers);
		GridBagConstraints gbc_removeComboBox = new GridBagConstraints();
		gbc_removeComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_removeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeComboBox.gridx = 1;
		gbc_removeComboBox.gridy = 6;
		getContentPane().add(removeComboBox, gbc_removeComboBox);
		
		removeButton = new JButton("Remove");
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.gridx = 2;
		gbc_removeButton.gridy = 6;
		getContentPane().add(removeButton, gbc_removeButton);
	}

	public String getSelectedUser()
	{
		return (String) this.requestsComboBox.getSelectedItem();
	}

	public String getSelectedRank()
	{
		return (String) this.ranksComboBox.getSelectedItem();
	}

	public JButton getAcceptButton()
	{
		return this.acceptButton;
	}

	public JButton getRejectButton()
	{
		return this.rejectButton;
	}
	
	public JButton getRemoveButton()
	{
		return this.removeButton;
	}
	
	public String getRemoveName()
	{
		return (String)removeComboBox.getSelectedItem();
	}

}
