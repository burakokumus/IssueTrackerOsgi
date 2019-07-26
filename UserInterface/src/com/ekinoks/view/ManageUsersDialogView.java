package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ManageUsersDialogView extends JDialog
{
	private JComboBox<String> requestsComboBox;
	private JComboBox<String> ranksComboBox;
	private String[] usersArr;
	private String[] ranks;
	private String[] allUsers;
	private String[] allProjects;
	private JButton acceptButton;
	private JButton rejectButton;
	private JLabel removeLabel;
	private JComboBox<String> removeComboBox;
	private JButton removeButton;
	private JLabel acceptRejectLabel;
	private JLabel setVisibleLabel;
	private JComboBox<String> setVisibleComboBox;
	private JButton setButton;

	public ManageUsersDialogView(ArrayList<String> requests, ArrayList<String> allUsers)
	{
		this(null, requests, allUsers);
	}

	public ManageUsersDialogView(Window parentWindow, ArrayList<String> requests, ArrayList<String> allUsers)
	{
		super(parentWindow);
		ranks = new String[]
		{ "Manager", "Analyst", "Tester", "Developer" };
		this.usersArr = new String[requests.size()];
		for (int i = 0; i < requests.size(); i++)
			usersArr[i] = requests.get(i);

		this.allUsers = new String[allUsers.size()];
		for (int i = 0; i < allUsers.size(); i++)
			this.allUsers[i] = allUsers.get(i);

		this.setLocationRelativeTo(parentWindow);
		this.setLocation(this.getLocation().x + parentWindow.getSize().width / 2, this.getLocation().y);
		this.setModal(true);
		this.setModalityType(ModalityType.APPLICATION_MODAL);

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

		acceptRejectLabel = new JLabel("Accept/Reject an application");
		GridBagConstraints gbc_acceptRejectLabel = new GridBagConstraints();
		gbc_acceptRejectLabel.insets = new Insets(0, 0, 5, 5);
		gbc_acceptRejectLabel.gridx = 1;
		gbc_acceptRejectLabel.gridy = 0;
		getContentPane().add(acceptRejectLabel, gbc_acceptRejectLabel);

		requestsComboBox = new JComboBox<String>(usersArr);
		GridBagConstraints gbc_requestsComboBox = new GridBagConstraints();
		gbc_requestsComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_requestsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_requestsComboBox.gridx = 1;
		gbc_requestsComboBox.gridy = 1;
		getContentPane().add(requestsComboBox, gbc_requestsComboBox);

		ranksComboBox = new JComboBox<String>(ranks);
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
		gbc_removeLabel.gridy = 3;
		getContentPane().add(removeLabel, gbc_removeLabel);

		removeComboBox = new JComboBox<>(allUsers);
		GridBagConstraints gbc_removeComboBox = new GridBagConstraints();
		gbc_removeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_removeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeComboBox.gridx = 1;
		gbc_removeComboBox.gridy = 4;
		getContentPane().add(removeComboBox, gbc_removeComboBox);

		removeButton = new JButton("Remove");
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeButton.gridx = 2;
		gbc_removeButton.gridy = 4;
		getContentPane().add(removeButton, gbc_removeButton);

		setVisibleLabel = new JLabel("Set Visible Projects For User");
		GridBagConstraints gbc_setVisibleLabel = new GridBagConstraints();
		gbc_setVisibleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_setVisibleLabel.gridx = 1;
		gbc_setVisibleLabel.gridy = 5;
		getContentPane().add(setVisibleLabel, gbc_setVisibleLabel);

		setVisibleComboBox = new JComboBox<>(allUsers);
		GridBagConstraints gbc_setVisibleComboBox = new GridBagConstraints();
		gbc_setVisibleComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_setVisibleComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_setVisibleComboBox.gridx = 1;
		gbc_setVisibleComboBox.gridy = 6;
		getContentPane().add(setVisibleComboBox, gbc_setVisibleComboBox);

		setButton = new JButton("Set");
		GridBagConstraints gbc_setButton = new GridBagConstraints();
		gbc_setButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_setButton.gridx = 2;
		gbc_setButton.gridy = 6;
		getContentPane().add(setButton, gbc_setButton);

	}

	public String getSelectedAcceptRejectUser()
	{
		return (String) this.requestsComboBox.getSelectedItem();
	}

	public String getSelectedRank()
	{
		return (String) this.ranksComboBox.getSelectedItem();
	}

	public String getSelectedSetProjectUser()
	{
		return (String) this.setVisibleComboBox.getSelectedItem();
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

	public JButton getSetButton()
	{
		return this.setButton;
	}

	public String getRemoveName()
	{
		return (String) removeComboBox.getSelectedItem();
	}

	public String[] getAllProjects()
	{
		return allProjects;
	}
}
