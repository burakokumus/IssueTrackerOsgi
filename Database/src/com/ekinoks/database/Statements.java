package com.ekinoks.database;

public class Statements
{
	public static final String ADD_ISSUE_STATEMENT = "INSERT INTO issues(project_name, title, type, priority, author, description, create_date) VALUES(?, ?, ?, ?, ?, ?, ?)";
	public static final String ADD_PROJECT_STATEMENT = "INSERT INTO projects(project_name) VALUES (?)";
	public static final String ADD_RELATION_STATEMENT = "INSERT INTO relation(user_id, issue_id) SELECT usr.user_id, iss.issue_id FROM users usr JOIN issues iss WHERE user_name = ? and title = ?";
	public static final String CHECK_USER_EXISTS_STATEMENT = "SELECT * FROM users WHERE user_name = ?";
	public static final String EDIT_ISSUE_STATEMENT = "UPDATE issues SET description = ?, title = ?, state = ? WHERE issue_id = ?";
	public static final String GET_ALL_ISSUES_STATEMENT = "SELECT * from issues";
	public static final String GET_ALL_ISSUES_OF_PROJECT_STATEMENT = "SELECT * from issues WHERE project_name = ?";
	public static final String GET_ALL_PROJECTS_STATEMENT = "SELECT project_name FROM projects";
	public static final String GET_ALL_REQUESTS_STATEMENT = "SELECT * FROM requests";
	public static final String GET_ALL_USERS_STATEMENT = "SELECT * from users";
	public static final String GET_AUTHOR_BY_ISSUE_TITLE_STATEMENT = "SELECT author FROM issues WHERE title = ?";
	public static final String GET_CREATE_DATE_BY_ID_STATEMENT = "SELECT create_date FROM issues WHERE issue_id = ?";
	public static final String GET_FINISH_DATE_BY_ID_STATEMENT = "SELECT finish_date FROM issues WHERE issue_id = ?";
	public static final String GET_IF_USER_AND_ISSUE_RELATED_STATEMENT = "SELECT * FROM relation WHERE user_id = ? AND issue_id = ?";
	public static final String GET_ISSUE_ID_FROM_TITLE_STATEMENT = "SELECT issue_id FROM issues WHERE title = ?";
	public static final String GET_ISSUE_STATEMENT = "SELECT * FROM issues WHERE title = ?";
	public static final String GET_ISSUE_STATE_STATEMENT = "SELECT state FROM issues WHERE title = ?";
	public static final String GET_PROGRESS_USER_STATEMENT = "SELECT progress_user FROM issues WHERE title = ?";
	public static final String GET_RANK_STATEMENT = "SELECT rank FROM users WHERE user_name = ?";
	public static final String GET_REQUEST_PASSWORD_BY_USER_NAME_STATEMENT = "SELECT password FROM requests WHERE user_name = ?";
	public static final String GET_START_DATE_BY_ID_STATEMENT = "SELECT start_date FROM issues WHERE issue_id = ?";
	public static final String GET_TIME_SPENT_BY_ID_STATEMENT = "SELECT time_spent FROM issues WHERE issue_id = ?";
	public static final String GET_USERS_BY_ISSUE_STATEMENT = "SELECT * FROM relation WHERE issue_id = ?";
	public static final String GET_USER_ID_BY_NAME_STATEMENT = "SELECT user_id FROM users WHERE user_name = ?";
	public static final String GET_USER_NAME_BY_ID_STATEMENT = "SELECT user_name FROM users WHERE user_id = ?";
	public static final String LOGIN_CHECK_STATEMENT = "SELECT * FROM users WHERE user_name = ? AND password = ?";
	public static final String REMOVE_REQUEST_STATEMENT = "DELETE FROM requests WHERE user_name = ?";
	public static final String REMOVE_USER_STATEMENT = "DELETE FROM users WHERE user_name = ?";
	public static final String SET_PROGRESS_USER_STATEMENT = "UPDATE issues SET progress_user = ? WHERE title = ?";
	public static final String SET_FINISH_DATE_STATEMENT = "UPDATE issues SET finish_date = ? WHERE title = ?";
	public static final String SET_START_DATE_STATEMENT = "UPDATE issues SET start_date = ? WHERE title = ?";
	public static final String SET_TIME_SPENT_STATEMENT = "UPDATE issues SET time_spent = ? WHERE title = ?";
	public static final String SIGN_UP_REQUEST_STATEMENT = "INSERT INTO requests(user_name, password) VALUES(?,?)";
	public static final String UPDATE_ISSUE_STATE_STATEMENT = "UPDATE issues SET state = ? WHERE title = ?";
	public static final String URL = "jdbc:sqlite:C:\\Users\\burak\\eclipse-workspace\\Database\\IssueTrackerDatabase.db";
	public static final String USER_INSERT_STATEMENT = "INSERT INTO users(user_name, password, rank) VALUES(?, ?, ?)";

	private Statements()
	{

	}
}
