import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a list of projects in the application.
 */
public class ProjectList {
    public ArrayList<Project> projects; // List to store projects
    public static ProjectList projectBoard; // Singleton instance of ProjectList

    /**
     * Constructor for ProjectList class.
     */
    public ProjectList() {
        projects = DataLoader.getProjects(); // Load projects data from the data source
    }

    /**
     * Get the singleton instance of ProjectList class.
     */
    public static ProjectList getInstance() {
        if (projectBoard == null) {
            projectBoard = new ProjectList(); // Create a new instance if it doesn't exist
        }
        return projectBoard;
    }

    /**
     * Create a new project with the given project name.
     */
    public void createProject(String projectName) {
        Project project = new Project(projectName); // Create a new project instance
        projects.add(project); // Add the project to the projects list
    }

    /**
     * Create a new project with specified properties.
     */
    public void createProject(UUID projectID, String projectName, ArrayList<Column> columns, ArrayList<User> users) {
        Project project = new Project(projectID, projectName, columns, users); // Create a project with specified properties
        projects.add(project); // Add the project to the projects list
    }

    /**
     * Add an existing project to the projects list.
     */
    public void addProject(Project project) {
        projects.add(project); // Add the specified project to the projects list
    }

    /**
     * Get a project by its unique identifier.
     */
    public Project getProject(UUID uuid) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectID() == uuid) {
                return projects.get(i); // Return the project with the specified unique identifier
            }
        }
        return null; // Return null if project with the specified unique identifier is not found
    }

    /**
     * Get a project by its name.
     */
    public Project getProject(String name) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equalsIgnoreCase(name)) {
                return projects.get(i); // Return the project with the specified name
            }
        }
        return null; // Return null if project with the specified name is not found
    }

    /**
     * Get a list of all projects in the application.

     */
    public ArrayList<Project> getAllProjects() {
        return projects; // Return the list of all projects in the application
    }
}
