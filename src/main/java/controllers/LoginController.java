package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import facade.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

public class LoginController implements Initializable {

    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        String enteredUsername = txt_username.getText();
        String enteredPassword = txt_password.getText();

        Facade facade = Facade.getInstance();

        if (!facade.login(enteredUsername, enteredPassword)) {
            lbl_error.setText("Username/Password is Incorrect");
            return;
        }

        App.setRoot("project");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
