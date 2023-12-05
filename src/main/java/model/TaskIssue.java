import java.util.ArrayList;
import java.util.UUID;

public class TaskIssue extends Task {
    
    public String TaskFix;
    public User admin;

    TaskIssue(String name, String taskFix, User admin){
        super(name);
        this.admin = admin;
    }

    TaskIssue(UUID taskUUID,ArrayList<User> users,String taskName, ArrayList<Comment> taskComments, String taskFix, User admin) {
        super(taskUUID, users, taskName, taskComments);

        this.admin = admin;
    }
}