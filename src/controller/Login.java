package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import utility.Alerter;

import java.io.IOException;

/**
 * Controller for the Login view.
 * */
public class Login {
    /**
     * Access to the time zone Label.
     * */
    public Label timeZoneLabel;
    /**
     * Access to the language Combo Box.
     * */
    public ComboBox languageComboBox;
    /**
     * Access to the username Text Field.
     * */
    public TextField usernameTextField;
    /**
     * Access to the Password Field.
     * */
    public PasswordField passwordField;
    /**
     * ObservableList to contain the language options for the language Combo Box.
     * */
    private final ObservableList<String> langaugeList = FXCollections.observableArrayList("English", "French");

    Stage scheduleStage = new Stage();
    Scene scheduleScene;

    /**
     * Initializes the Login view.
     * */
    @FXML
    private void initialize() {
        languageComboBox.setItems(langaugeList);
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

    public void onLanguageComboBox(ActionEvent actionEvent) {
        Alerter.alert("This is a test");
    }
}
