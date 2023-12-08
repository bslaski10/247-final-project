package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Facade;
import model.Project;
import model.User;
import facade.App;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException; 

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {
    @FXML private Label lbl_title;
    @FXML private VBox projectList;
    private Facade facade;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = Facade.getInstance();
        user = facade.getCurrentUser();

        lbl_title.setText("Welcome\n" + user.getFirstName() + " " + user.getLastName());

        populateProjectList();
    }

    private void populateProjectList() {
        System.out.println("In populate project list");
        for (Project project : facade.getProjectList()) {
            System.out.println("In project loop");
            Label projectLabel = new Label(project.getProjectName());

            projectLabel.setOnMouseClicked(event -> {
                try {
                    handleProjectSelection(project);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            projectList.getChildren().add(projectLabel);
        }
    }

    @FXML
    private void onSignOut(MouseEvent event) throws IOException {
        App.setRoot("login");
    }
    
    private void handleProjectSelection(Project selectedProject) throws IOException {
        App.setRoot("column");
    }
}
