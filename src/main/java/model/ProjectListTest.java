import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.*;

public class ProjectListTest {

    private ProjectList projectList;

    @Before
    public void setUp() {
        projectList = ProjectList.getInstance();
    }

    @Test
    public void testCreateProjectWithProjectName() {
        String projectName = "Test Project";
        projectList.createProject(projectName);
        ArrayList<Project> projects = projectList.getAllProjects();
        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(projectName, projects.get(0).getProjectName());
    }

    @Test
    public void testCreateProjectWithProperties() {
        UUID projectId = UUID.randomUUID();
        String projectName = "Test Project";
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        projectList.createProject(projectId, projectName, columns, users);
        ArrayList<Project> projects = projectList.getAllProjects();
        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(projectId, projects.get(0).getProjectID());
        assertEquals(projectName, projects.get(0).getProjectName());
        assertEquals(columns, projects.get(0).getColumns());
        assertEquals(users, projects.get(0).getUsers());
    }

    @Test
    public void testAddExistingProject() {
        Project project = new Project("Existing Project");
        projectList.addProject(project);
        ArrayList<Project> projects = projectList.getAllProjects();
        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(project, projects.get(0));
    }

    @Test
    public void testGetProjectById() {
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, "Test Project", new ArrayList<>(), new ArrayList<>());
        projectList.addProject(project);
        Project retrievedProject = projectList.getProject(projectId);
        assertNotNull(retrievedProject);
        assertEquals(project, retrievedProject);
    }

    @Test
    public void testGetProjectByName() {
        String projectName = "Test Project";
        Project project = new Project(projectName);
        projectList.addProject(project);
        Project retrievedProject = projectList.getProject(projectName);
        assertNotNull(retrievedProject);
        assertEquals(project, retrievedProject);
    }

    @Test
    public void testGetAllProjects() {

        projectList.addProject(new Project("Project 1"));
        projectList.addProject(new Project("Project 2"));
        projectList.addProject(new Project("Project 3"));

        ArrayList<Project> projects = projectList.getAllProjects();
        assertNotNull(projects);
        assertEquals(3, projects.size()); 
    }
}
