package main;

import com.sun.javafx.runtime.VersionInfo;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Contains the entry point for the program.
 * */
public class Main extends Application {
    /**
     * The entry point for the program.
     * */
    public static void main(String[] args) {
        launch(args);
        JDBC.closeConnection();
    }
    /**
     * Sets and loads the Login view.
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root, 350, 300));
        primaryStage.show();
    }
    /**
     * Connects the user to the database with openConnection() from the JDBC class.
     * @param username the username used to access the database
     * @param password the password used to access the database
     * */
    public static void startConnection(String username, String password) {
        JDBC.openConnection(username, password);
    }
}
