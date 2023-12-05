import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class UserDatabaseTest {
    private UserDatabase userDatabase;

    @BeforeEach
    public void setUp() {
        userDatabase = UserDatabase.getInstance();
        // Clear the users in the database for each test
        userDatabase.getUsers().clear();
    }

    @Test
    public void testAddUser() {
        assertTrue(userDatabase.add("John", "Doe", "johndoe@example.com", "password123"));
        assertEquals(1, userDatabase.getUsers().size());
        assertEquals("John", userDatabase.getUsers().get(0).getFirstName());
    }

    @Test
    public void testAddDuplicateUser() {
        userDatabase.add("John", "Doe", "johndoe@example.com", "password123");
        assertFalse(userDatabase.add("Jane", "Smith", "johndoe@example.com", "password456"));
        assertEquals(1, userDatabase.getUsers().size());
    }

    @Test
    public void testGetUserById() {
        UUID uuid = UUID.randomUUID();
        userDatabase.add("John", "Doe", "johndoe@example.com", "password123");
        User user = userDatabase.getUsers().get(0);
        User retrievedUser = userDatabase.getUserbyId(user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user.getId(), retrievedUser.getId());
    }

    @Test
    public void testGetUserByName() {
        userDatabase.add("John", "Doe", "johndoe@example.com", "password123");
        User user = userDatabase.getUsers().get(0);
        User retrievedUser = userDatabase.getUserbyName("John", "Doe");
        assertNotNull(retrievedUser);
        assertEquals(user.getId(), retrievedUser.getId());
    }

    @Test
    public void testGetNonExistentUserById() {
        UUID nonExistentId = UUID.randomUUID();
        User retrievedUser = userDatabase.getUserbyId(nonExistentId);
        assertNull(retrievedUser);
    }

    @Test
    public void testGetNonExistentUserByName() {
        User retrievedUser = userDatabase.getUserbyName("Non", "Existent");
        assertNull(retrievedUser);
    }
}
