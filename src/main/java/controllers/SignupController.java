package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import facade.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Facade;
import model.User;

public class SignupController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_first_name;
    @FXML
    private TextField txt_last_name;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String firstName = txt_first_name.getText();
        String lastName = txt_last_name.getText();



        if (username.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")) {
            lbl_error.setText("Sorry, You cannot leave blank fields");
            return;
        }

        Facade facade = Facade.getInstance();

        if (!facade.createAccount(username, password, firstName, lastName)) {
            lbl_error.setText("Sorry, this user couldn't be created.");
            return;
        }

        facade.login(username,password);
        User user = facade.getCurrentUser();
        App.setRoot("project");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
