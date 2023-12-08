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

public class ColumnController {

    @FXML
    private Label lbl_title;

    @FXML
    private VBox projectList;

    @FXML
    private void toProject(MouseEvent event) throws IOException {
        App.setRoot("project");
    }
    
    @FXML
    private void toBackLogTask(MouseEvent event) throws IOException {
         App.setRoot("task1");
    }
    
    @FXML
    private void toTask2(MouseEvent event) throws IOException {
        App.setRoot("task2");

    }

    @FXML
    private void toColumn(MouseEvent event) throws IOException{
        App.setRoot("column");

    }

}
