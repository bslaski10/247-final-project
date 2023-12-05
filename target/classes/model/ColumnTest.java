import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;

public class ColumnTest {

    private Column column;
    private Task task1;
    private Task task2;

    @Before
    public void setUp() {
        task1 = new TaskIssue("Task1", "Title1", new User("Admin1"));
        task2 = new TaskNewFeature("Task2", "Title2");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        column = new Column("Column1", tasks);
    }

    @Test
    public void testAddTask() {
        Task task3 = new TaskIssue("Task3", "Title3", new User("Admin2"));
        column.addtask(task3);
        assertEquals(3, column.getTasks().size());
        assertTrue(column.getTasks().contains(task3));
    }

    @Test
    public void testRemoveTask() {
        column.removeTask(task1);
        assertEquals(1, column.getTasks().size());
        assertFalse(column.getTasks().contains(task1));
    }

    @Test
    public void testCreateTaskWithAdmin() {
        column.createTask("Task3", "Title3", new User("Admin3"));
        assertEquals(3, column.getTasks().size());
        assertTrue(column.getTasks().get(2) instanceof TaskIssue);
    }

    @Test
    public void testCreateTaskWithoutAdmin() {
        column.createTask(UUID.randomUUID(), new ArrayList<>(), "Task3", new ArrayList<>(), "Title3", null);
        assertEquals(3, column.getTasks().size());
        assertTrue(column.getTasks().get(2) instanceof TaskNewFeature);
    }

    @Test
    public void testGetTaskByName() {
        Task foundTask = column.getTask("Task1");
        assertEquals(task1, foundTask);
    }

    @Test
    public void testGetTaskById() {
        UUID taskId = task2.getTaskId();
        Task foundTask = column.getTask(taskId);
        assertEquals(task2, foundTask);
    }

    @Test
    public void testChangeColumnName() {
        column.changeName("NewColumn");
        assertEquals("NewColumn", column.getName());
    }

    @Test
    public void testToString() {
        String expectedString = "COLUMN1\n [Task1, Task2]";
        assertEquals(expectedString, column.toString());
    }
}
