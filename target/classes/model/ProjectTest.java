import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.*;

public class ProjectTest {

    private Project project;

    @Before
    public void setUp() {
        UUID projectId = UUID.randomUUID();
        String projectName = "Test Project";
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        project = new Project(projectId, projectName, columns, users);
    }

    @Test
    public void testProjectConstructorWithProperties() {
        assertNotNull(project);
        assertNotNull(project.getProjectID());
        assertNotNull(project.getProjectName());
        assertNotNull(project.getColumns());
        assertNotNull(project.getUsers());
        assertEquals(0, project.getColumns().size());
        assertEquals(0, project.getUsers().size());
    }

    @Test
    public void testProjectConstructorWithName() {
        String projectName = "New Project";
        Project newProject = new Project(projectName);
        assertNotNull(newProject);
        assertNotNull(newProject.getProjectID());
        assertEquals(projectName, newProject.getProjectName());
        assertNull(newProject.getColumns());
        assertNull(newProject.getUsers());
    }

    @Test
    public void testAddColumn() {
        Column column = new Column("To Do");
        project.addColumn(column);
        ArrayList<Column> columns = project.getColumns();
        assertNotNull(columns);
        assertEquals(1, columns.size());
        assertEquals(column, columns.get(0));
    }

    @Test
    public void testMoveTask() {
        Column columnToDo = new Column("To Do");
        Column columnInProgress = new Column("In Progress");
        project.addColumn(columnToDo);
        project.addColumn(columnInProgress);

        Task task = new TaskNewFeature(UUID.randomUUID(), new ArrayList<>(), "Task 1", new ArrayList<>(), "Implement new feature");
        columnToDo.addtask(task);

        project.moveTask(task, "In Progress");

        assertEquals(0, columnToDo.getTasks().size());
        assertEquals(1, columnInProgress.getTasks().size());
        assertEquals(task, columnInProgress.getTasks().get(0));
    }

    @Test
    public void testGetColumn() {
        Column column = new Column("To Do");
        project.addColumn(column);
        Column retrievedColumn = project.getColumn("To Do");
        assertNotNull(retrievedColumn);
        assertEquals(column, retrievedColumn);
    }

    @Test
    public void testGetTask() {
        Column column = new Column("To Do");
        Task task = new TaskNewFeature(UUID.randomUUID(), new ArrayList<>(), "Task 1", new ArrayList<>(), "Implement new feature");
        column.addtask(task);
        project.addColumn(column);
        Task retrievedTask = project.getTask("Task 1");
        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void testAddUsers() {
        User user = new User("Micah Richards");
        project.addUsers(user);
        ArrayList<User> users = project.getUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }
    @Test
    public void testAddColumnAndGetColumns() {
        Column column1 = new Column("To Do");
        Column column2 = new Column("In Progress");
        project.addColumn(column1);
        project.addColumn(column2);

        ArrayList<Column> columns = project.getColumns();
        assertNotNull(columns);
        assertEquals(2, columns.size());
        assertTrue(columns.contains(column1));
        assertTrue(columns.contains(column2));
    }

    @Test
    public void testDeleteColumn() {
        Column column = new Column("To Do");
        project.addColumn(column);
        project.deleteColumn(column);
        ArrayList<Column> columns = project.getColumns();
        assertNotNull(columns);
        assertEquals(0, columns.size());
    }

    @Test
    public void testMoveTaskBetweenColumns() {
        Column columnToDo = new Column("To Do");
        Column columnInProgress = new Column("In Progress");
        project.addColumn(columnToDo);
        project.addColumn(columnInProgress);

        Task task = new TaskNewFeature(UUID.randomUUID(), new ArrayList<>(), "Task 1", new ArrayList<>(), "Implement new feature");
        columnToDo.addtask(task);

        project.moveTask(task, "In Progress");

        assertEquals(0, columnToDo.getTasks().size());
        assertEquals(1, columnInProgress.getTasks().size());
        assertTrue(columnInProgress.getTasks().contains(task));
    }

    @Test
    public void testGetColumnByName() {
        Column column1 = new Column("To Do");
        Column column2 = new Column("In Progress");
        project.addColumn(column1);
        project.addColumn(column2);

        Column retrievedColumn = project.getColumn("In Progress");
        assertNotNull(retrievedColumn);
        assertEquals(column2, retrievedColumn);
    }

    @Test
    public void testGetTaskByName() {
        Column column = new Column("To Do");
        Task task = new TaskNewFeature(UUID.randomUUID(), new ArrayList<>(), "Task 1", new ArrayList<>(), "Implement new feature");
        column.addtask(task);
        project.addColumn(column);

        Task retrievedTask = project.getTask("Task 1");
        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void testAddAndGetUsers() {
        User user1 = new User("Micah Richards");
        User user2 = new User("Jane Smith");
        project.addUsers(user1);
        project.addUsers(user2);

        ArrayList<User> users = project.getUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    public void testProjectToString() {
        Column column1 = new Column("To Do");
        Column column2 = new Column("In Progress");
        project.addColumn(column1);
        project.addColumn(column2);

        String expectedToString = "\n\nPROJECT\nNAME: Test Project\nUSERS: []\nCOLUMNS:\n[Column{name='To Do', tasks=[]}, Column{name='In Progress', tasks=[]}]";
        assertEquals(expectedToString, project.toString());
    }
}
