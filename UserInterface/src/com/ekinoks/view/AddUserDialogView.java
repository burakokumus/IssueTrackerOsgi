package com.ekinoks.view;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class AddUserDialogView extends JDialog
{
	JComboBox<String> requestsComboBox;
	JComboBox<String> ranksComboBox;
	String[] usersArr;
	String[] ranks;
	JButton acceptButton;
	JButton rejectButton;

	public AddUserDialogView(ArrayList<String> requests)
	{
		ranks = new String[]
		{ "Manager", "Analyst", "Tester", "Developer" };
		this.usersArr = new String[requests.size()];
		for (int i = 0; i < requests.size(); i++)
			usersArr[i] = requests.get(i);
		initialize();
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
		gbc_ranksComboBox.insets = new Insets(5, 5, 5, 5);
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
		gbc_rejectButton.insets = new Insets(5, 5, 5, 5);
		gbc_rejectButton.gridx = 2;
		gbc_rejectButton.gridy = 2;
		getContentPane().add(rejectButton, gbc_rejectButton);
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

}
