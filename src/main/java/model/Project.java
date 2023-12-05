import java.util.ArrayList;
import java.util.UUID;

public class Project {
    private String projectName;
    private UUID projectID;
    private ArrayList<Column> columns;
    private ArrayList<User> users;

    public Project(UUID projectID, String projectName, ArrayList<Column> columns, ArrayList<User> users) {
        this.projectName = projectName;
        this.projectID = projectID;
        this.users = users;
        this.columns = columns;
    }

    public Project(String projectName)
    {
        this.projectID = UUID.randomUUID();
        this.projectName = projectName;
        this.users = null;
        this.columns=null;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public void deleteColumn(Column column) {
        columns.remove(column);
    }

    public boolean completedTask(Task task) {
        return true;

    }

    public void moveTask(Task task, String columnName)
    {
        for(int i=0; i<columns.size();i++) {
            if(columns.get(i).getTask(task.getTaskName()) != null) {
                columns.get(i).removeTask(task);
            }
        }
        getColumn(columnName).addtask(task);
    }


    public Column getColumn(String columnName)
    {
        for(int i=0;i<columns.size();i++)
        {
            if(columns.get(i).name.equalsIgnoreCase(columnName))
            {
                return columns.get(i);
            }
        }
        return null;
    }

    public Task getTask(String taskName)
    {
        for(int i=0;i<columns.size();i++)
        {
            for(int j=0;j<columns.get(i).getTasks().size();j++)
            {
                if(columns.get(i).getTasks().get(j).taskName.equalsIgnoreCase(taskName))
                {
                    return columns.get(i).getTasks().get(j);
                }
            }
        }
        return null;
    }


    public void addUsers(User user) 
    {
       users.add(user);
    }

    public String getProjectName() {
        return this.projectName;
    }

    public UUID getProjectID() {
        return this.projectID;
    }


    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Column> getColumns() {
        return this.columns;
    }


    public Task getTask()
    {
        return null;
    }

    public String toString()
    {
        return "\n\nPROJECT\nNAME: "+"\nUSERS: "+this.users+"\nCOLUMNS:\n"+this.columns;
    }


}