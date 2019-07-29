package com.ekinoks.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.ekinoks.model.Comment;
import com.ekinoks.model.Issue;
import com.ekinoks.model.IssueState;
import com.ekinoks.model.User;

public class DatabaseManager
{

	private static DatabaseManager databaseManager = new DatabaseManager();

	private DatabaseManager()
	{

	}

	public static DatabaseManager getInstance()
	{
		return databaseManager;
	}

	/**
	 * Provides connection to the SQL server
	 * 
	 * @return Connection
	 */

	private Connection connect()
	{
		Connection conn = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(Statements.URL);
		}
		catch (SQLException | ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * Checks if the user with the given user name already exists
	 * 
	 * @param userName
	 * @return
	 */
	public boolean checkUserExists(String userName)
	{
		if (userName.trim().length() == 0)
			return true;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.CHECK_USER_EXISTS_STATEMENT))
		{
			pstmt.setString(1, userName);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				return true;
			}
			return false;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Logs in with the given user name and password
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean login(String userName, String password)
	{
		if (userName.trim().length() == 0 || password.trim().length() == 0)
			return false;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.LOGIN_CHECK_STATEMENT))
		{

			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				return true;
			}

			return false;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Get the rank of the user with the given user name
	 * 
	 * @param userName
	 * @return
	 */
	public int getUserRank(String userName)
	{
		int result = -1;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_RANK_STATEMENT))
		{
			pstmt.setString(1, userName);

			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getInt("rank");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Get the state of the issue with the given title
	 * 
	 * @param title
	 * @return
	 */
	public int getIssueState(String title)
	{
		int result = -1;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ISSUE_STATE_STATEMENT))
		{
			pstmt.setString(1, title);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getInt("state");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Updates the state of the issue
	 * 
	 * @param title
	 * @param newState
	 */
	public void updateIssueState(String title, IssueState newState)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.UPDATE_ISSUE_STATE_STATEMENT))
		{
			pstmt.setString(1, newState.toString());
			pstmt.setString(2, title);

			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Adds user to the database
	 * 
	 * @param userName
	 * @param password
	 * @param rank
	 * @return
	 */
	public boolean addUser(String userName, String password, String rank)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.USER_INSERT_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			if (rank.equals("Manager"))
			{
				pstmt.setInt(3, 1);
			}
			else if (rank.equals("Analyst"))
			{
				pstmt.setInt(3, 2);
			}
			else if (rank.equals("Tester"))
			{
				pstmt.setInt(3, 3);
			}
			else if (rank.equals("Developer"))
			{
				pstmt.setInt(3, 4);
			}

			pstmt.executeUpdate();
			return true;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Adds issue to the database
	 * 
	 * @param title
	 * @param type
	 * @param priority
	 * @param author
	 * @param description
	 * @param detectedVersion
	 * @return
	 */
	public boolean addIssue(String projectName, String title, String type, int priority, String author,
			String description, String detectedVersion, String targetVersion)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_ISSUE_STATEMENT))
		{
			pstmt.setString(1, projectName);
			pstmt.setString(2, title);
			pstmt.setString(3, type);
			pstmt.setInt(4, priority);
			pstmt.setString(5, author);
			pstmt.setString(6, description);
			pstmt.setString(7, getCurrentDate());
			pstmt.setString(8, detectedVersion);
			pstmt.setString(9, targetVersion);
			pstmt.executeUpdate();
			return true;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean editIssue(int id, String description, String title, String state)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.EDIT_ISSUE_STATEMENT))
		{
			pstmt.setString(1, description);
			pstmt.setString(2, title);
			pstmt.setString(3, state);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();

			return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}

	}

	public boolean addProject(String projectName)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_PROJECT_STATEMENT))
		{
			pstmt.setString(1, projectName);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	/**
	 * Add relation between a given user and an issue
	 * 
	 * @param userName
	 * @param issueTitle
	 * @return
	 */
	public boolean addRelation(String userName, String issueTitle)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_RELATION_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, issueTitle);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * returns the issue with the given title
	 * 
	 * @param title
	 * @return
	 */
	public Issue getIssue(String title)
	{
		String projectName = "";
		int issueID = -1;
		String issueTitle = "";
		String issueType = "";
		int issuePriority = -1;
		String issueAuthor = "";
		String issueDescription = "";
		String issueState = "";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ISSUE_STATEMENT))
		{

			pstmt.setString(1, title);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				projectName = executeQuery.getString("project_name");
				issueID = executeQuery.getInt("issue_id");
				issueTitle = executeQuery.getString("title");
				issueType = executeQuery.getString("type");
				issuePriority = executeQuery.getInt("priority");
				issueAuthor = executeQuery.getString("author");
				issueDescription = executeQuery.getString("description");
				issueState = executeQuery.getString("state");
			}

			Issue result = new Issue(projectName, issueID, issueTitle, issueType, issuePriority, issueAuthor,
					issueDescription, issueState);
			return result;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * returns the author of the given issue
	 * 
	 * @param issueTitle
	 * @return
	 */
	public String getAuthorByIssueTitle(String issueTitle)
	{
		String result = "";
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_AUTHOR_BY_ISSUE_TITLE_STATEMENT))
		{
			pstmt.setString(1, issueTitle);
			ResultSet executeQuery = pstmt.executeQuery();

			if (executeQuery.next())
			{
				result = executeQuery.getString("author");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * returns all issues
	 * 
	 * @return
	 */
	public ArrayList<Issue> getAllIssues()
	{
		String projectName = "";
		int issueID = -1;
		String issueTitle = "";
		String issueType = "";
		int issuePriority = -1;
		String issueAuthor = "";
		String issueDescription = "";
		String issueState = "";

		ArrayList<Issue> result = new ArrayList<>();

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_ISSUES_STATEMENT))
		{

			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				projectName = executeQuery.getString("project_name");
				issueID = executeQuery.getInt("issue_id");
				issueTitle = executeQuery.getString("title");
				issueType = executeQuery.getString("type");
				issuePriority = executeQuery.getInt("priority");
				issueAuthor = executeQuery.getString("author");
				issueDescription = executeQuery.getString("description");
				issueState = executeQuery.getString("state");
				Issue temp = new Issue(projectName, issueID, issueTitle, issueType, issuePriority, issueAuthor,
						issueDescription, issueState);
				result.add(temp);
			}

			return result;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * returns all users
	 * 
	 * @return
	 */
	public ArrayList<User> getAllUsers()
	{
		ArrayList<User> result = new ArrayList<>();

		int user_id = -1;
		String user_name = "";
		String password = "";
		int rank = -1;

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_USERS_STATEMENT))
		{
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				user_id = executeQuery.getInt("user_id");
				user_name = executeQuery.getString("user_name");
				password = executeQuery.getString("password");
				rank = executeQuery.getInt("rank");
				User temp = new User(user_id, user_name, password, rank);
				result.add(temp);
			}
			return result;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}

	}

	/**
	 * returns all users that are assigned to a given issue
	 * 
	 * @param issueTitle
	 * @return
	 */
	public ArrayList<String> getAssigneesByIssueTitle(String issueTitle)
	{
		ArrayList<String> result = new ArrayList<>();

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_USERS_BY_ISSUE_STATEMENT))
		{
			int id = this.getIssueID(issueTitle);
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				int user_id = executeQuery.getInt("user_id");
				result.add(this.getUserNameById(user_id));
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * returns the user name of the user whose id is given
	 * 
	 * @param id
	 * @return
	 */
	private String getUserNameById(int id)
	{
		String result = "";
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_USER_NAME_BY_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("user_name");
			}

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	public String getIssueNameById(int id)
	{
		String result = "";
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ISSUE_TITLE_FROM_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("title");
			}

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * returns the id of the issue whose title is given
	 * 
	 * @param issueTitle
	 * @return
	 */
	public int getIssueID(String issueTitle)
	{
		int result = -1;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ISSUE_ID_FROM_TITLE_STATEMENT))
		{
			pstmt.setString(1, issueTitle);

			ResultSet executeQuery = pstmt.executeQuery();

			result = executeQuery.getInt("issue_id");

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			result = -1;
		}
		return result;
	}

	/**
	 * returns the id of the user whose user name is given.
	 * 
	 * @param userName
	 * @return
	 */
	public int getUserIdByName(String userName)
	{
		int result = -1;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_USER_ID_BY_NAME_STATEMENT))
		{
			pstmt.setString(1, userName);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getInt("user_id");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());

		}
		return result;
	}

	/**
	 * 
	 * @param issueTitle
	 * @return the users that can be assigned to the given issue.
	 */
	public Vector<String> getPossibleAssignees(String issueTitle)
	{
		ArrayList<User> allUsers = getAllUsers();
		Vector<String> result = new Vector<>();
		ResultSet executeQuery;
		int issueId = getIssueID(issueTitle);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_IF_USER_AND_ISSUE_RELATED_STATEMENT))
		{
			for (User user : allUsers)
			{
				int userId = getUserIdByName(user.getUserName());
				pstmt.setInt(1, userId);
				pstmt.setInt(2, issueId);
				executeQuery = pstmt.executeQuery();
				if (!executeQuery.next())
				{
					result.add(user.getUserName());
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Adds new sign up request to the database.
	 * 
	 * @param userName
	 * @param password
	 */
	public void addSignUpRequest(String userName, String password)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.SIGN_UP_REQUEST_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @return all current sign up requests.
	 */
	public ArrayList<String> getAllSignUpRequestUserNames()
	{
		ArrayList<String> result = new ArrayList<>();
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_REQUESTS_STATEMENT))
		{
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				result.add(executeQuery.getString("user_name"));
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param name
	 * @return the password of the sign up request with the given name.
	 */
	public String getSignUpRequestPassword(String name)
	{
		String result = "";
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_REQUEST_PASSWORD_BY_USER_NAME_STATEMENT))
		{
			pstmt.setString(1, name);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("password");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * removes the sign up request.
	 * 
	 * @param userName
	 */
	public void removeRequest(String userName)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.REMOVE_REQUEST_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * removes an existing user.
	 * 
	 * @param userName
	 */
	public void removeUser(String userName)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.REMOVE_USER_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public boolean removeRelation(String userName, String issueTitle)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.REMOVE_RELATION_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, issueTitle);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param title
	 * @return the progress user of a given issue.
	 */
	public String getProgressUser(String title)
	{
		String result = null;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_PROGRESS_USER_STATEMENT))
		{
			pstmt.setString(1, title);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("progress_user");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * sets the progress user of a given issue.
	 * 
	 * @param title
	 * @param userName
	 */
	public void setProgressUser(String title, String userName)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.SET_PROGRESS_USER_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, title);
			pstmt.executeUpdate();
			setStartDate(title);
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Sets the start date of the issue. Called by setProgressUser method.
	 * 
	 * @param title
	 */
	private void setStartDate(String title)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.SET_START_DATE_STATEMENT))
		{
			pstmt.setString(1, getCurrentDate());
			pstmt.setString(2, title);
			pstmt.executeUpdate();

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Sets the finish date of the issue.
	 * 
	 * @param title
	 */
	public void setFinishDate(String title)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.SET_FINISH_DATE_STATEMENT))
		{
			pstmt.setString(1, getCurrentDate());
			pstmt.setString(2, title);
			pstmt.executeUpdate();

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @return current date taken from the computer.
	 */
	public String getCurrentDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String s = dateFormat.format(date);
		return s;
	}

	/**
	 * Sets the time spent on an issue when its being set to done / verified done
	 * 
	 * @param title
	 * @param hours
	 */
	public void setTimeSpent(String title, int hours)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.SET_TIME_SPENT_STATEMENT))
		{
			pstmt.setInt(1, hours);
			pstmt.setString(2, title);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @return all the project names
	 */
	public ArrayList<String> getAllProjectNames()
	{
		ArrayList<String> result = new ArrayList<>();
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_PROJECTS_STATEMENT))
		{
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				result.add(executeQuery.getString("project_name"));
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param projectName
	 * @return all issues of a given project
	 */
	public ArrayList<Issue> getAllIssuesOfProject(String projectName)
	{
		int issueID = -1;
		String issueTitle = "";
		String issueType = "";
		int issuePriority = -1;
		String issueAuthor = "";
		String issueDescription = "";
		String issueState = "";

		ArrayList<Issue> result = new ArrayList<>();

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_ISSUES_OF_PROJECT_STATEMENT))
		{

			pstmt.setString(1, projectName);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				issueID = executeQuery.getInt("issue_id");
				issueTitle = executeQuery.getString("title");
				issueType = executeQuery.getString("type");
				issuePriority = executeQuery.getInt("priority");
				issueAuthor = executeQuery.getString("author");
				issueDescription = executeQuery.getString("description");
				issueState = executeQuery.getString("state");
				Issue temp = new Issue(projectName, issueID, issueTitle, issueType, issuePriority, issueAuthor,
						issueDescription, issueState);
				result.add(temp);
			}

			return result;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param id
	 * @return the date that given issue is created
	 */
	public String getIssueCreateDate(int id)
	{
		String result = null;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_CREATE_DATE_BY_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("create_date");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param id
	 * @return the date that given issue is set to in progress
	 */
	public String getIssueStartDate(int id)
	{
		String result = null;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_START_DATE_BY_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("start_date");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param id
	 * @return the date that given issue is set to verified done
	 */
	public String getIssueFinishDate(int id)
	{
		String result = null;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_FINISH_DATE_BY_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getString("finish_date");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @param id
	 * @return the time that is entered by the user who sets the issue to done /
	 *         verified done
	 */
	public int getIssueTimeSpent(int id)
	{
		int result = 0;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_TIME_SPENT_BY_ID_STATEMENT))
		{
			pstmt.setInt(1, id);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getInt("time_spent");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Adds a new comment to an issue
	 * 
	 * @param issueID
	 * @param userName
	 * @param comment
	 */
	public void addComment(int issueID, String userName, String comment)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_COMMENT_STATEMENT))
		{
			pstmt.setInt(1, issueID);
			pstmt.setString(2, userName);
			pstmt.setString(3, comment);
			pstmt.setString(4, getCurrentDate());
			pstmt.executeUpdate();

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param issueID
	 * @return all the comments of a given issue
	 */
	public ArrayList<Comment> getAllCommentsOfIssue(int issueID)
	{
		ArrayList<Comment> result = new ArrayList<>();
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_COMMENTS_OF_ISSUE_STATEMENT))
		{
			pstmt.setInt(1, issueID);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				int commentID = executeQuery.getInt("comment_id");
				String userName = executeQuery.getString("user_name");
				String comment = executeQuery.getString("comment");
				String date = executeQuery.getString("date");
				Comment temp = new Comment(commentID, issueID, userName, comment, date);
				result.add(temp);
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}

		return result;
	}

	/**
	 * Adds an invitation to a user for an issue
	 * 
	 * @param userName
	 * @param issueTitle
	 */
	public void addInvitation(String userName, String issueTitle)
	{
		int userId = getUserIdByName(userName);
		int issueId = getIssueID(issueTitle);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_INVITATION_STATEMENT))
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, issueId);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public void removeInvitation(String userName, String issueTitle)
	{
		int userId = getUserIdByName(userName);
		int issueId = getIssueID(issueTitle);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.REMOVE_INVITATION_STATEMENT))
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, issueId);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public boolean invitationForUserExists(String userName)
	{
		int userId = getUserIdByName(userName);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_INVITATIONS_OF_USER_STATEMENT))
		{
			pstmt.setInt(1, userId);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}

	}

	public ArrayList<String> getAllInvitationsForUser(String userName)
	{
		ArrayList<String> result = new ArrayList<String>();

		int userId = getUserIdByName(userName);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_INVITATIONS_OF_USER_STATEMENT))
		{
			pstmt.setInt(1, userId);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next())
			{
				int issueId = executeQuery.getInt("issue_id");
				result.add(getIssueNameById(issueId));
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}

		return result;
	}

	public int getProjectId(String projectName)
	{
		int result = -1;
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.GET_PROJECT_ID_STATEMENT))
		{
			pstmt.setString(1, projectName);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				result = executeQuery.getInt("project_id");
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
		return result;
	}

	public void addProjectAvailabilityToUser(String userName, String projectName)
	{
		int userId = getUserIdByName(userName);
		int projectId = getProjectId(projectName);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_PROJECT_AVAILABILITY_TO_USER))
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, projectId);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public boolean removeProjectAvailabilityFromUser(String userName, String projectName)
	{
		int userId = getUserIdByName(userName);
		int projectId = getProjectId(projectName);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.REMOVE_PROJECT_AVAILABILITY_FROM_USER))
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, projectId);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
	}

	public boolean isProjectAvailableToUser(String userName, String projectName)
	{

		int userId = getUserIdByName(userName);
		int projectId = getProjectId(projectName);

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn
						.prepareStatement(Statements.GET_IF_PROJECT_IS_AVAILABLE_TO_USER_STATEMENT))
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, projectId);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}

	}

	public void saveImage(String image, int issueId, int userId)
	{
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_IMAGE_STATEMENT))
		{
			pstmt.setString(1, image);
			pstmt.setInt(2, issueId);
			pstmt.setInt(3, userId);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
