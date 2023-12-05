import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.*;

public class TaskNewFeatureTest {

    @Test
    public void testTaskNewFeatureConstructorWithNameAndTaskItem() {
        String taskName = "New Feature Task";
        String taskItem = "Implement new feature XYZ";
        TaskNewFeature newFeatureTask = new TaskNewFeature(taskName, taskItem);
        assertNotNull(newFeatureTask);
        assertEquals(taskName, newFeatureTask.getTaskName());
        assertEquals(taskItem, newFeatureTask.getTaskId());
    }

    @Test
    public void testTaskNewFeatureConstructorWithProperties() {
        UUID taskId = UUID.randomUUID();
        String taskName = "New Feature Task";
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        String taskItem = "Implement new feature XYZ";

        TaskNewFeature newFeatureTask = new TaskNewFeature(taskId, users, taskName, comments, taskItem);
        assertNotNull(newFeatureTask);
        assertEquals(taskId, newFeatureTask.getTaskId());
        assertEquals(taskName, newFeatureTask.getTaskName());
        assertEquals(users, newFeatureTask.getUsers());
        assertEquals(comments, newFeatureTask.getTaskComments());
        assertEquals(taskItem, newFeatureTask.getTaskId());
    }

    @Test
    public void testTaskNewFeatureGetSetTaskItem() {
        String taskItem = "Implement new feature XYZ";
        TaskNewFeature newFeatureTask = new TaskNewFeature("New Feature Task", taskItem);
        assertNotNull(newFeatureTask);
        assertEquals(taskItem, newFeatureTask.getTaskId());

        String updatedTaskItem = "Update new feature XYZ implementation";
        assertEquals(updatedTaskItem, newFeatureTask.getTaskId());
    }
}
