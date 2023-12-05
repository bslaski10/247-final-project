import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a specific type of task in the application called TaskItem.
 */
public class TaskNewFeature extends Task{
    
    public String TaskItem;

    /**
     * Constructor for creating a TaskNewFeature with a name and TaskNewFeature description.
     */
    public TaskNewFeature(String name, String TaskItem){
        super(name);
        this.TaskItem = TaskItem;
    }

    /**
     * Constructor for creating a TaskNewFeature with specific properties.
     */
    public TaskNewFeature(UUID taskUUID,ArrayList<User> users,String taskName, ArrayList<Comment> taskComments, String taskItem) {
        super(taskUUID, users, taskName, taskComments);
    }

 
}