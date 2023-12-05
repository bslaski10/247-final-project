import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants
{   

    public static JSONObject getTaskJSON(Task task)
    {
        JSONObject taskDetails = new JSONObject();
        taskDetails.put(TASK_ID,task.getTaskId().toString());
		taskDetails.put(TASK_NAME,task.getTaskName());
        
        if(task.getUsers()!=null)
        {
            JSONArray userids = new JSONArray();
            for(int i=0;i<task.getUsers().size();i++)
            {
                userids.add(i, task.getUsers().get(i).getId().toString());
            }
            taskDetails.put(TASK_USER_ID, userids);
        }
        else
        {
            taskDetails.put(TASK_USER_ID, "none");
        }
        
        JSONArray taskComments = new JSONArray();
        if(task.getTaskComments()!=null)
        {
            for(int i =0;i<task.getTaskComments().size();i++)
            {
                JSONObject comment = new JSONObject();
                comment.put(TASK_COMMENT_USER_ID,task.getTaskComments().get(i).getUserID().toString());
                comment.put(TASK_COMMENT_STRING,task.getTaskComments().get(i).getComment());
                taskComments.add(comment);
                taskDetails.put(TASK_COMMENT, taskComments);
            }

        }
        else
        {
            JSONObject comment = new JSONObject();
            comment.put(TASK_COMMENT_USER_ID,"");
            comment.put(TASK_COMMENT_STRING,"");
            taskComments.add(comment);
            taskDetails.put(TASK_COMMENT, taskComments);
        }

        if (task instanceof TaskIssue) {
            taskDetails.put(TASK_TITLE, ((TaskIssue)task));
            taskDetails.put(TASK_ADMIN, ((TaskIssue)task).getTaskId());
        } else {
            taskDetails.put(TASK_TITLE, ((TaskNewFeature)task).getTaskId());
            taskDetails.put(TASK_ADMIN, null);
        }
        

        return taskDetails;
    }

    public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID,user.getId().toString());
		userDetails.put(USER_FIRST_NAME, user.getFirstName());
		userDetails.put(USER_LAST_NAME, user.getLastName());
		userDetails.put(USER_NAME, user.getUserName());
		userDetails.put(USER_PASSWORD, user.getPassword());
        
        return userDetails;
	}

    public static void saveProjects()
    {
        ArrayList<Project> projects = ProjectList.getInstance().getAllProjects();
		JSONArray jsonProjects = new JSONArray();
		
        for(int i=0; i < projects.size(); i++) {
			jsonProjects.add(getProjectJSON(projects.get(i)));
		}
		
        try (FileWriter file = new FileWriter("json/projects1.json")) {
 
            file.write(jsonProjects.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static JSONObject getProjectJSON(Project project) 
    {
		JSONObject projectDetails = new JSONObject();
		projectDetails.put(PROJECT_ID, project.getProjectID().toString());
        
        JSONArray userids = new JSONArray();
        for (int i=0; i<project.getUsers().size(); i++) {
            userids.add(i, project.getUsers().get(i).getId().toString());
        }
        projectDetails.put(PROJECT_USERS, userids);
        projectDetails.put(PROJECT_NAME, project.getProjectName());

        JSONArray columns = new JSONArray();
        for (int i=0; i<project.getColumns().size(); i++) {
            JSONObject column = new JSONObject();
            column.put(PROJECT_COLUMN_NAME, project.getColumns().get(i).getName());
            JSONArray tasks = new JSONArray();
            if (project.getColumns().get(i).getTasks() != null) {
                    for (int j=0; j<project.getColumns().get(i).getTasks().size(); j++) {
                    tasks.add(j, project.getColumns().get(i).getTasks().get(j).getTaskId().toString());;
                }
            }
            
            column.put(PROJECT_COLUMN_NAME, project.getColumns().get(i).getName());
            column.put(PROJECT_COLUMN_TASKS, tasks);
            columns.add(column);
        }
		projectDetails.put(PROJECT_COLUMNS, columns);



        return projectDetails;
        }
}
