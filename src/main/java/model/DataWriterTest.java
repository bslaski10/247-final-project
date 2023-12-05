import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

public class DataWriterTest {
    private TaskIssue taskIssue;
    private TaskNewFeature taskNewFeature;
    private User user;
    private Project project;

    @Before
    public void setUp() {
        user = new User("TestUser");
        taskIssue = new TaskIssue("Task Name", "Task Fix Description", user);
        project = new Project("TestProject");
    }

    @Test
    public void testGetTaskJSON() {
        JSONObject taskIssueJSON = DataWriter.getTaskJSON(taskIssue);
        JSONObject taskNewFeatureJSON = DataWriter.getTaskJSON(taskNewFeature);

        assertNotNull(taskIssueJSON);
        assertNotNull(taskNewFeatureJSON);

    }

    @Test
    public void testGetUserJSON() {
        JSONObject userJSON = DataWriter.getUserJSON(user);
        assertNotNull(userJSON);

    }

    @Test
    public void testGetProjectJSON() {
        JSONObject projectJSON = DataWriter.getProjectJSON(project);
        assertNotNull(projectJSON);

    }

    @Test
    public void testSaveProjects() throws IOException, ParseException {
        
        DataWriter.saveProjects();
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("json/projects1.json"));
        JSONObject savedProjects = (JSONObject) obj;
        
    }
}
