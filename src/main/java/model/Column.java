import java.util.ArrayList;
import java.util.UUID;

public class Column {

  public String name;
  private ArrayList<Task> tasks;

  public Column(String name, ArrayList<Task> tasks) 
  {
    this.name = name;
    this.tasks = tasks;
  }

  public Column(String name) {
   this.name = name;
   this.tasks = new ArrayList<Task>();
  }

  public void addtask(Task task)
  {
   if(this.tasks==null)
   {
      this.tasks = new ArrayList<Task>();
      tasks.add(task);
   }
   else
   {
      tasks.add(task);
   }
    
  }

  public void removeTask(Task task)
  {
    tasks.remove(task);
  }

  public void createTask(String taskName, String title, User admin) {
     if (admin != null) {
        TaskIssue task = new TaskIssue(taskName, title, admin);
        tasks.add(task);
     } else {
        TaskNewFeature task = new TaskNewFeature(taskName, title);
        tasks.add(task);
     }
  }

  public void createTask(UUID taskUUID, ArrayList<User> users, String taskName, ArrayList<Comment> taskComments, String title, User admin) {
     if (admin != null) {
        TaskIssue task = new TaskIssue(taskUUID, users, taskName, taskComments, title, admin);
        tasks.add(task);
     } else {
        TaskNewFeature task = new TaskNewFeature(taskUUID, users, taskName, taskComments, title);
        tasks.add(task);
     }
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Task> getTasks() {
   return this.tasks;
  }

  public void changeName(String name) {
   this.name = name;
  }

  public Task getTask(String name) {
   for (int i=0; i<tasks.size(); i++) {
      if (name == tasks.get(i).getTaskName()){
         return tasks.get(i);
      }
   }
   return null;
  }

  public Task getTask(UUID taskid) {
   for (int i=0; i<tasks.size(); i++) {
      if (taskid == tasks.get(i).getTaskId()) {
         return tasks.get(i);
      }
   }
   return null; 
   }

   public String toString()
   {
      return ""+this.name.toUpperCase()+"\n "+this.tasks;
   }
}