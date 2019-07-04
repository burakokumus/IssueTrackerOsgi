package com.ekinoks.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ekinoks.model.Issue;
import com.ekinoks.ui.components.listtable.ListTable;
import com.ekinoks.ui.components.listtable.ListTableModel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainView extends JFrame
{
	private String currentUserName;

	public JDialog addIssueDialog;
	private ListTable<Issue> table;
	private JPanel buttonPanel;
	private JButton addIssueButton;
	private JButton addUserButton;
	private JPanel infoPanel;
	private JLabel userNameLabel;
	private int rank = -1;

	public MainView()
	{
		this.currentUserName = "";
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
		{ 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 36, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
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

		addUserButton = new JButton(Messages.getString("addUser"));
		buttonPanel.add(addUserButton);

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
		
		infoPanel = new JPanel();
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.HORIZONTAL;
//		gbc_infoPanel.anchor = GridBagConstraints.SOUTH;
		gbc_infoPanel.insets = new Insets(5, 5, 5, 5);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 2;
		getContentPane().add(infoPanel, gbc_infoPanel);
		infoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		userNameLabel = new JLabel(Messages.getString("MainView.userNameLabel.text")); //$NON-NLS-1$ //$NON-NLS-1$
		infoPanel.add(userNameLabel);

		this.pack();
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
		if(this.rank == 1)
			rankName = "Manager";
		else if(this.rank == 2)
			rankName = "Analyst";
		else if(this.rank == 3)
			rankName = "Tester";
		else if(this.rank == 4)
			rankName = "Developer";
			
		this.userNameLabel.setText("User: " + userName + ",  " + rankName);
		
	}
	
	/**
	 * 
	 * @param rank
	 */
	public void setRank(int rank)
	{
		this.rank = rank;
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
