import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.UUID;

public class DataLoaderTest {
    private UserDatabase userDatabase = UserDatabase.getInstance();
    private ArrayList<User> userList = userDatabase.getUsers();

    private TaskList tasks = TaskList.getInstance();
    private ArrayList<Task> taskList = tasks.getTasks();

    private ProjectList projects = ProjectList.getInstance();
    private ArrayList<Project> projectList = projects.getAllProjects();

    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new User("Brooks", "Slaski", "bslaski", "abcd"));
        userList.add(new User("Rice", "Davis", "rdavis", "efgh"));

        taskList.clear();
        User user = userList.get(0);
        taskList.add(new TaskIssue("NewBug", "", user));
        taskList.add(new TaskNewFeature("Newfeature", ""));

        projectList.clear();
        projectList.add(new Project(null, "project name1", null, userList));
        projectList.add(new Project("project name2"));
    }

    @AfterEach
    public void tearDown() {
        UserDatabase.getInstance().getUsers().clear();
        TaskList.getInstance().getTasks().clear();

        ProjectList.getInstance().getAllProjects().clear();
    }

    @Test
	public void testGetUsersSize() {
		userList = DataLoader.getUsers();
		assertEquals(2, userList.size());
	}

    @Test
	public void testGetUsersSizeZero() {
		UserDatabase.getInstance().getUsers().clear();
		assertEquals(0, userList.size());
	}

    @Test
	public void testGetUserFirstUserName() {
		userList = DataLoader.getUsers();
		assertEquals("bslaski", userList.get(0).getUserName());
	}

    @Test
    public void testGetTask() {
        UUID taskId = taskList.get(0).getTaskId();
        Task task = DataLoader.getTask(taskId);
        assertNotNull(task);
        assertEquals(taskId, task.getTaskId());
    }

    @Test
    public void testGetProject() {
        UUID projectId = projectList.get(0).getProjectID();
        ArrayList<Project> project = DataLoader.getProjects();
        assertNotNull(project);
        // assertEquals(projectId, project());  //going to need to creat a getProject
    }

    @Test
    public void testGetUserById() {
        UUID userId = userList.get(0).getId();
        User user = DataLoader.getUserbyId(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
    }
}
