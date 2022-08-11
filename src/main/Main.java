package main;

import connection.DBConnector;
import controller.Login;
import model.User;
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

import static java.util.Objects.hash;

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
            System.out.println(a.getAppointmentId());
        }
        System.out.println("\nUsers:");
        for (User u : usrList) {
            System.out.println(u.getUsername());
        }
        System.out.println("\nContacts:");
        for (Contact c : conList) {
            System.out.println(c.getContactName());
        }
        System.out.println("\nCustomers:");
        for (Customer c : custList) {
            System.out.println(c.getCustomerName());
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
}
