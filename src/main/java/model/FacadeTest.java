import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class FacadeTest {
    private Facade facade = new Facade();

    @BeforeEach
    public void setUp() {
        UserDatabase.getInstance().getUsers().clear();
    }

    @AfterEach
    public void tearDown() {
        UserDatabase.getInstance().getUsers().clear();
    }

    @Test
    public void testLogin() {
        UserDatabase.getInstance().add("Brooks", "Slaski", "bslaski", "clever10");

        User user = facade.login("bslaski", "clever10");

        assertNotNull(user);
        assertEquals("Brooks", user.getFirstName());
        assertEquals("Slaski", user.getLastName());
        assertEquals("bslaski", user.getUserName());
    }

    @Test
    public void testSignUp() {
        boolean isCreated = facade.signUp("Rice", "Davis", "rdavis", "dumb11");
        assertTrue(isCreated);
    }

    @Test
    public void testAddTask() {
        User admin = new User("Ben", "Pettit", "bpettit", "smart12");

        boolean isAdded = facade.addTask("New Task", "Title", admin);
        assertTrue(isAdded);
    }

    @Test
    public void testGetTask() {
        TaskList.getInstance().add("Task test", "Task Title test", new User("Sam", "Porter", "sporter", "password20"));

        Task task = facade.getTask("Task 2");

        assertNotNull(task);
        assertEquals("Task test", task.getTaskName());
        assertEquals("Task Title test", task.getTaskName()); // Need to add a getTaskTitle
        assertEquals("Sam", task.getUsers());
    }

    @Test
    public void testGetTasks() {
        ArrayList<Task> tasks = facade.getTasks();
        assertNotNull(tasks);
    }

    @Test
    public void testGetUserbyName() {
        UserDatabase.getInstance().add("Brooks", "Slaski", "bslaski", "wordpass");

        User user = facade.getUserbyName("Brooks", "Slaski");

        assertNotNull(user);
        assertEquals("Brooks", user.getFirstName());
        assertEquals("Slaski", user.getLastName());
        assertEquals("bslaski", user.getUserName());
    }

    @Test
    public void testCreateValidAccount() {
        boolean isCreated = facade.signUp("Brooks", "Slaski", "bslaski", "wordpass");

        assertTrue(isCreated);

        User loggedInUser = facade.login("bslaski", "wordpass");

        assertNotNull(loggedInUser);
        assertEquals("Brooks", loggedInUser.getFirstName());
        assertEquals("Slaski", loggedInUser.getLastName());
        assertEquals("bslaski", loggedInUser.getUserName());
    }

    @Test
    public void testCreateDuplicateUserName() {
        facade.signUp("Brooks", "Slaski", "bslaski", "wordpass");
        boolean isCreated = facade.signUp("Bob", "Slaski", "bslaski", "wordpass10");
        assertFalse(isCreated); 
}

    @Test
	public void testCreateEmptyUserName() {
		boolean isCreated = facade.signUp("", "", "", "");
		assertFalse(isCreated);
	}

    @Test
    public void testAddCommentToTask() {
        User user = new User("Brooks", "Slaski", "bslaski", "wordpass");

        Task task = new TaskIssue("Task name", "Task title", user);
        TaskList.getInstance().add(task);

        Comment comment = new Comment("comment", user);
        task.addComment(comment);

         assertTrue(task.getTaskComments().contains(comment));
}
}
