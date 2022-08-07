package main;

import connection.DBConnector;
import database.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import resources.LanguageManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Contains the entry point for the program.
 * */
public class Main extends Application {
    /**
     * The entry point for the program.
     * */
    public static void main(String[] args) throws SQLException {
        launch(args);
        // Test area
        ObservableList<Appointment> aptList = FXCollections.observableArrayList();
        ObservableList<Country> ctryList = FXCollections.observableArrayList();
        ObservableList<User> usrList = FXCollections.observableArrayList();
        ObservableList<Contact> conList = FXCollections.observableArrayList();
        ObservableList<Customer> custList = FXCollections.observableArrayList();

        aptList = AppointmentDB.getAllAppointments();
        ctryList = CountryDB.getAllCountries();
        usrList = UserDB.getAllUsers();
        conList = ContactDB.getAllContacts();
        custList = CustomerDB.getAllCustomers();

        System.out.println("\nCountries:");
        for (Country c : ctryList) {
            System.out.println(c.getCreateDate().toString());
        }
        System.out.println("\nAppointments:");
        for (Appointment a : aptList) {
            a.printAppointment();
        }
        System.out.println("\nUsers:");
        for (User u : usrList) {
            u.printUsername();
        }
        System.out.println("\nContacts:");
        for (Contact c : conList) {
            c.printName();
        }
        System.out.println("\nCustomers:");
        for (Customer c : custList) {
            c.printName();
        }
        //Test area end
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
    /**
     * Connects the user to the database with openConnection() from the DBConnector class.
     * @param username the username used to access the database
     * @param password the password used to access the database
     * */
    public static void startConnection(String username, String password) {
        DBConnector.openConnection(username, password);
    }
}
