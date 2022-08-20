package controller;

import database.UserDB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import model.User;
import resources.LanguageManager;
import utility.Alerter;
import utility.LoginMonitor;

import java.io.IOException;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Controller for the Login view.
 * */
public class Login {
    /**
     * Access to the time zone Label description
     * */
    public Label timeZoneDescLabel;
    /**
     * Access to the time zone Label that is updated to display the current time zone.
     * */
    public Label timeZoneLabel;
    /**
     * Access to the username Label.
     * */
    public Label usernameLabel;
    /**
     * Access to the username Text Field.
     * */
    public TextField usernameTextField;
    /**
     * Access to the password Label.
     * */
    public Label passwordLabel;
    /**
     * Access to the Password Field.
     * */
    public PasswordField passwordField;
    /**
     * Access to the login Button.
     * */
    public Button loginButton;
    /**
     * Used to load a Stage for the Schedule view.
     * */
    Stage scheduleStage = new Stage();
    /**
     * Used to load a Scene for the Schedule view.
     * */
    Scene scheduleScene;
    /**
     * Used to check if the user credentials have been verified.
     * */
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
