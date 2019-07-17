package com.ekinoks.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.ekinoks.model.Issue;
import com.ekinoks.model.IssueState;
import com.ekinoks.ui.components.listtable.ListTable;
import com.ekinoks.ui.components.listtable.ListTableModel;

@SuppressWarnings("serial")
public class MainView extends JFrame
{
	private int currentRank = -1;
	private String currentUserName;
	private String progressUser;
	private String currentState;
	private String[] possibleStates;

	private DefaultComboBoxModel<String> comboBoxModel;

	public JDialog addIssueDialog;
	private ListTable<Issue> table;
	private JPanel buttonPanel;
	private JButton addIssueButton;
	private JButton addUserButton;
	private JPanel userPanel;
	private JLabel userNameLabel;
	private JButton refreshButton;
	private JButton exportButton;
	private JButton logOutButton;
	private JPanel issuePanel;
	private JLabel issueIDLabel;
	private JLabel issueTitleLabel;
	private JLabel issueAuthorLabel;
	private JLabel issueTypeLabel;
	private JLabel issuePriorityLabel;
	private JLabel issueStatusLabel;
	private JComboBox<String> stateComboBox;
	private JButton setStatusButton;
	private JTextArea assigneesTextArea;

	public MainView()
	{
		this.currentUserName = "";
		this.possibleStates = new String[]
		{};
		initialize();
	}

	/**
	 * makes the screen visible.
	 */
	public void showScreen()
	{
		this.setVisible(true);
	}

	/**
	 * initializes the UI.
	 */
	private void initialize()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 36, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		this.getContentPane().setLayout(gridBagLayout);

		buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.anchor = GridBagConstraints.NORTH;
		gbc_buttonPanel.insets = new Insets(5, 5, 5, 5);
		gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 0;
		this.getContentPane().add(buttonPanel, gbc_buttonPanel);

		addUserButton = new JButton(Messages.getString("manageUsers"));
		buttonPanel.add(addUserButton);

		exportButton = new JButton(Messages.getString("MainView.btnExport.text")); //$NON-NLS-1$
		buttonPanel.add(exportButton);

		addIssueButton = new JButton(Messages.getString("addIssue"));
		buttonPanel.add(addIssueButton);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.getContentPane().add(scrollPane, gbc_scrollPane);

		table = new ListTable<Issue>(Issue.class);
		table.setFilterable(true);
		table.setSortable(true);
		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		issuePanel = new JPanel();
		GridBagConstraints gbc_issuePanel = new GridBagConstraints();
		gbc_issuePanel.insets = new Insets(0, 0, 5, 0);
		gbc_issuePanel.fill = GridBagConstraints.BOTH;
		gbc_issuePanel.gridx = 2;
		gbc_issuePanel.gridy = 1;
		getContentPane().add(issuePanel, gbc_issuePanel);
		GridBagLayout gbl_issuePanel = new GridBagLayout();
		gbl_issuePanel.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_issuePanel.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_issuePanel.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gbl_issuePanel.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		issuePanel.setLayout(gbl_issuePanel);

