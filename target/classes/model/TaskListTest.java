import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = TaskList.getInstance();
        // Clear the tasks in the task list for each test
        taskList.getTasks().clear();
    }

    @Test
    public void testAddTask() {
        assertTrue(taskList.addTask("Task 1"));
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Task 1", taskList.getTasks().get(0).getTaskName());
    }

    @Test
    public void testAddDuplicateTask() {
        taskList.addTask("Task 1");
        assertFalse(taskList.addTask("Task 1"));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void testGetTaskByName() {
        taskList.addTask("Task 1");
        Task task = taskList.getTask("Task 1");
        assertNotNull(task);
        assertEquals("Task 1", task.getTaskName());
    }

    @Test
    public void testGetTaskById() {
        UUID taskId = UUID.randomUUID();
        ArrayList<User> users = new ArrayList<>();
        String taskName = "Task 1";
        ArrayList<Comment> taskComments = new ArrayList<>();
        String taskFix = "Fix the task";
        User admin = new User(UUID.randomUUID(), "Admin", "User", "adminuser", "password");
        TaskIssue task = new TaskIssue(taskId, users, taskName, taskComments, taskFix, admin);
        taskList.add(task);
        Task retrievedTask = taskList.getTask(taskId);
        assertNotNull(retrievedTask);
        assertEquals(taskId, retrievedTask.getTaskId());
    }

    @Test
    public void testAddTaskWithAdmin() {
        User admin = new User(UUID.randomUUID(), "Admin", "User", "adminuser", "password");
        assertTrue(taskList.add("Task 1", "Title", admin));
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof TaskIssue);
    }

    @Test
    public void testAddTaskWithoutAdmin() {
        assertTrue(taskList.add("Task 1", "Title", null));
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof TaskNewFeature);
    }
}
