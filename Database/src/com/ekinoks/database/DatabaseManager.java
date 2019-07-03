package com.ekinoks.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.ekinoks.model.Issue;
import com.ekinoks.model.User;

public class DatabaseManager
{

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
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.LOGIN_CHECK_STATEMENT))
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
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.GET_RANK_STATEMENT))
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
	 * Adds user to the database
	 * 
	 * @param userName
	 * @param password
	 * @param rank
	 * @return
	 */
	public boolean addUser(String userName, String password, String rank)
	{
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.USER_INSERT_STATEMENT))
		{
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			if (rank.equals("Admin"))
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
	 * @return
	 */
	public boolean addIssue(String title, String type, int priority, String author, String description)
	{
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.ISSUE_INSERT_STATEMENT))
		{
			pstmt.setString(1, title);
			pstmt.setString(2, type);
			pstmt.setInt(3, priority);
			pstmt.setString(4, author);
			pstmt.setString(5, description);
			pstmt.executeUpdate();
			System.out.println("data added");
			return true;

		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
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
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.ADD_RELATION_STATEMENT))
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
		int issueID = -1;
		String issueTitle = "";
		String issueType = "";
		int issuePriority = -1;
		String issueAuthor = "";
		String issueDescription = "";
		String issueState = "";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ISSUE_STATEMENT))
		{

			pstmt.setString(1, title);
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery.next())
			{
				issueID = executeQuery.getInt("issue_id");
				issueTitle = executeQuery.getString("title");
				issueType = executeQuery.getString("type");
				issuePriority = executeQuery.getInt("priority");
				issueAuthor = executeQuery.getString("author");
				issueDescription = executeQuery.getString("description");
				issueState = executeQuery.getString("state");
			}

			Issue result = new Issue(issueID, issueTitle, issueType, issuePriority, issueAuthor, issueDescription,
					issueState);
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
				issueID = executeQuery.getInt("issue_id");
				issueTitle = executeQuery.getString("title");
				issueType = executeQuery.getString("type");
				issuePriority = executeQuery.getInt("priority");
				issueAuthor = executeQuery.getString("author");
				issueDescription = executeQuery.getString("description");
				issueState = executeQuery.getString("state");
				Issue temp = new Issue(issueID, issueTitle, issueType, issuePriority, issueAuthor, issueDescription,
						issueState);
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

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(Statements.GET_ALL_USERS_STATEMENT))
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
	public ArrayList<String> getUsersByIssueTitle(String issueTitle)
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

	/**
	 * returns the id of the issue whose title is given
	 * 
	 * @param issueTitle
	 * @return
	 */
	private int getIssueID(String issueTitle)
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
	private int getUserIdByName(String userName)
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
}
