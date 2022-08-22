package main;

import connection.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.LanguageManager;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Contains the entry point for the program.
 * */
public class Main extends Application {
    /**
     * The entry point for the program.
     * */
    public static void main(String[] args) throws SQLException {
        DBConnector.openConnection();
        launch(args);
        DBConnector.closeConnection();
    }

    /**
     * Sets and loads the Login view.
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root, 350, 300));
        primaryStage.setTitle(LanguageManager.getLocalString("Login"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
