package controller;

import database.AppointmentDB;
import database.CustomerDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import resources.LanguageManager;
import utility.Alerter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;

/**
 * Controller for the Schedule view.
 * */
public class Schedule {
    /**
     * Access to the addButton
     * */
    public Button addButton;
    /**
     * Access to the updateButton
     * */
    public Button updateButton;
    /**
     * Access to the deleteButton
     * */
    public Button deleteButton;
    /**
     * Access to the appointmentByMonthRadio
     * */
    public RadioButton appointmentByMonthRadio;
    /**
     * Access to the appointmentByWeekRadio
     * */
    public RadioButton appointmentByWeekRadio;
    /**
     * Access to the time zone Label description
     * */
    public Label timeZoneDescLabel;
    /**
     * Access to the time zone Label
     * */
    public Label timeZoneLabel;
    /**
     * Access to the toggleViewButton
     * */
    public ToggleButton toggleViewButton;
    /**
     * Keep track of which TableView should be active.
     * */
    public boolean viewAppointments = true;
    /**
     * Access to the appointmentTableView
     * */
    public TableView appointmentTableView;
    /**
     * Access to the customerTableView
     * */
    public TableView customerTableView;
    // Access to Appointment columns
    public TableColumn appointmentIdCol_a;
    public TableColumn titleCol_a;
    public TableColumn descriptionCol_a;
    public TableColumn locationCol_a;
    public TableColumn contactCol_a;
    public TableColumn typeCol_a;
    public TableColumn startCol_a;
    public TableColumn endCol_a;
    public TableColumn customerIdCol_a;
    public TableColumn userIdCol_a;

    // Access to Customer columns
    public TableColumn customerIdCol_c;
    public TableColumn customerNameCol_c;
    public TableColumn addressCol_c;
    public TableColumn postalCodeCol_c;
    public TableColumn phoneCol_c;
    public TableColumn createDateCol_c;
    public TableColumn createdByCol_c;
    public TableColumn lastUpdateCol_c;
    public TableColumn lastUpdatedByCol_c;
    public TableColumn divisionIdCol_c;
    /**
     * Access to the logOutButton.
     * */
    public Button logOutButton;
    /**
     * Login scene.
     * */
    Scene loginScene;
    /**
     * Login stage.
     * */
    Stage loginStage = new Stage();
    /**
     * AppointmentForm scene.
     * */
    Scene appointmentFormScene;
    /**
     * AppointmentForm stage.
     * */
    Stage appointmentFormStage = new Stage();
    /**
     * CustomerForm scene.
     * */
    Scene customerFormScene;
    /**
     * CustomerForm stage.
     * */
    Stage customerFormStage = new Stage();
    /**
     * Stores the data of the selected Appointment.
     * */
    public static Appointment selectedAppointment = null;
    /**
     * Stores the data of the selected Customer.
     * */
    public static Customer selectedCustomer = null;


