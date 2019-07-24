package com.ekinoks.controller;

import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.database.LogManager;
import com.ekinoks.model.Issue;
import com.ekinoks.model.IssueConfiguration;
import com.ekinoks.model.IssueState;
import com.ekinoks.model.User;
import com.ekinoks.ui.components.common.fieldGenerator.FieldConfiguration;
import com.ekinoks.view.AddIssueDialogView;
import com.ekinoks.view.AddUserDialogView;
import com.ekinoks.view.MainView;
import com.ekinoks.view.Messages;

public class Controller
{
	private MainView view;
	private String currentUserName;
	private String currentTitle;
	private String currentProjectName;

	@SuppressWarnings("unchecked")
	public Controller()
	{
		this.view = new MainView();
		this.currentUserName = "";
		this.currentTitle = "";
		this.currentProjectName = "prj1";

		IssueConfiguration configuration = new IssueConfiguration();

		if (configuration.columnPreferences != null)
		{

			view.getTable().setColumnPreferences(configuration.columnPreferences);
		}

		view.getTable().addColumnPreferencesChangedListener(l ->
		{

			configuration.columnPreferences = (List<FieldConfiguration>) view.getTable().getColumnPreferences();

			configuration.save();

		});
	}

	public void initController()
	{
		view.getAddIssueButton().addActionListener(e -> addIssue());
		view.getManagerUsersButton().addActionListener(e -> manageUsers());
		view.getAddProjectButton().addActionListener(e -> addProject());
		view.getSelectProjectButton().addActionListener(e -> selectProject());
		view.getRefreshButton().addActionListener(e -> refresh());
		view.getEditButton().addActionListener(e -> editIssue());
		view.getExportButton().addActionListener(e -> exportToExcel());
		view.getLogOutButton().addActionListener(e -> logout());
		view.getCommentsButton().addActionListener(e -> comments());
		view.getSetStatusButton().addActionListener(e -> setStatus());
		view.getAssignButton().addActionListener(e -> assign());
		view.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				int rowNo = view.getTable().getSelectedRow();
				if (rowNo > -1)
				{
					view.setDetailPanelVisible(true);
					String projectName = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 0));
					String id = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 1));
					String title = (String) view.getDefaultTableModel().getValueAt(rowNo, 2);
					String type = (String) view.getDefaultTableModel().getValueAt(rowNo, 3);
					String priority = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 4));
					String author = (String) view.getDefaultTableModel().getValueAt(rowNo, 5);
					String description = (String) view.getDefaultTableModel().getValueAt(rowNo, 6);
					IssueState state = IssueState.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 7).toString());
					String progressUser = DatabaseManager.getInstance().getProgressUser(title);
					ArrayList<String> assignees = DatabaseManager.getInstance().getUsersByIssueTitle(title);
					String createDate = DatabaseManager.getInstance().getIssueCreateDate(Integer.parseInt(id));
					String startDate = DatabaseManager.getInstance().getIssueStartDate(Integer.parseInt(id));
					String finishDate = DatabaseManager.getInstance().getIssueFinishDate(Integer.parseInt(id));
					int timeSpent = DatabaseManager.getInstance().getIssueTimeSpent(Integer.parseInt(id));

					int rank = DatabaseManager.getInstance().getUserRank(currentUserName);

					currentTitle = title;

					if (rank == 1)
					{
						view.getAssignComboBox().setVisible(true);
						view.getAssignButton().setVisible(true);
						view.getStateComboBox().setVisible(true);
						view.getSetStatusButton().setVisible(true);
						view.getEditButton().setVisible(true);
					}
					else if (state.toString().equals(IssueState.VerifiedDone.toString()))
					{
						view.getAssignComboBox().setVisible(false);
						view.getAssignButton().setVisible(false);
						view.getStateComboBox().setVisible(false);
						view.getSetStatusButton().setVisible(false);
						view.getEditButton().setVisible(false);
					}
					else if (currentUserName.equals(author) || currentUserName.equals(progressUser))
					{
						view.getAssignComboBox().setVisible(true);
						view.getAssignButton().setVisible(true);
						view.getStateComboBox().setVisible(true);
						view.getSetStatusButton().setVisible(true);
						view.getEditButton().setVisible(true);
					}

					else if (assignees.contains(currentUserName))
					{
						view.getAssignComboBox().setVisible(false);
						view.getAssignButton().setVisible(false);
						view.getStateComboBox().setVisible(true);
						view.getSetStatusButton().setVisible(true);
						view.getEditButton().setVisible(true);
					}
					else
					{
						view.getAssignComboBox().setVisible(false);
						view.getAssignButton().setVisible(false);
						view.getStateComboBox().setVisible(false);
						view.getSetStatusButton().setVisible(false);
						view.getEditButton().setVisible(false);
					}

					view.getIssueProjectNameLabel().setText("Project Name: " + projectName);
					view.getIssueIDLabel().setText("ID: " + id);
					view.getIssueTitleLabel().setText("Title: " + title);
					view.getIssueTypeLabel().setText("Type: " + type);
					view.getIssuePriorityLabel().setText("Priority: " + priority);
					view.getIssueAuthorLabel().setText("Author: " + author);
					view.getIssueStatusLabel().setText("State: " + state);
					view.getIssueDescriptionLabel().setText("Description: " + description);
					view.getCreateDateLabel().setText("Create Date: " + createDate);
					if (progressUser == null)
					{
						view.getIssueProgressUserLabel().setVisible(false);
					}

					else
					{
						view.getIssueProgressUserLabel().setText("Progress User: " + progressUser);
						view.getIssueProgressUserLabel().setVisible(true);
					}

					if (startDate == null)
					{
						view.getStartDateLabel().setVisible(false);
					}

					else
					{
						view.getStartDateLabel().setText("Start Date: " + startDate);
						view.getStartDateLabel().setVisible(true);
					}

					if (finishDate == null)
					{
						view.getFinishDateLabel().setVisible(false);
					}
					else
					{
						view.getFinishDateLabel().setText("Finish Date: " + finishDate);
						view.getFinishDateLabel().setVisible(true);
					}

					if (timeSpent == 0)
					{
						view.getTimeSpentLabel().setVisible(false);
					}
					else
					{
						view.getTimeSpentLabel().setText("Time Spent: " + timeSpent);
						view.getTimeSpentLabel().setVisible(true);
					}

					view.setCurrentState(state);
					view.setProgressUser(DatabaseManager.getInstance().getProgressUser(title));
					view.setCurrentTitle(title);
					String currentAssignees = "Current Assignees: ";
					for (String as : assignees)
						currentAssignees = currentAssignees + as + ", ";
					view.getIssueAssigneesTextArea()
							.setText(currentAssignees.substring(0, currentAssignees.length() - 2));
					view.setPossibleStates();

					view.pack();
				}

			}
		});

		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssuesOfProject(currentProjectName);

		for (Issue issue : issueList)
		{

			view.addIssueToJTable(issue);
		}
	}

	/**
	 * Action Listener for the Add Issue Button
	 */
	public void addIssue()
	{
		LogManager.getInstance().log("Add Issue button is pressed by " + currentUserName);
		AddIssueDialogView addIssueDialogView = new AddIssueDialogView();
		addIssueDialogView.setVisible(true);
		AddIssueDialogController addIssueDialogController = new AddIssueDialogController(addIssueDialogView, view);
		addIssueDialogController.initController();
	}

	/**
	 * Action Listener for the Add User Button
	 */
	@SuppressWarnings("unused")
	private void manageUsers()
	{
		LogManager.getInstance().log("Manage Users button is pressed by " + currentUserName);
		if (DatabaseManager.getInstance().getUserRank(currentUserName) > 1)
		{
			JOptionPane.showOptionDialog(view, Messages.getString("cannotAddUser"), "", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}
		else
		{
			ArrayList<String> requests = DatabaseManager.getInstance().getAllSignUpRequestUserNames();
			ArrayList<User> allUsers = DatabaseManager.getInstance().getAllUsers();
			ArrayList<String> allUsersString = new ArrayList<>();
			for (User user : allUsers)
				allUsersString.add(user.getUserName());
			AddUserDialogView addUserDialogView = new AddUserDialogView(view, requests, allUsersString);
			AddUserDialogController addUserDialogController = new AddUserDialogController(addUserDialogView);
			addUserDialogView.setPreferredSize(new Dimension(300, 300));
			addUserDialogView.pack();
			addUserDialogView.setVisible(true);
		}

	}

	/**
	 * Action Listener for the Add Project Button
	 */
	private void addProject()
	{
		LogManager.getInstance().log("Add Project button is pressed by " + currentUserName);
		@SuppressWarnings("unused")
		AddProjectDialogController addProjectDialogController = new AddProjectDialogController();
	}

	/**
	 * Action Listener for the Select Project Button
	 */
	private void selectProject()
	{
		LogManager.getInstance().log("Select Project button is pressed by " + currentUserName);
		@SuppressWarnings("unused")
		SelectProjectDialogController selectProjectDialogController = new SelectProjectDialogController(view, this,
				view.getAllProjects());
	}

	/**
	 * Action Listener for the Comments Button
	 */
	private void comments()
	{
		int id = DatabaseManager.getInstance().getIssueID(currentTitle);
		@SuppressWarnings("unused")
		CommentsController commentsController = new CommentsController(id);
	}

	/**
	 * Action Listener for the Edit Issue Button
	 */
	private void editIssue()
	{
		@SuppressWarnings("unused")
		EditIssueDialogController editIssueDialogController = new EditIssueDialogController(view, currentTitle);
	}

	/**
	 * Action Listener for the Refresh Button.
	 */
	private void refresh()
	{
		LogManager.getInstance().log("Refresh button is pressed by " + currentUserName);
		view.clearJTable();
		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssuesOfProject(currentProjectName);

		for (Issue issue : issueList)
		{
			view.addIssueToJTable(issue);
		}
	}

	/**
	 * Action Listener for the Log Out Button.
	 */
	private void logout()
	{
		LoginController loginController = new LoginController(this);
		loginController.initController();
		this.view.dispose();
		LogManager.getInstance().log(currentUserName + " logged out!");
	}

	/**
	 * Action Listener for the Set Status Button.
	 */
	private void setStatus()
	{
		IssueState selectedState = view.getSelectedState();
		if (selectedState.equals(IssueState.InProgress))
		{
			DatabaseManager.getInstance().setProgressUser(currentTitle, currentUserName);
		}
		else
		{
			DatabaseManager.getInstance().setProgressUser(currentTitle, null);
		}
		if (selectedState.equals(IssueState.Done))
		{
			@SuppressWarnings("unused")
			DoneConfirmationController verifiedDoneController = new DoneConfirmationController(currentTitle, view);
		}
		else
		{
			DatabaseManager.getInstance().updateIssueState(currentTitle, selectedState);
			view.updateIssueStateOnJTable(currentTitle, selectedState);
			view.setCurrentState(selectedState);
			view.setPossibleStates();
			view.setIssueState(selectedState);
		}

	}

	/**
	 * Action Listener for the Assign Button.
	 */
	private void assign()
	{
		String assignee = view.getSelectedUser();
		DatabaseManager.getInstance().addRelation(assignee, currentTitle);
		view.getPossibleAssignees().remove(assignee);
		String temp = view.getIssueAssigneesTextArea().getText();
		temp += ", " + assignee;
		view.getIssueAssigneesTextArea().setText(temp);
		LogManager.getInstance().log("Issue " + currentTitle + "is assigned to " + assignee + " by " + currentUserName);
	}

	private void exportToExcel()
	{
		LogManager.getInstance().log("Export button is pressed by " + currentUserName);
		String[] columns = new String[]
		{ "ID", "Title", "Type", "Priority", "Author", "Description", "State" };
		ArrayList<Issue> issues = new ArrayList<>();
		List<Issue> temp = view.getTable().getAllRows();
		for (Issue i : temp)
		{
			issues.add(i);
		}

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Issues");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < columns.length; i++)
		{
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 1;
		for (Issue issue : issues)
		{
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(issue.getId());
			row.createCell(1).setCellValue(issue.getTitle());
			row.createCell(2).setCellValue(issue.getType());
			row.createCell(3).setCellValue(issue.getPriority());
			row.createCell(4).setCellValue(issue.getAuthor());
			row.createCell(5).setCellValue(issue.getDescription());
			row.createCell(6).setCellValue(issue.getState());

		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++)
		{
			sheet.autoSizeColumn(i);
		}
		JFileChooser fileChooser = new JFileChooser("./");
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.xlsx", "xlsx"));
		int showSaveDialog = fileChooser.showSaveDialog(null);
		File selectedFile = fileChooser.getSelectedFile();

		try (FileOutputStream fileOut = new FileOutputStream(selectedFile.getPath()))
		{
			// Write the output to a file
			if (showSaveDialog == 0)
			{
				workbook.write(fileOut);
			}

			// Closing the workbook
			workbook.close();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Setter for current user name
	 * 
	 * @param userName
	 */
	public void setCurrentUserName(String userName)
	{
		this.currentUserName = userName;
	}

	/**
	 * Getter for current user name
	 * 
	 * @return
	 */
	public String getCurrentUserName()
	{
		return this.currentUserName;
	}

	public MainView getCurrentView()
	{
		return view;
	}

	public String getCurrentProjectName()
	{
		return currentProjectName;
	}

	public void setCurrentProjectName(String projectName)
	{
		this.currentProjectName = projectName;
	}
}
