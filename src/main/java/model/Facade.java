 import java.util.ArrayList;

public class Facade {
    private User user;
    private Task task;
    private Project project;

    public User login(String userName, String password) {
        user = UserDatabase.getInstance().getUser(userName,password);
        return user;
    }

    public Boolean signUp(String firstName, String LastName, String userName, String password) 
    {
        return UserDatabase.getInstance().add(firstName, LastName, userName, password);

    }

    public Boolean addTask(String taskName, String title, User admin)
    {
        return TaskList.getInstance().add(taskName, title, admin);
    }

    public Task getTask(String taskName)
    {
        task = TaskList.getInstance().getTask(taskName);
        return task;
    }

    public ArrayList<Task> getTasks()
    {
        return TaskList.getInstance().getTasks();
    }

    public User getUser()
    {
        return user;
    }

    public User getUserbyName(String firstName, String lastName){
        
        return user= UserDatabase.getInstance().getUserbyName(firstName,lastName);
    }

    public Project getProject(String name) {
        project = ProjectList.getInstance().getProject(name);
        return project;

    }

    public ArrayList<Project> getAllProjects() {
        return ProjectList.getInstance().getAllProjects();
    }

    public void addCommentProject(Project project) {

    }
}