    /**
     * Initializes Schedule by translating all text and filling the TableView.
     * */
    @FXML
    private void initialize() throws SQLException {
        //Set the TableViews
        appointmentTableView.setItems(AppointmentDB.getAllAppointments());
        customerTableView.setItems(CustomerDB.getAllCustomers());

        // Translate the window text
        addButton.setText(LanguageManager.getLocalString("Add"));
        updateButton.setText(LanguageManager.getLocalString("Update"));
        deleteButton.setText(LanguageManager.getLocalString("Delete"));
        appointmentByMonthRadio.setText(LanguageManager.getLocalString("Appointments_By_Month"));
        appointmentByWeekRadio.setText(LanguageManager.getLocalString("Appointments_By_Week"));
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        toggleViewButton.setText(LanguageManager.getLocalString("View_Customers"));
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        timeZoneLabel.setText(ZoneId.systemDefault().toString());
        logOutButton.setText(LanguageManager.getLocalString("Log_Out"));
        // Initialize the Appointment TableView
        appointmentIdCol_a.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol_a.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol_a.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol_a.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol_a.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol_a.setCellValueFactory(new PropertyValueFactory<>("start"));
        startCol_a.setCellValueFactory(new PropertyValueFactory<>("end"));
        endCol_a.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerIdCol_a.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userIdCol_a.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        // Initialize the Customer TableView
        customerIdCol_c.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol_c.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol_c.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol_c.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol_c.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createDateCol_c.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol_c.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol_c.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol_c.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionIdCol_c.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
    }
    public void closeSchedule(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    /**
     * Used to set the selectedAppointment.
     * */
    public void setSelectedAppointment() {
        selectedAppointment = (Appointment) appointmentTableView.getSelectionModel().getSelectedItem();
    }
    /**
     * Used to set the selectedCustomer.
     * */
    public void setSelectedCustomer() {
        selectedCustomer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
    }
    /**
     * Opens the AppointmentForm view when the user presses the addAppointmentButton.
     * */
    public void onAddButton(ActionEvent actionEvent) throws IOException {
        if (viewAppointments) {
            FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/AppointmentForm.fxml"));
            Parent root = loadSchedule.load();
            appointmentFormScene = new Scene(root);
            appointmentFormStage.setScene(appointmentFormScene);
            appointmentFormStage.setTitle(LanguageManager.getLocalString("Add_Appointment"));
            appointmentFormStage.setResizable(false);
            appointmentFormStage.show();
        }
        else {
            FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
            Parent root = loadSchedule.load();
            customerFormScene = new Scene(root);
            customerFormStage.setScene(customerFormScene);
            customerFormStage.setTitle(LanguageManager.getLocalString("Add_Customer"));
            customerFormStage.setResizable(false);
            customerFormStage.show();
        }
    }
    /**
     * Opens the UpdateAppointment view when the user presses the updateAppointmentButton.
     * */
    public void onUpdateButton(ActionEvent actionEvent) throws IOException {
        if (selectedCustomer != null) {
            if (viewAppointments) {
                FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/AppointmentForm.fxml"));
                Parent root = loadSchedule.load();
                appointmentFormScene = new Scene(root);
                appointmentFormStage.setScene(appointmentFormScene);
                appointmentFormStage.setTitle(LanguageManager.getLocalString("Update_Appointment"));
                appointmentFormStage.setResizable(false);
                appointmentFormStage.show();
            }
            else {
                FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
                Parent root = loadSchedule.load();
                customerFormScene = new Scene(root);
                customerFormStage.setScene(customerFormScene);
                customerFormStage.setTitle(LanguageManager.getLocalString("Update_Customer"));
                customerFormStage.setResizable(false);
                customerFormStage.show();
            }
        }
        else {
            Alerter.alert("Make_Selection");
        }

    }
    /**
     * Deletes the selected appointment when the user presses the deleteAppointmentButton.
     *
     * */
    public void onDeleteButton(ActionEvent actionEvent) {
        //TODO ensure customer appointments are deleted BEFORE the customer is deleted
        if (viewAppointments) {
            // TODO ask for confirmation
            //  delete Appointment from the database
        }
        else {
            // TODO ask for confirmation
            //  delete Customer Appointments from the database
            //  delete Customer
        }
    }
    /**
     * Allows the user to toggle between the Appointment TableView and the Customer TableView
     * */
    public void onToggleViewButton(ActionEvent actionEvent) {
        if (viewAppointments) {
            toggleViewButton.setText(LanguageManager.getLocalString("View_Appointments"));
            appointmentTableView.setDisable(true);
            appointmentTableView.setVisible(false);
            customerTableView.setDisable(false);
            customerTableView.setVisible(true);
            appointmentByWeekRadio.setDisable(true);
            appointmentByWeekRadio.setVisible(false);
            appointmentByMonthRadio.setDisable(true);
            appointmentByMonthRadio.setVisible(false);
            viewAppointments = false;
        }
        else {
            toggleViewButton.setText(LanguageManager.getLocalString("View_Customers"));
            appointmentTableView.setDisable(false);
            appointmentTableView.setVisible(true);
            customerTableView.setDisable(true);
            customerTableView.setVisible(false);
            appointmentByWeekRadio.setDisable(false);
            appointmentByWeekRadio.setVisible(true);
            appointmentByMonthRadio.setDisable(false);
            appointmentByMonthRadio.setVisible(true);
            viewAppointments = true;
        }
    }
    /**
     * Set the selectedAppointment to the correct item in the TableView.
     * */
    public void onAppointmentTableViewClicked(MouseEvent mouseEvent) {
        setSelectedAppointment();
        System.out.println("Selected appointment ID: " + selectedAppointment.getAppointmentId());
    }
    /**
     * Set the selectedCustomer to the correct item in the TableView.
     * */
    public void onCustomerTableViewClicked(MouseEvent mouseEvent) {
        setSelectedCustomer();
        System.out.println("Selected customer ID: " + selectedCustomer.getCustomerId());
    }
    /**
     * Opens the Login screen and closes the Schedule.
     * */
    public void onLogOutButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
        Parent root = loadSchedule.load();
        loginScene = new Scene(root);
        loginStage.setScene(loginScene);
        loginStage.setTitle(LanguageManager.getLocalString("Login"));
        loginStage.setResizable(false);
        loginStage.show();
        closeSchedule(actionEvent);
    }
}
