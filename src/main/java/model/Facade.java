package model;

import java.util.ArrayList;
import java.util.UUID;

public class Facade {
    private ProjectList project;
    private Users users;
    private User currentUser;
    private static Facade facade;

    private Facade() {
        project = ProjectList.getInstance();
        users = Users.getInstance();
    }

    public static Facade getInstance() {
        if (facade == null) {
            facade = new Facade();
        }

        return facade;
    }

    public boolean createAccount(String userName, String password, String firstName, String lastName) {
        return users.addUser(userName, password, firstName, lastName);
    }

    public boolean login(String userName, String password) {
        if (!users.haveUser(userName, password)) return false;

        User user = users.getUser(userName, password);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }

        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Project getProject(String name) {
        return project.getProject(name);
    }

    public Project getProject(UUID projectID) {
        return project.getProject(projectID);
    }

    public ArrayList<Project> getProjectList() {
        return project.getAllProjects();
    }

    public void logout() {
        users.saveUsers();
    }
}
