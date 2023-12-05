import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        user = new User(uuid, "John", "Doe", "johndoe", "password123");
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources after each test if needed
    }

    @Test
    public void testGetId() {
        UUID expectedId = user.getId();
        assertNotNull(expectedId);
        assertEquals(expectedId, user.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testGetUserName() {
        assertEquals("johndoe", user.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testToString() {
        assertEquals("John Doe", user.toString());
    }
}