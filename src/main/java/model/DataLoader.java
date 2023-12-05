import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants{
	

	public static ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray peopleJSON = (JSONArray)parser.parse(reader);
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String lastName = (String)personJSON.get(USER_LAST_NAME);
				String userName = (String)personJSON.get(USER_NAME);
				String userPassword = (String)personJSON.get(USER_PASSWORD);
				String firstName = (String)personJSON.get(USER_FIRST_NAME);
				UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				users.add(new User(id, firstName, lastName, userName, userPassword));			
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public static User getUserbyId(UUID uuid)
	{
		try 
		{
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray peopleJSON = (JSONArray)parser.parse(reader);
			for(int i=0; i < peopleJSON.size(); i++) 
			{
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				if(id.equals(uuid))
				{
					String lastName = (String)personJSON.get(USER_LAST_NAME);
					String userName = (String)personJSON.get(USER_NAME);
					String userPassword = (String)personJSON.get(USER_PASSWORD);
					String firstName = (String)personJSON.get(USER_FIRST_NAME);
					User user = new User(id, firstName, lastName, userName, userPassword);
					return user;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

    public static ArrayList<Project> getProjects()
	{
		ArrayList<Project> projects = new ArrayList<Project>();
		try {
			FileReader reader = new FileReader(PROJECT_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray projectsJSON = (JSONArray)parser.parse(reader);
			for(int i=0; i < projectsJSON.size(); i++) 
			{
				JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
				UUID projectID = UUID.fromString((String)projectJSON.get(PROJECT_ID));
				String projectName = (String)projectJSON.get(PROJECT_NAME);
				ArrayList<User> Users = new ArrayList<User>();
				JSONArray user = (JSONArray)projectJSON.get(PROJECT_USERS);
				for(int j=0;j<user.size();j++)
				{
					Users.add(UserDatabase.getInstance().getUserbyId(UUID.fromString((String)user.get(j))));
				}

				ArrayList<Column> projectColumns = new ArrayList<Column>();
				JSONArray columns = (JSONArray)projectJSON.get(PROJECT_COLUMNS);
				for(int j =0;j<columns.size();j++)
				{
					JSONObject columnJSON = (JSONObject)columns.get(j);
					String columnName = (String)columnJSON.get(PROJECT_COLUMN_NAME);
					ArrayList<Task> columnTasks = new ArrayList<Task>();
					JSONArray columnTask = (JSONArray)columnJSON.get(PROJECT_COLUMN_TASKS);
					for(int k=0;k<columnTask.size();k++)
					{
						columnTasks.add(TaskList.getInstance().getTask(UUID.fromString((String)columnTask.get(k))));
					}
					Column column = new Column(columnName,columnTasks);
					projectColumns.add(column);
				}
				projects.add(new Project(projectID, projectName, projectColumns, Users));			
			}
			return projects;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Task getTask(UUID uuid)
	{
		try {
			FileReader reader = new FileReader(TASK_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray TasksJSON = (JSONArray)parser.parse(reader);
			
			for(int i=0; i < TasksJSON.size(); i++) 
			{
				JSONObject TaskJSON = (JSONObject)TasksJSON.get(i);

				UUID taskID = UUID.fromString((String)TaskJSON.get(TASK_ID));
				if(taskID.equals(uuid))
				{

					ArrayList<User> Users = new ArrayList<User>();
					JSONArray user = (JSONArray)TaskJSON.get(TASK_USER_ID);
					for(int j=0;j<user.size();j++)
					{
						UUID userID = UUID.fromString((String)user.get(j));

						Users.add(getUserbyId(userID));
					}

					String taskName = (String)TaskJSON.get(TASK_NAME);


					ArrayList<Comment> taskComments = new ArrayList<Comment>();
					JSONArray comments = (JSONArray)TaskJSON.get(TASK_COMMENT);
					for(int j =0;j<comments.size();j++)
					{
						JSONObject commentJSON = (JSONObject)comments.get(j);

						UUID commentUserID = UUID.fromString((String)commentJSON.get(TASK_COMMENT_USER_ID));
						String commentString = (String)commentJSON.get(TASK_COMMENT_STRING);

						taskComments.add(new Comment(commentUserID, commentString));
					}

					String title = (String)TaskJSON.get(TASK_TITLE);
					User admin = null;
					if (TaskJSON.get(TASK_ADMIN) != null) {
						admin = getUserbyId(UUID.fromString((String)TaskJSON.get(TASK_ADMIN)));
					}
					Task task;
					if(admin!=null)
					{
						task = new TaskIssue(taskID,Users,taskName,taskComments,title,admin);
					}
					else
					{
						task = new TaskNewFeature(taskID, Users, taskName, taskComments, title);
					}
					return task;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
 public static ArrayList<Task> getTasks()
	{
		ArrayList<Task> Tasks = new ArrayList<Task>();
		try {
			FileReader reader = new FileReader(TASK_FILE_NAME);
			JSONParser parser = new JSONParser();	
			JSONArray TasksJSON = (JSONArray)parser.parse(reader);
			for(int i=0; i < TasksJSON.size(); i++) {
				JSONObject TaskJSON = (JSONObject)TasksJSON.get(i);
				UUID taskID = UUID.fromString((String)TaskJSON.get(TASK_ID));
				ArrayList<User> Users = new ArrayList<User>();
				JSONArray user = (JSONArray)TaskJSON.get(TASK_USER_ID);
				for(int j=0;j<user.size();j++)
				{
					Users.add(UserDatabase.getInstance().getUserbyId(UUID.fromString((String)user.get(j))));
				}
				String taskName = (String)TaskJSON.get(TASK_NAME);
				ArrayList<Comment> taskComments = new ArrayList<Comment>();
				JSONArray comments = (JSONArray)TaskJSON.get(TASK_COMMENT);
				for(int j =0;j<comments.size();j++)
				{
					JSONObject commentJSON = (JSONObject)comments.get(j);
					UUID commentUserID = UUID.fromString((String)commentJSON.get(TASK_COMMENT_USER_ID));
					String commentString = (String)commentJSON.get(TASK_COMMENT_STRING);
					taskComments.add(new Comment(commentUserID, commentString));
				}
				
				String title = (String)TaskJSON.get(TASK_TITLE);
				User admin = null;
				if (TaskJSON.get(TASK_ADMIN) != null)
					admin = UserDatabase.getInstance().getUserbyId(UUID.fromString((String)TaskJSON.get(TASK_ADMIN)));
				Task task;
				if(admin!=null)
				{
					task = new TaskIssue(taskID,Users,taskName,taskComments, title, admin);
				}
				else
				{
					task = new TaskNewFeature(taskID, Users, taskName, taskComments, title);
				}
					Tasks.add(task);
			}
			return Tasks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
