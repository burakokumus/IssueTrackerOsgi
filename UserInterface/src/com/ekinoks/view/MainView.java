package com.ekinoks.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.ekinoks.database.DatabaseManager;
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
	private IssueState currentState;
	private String currentTitle;
	private String currentProject;
	private IssueState[] possibleStates;
	private String[] allProjects;
	private Vector<String> possibleAssignees;

	private DefaultComboBoxModel<IssueState> comboBoxModel;
	private DefaultComboBoxModel<String> assignComboBoxModel;

	public JDialog addIssueDialog;
	private ListTable<Issue> table;
	private JPanel buttonPanel;
	private JButton addIssueButton;
	private JButton manageUsersButton;
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
	private JComboBox<IssueState> stateComboBox;
	private JButton setStatusButton;
	private JTextArea assigneesTextArea;
	private JComboBox<String> assignComboBox;
	private JButton assignButton;
	private JLabel progressUserLabel;
	private JLabel projectNameLabel;
	private JButton selectProjectButton;
	private JButton addProjectButton;
	private JButton editButton;
	private JLabel descriptionLabel;
	private JLabel createDateLabel;
	private JLabel startDateLabel;
	private JLabel finishDateLabel;
	private JLabel timeSpentLabel;
	private JButton commentsButton;
	private JButton manageAssigneesButton;

	/**
	 * Default constructor
	 */
	public MainView()
	{
		this.currentUserName = "";
		this.possibleStates = new IssueState[]
		{};
		ArrayList<String> allProjects = DatabaseManager.getInstance().getAllProjectNames();
		this.allProjects = new String[allProjects.size()];
		for (int i = 0; i < allProjects.size(); i++)
		{
			this.allProjects[i] = allProjects.get(i);
		}
		initialize();
		this.setTitle("Issue Tracker");
		this.setCurrentProjectName("prj1");
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
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 36, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
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

		manageUsersButton = new JButton(Messages.getString("manageUsers"));
		buttonPanel.add(manageUsersButton);

		addProjectButton = new JButton(Messages.getString("MainView.btnAddProject.text")); //$NON-NLS-1$
		buttonPanel.add(addProjectButton);

		selectProjectButton = new JButton(Messages.getString("MainView.btnSelectProject.text")); //$NON-NLS-1$
		buttonPanel.add(selectProjectButton);

		exportButton = new JButton(Messages.getString("MainView.btnExport.text")); //$NON-NLS-1$
		buttonPanel.add(exportButton);

		addIssueButton = new JButton(Messages.getString("addIssue"));
		buttonPanel.add(addIssueButton);

		table = new ListTable<Issue>(Issue.class);
		table.setFilterable(true);
		table.setSortable(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(40);
		Font tableFont = new Font("Times New Roman", Font.PLAIN, 17);
		table.setGridColor(new Color(30, 20, 120));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(tableFont);
		table.setContextMenuVisible(true);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		scrollPane.setMinimumSize(new Dimension(800, 600));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.getContentPane().add(scrollPane, gbc_scrollPane);

		issuePanel = new JPanel();
		setDetailPanelVisible(false);
		GridBagConstraints gbc_issuePanel = new GridBagConstraints();
		gbc_issuePanel.anchor = GridBagConstraints.EAST;
		gbc_issuePanel.insets = new Insets(0, 0, 5, 0);
		gbc_issuePanel.fill = GridBagConstraints.VERTICAL;
		gbc_issuePanel.gridx = 1;
		gbc_issuePanel.gridy = 1;
		getContentPane().add(issuePanel, gbc_issuePanel);
		GridBagLayout gbl_issuePanel = new GridBagLayout();
		gbl_issuePanel.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_issuePanel.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_issuePanel.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gbl_issuePanel.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		issuePanel.setLayout(gbl_issuePanel);

		// STATES
		comboBoxModel = new DefaultComboBoxModel<>(possibleStates);

		possibleAssignees = DatabaseManager.getInstance().getPossibleAssignees(currentTitle);
		assignComboBoxModel = new DefaultComboBoxModel<String>(possibleAssignees);

		projectNameLabel = new JLabel(Messages.getString("MainView.lblProjectName.text")); //$NON-NLS-1$
		GridBagConstraints gbc_projectNameLabel = new GridBagConstraints();
		gbc_projectNameLabel.anchor = GridBagConstraints.WEST;
		gbc_projectNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_projectNameLabel.gridx = 0;
		gbc_projectNameLabel.gridy = 0;
		issuePanel.add(projectNameLabel, gbc_projectNameLabel);

		issueIDLabel = new JLabel(Messages.getString("MainView.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueIDLabel = new GridBagConstraints();
		gbc_issueIDLabel.anchor = GridBagConstraints.WEST;
		gbc_issueIDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueIDLabel.gridx = 0;
		gbc_issueIDLabel.gridy = 1;
		issuePanel.add(issueIDLabel, gbc_issueIDLabel);

		issueTitleLabel = new JLabel(Messages.getString("MainView.lblTitle.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueTitleLabel = new GridBagConstraints();
		gbc_issueTitleLabel.anchor = GridBagConstraints.WEST;
		gbc_issueTitleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueTitleLabel.gridx = 0;
		gbc_issueTitleLabel.gridy = 2;
		issuePanel.add(issueTitleLabel, gbc_issueTitleLabel);

		issueAuthorLabel = new JLabel(Messages.getString("MainView.lblAuthor.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueAuthorLabel = new GridBagConstraints();
		gbc_issueAuthorLabel.anchor = GridBagConstraints.WEST;
		gbc_issueAuthorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueAuthorLabel.gridx = 0;
		gbc_issueAuthorLabel.gridy = 3;
		issuePanel.add(issueAuthorLabel, gbc_issueAuthorLabel);

		issueTypeLabel = new JLabel(Messages.getString("MainView.lblType.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueTypeLabel = new GridBagConstraints();
		gbc_issueTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_issueTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueTypeLabel.gridx = 0;
		gbc_issueTypeLabel.gridy = 4;
		issuePanel.add(issueTypeLabel, gbc_issueTypeLabel);

		issuePriorityLabel = new JLabel(Messages.getString("MainView.lblPriority.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issuePriorityLabel = new GridBagConstraints();
		gbc_issuePriorityLabel.anchor = GridBagConstraints.WEST;
		gbc_issuePriorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issuePriorityLabel.gridx = 0;
		gbc_issuePriorityLabel.gridy = 5;
		issuePanel.add(issuePriorityLabel, gbc_issuePriorityLabel);

		assigneesTextArea = new JTextArea();
		assigneesTextArea.setFont(issuePriorityLabel.getFont());
		assigneesTextArea.setOpaque(false);
		assigneesTextArea.setEditable(false);
		assigneesTextArea.setText(Messages.getString("MainView.textArea.text")); //$NON-NLS-1$
		GridBagConstraints gbc_assigneesTextArea = new GridBagConstraints();
		gbc_assigneesTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_assigneesTextArea.fill = GridBagConstraints.BOTH;
		gbc_assigneesTextArea.gridx = 0;
		gbc_assigneesTextArea.gridy = 6;
		issuePanel.add(assigneesTextArea, gbc_assigneesTextArea);

		issueStatusLabel = new JLabel(Messages.getString("MainView.lblStatus.text")); //$NON-NLS-1$
		GridBagConstraints gbc_issueStatusLabel = new GridBagConstraints();
		gbc_issueStatusLabel.anchor = GridBagConstraints.WEST;
		gbc_issueStatusLabel.insets = new Insets(0, 0, 5, 5);
		gbc_issueStatusLabel.gridx = 0;
		gbc_issueStatusLabel.gridy = 7;
		issuePanel.add(issueStatusLabel, gbc_issueStatusLabel);

		progressUserLabel = new JLabel(Messages.getString("MainView.lblProgressUser.text")); //$NON-NLS-1$
		GridBagConstraints gbc_progressUserLabel = new GridBagConstraints();
		gbc_progressUserLabel.anchor = GridBagConstraints.WEST;
		gbc_progressUserLabel.insets = new Insets(0, 0, 5, 5);
		gbc_progressUserLabel.gridx = 0;
		gbc_progressUserLabel.gridy = 8;
		issuePanel.add(progressUserLabel, gbc_progressUserLabel);

		descriptionLabel = new JLabel(Messages.getString("MainView.lblDescription.text")); //$NON-NLS-1$
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.WEST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 9;
		issuePanel.add(descriptionLabel, gbc_descriptionLabel);

		createDateLabel = new JLabel(Messages.getString("MainView.lblCreateDate.text")); //$NON-NLS-1$
		GridBagConstraints gbc_createDateLabel = new GridBagConstraints();
		gbc_createDateLabel.anchor = GridBagConstraints.WEST;
		gbc_createDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_createDateLabel.gridx = 0;
		gbc_createDateLabel.gridy = 10;
		issuePanel.add(createDateLabel, gbc_createDateLabel);

		startDateLabel = new JLabel(Messages.getString("MainView.lblStartDate.text")); //$NON-NLS-1$
		GridBagConstraints gbc_startDateLabel = new GridBagConstraints();
		gbc_startDateLabel.anchor = GridBagConstraints.WEST;
		gbc_startDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_startDateLabel.gridx = 0;
		gbc_startDateLabel.gridy = 11;
		issuePanel.add(startDateLabel, gbc_startDateLabel);

		finishDateLabel = new JLabel(Messages.getString("MainView.lblFinishDate.text")); //$NON-NLS-1$
		GridBagConstraints gbc_finishDateLabel = new GridBagConstraints();
		gbc_finishDateLabel.anchor = GridBagConstraints.WEST;
		gbc_finishDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_finishDateLabel.gridx = 0;
		gbc_finishDateLabel.gridy = 12;
		issuePanel.add(finishDateLabel, gbc_finishDateLabel);

		timeSpentLabel = new JLabel(Messages.getString("MainView.lblTimeSpent.text")); //$NON-NLS-1$
		GridBagConstraints gbc_timeSpentLabel = new GridBagConstraints();
		gbc_timeSpentLabel.anchor = GridBagConstraints.WEST;
		gbc_timeSpentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timeSpentLabel.gridx = 0;
		gbc_timeSpentLabel.gridy = 13;
		issuePanel.add(timeSpentLabel, gbc_timeSpentLabel);
		stateComboBox = new JComboBox<>(comboBoxModel);

		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_stateComboBox.gridx = 0;
		gbc_stateComboBox.gridy = 16;
		issuePanel.add(stateComboBox, gbc_stateComboBox);

		setStatusButton = new JButton(Messages.getString("MainView.btnSet.text")); //$NON-NLS-1$
		GridBagConstraints gbc_setStatusButton = new GridBagConstraints();
		gbc_setStatusButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_setStatusButton.insets = new Insets(0, 0, 5, 0);
		gbc_setStatusButton.gridx = 1;
		gbc_setStatusButton.gridy = 16;
		issuePanel.add(setStatusButton, gbc_setStatusButton);
		assignComboBox = new JComboBox<>(assignComboBoxModel);
		GridBagConstraints gbc_assignComboBox = new GridBagConstraints();
		gbc_assignComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_assignComboBox.gridx = 0;
		gbc_assignComboBox.gridy = 17;
		issuePanel.add(assignComboBox, gbc_assignComboBox);

		assignButton = new JButton(Messages.getString("MainView.btnAssing.text")); //$NON-NLS-1$
		GridBagConstraints gbc_assignButton = new GridBagConstraints();
		gbc_assignButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignButton.insets = new Insets(0, 0, 5, 0);
		gbc_assignButton.gridx = 1;
		gbc_assignButton.gridy = 17;
		issuePanel.add(assignButton, gbc_assignButton);

		editButton = new JButton(Messages.getString("MainView.btnEdit.text")); //$NON-NLS-1$
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 0);
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 18;
		issuePanel.add(editButton, gbc_editButton);

		commentsButton = new JButton(Messages.getString("MainView.btnComments.text")); //$NON-NLS-1$
		GridBagConstraints gbc_commentsButton = new GridBagConstraints();
		gbc_commentsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_commentsButton.insets = new Insets(0, 0, 5, 0);
		gbc_commentsButton.gridx = 1;
		gbc_commentsButton.gridy = 19;
		issuePanel.add(commentsButton, gbc_commentsButton);

		manageAssigneesButton = new JButton(Messages.getString("MainView.btnManageAssignees.text")); //$NON-NLS-1$
		GridBagConstraints gbc_manageAssigneesButton = new GridBagConstraints();
		gbc_manageAssigneesButton.anchor = GridBagConstraints.EAST;
		gbc_manageAssigneesButton.gridx = 1;
		gbc_manageAssigneesButton.gridy = 20;
		issuePanel.add(manageAssigneesButton, gbc_manageAssigneesButton);

		userPanel = new JPanel();
		GridBagConstraints gbc_userPanel = new GridBagConstraints();
		gbc_userPanel.anchor = GridBagConstraints.SOUTH;
		gbc_userPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userPanel.insets = new Insets(5, 5, 0, 5);
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

	/**
	 * Sets the combo box for changing the state of the issue
	 */
	public void setPossibleStates()
	{
		if (progressUser != null)
		{
			if (currentUserName.equals(progressUser))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.Done, IssueState.Rejected, IssueState.Reopen,
						IssueState.VerifiedDone };
			}
			else
			{
				possibleStates = new IssueState[]
				{};
			}

			comboBoxModel.removeAllElements();
			for (IssueState state : possibleStates)
			{
				comboBoxModel.addElement(state);
			}

			possibleAssignees = DatabaseManager.getInstance().getPossibleAssignees(currentTitle);
			assignComboBoxModel.removeAllElements();
			for (String user : possibleAssignees)
			{
				assignComboBoxModel.addElement(user);
			}
			return;
		}

		if (currentRank == 1)
		{
			if (currentState.equals(IssueState.Pending))
			{
				possibleStates = new IssueState[]
				{ IssueState.InProgress, IssueState.Done, IssueState.Rejected, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Done))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Rejected, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Rejected))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Done, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Reopen))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Done, IssueState.Rejected,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.VerifiedDone))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Done, IssueState.Rejected, IssueState.Reopen };
			}

		}
		else if (currentRank == 2)
		{
			if (currentState.equals(IssueState.Pending))
			{
				possibleStates = new IssueState[]
				{ IssueState.Done, IssueState.Rejected };
			}

			else if (currentState.equals(IssueState.Done))
			{
				possibleStates = new IssueState[]
				{ IssueState.Rejected, IssueState.Reopen };
			}

			else if (currentState.equals(IssueState.Rejected))
			{
				possibleStates = new IssueState[]
				{ IssueState.Reopen };
			}

			else if (currentState.equals(IssueState.Reopen))
			{
				possibleStates = new IssueState[]
				{ IssueState.Done, IssueState.Rejected };
			}

			else if (currentState.equals(IssueState.VerifiedDone))
			{
				possibleStates = new IssueState[]
				{ IssueState.Reopen };
			}
		}
		else if (currentRank == 3)
		{

			if (currentState.equals(IssueState.Pending))
			{
				possibleStates = new IssueState[]
				{ IssueState.Done, IssueState.InProgress, IssueState.Rejected, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Done))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Rejected, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Rejected))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Done, IssueState.Reopen,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.Reopen))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.InProgress, IssueState.Done, IssueState.Rejected,
						IssueState.VerifiedDone };
			}

			else if (currentState.equals(IssueState.VerifiedDone))
			{
				possibleStates = new IssueState[]
				{};
			}

		}
		else if (currentRank == 4)
		{
			if (currentState.equals(IssueState.Pending))
			{
				possibleStates = new IssueState[]
				{ IssueState.InProgress, IssueState.Done, IssueState.Rejected };
			}

			else if (currentState.equals(IssueState.Done))
			{
				possibleStates = new IssueState[]
				{};
			}

			else if (currentState.equals(IssueState.Rejected))
			{
				possibleStates = new IssueState[]
				{ IssueState.Pending, IssueState.Reopen };
			}

			else if (currentState.equals(IssueState.Reopen))
			{
				possibleStates = new IssueState[]
				{ IssueState.Done };
			}

			else if (currentState.equals(IssueState.VerifiedDone))
			{
				possibleStates = new IssueState[]
				{};
			}

		}
		comboBoxModel.removeAllElements();
		for (IssueState state : possibleStates)
		{
			comboBoxModel.addElement(state);
		}

		possibleAssignees = DatabaseManager.getInstance().getPossibleAssignees(currentTitle);
		assignComboBoxModel.removeAllElements();
		for (String user : possibleAssignees)
		{
			assignComboBoxModel.addElement(user);
		}
	}

	/**
	 * Sets the current state of the selected issue
	 * 
	 * @param newState
	 */
	public void setCurrentState(IssueState newState)
	{
		this.currentState = newState;
	}

	/**
	 * Sets the progress user of the selected issue
	 * 
	 * @param newProgressUser
	 */
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
	public JButton getManagerUsersButton()
	{
		return manageUsersButton;
	}

	/**
	 * 
	 * @return the add project button
	 */
	public JButton getAddProjectButton()
	{
		return addProjectButton;
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
	 * @return the edit button.
	 */
	public JButton getEditButton()
	{
		return editButton;
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

	/**
	 * 
	 * @return the set status button
	 */
	public JButton getSetStatusButton()
	{
		return setStatusButton;
	}

	/**
	 * 
	 * @return the comments button
	 */
	public JButton getCommentsButton()
	{
		return commentsButton;
	}

	/**
	 * 
	 * @return the manage assignees button
	 */
	public JButton getManageAssigneesButton()
	{
		return manageAssigneesButton;
	}

	/**
	 * 
	 * @return assign button
	 */
	public JButton getAssignButton()
	{
		return assignButton;
	}

	/**
	 * 
	 * @return select project button
	 */
	public JButton getSelectProjectButton()
	{
		return selectProjectButton;
	}

	/**
	 * 
	 * @return the selected user on the JComboBox
	 */
	public String getSelectedUser()
	{
		return assignComboBox.getSelectedItem().toString();
	}

	/**
	 * 
	 * @return the selected state on the JComboBox
	 */
	public IssueState getSelectedState()
	{
		return (IssueState) stateComboBox.getSelectedItem();
	}

	/**
	 * 
	 * @return the state selection JComboBox
	 */
	public JComboBox<IssueState> getStateComboBox()
	{
		return stateComboBox;
	}

	/**
	 * Sets the issue State
	 * 
	 * @param input
	 */
	public void setIssueState(IssueState input)
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
	 * @return the table
	 */
	public ListTable<Issue> getListTable()
	{
		return table;
	}

	/**
	 * setter for the title
	 * 
	 * @param title
	 */
	public void setCurrentTitle(String title)
	{
		this.currentTitle = title;
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
		editButton.setVisible(false);
		setStatusButton.setVisible(false);
		assignButton.setVisible(false);
		stateComboBox.setVisible(false);
		assignComboBox.setVisible(false);

		if (currentRank > 1)
		{
			manageUsersButton.setVisible(false);
			addProjectButton.setVisible(false);
		}
		else
		{
			manageUsersButton.setVisible(true);
			addProjectButton.setVisible(true);
		}

	}

	/**
	 * setter for the current project.
	 * 
	 * @param project
	 */
	public void setCurrentProjectName(String project)
	{
		this.currentProject = project;
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
	 * @return the current project.
	 */
	public String getCurrentProject()
	{
		return currentProject;
	}

	/**
	 * 
	 * @return the table.
	 */
	public ListTable<Issue> getTable()
	{
		return table;
	}

	public JLabel getIssueProjectNameLabel()
	{
		return projectNameLabel;
	}

	/**
	 * 
	 * @return issue id label
	 */
	public JLabel getIssueIDLabel()
	{
		return issueIDLabel;
	}

	/**
	 * 
	 * @return issue title label
	 */
	public JLabel getIssueTitleLabel()
	{
		return issueTitleLabel;
	}

	/**
	 * 
	 * @return issue author label
	 */
	public JLabel getIssueAuthorLabel()
	{
		return issueAuthorLabel;
	}

	/**
	 * 
	 * @return issue type label
	 */
	public JLabel getIssueTypeLabel()
	{
		return issueTypeLabel;
	}

	/**
	 * 
	 * @return issue priority label
	 */
	public JLabel getIssuePriorityLabel()
	{
		return issuePriorityLabel;
	}

	/**
	 * 
	 * @return issue progress user label
	 */
	public JLabel getIssueProgressUserLabel()
	{
		return progressUserLabel;
	}

	/**
	 * 
	 * @return issue assignees label
	 */
	public JTextArea getIssueAssigneesTextArea()
	{
		return assigneesTextArea;
	}

	/**
	 * 
	 * @return issue status label
	 */
	public JLabel getIssueStatusLabel()
	{
		return issueStatusLabel;
	}

	/**
	 * 
	 * @return issue description label
	 */
	public JLabel getIssueDescriptionLabel()
	{
		return descriptionLabel;
	}

	/**
	 * 
	 * @return create date label
	 */
	public JLabel getCreateDateLabel()
	{
		return createDateLabel;
	}

	/**
	 * 
	 * @return start date label
	 */
	public JLabel getStartDateLabel()
	{
		return startDateLabel;
	}

	/**
	 * 
	 * @return finish date label
	 */
	public JLabel getFinishDateLabel()
	{
		return finishDateLabel;
	}

	/**
	 * 
	 * @return time spent label
	 */
	public JLabel getTimeSpentLabel()
	{
		return timeSpentLabel;
	}

	/**
	 * adds the given issue to the table.
	 * 
	 * @param issue
	 */
	public void addIssueToJTable(Issue issue)
	{
		table.getModel().addRow(issue);
	}

	/**
	 * Clears the JTable
	 */
	public void clearJTable()
	{
		table.getModel().removeAllRows();
	}

	/**
	 * Updates the issue state on the JTable
	 * 
	 * @param title
	 * @param newState
	 */
	public void updateIssueStateOnJTable(String title, IssueState newState)
	{
		List<Issue> temp = table.getModel().getRows();
		int count = 0;
		for (Issue issue : temp)
		{
			if (issue.getTitle().equals(title))
			{
				table.getModel().setRow(count,
						new Issue(issue.getProjectName(), issue.getId(), issue.getTitle(), issue.getType(),
								issue.getPriority(), issue.getAuthor(), issue.getDescription(), newState.toString()));
				break;
			}
			else
			{
				count++;
			}
		}
	}

	/**
	 * Sets the panel on the right side visible or not
	 */
	public void setDetailPanelVisible(boolean isVisible)
	{
		issuePanel.setVisible(isVisible);
	}

	public Vector<String> getPossibleAssignees()
	{
		return possibleAssignees;
	}

	public JComboBox<String> getAssignComboBox()
	{
		return assignComboBox;
	}

	public String[] getAllProjects()
	{
		return allProjects;
	}

}
