import java.util.ArrayList;
import java.util.UUID;

/**
 * A list of tasks in the application
 */
public class TaskList {
    private ArrayList<Task> tasks; // List to store task objects
    private static TaskList taskList; // Singleton instance of TaskList

    /**
     * Private constructor initializes the list of tasks from the DataLoader
     */
    private TaskList() {
        this.tasks = DataLoader.getTasks();
    }

    /**
     * Get instance of the task list (Singleton pattern)
     */
    public static TaskList getInstance() {
        if (taskList == null) {
            taskList = new TaskList();
        }
        return taskList;
    }

    /**
     * Get a task by its name
     */
    public Task getTask(String taskName) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskName)) {
                return tasks.get(i);
            }
        }
        return null;
    }

    /**
     * Get a task by its unique ID
     */
    public Task getTask(UUID taskID) {
        tasks = DataLoader.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskID))
                return tasks.get(i);
        }
        return null;
    }

    /**
     * Get the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Check if a task with the given name already exists
     */
    public boolean addTask(String taskName) {
        tasks = DataLoader.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.equalsIgnoreCase(taskName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a new task to the task list
     */
    public boolean add(String taskName, String title, User admin) {
        if (admin != null) {
            TaskIssue task = new TaskIssue(taskName, title, admin);
            tasks.add(task);
        } else {
            TaskNewFeature task = new TaskNewFeature(taskName, title);
            tasks.add(task);
        }
        return true;
    }

    /**
     * Add a task to the task list
     */
    public void add(Task task) {
        tasks.add(task);
    } 
}