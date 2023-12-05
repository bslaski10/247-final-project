import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;

public class TaskTest {

    private Task task;
    private User user1;
    private User user2;
    private Comment comment1;
    private Comment comment2;

    @Before
    public void setUp() {

        user1 = new User("User1");
        user2 = new User("User2");
        comment1 = new Comment("Comment 1", user1);
        comment2 = new Comment("Comment 2", user2);

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        UUID taskId = UUID.randomUUID();
        task = new Task(taskId, users, "TaskName", comments) {
        };
    }

    @Test
    public void testSetTaskName() {
        task.setTaskName("NewTaskName");
        assertEquals("NewTaskName", task.getTaskName());
    }

    @Test
    public void testAddComment() {
        Comment newComment = new Comment("New Comment", new User("User3"));
        task.addComment(newComment);
        assertEquals(3, task.getTaskComments().size());
        assertTrue(task.getTaskComments().contains(newComment));
    }

    @Test
    public void testAddUser() {
        User newUser = new User("User3");
        task.addUser(newUser);
        assertEquals(3, task.getUsers().size());
        assertTrue(task.getUsers().contains(newUser));
    }

    @Test
    public void testChangeUser() {
        User newUser = new User("User3");
        task.changeUser(newUser, user1);
        assertFalse(task.getUsers().contains(user1));
        assertTrue(task.getUsers().contains(newUser));
    }

    @Test
    public void testToString() {
        String expectedString = "\nTask Name: TaskName\nUsers:[User1, User2]\nComments: [Comment 1, Comment 2]";
        assertEquals(expectedString, task.toString());
    }
}
