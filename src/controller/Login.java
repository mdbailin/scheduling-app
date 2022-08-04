package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;

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
    public void onLoginButton(ActionEvent actionEvent) {
        Main.startConnection(usernameTextField.getText(), passwordField.getText());
    }

    public void onLanguageComboBox(ActionEvent actionEvent) {
    }
}
