package controller;

import database.UserDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import resources.LanguageManager;
import utility.Alerter;
import java.io.IOException;
import java.time.ZoneId;

/**
 * Controller for Login.fxml.
 * Login handles user login.
 * */
public class Login {
    public Label timeZoneDescLabel;
    public Label timeZoneLabel;
    public Label usernameLabel;
    public TextField usernameTextField;
    public Label passwordLabel;
    public PasswordField passwordField;
    public Button loginButton;
    Stage scheduleStage = new Stage();
    Scene scheduleScene;
    private boolean access = false;
    /**
     * Initializes the Login view. This includes translating text on the Login view.
     * */
    @FXML
    private void initialize() {
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        timeZoneLabel.setText(ZoneId.systemDefault().toString());
        usernameLabel.setText(LanguageManager.getLocalString("Username"));
        passwordLabel.setText(LanguageManager.getLocalString("Password"));
        loginButton.setText(LanguageManager.getLocalString("Login"));
    }

    /**
     * Closes the Login stage.
     * */
    public void closeLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Used to establish a connection and log the user into the database.
     * */
    public void onLoginButton(ActionEvent actionEvent) throws IOException {
        if (usernameTextField.getText().equals("") || passwordField.getText().equals("")) {
            Alerter.alert("Missing_Credentials", "Message");
        }
        else {
            if(UserDB.userLogin(usernameTextField.getText(), passwordField.getText())) {
                access = true;
            }
            if (access) {
                FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/Schedule.fxml"));
                Parent root = loadSchedule.load();
                scheduleScene = new Scene(root);
                scheduleStage.setScene(scheduleScene);
                scheduleStage.setTitle(LanguageManager.getLocalString("Schedule"));
                scheduleStage.setResizable(false);
                scheduleStage.show();
                closeLogin(actionEvent);
            }
        }
    }
}
