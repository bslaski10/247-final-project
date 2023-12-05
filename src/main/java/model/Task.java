import java.util.ArrayList;
import java.util.UUID;

/**
 * An abstract class representing a task in the application.
 */
public abstract class Task {

    public UUID taskID; // Unique identifier for the task
    public ArrayList<User> users; // List of users assigned to the task
    public String taskName; // Name of the task
    public ArrayList<Comment> taskComments; // List of comments associated with the task

    /**
     * Constructor for creating a task with specific properties.
     */
    public Task(UUID taskUUID, ArrayList<User> users, String taskName, ArrayList<Comment> taskComments) {
        this.taskID = taskUUID; // Set the task ID
        this.users = users; // Set the list of users assigned to the task
        this.taskName = taskName; // Set the task name
        this.taskComments = taskComments; // Set the list of comments associated with the task
    }

    /**
     * Constructor for creating a task with a name.
     */
    public Task(String taskName){
        this.taskName = taskName; // Set the task name
    }

    /**
     * Set the name of the task.
     */
    public void setTaskName(String taskName){
        this.taskName = taskName; // Update the task name
    }

    /**
     * Add a comment to the task.
     */
    public void addComment(Comment comment) {
        if (taskComments == null) {
            taskComments = new ArrayList<Comment>(); // Initialize the comments list if null
            taskComments.add(comment);
        } else {
            taskComments.add(comment); // Add the comment to the existing list
        }
    }

    /**
     * Add a user to the task.
     */
    public void addUser(User user) {
        users.add(user); // Add the user to the task's assigned users
    }

    /**
     * Get a string representation of the task.
     * @return String representation of the task including its name, assigned users, and comments
     */
    public String toString() {
        return "\n" + "Task Name: " + this.taskName + "\nUsers:" + this.users + "\nComments: " + this.taskComments;
    }

    /**
     * Get the unique identifier of the task.
     * @return Unique identifier (UUID) of the task
     */
    public UUID getTaskId() {
        return this.taskID; // Return the task's unique identifier
    }

    /**
     * Get the name of the task.
     */
    public String getTaskName() {
        return this.taskName; // Return the task's name
    }

    /**
     * Get the list of users assigned to the task.
     */
    public ArrayList<User> getUsers() {
        return this.users; // Return the list of users assigned to the task
    }

    /**
     * Get the list of comments associated with the task.
     */
    public ArrayList<Comment> getTaskComments() {
        return this.taskComments; // Return the list of comments associated with the task
    }

    /**
     * Change the user assigned to the task. Replaces the specified user with a new user.
     */
    public void changeUser(User addUser, User removeUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(removeUser)) {
                users.remove(i); // Remove the specified user
            }
        }
        users.add(addUser); // Add the new user to the task
    }
}
