package com.ekinoks.controller;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
import com.ekinoks.model.IssueState;
import com.ekinoks.model.User;
import com.ekinoks.view.AddIssueDialogView;
import com.ekinoks.view.AddUserDialogView;
import com.ekinoks.view.IssueDetailsDialogView;
import com.ekinoks.view.MainView;
import com.ekinoks.view.Messages;

public class Controller
{
	private MainView view;
	private String currentUserName;

	public Controller(MainView viewInput)
	{
		this.view = viewInput;
		this.currentUserName = "";
	}

	public void initController()
	{
		view.getAddIssueButton().addActionListener(e -> addIssue());
		view.getAddUserButton().addActionListener(e -> addUser());
		view.getRefreshButton().addActionListener(e -> refresh());
		view.getExportButton().addActionListener(e -> exportToExcel());
		view.getTable().addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				super.mouseClicked(e);
				if (e.getClickCount() % 2 == 0)
				{
					int rowNo = view.getTable().rowAtPoint(e.getPoint());
					String id = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 0));
					String title = (String) view.getDefaultTableModel().getValueAt(rowNo, 1);
					String type = (String) view.getDefaultTableModel().getValueAt(rowNo, 2);
					String priority = String.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 3));
					String author = (String) view.getDefaultTableModel().getValueAt(rowNo, 4);
					String description = (String) view.getDefaultTableModel().getValueAt(rowNo, 5);
					IssueState state = IssueState.valueOf(view.getDefaultTableModel().getValueAt(rowNo, 6).toString());
					ArrayList<String> assignees = DatabaseManager.getInstance().getUsersByIssueTitle(title);
					String progressUser = DatabaseManager.getInstance().getProgressUser(title);

					Vector<String> possibleAssignees = DatabaseManager.getInstance().getPossibleAssignees(title);

					IssueDetailsDialogView issueDetailsDialogView = new IssueDetailsDialogView(currentUserName,
							DatabaseManager.getInstance().getUserRank(currentUserName), author, progressUser, state,
							assignees, possibleAssignees);
					issueDetailsDialogView.setIssueID(id);
					issueDetailsDialogView.setIssueTitle(title);
					issueDetailsDialogView.setIssueType(type);
					issueDetailsDialogView.setIssuePriority(priority);
					issueDetailsDialogView.setIssueAuthor(author);
					issueDetailsDialogView.setIssueDescription(description);
					issueDetailsDialogView.setIssueState(state.toString());
					issueDetailsDialogView.showScreen();
					issueDetailsDialogView.setIssueAssignees(assignees);

					IssueDetailsDialogController issueDetailsDialogController = new IssueDetailsDialogController(
							issueDetailsDialogView, view, title, currentUserName, assignees);
					issueDetailsDialogController.initController();

					issueDetailsDialogView.pack();
				}
			}
		});

		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssues();

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
		AddIssueDialogView addIssueDialogView = new AddIssueDialogView();
		addIssueDialogView.showScreen();
		AddIssueDialogController addIssueDialogController = new AddIssueDialogController(addIssueDialogView, view);
		addIssueDialogController.initController();
	}

	/**
	 * Action Listener for the Add User Button
	 */
	private void addUser()
	{
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
			AddUserDialogView addUserDialogView = new AddUserDialogView(requests, allUsersString);
			addUserDialogView.setPreferredSize(new Dimension(300, 300));
			addUserDialogView.pack();
			addUserDialogView.setVisible(true);
			AddUserDialogController addUserDialogController = new AddUserDialogController(addUserDialogView);
			addUserDialogController.initController();
		}

	}

	/**
	 * Action Listener for the Refresh Button.
	 */
	private void refresh()
	{
		LogManager.getInstance().log("Refresh button is pressed by " + currentUserName);
		view.clearJTable();
		ArrayList<Issue> issueList = DatabaseManager.getInstance().getAllIssues();

		for (Issue issue : issueList)
		{
			view.addIssueToJTable(issue);
		}
	}

	private void exportToExcel()
	{
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

		try(FileOutputStream fileOut = new FileOutputStream(selectedFile.getPath()))
		{
			// Write the output to a file
			if(showSaveDialog == 0)
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

}
