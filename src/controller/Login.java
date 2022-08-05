package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import resources.LanguageManager;
import utility.Alerter;

import java.io.IOException;

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

    Stage scheduleStage = new Stage();
    Scene scheduleScene;

    /**
     * Initializes the Login view. This includes translating text on the Login view.
     * */
    @FXML
    private void initialize() {
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        usernameLabel.setText(LanguageManager.getLocalString("Username"));
        passwordLabel.setText(LanguageManager.getLocalString("Password"));
        loginButton.setText(LanguageManager.getLocalString("Login"));
    }

    /**
     * Used to establish a connection and log the user into the database.
     * */
    public void onLoginButton(ActionEvent actionEvent) throws IOException {
        Main.startConnection(usernameTextField.getText(), passwordField.getText());

        FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/Schedule.fxml"));
        Parent root = loadSchedule.load();
        scheduleScene = new Scene(root);
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }
}
