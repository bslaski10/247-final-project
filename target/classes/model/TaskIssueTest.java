import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

public class TaskIssueTest {
    private TaskIssue taskIssue;
    private User admin;

    @Before
    public void setUp() {
        admin = new User("AdminUser"); // You may need to create a User constructor
        taskIssue = new TaskIssue("Task Name", "Task Fix Description", admin);
    }

    @Test
    public void testTaskIssueConstructor() {
        assertEquals("Task Name", taskIssue.getTaskName());
    }

    @Test
    public void testTaskIssueConstructorWithUUID() {
        UUID taskUUID = UUID.randomUUID();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Comment> taskComments = new ArrayList<>();

        TaskIssue taskIssueWithUUID = new TaskIssue(taskUUID, users, "Task Name", taskComments, "Task Fix Description", admin);
        
        assertEquals("Task Name", taskIssueWithUUID.getTaskName());
    }
}
