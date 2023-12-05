import java.util.UUID;

public class User {
    private UUID uuid; // Unique identifier for the user
    private String firstName; // First name of the user
    private String lastName; // Last name of the user
    public String userName; // Username of the user
    private String userPassword; // Password of the user
    
    // Constructor for creating a User object with a specified UUID
    public User(UUID uuid, String firstName, String lastName, String userName, String userPassword) {
       this.uuid = uuid;
       this.firstName = firstName;
       this.lastName = lastName;
       this.userName = userName;
       this.userPassword = userPassword;
    }

    // Constructor for creating a User object with a randomly generated UUID
    public User(String firstName, String lastName, String userName, String userPassword) {
       this.uuid = UUID.randomUUID();
       this.firstName = firstName;
       this.lastName = lastName;
       this.userName = userName;
       this.userPassword = userPassword;
    }

    public User(String string) {
    }

    // Returns a string representation of the user's full name
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    // Getter method to retrieve the UUID of the user
    public UUID getId() {
        return this.uuid;
    }

    // Getter method to retrieve the first name of the user
    public String getFirstName() {
        return this.firstName;
    }

    // Getter method to retrieve the last name of the user
    public String getLastName() {
        return this.lastName;
    }

    // Getter method to retrieve the username of the user
    public String getUserName() {
        return this.userName;
    }

    // Getter method to retrieve the password of the user
    public String getPassword() {
        return this.userPassword;
    }

    // Unused method (seems redundant), consider removing it
    public String getInstance() {
        return this.userName;
    }
}