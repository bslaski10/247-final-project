import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class UI {
    private Facade facade; // Facade object to interact with the underlying system
    private Writer fileWriter; // Writer object for writing to a text file

    /**
     * Constructor initializes the file writer for logging purposes
     */
    public UI() {
        try {
            fileWriter = new BufferedWriter(new FileWriter("textfile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a line to both the terminal and the text file
     * @param line The line to be written
     */
    private void writeLine(String line) {
        System.out.println(line); // Print to the terminal
        try {
            fileWriter.write(line + "\n"); // Write to the text file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to run the user interface
     */
    public void run() {
        facade = new Facade();

        writeLine("Code Mission Impossible Company");

        facade.signUp("Atticus", "Madden", "Madden007", "impossible");
        facade.login("Madden007", "impossible");
        User currentUser = facade.getUser();
        writeLine("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());

        ArrayList<Project> allProjects = facade.getAllProjects();
        writeLine("\n"+"All Projects:");
        for (Project project : allProjects) {
            writeLine(project.getProjectName());
        }

        Project currentProject = facade.getProject("Electric Missiles");
        writeLine("\n"+"Current Project: " + currentProject.getProjectName());
        
        // Display columns in the current project
        writeLine("\n"+"Column: BackLog");
        writeLine("Column: Doing");

        // Change user assigned to a task and display the task details
        Task getTask = currentProject.getTask("Initialize super algorithm to detonate at warp speed");
        User users = facade.getUserbyName("Jeff", "Goldblum");
        TaskList.getInstance().getTask("Initialize super algorithm to detonate at warp speed").changeUser(users, currentUser);
        writeLine(getTask.toString());

        // Display task details for another task in the current project
        Task task1 = currentProject.getTask("Curve the metal to make a cylindrical shape");
        writeLine(task1.toString());

        // Display columns in the current project
        writeLine("\n"+"Column: Done");
        writeLine("Column: Abandoned");

        // Display task details for another task in the current project
        Task task2 = currentProject.getTask("Make impossible burger possible");
        writeLine(task2.toString());
      
        try {
            fileWriter.close(); // Close the file writer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to start the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.run(); // Run the user interface
    }
}