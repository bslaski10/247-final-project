import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    @Test
    public void testCommentCreationWithUserObject() {
        User user = new User(UUID.randomUUID(), "John", "Doe", "johndoe", "password123");
        Comment comment = new Comment("Sample Comment", user);

        assertEquals("Sample Comment", comment.getComment());
        assertEquals(user.getId(), comment.getUserID());
    }

    @Test
    public void testCommentCreationWithUserID() {
        UUID userId = UUID.randomUUID();
        Comment comment = new Comment(userId, "Sample Comment");

        assertEquals("Sample Comment", comment.getComment());
        assertEquals(userId, comment.getUserID());
    }

    @Test
    public void testEditComment() {
        Comment comment = new Comment(UUID.randomUUID(), "Initial Comment");
        assertEquals("Initial Comment", comment.getComment());

        comment.editComment("Edited Comment");
        assertEquals("Edited Comment", comment.getComment());
    }

    @Test
    public void testToString() {
        User user = new User(UUID.randomUUID(), "John", "Doe", "johndoe", "password123");
        Comment comment = new Comment("Sample Comment", user);
        assertEquals("(johndoe): Sample Comment", comment.toString());

        // Test when the user is not found in the database
        Comment commentWithoutUser = new Comment(UUID.randomUUID(), "Sample Comment");
        assertNull(commentWithoutUser.toString());
    }
}
