import java.util.ArrayList;
import java.util.UUID;

/**
 * A list of users
 */
public class UserDatabase {
    private ArrayList<User> users; // List to store user objects
    private static UserDatabase userDatabase; // Singleton instance of UserDatabase

    /**
     * Private constructor initializes the list of users from the DataLoader
     */
    private UserDatabase() {
        users = DataLoader.getUsers();
    }

    /**
     * Get instance of the user database (Singleton pattern)
     */
    public static UserDatabase getInstance() {
        if (userDatabase == null) {
            userDatabase = new UserDatabase();
        }
        return userDatabase;
    }

    /**
     * Get a user based on username and password
     */
    public User getUser(String userName, String userPassword) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equalsIgnoreCase(userName) && users.get(i).getPassword().equalsIgnoreCase(userPassword)) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * Check if a user with the given email ID already exists
     */
    public boolean addUser(String emailID) {
        users = DataLoader.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userName.equalsIgnoreCase(emailID)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a new user to the database
     */
    public boolean add(String firstName, String lastName, String userEmail, String userPassword) {
        User user = new User(firstName, lastName, userEmail, userPassword);
        if (addUser(userEmail)) {
            users.add(user);
        }
        return true;
    }

    /**
     * Get the list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Get a user by their unique ID
     */
    public User getUserbyId(UUID id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id))
                return users.get(i);
        }
        return null;
    }

    /**
     * Get a user by their first and last name
     */
    public User getUserbyName(String firstName, String lastName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstName().equalsIgnoreCase(firstName) && users.get(i).getLastName().equalsIgnoreCase(lastName))
                return users.get(i);
        }
        return null;
    }
}