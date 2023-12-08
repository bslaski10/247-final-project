package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Database {
	protected static final String USER_FILE_NAME = "src/main/java/data/users.json";
	protected static final String USER_ID = "id";
	protected static final String USER_USER_NAME = "userName";
	protected static final String USER_PASSWORD = "password";
	protected static final String USER_FIRST_NAME = "firstName";
	protected static final String USER_LAST_NAME = "lastName";
	protected static final String PROJECT_FILE_NAME = "src/main/java/data/projects.json";
    protected static final String PROJECT_NAME="projectName";
    protected static final String PROJECT_ID= "projectID";
    protected static final String PROJECT_USERS="userids";
    protected static final String PROJECT_COLUMNS="columns";
    protected static final String PROJECT_COLUMN_NAME = "columnName";
    protected static final String PROJECT_COLUMN_TASKS = "tasks";
    protected static final String PROJECT_COMMENT_STRING = "comment";

	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();

		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);

			for (int i = 0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject) peopleJSON.get(i);
				UUID id = UUID.fromString((String) personJSON.get(USER_ID));
				String userName = (String) personJSON.get(USER_USER_NAME);
				String password = (String) personJSON.get(USER_PASSWORD);
				String firstName = (String) personJSON.get(USER_FIRST_NAME);
				String lastName = (String) personJSON.get(USER_LAST_NAME);

				users.add(new User(id, userName, password, firstName, lastName));
			}

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Project> getProjects() {
		ArrayList<Project> projectList = new ArrayList<>();
	
		Project project1 = new Project(UUID.randomUUID(), "Project One", new ArrayList<>(), new ArrayList<>());
		project1.addColumn(new Column("To Do", new ArrayList<>()));
		project1.addColumn(new Column("In Progress", new ArrayList<>()));
		project1.addColumn(new Column("Done", new ArrayList<>()));
		projectList.add(project1);
		
	
		Project project2 = new Project(UUID.randomUUID(), "Project Two", new ArrayList<>(), new ArrayList<>());
		project2.addColumn(new Column("Backlog", new ArrayList<>()));
		project2.addColumn(new Column("Development", new ArrayList<>()));
		project2.addColumn(new Column("Testing", new ArrayList<>()));
		project2.addColumn(new Column("Done", new ArrayList<>()));
		projectList.add(project2);
	
		Project project3 = new Project(UUID.randomUUID(), "Project Three", new ArrayList<>(), new ArrayList<>());
		project3.addColumn(new Column("Ideas", new ArrayList<>()));
		project3.addColumn(new Column("Planning", new ArrayList<>()));
		project3.addColumn(new Column("Execution", new ArrayList<>()));
		project3.addColumn(new Column("Review", new ArrayList<>()));
		project3.addColumn(new Column("Completed", new ArrayList<>()));
		projectList.add(project3);
	
		return projectList;
	}

	public static void saveUsers() {

		Users users = Users.getInstance();
		ArrayList<User> userList = users.getUsers();
		JSONArray jsonUsers = new JSONArray();

		for (int i = 0; i < userList.size(); i++) {
			jsonUsers.add(getUserJSON(userList.get(i)));
		}

		try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

			file.write(jsonUsers.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(USER_USER_NAME, user.getUserName());
		userDetails.put(USER_PASSWORD, user.getPassword());
		userDetails.put(USER_FIRST_NAME, user.getFirstName());
		userDetails.put(USER_LAST_NAME, user.getLastName());

		return userDetails;

	}
}