		issueIDLabel = new JLabel(Messages.getString("MainView.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueIDLabel = new GridBagConstraints();
		gbc_issueIDLabel.anchor = GridBagConstraints.WEST;
		gbc_issueIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueIDLabel.gridx = 0;
		gbc_issueIDLabel.gridy = 0;
		issuePanel.add(issueIDLabel, gbc_issueIDLabel);

		issueTitleLabel = new JLabel(Messages.getString("MainView.lblTitle.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueTitleLabel = new GridBagConstraints();
		gbc_issueTitleLabel.anchor = GridBagConstraints.WEST;
		gbc_issueTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueTitleLabel.gridx = 0;
		gbc_issueTitleLabel.gridy = 1;
		issuePanel.add(issueTitleLabel, gbc_issueTitleLabel);

		issueAuthorLabel = new JLabel(Messages.getString("MainView.lblAuthor.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueAuthorLabel = new GridBagConstraints();
		gbc_issueAuthorLabel.anchor = GridBagConstraints.WEST;
		gbc_issueAuthorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueAuthorLabel.gridx = 0;
		gbc_issueAuthorLabel.gridy = 2;
		issuePanel.add(issueAuthorLabel, gbc_issueAuthorLabel);

		issueTypeLabel = new JLabel(Messages.getString("MainView.lblType.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueTypeLabel = new GridBagConstraints();
		gbc_issueTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_issueTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueTypeLabel.gridx = 0;
		gbc_issueTypeLabel.gridy = 3;
		issuePanel.add(issueTypeLabel, gbc_issueTypeLabel);

		issuePriorityLabel = new JLabel(Messages.getString("MainView.lblPriority.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issuePriorityLabel = new GridBagConstraints();
		gbc_issuePriorityLabel.anchor = GridBagConstraints.WEST;
		gbc_issuePriorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issuePriorityLabel.gridx = 0;
		gbc_issuePriorityLabel.gridy = 4;
		issuePanel.add(issuePriorityLabel, gbc_issuePriorityLabel);

		assigneesTextArea = new JTextArea();
		assigneesTextArea.setOpaque(false);
		assigneesTextArea.setFont(issuePriorityLabel.getFont());
		assigneesTextArea.setEditable(false);
		assigneesTextArea.setText(Messages.getString("MainView.textArea.text")); //$NON-NLS-1$
		GridBagConstraints gbc_assigneesTextArea = new GridBagConstraints();
		gbc_assigneesTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_assigneesTextArea.fill = GridBagConstraints.BOTH;
		gbc_assigneesTextArea.gridx = 0;
		gbc_assigneesTextArea.gridy = 5;
		issuePanel.add(assigneesTextArea, gbc_assigneesTextArea);

		issueStatusLabel = new JLabel(Messages.getString("MainView.lblStatus.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueStatusLabel = new GridBagConstraints();
		gbc_issueStatusLabel.anchor = GridBagConstraints.WEST;
		gbc_issueStatusLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueStatusLabel.gridx = 0;
		gbc_issueStatusLabel.gridy = 6;
		issuePanel.add(issueStatusLabel, gbc_issueStatusLabel);

		// STATES
		comboBoxModel = new DefaultComboBoxModel<>(possibleStates);
		stateComboBox = new JComboBox<>(comboBoxModel);

		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.anchor = GridBagConstraints.WEST;
		gbc_stateComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_stateComboBox.gridx = 0;
		gbc_stateComboBox.gridy = 7;
		issuePanel.add(stateComboBox, gbc_stateComboBox);

		setStatusButton = new JButton(Messages.getString("MainView.btnSet.text")); //$NON-NLS-1$
		GridBagConstraints gbc_setStatusButton = new GridBagConstraints();
		gbc_setStatusButton.gridx = 1;
		gbc_setStatusButton.gridy = 7;
		issuePanel.add(setStatusButton, gbc_setStatusButton);

		userPanel = new JPanel();
		GridBagConstraints gbc_userPanel = new GridBagConstraints();
		gbc_userPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userPanel.insets = new Insets(5, 5, 5, 5);
		gbc_userPanel.gridx = 0;
		gbc_userPanel.gridy = 2;
		getContentPane().add(userPanel, gbc_userPanel);
		userPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		refreshButton = new JButton(Messages.getString("MainView.btnRefresh.text"));
		refreshButton.setHorizontalAlignment(SwingConstants.LEFT);
		userPanel.add(refreshButton);

		logOutButton = new JButton(Messages.getString("MainView.btnLogout.text"));
		userPanel.add(logOutButton);

		userNameLabel = new JLabel(Messages.getString("MainView.userNameLabel.text"));
		userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userPanel.add(userNameLabel);

		this.pack();
	}

	public void setPossibleStates()
	{
		// currentUserName
		// currentState
		// currentRank
		// progressUser
		if (progressUser != null)
		{
			if (currentUserName.equals(progressUser))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Rejected.toString(), IssueState.Reopen.toString(),
						IssueState.VerifiedDone.toString() };
			}
			else
			{
				possibleStates = new String[]
				{};
			}

			comboBoxModel = new DefaultComboBoxModel<>(possibleStates);
			stateComboBox = new JComboBox<>(comboBoxModel);
			return;
		}

		if (currentRank == 1)
		{
			if (currentState.equals(IssueState.Pending.toString()))
			{
				possibleStates = new String[]
				{ IssueState.InProgress.toString(), IssueState.Done.toString(), IssueState.Rejected.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Done.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Rejected.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Rejected.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Reopen.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Rejected.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.VerifiedDone.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Rejected.toString(), IssueState.Reopen.toString() };
			}

		}
		else if (currentRank == 2)
		{
			if (currentState.equals(IssueState.Pending.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Done.toString(), IssueState.Rejected.toString() };
			}

			else if (currentState.equals(IssueState.Done.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Rejected.toString(), IssueState.Reopen.toString() };
			}

			else if (currentState.equals(IssueState.Rejected.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Reopen.toString() };
			}

			else if (currentState.equals(IssueState.Reopen.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Done.toString(), IssueState.Rejected.toString() };
			}

			else if (currentState.equals(IssueState.VerifiedDone.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Reopen.toString() };
			}
		}
		else if (currentRank == 3)
		{

			if (currentState.equals(IssueState.Pending.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Done.toString(), IssueState.InProgress.toString(), IssueState.Rejected.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Done.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Rejected.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Rejected.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Reopen.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.Reopen.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Rejected.toString(), IssueState.VerifiedDone.toString() };
			}

			else if (currentState.equals(IssueState.VerifiedDone.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.InProgress.toString(), IssueState.Done.toString(),
						IssueState.Rejected.toString(), IssueState.Reopen.toString() };
			}

		}
		else if (currentRank == 4)
		{
			if (currentState.equals(IssueState.Pending.toString()))
			{
				possibleStates = new String[]
				{ IssueState.InProgress.toString(), IssueState.Done.toString(), IssueState.Rejected.toString() };
			}

			else if (currentState.equals(IssueState.Done.toString()))
			{
				possibleStates = new String[]
				{};
				// this.stateSetButton.setEnabled(false); // TODO
				// this.assignButton.setEnabled(false);
			}

			else if (currentState.equals(IssueState.Rejected.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Pending.toString(), IssueState.Reopen.toString() };
			}

			else if (currentState.equals(IssueState.Reopen.toString()))
			{
				possibleStates = new String[]
				{ IssueState.Done.toString() };
			}

			else if (currentState.equals(IssueState.VerifiedDone.toString()))
			{
				possibleStates = new String[]
				{};
				// this.stateSetButton.setEnabled(false); // TODO
			}

			comboBoxModel.removeAllElements();
			for (String state : possibleStates)
			{
				comboBoxModel.addElement(state);
			}
		}
	}

	public void setCurrentState(String newState)
	{
		this.currentState = newState;
	}

	public void setProgressUser(String newProgressUser)
	{
		this.progressUser = newProgressUser;
	}

	/**
	 * 
	 * @return the add issue button.
	 */
	public JButton getAddIssueButton()
	{
		return addIssueButton;
	}

	/**
	 * 
	 * @return the add user button.
	 */
	public JButton getAddUserButton()
	{
		return addUserButton;
	}

	/**
	 * 
	 * @return the refresh button.
	 */
	public JButton getRefreshButton()
	{
		return refreshButton;
	}

	/**
	 * 
	 * @return the export button.
	 */
	public JButton getExportButton()
	{
		return exportButton;
	}

	/**
	 * 
	 * @return the log out button
	 */
	public JButton getLogOutButton()
	{
		return logOutButton;
	}

	public JButton getSetStatusButton()
	{
		return setStatusButton;
	}

	public String getSelectedState()
	{
		return stateComboBox.getSelectedItem().toString();
	}

	public JComboBox<String> getStateComboBox()
	{
		return stateComboBox;
	}

	public void setIssueState(String input)
	{
		issueStatusLabel.setText("State: " + input);
	}

	/**
	 * 
	 * @return the table model.
	 */
	public ListTableModel<Issue> getDefaultTableModel()
	{
		return table.getModel();
	}

	/**
	 * setter for the current user name.
	 * 
	 * @param userName
	 */
	public void setCurrentUserName(String userName)
	{
		this.currentUserName = userName;
		String rankName = "";
		if (this.currentRank == 1)
			rankName = "Manager";
		else if (this.currentRank == 2)
			rankName = "Analyst";
		else if (this.currentRank == 3)
			rankName = "Tester";
		else if (this.currentRank == 4)
			rankName = "Developer";

		this.userNameLabel.setText("User: " + userName + ",  " + rankName);

		if (currentRank > 1)
			addUserButton.setVisible(false);

	}

	/**
	 * 
	 * @param rank
	 */
	public void setRank(int rank)
	{
		this.currentRank = rank;
	}

	/**
	 * 
	 * @return the current user name.
	 */
	public String getCurrentUserName()
	{
		return this.currentUserName;
	}

	/**
	 * 
	 * @return the table.
	 */
	public ListTable<Issue> getTable()
	{
		return table;
	}

	public JLabel getIssueIDLabel()
	{
		return issueIDLabel;
	}

	public JLabel getIssueTitleLabel()
	{
		return issueTitleLabel;
	}

	public JLabel getIssueAuthorLabel()
	{
		return issueAuthorLabel;
	}

	public JLabel getIssueTypeLabel()
	{
		return issueTypeLabel;
	}

	public JLabel getIssuePriorityLabel()
	{
		return issuePriorityLabel;
	}

	public JTextArea getIssueAssigneesTextArea()
	{
		return assigneesTextArea;
	}

	public JLabel getIssueStatusLabel()
	{
		return issueStatusLabel;
	}

	/**
	 * adds the given issue to the table.
	 * 
	 * @param issue
	 */
	public void addIssueToJTable(Issue issue)
	{

		table.getModel().addRow(issue);

//		defaultTableModel.addRow(new String[]
//		{ Integer.toString(issueID), title, type, Integer.toString(priority), author, description, state });
	}

	/**
	 * Clears the JTable
	 */
	public void clearJTable()
	{
		table.getModel().removeAllRows();
	}

	public void updateIssueStateOnJTable(String title, String newState)
	{
		List<Issue> temp = table.getModel().getRows();
		int count = 0;
		for (Issue issue : temp)
		{
			if (issue.getTitle().equals(title))
			{
				table.getModel().setRow(count, new Issue(issue.getId(), issue.getTitle(), issue.getType(),
						issue.getPriority(), issue.getAuthor(), issue.getDescription(), newState));
				break;
			}
			else
			{
				count++;
			}
		}
	}

}
