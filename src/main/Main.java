package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        JDBC.openConnection();
        // TODO close connection on application exit.
        JDBC.closeConnection();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/test.fxml"));
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }
}
