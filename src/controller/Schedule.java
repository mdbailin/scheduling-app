package controller;

import database.AppointmentDB;
import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import resources.LanguageManager;
import utility.Alerter;
import utility.ReportManager;
import utility.TimeManager;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 * Controller for the Schedule view.
 * */
public class Schedule {
    /**
     * Access to the addButton
     */
    public Button addButton;
    /**
     * Access to the updateButton
     */
    public Button updateButton;
    /**
     * Access to the deleteButton
     */
    public Button deleteButton;
    /**
     * Access to the appointmentByMonthRadio
     */
    public RadioButton appointmentByMonthRadio;
    /**
     * Access to the appointmentByWeekRadio
     */
    public RadioButton appointmentByWeekRadio;
    /**
     * Access to the time zone Label description
     */
    public Label timeZoneDescLabel;
    /**
     * Access to the time zone Label
     */
    public Label timeZoneLabel;
    /**
     * Access to the server time zone description
     */
    public Label serverTimeZoneDescLabel;
    /**
     * Access to the server time zone Label
     */
    public Label serverTimeZoneLabel;
    /**
     * Access to the toggleViewButton
     */
    public ToggleButton toggleViewButton;
    /**
     * Keep track of which TableView should be active.
     */
    public boolean viewAppointments = true;
    /**
     * Access to the appointmentTableView
     */
    public TableView appointmentTableView;
    /**
     * Access to the customerTableView
     */
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
     */
    public Button logOutButton;
    /**
     * Access to the reportsButton.
     * */
    public Button reportsButton;
    /**
     * Login scene.
     */
    Scene loginScene;
    /**
     * Login stage.
     */
    Stage loginStage = new Stage();
    /**
     * AppointmentForm scene.
     */
    Scene appointmentFormScene;
    /**
     * AppointmentForm stage.
     */
    Stage appointmentFormStage = new Stage();
    /**
     * CustomerForm scene.
     */
    Scene customerFormScene;
    /**
     * CustomerForm stage.
     */
    Stage customerFormStage = new Stage();
    /**
     * Toggle group to allow toggling of radio buttons.
     */
    static ToggleGroup radioToggle = new ToggleGroup();
    /**
     * Stores the data of the selected Appointment.
     */
    public static Appointment selectedAppointment = null;
    /**
     * Stores the data of the selected Customer.
     */
    public static Customer selectedCustomer = null;
    public static ZonedDateTime selectedDate = null;
    public ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * Initializes Schedule by translating all text and filling the TableView.
     */
    @FXML
    private void initialize() throws SQLException {
        // Set up the toggle group
        appointmentByMonthRadio.setToggleGroup(radioToggle);
        appointmentByWeekRadio.setToggleGroup(radioToggle);

        //Set the TableViews
        setUpTables();

        // Translate the window text
        addButton.setText(LanguageManager.getLocalString("Add"));
        updateButton.setText(LanguageManager.getLocalString("Update"));
        deleteButton.setText(LanguageManager.getLocalString("Delete"));
        appointmentByMonthRadio.setText(LanguageManager.getLocalString("Appointments_By_Month"));
        appointmentByWeekRadio.setText(LanguageManager.getLocalString("Appointments_By_Week"));
        toggleViewButton.setText(LanguageManager.getLocalString("View_Customers"));
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        timeZoneLabel.setText(ZoneId.systemDefault().toString());
        serverTimeZoneDescLabel.setText(LanguageManager.getLocalString("Server_Time_Zone"));
        serverTimeZoneLabel.setText(ZoneId.of("UTC").toString());
        logOutButton.setText(LanguageManager.getLocalString("Log_Out"));
        appointmentIdCol_a.setText(LanguageManager.getLocalString("Appointment_ID"));
        titleCol_a.setText(LanguageManager.getLocalString("Title"));
        reportsButton.setText(LanguageManager.getLocalString("Reports"));
        // Translate Appointment cols
        descriptionCol_a.setText(LanguageManager.getLocalString("Description"));
        locationCol_a.setText(LanguageManager.getLocalString("Location"));
        contactCol_a.setText(LanguageManager.getLocalString("Contact"));
        typeCol_a.setText(LanguageManager.getLocalString("Type"));
        startCol_a.setText(LanguageManager.getLocalString("Start"));
        endCol_a.setText(LanguageManager.getLocalString("End"));
        customerIdCol_a.setText(LanguageManager.getLocalString("Customer_ID"));
        userIdCol_a.setText(LanguageManager.getLocalString("User_ID"));
        // Translate Customer cols
        customerIdCol_c.setText(LanguageManager.getLocalString("Customer_ID"));
        customerNameCol_c.setText(LanguageManager.getLocalString("Customer_Name"));
        addressCol_c.setText(LanguageManager.getLocalString("Address"));
        postalCodeCol_c.setText(LanguageManager.getLocalString("Postal_Code"));
        phoneCol_c.setText(LanguageManager.getLocalString("Phone"));
        createDateCol_c.setText(LanguageManager.getLocalString("Create_Date"));
        createdByCol_c.setText(LanguageManager.getLocalString("Created_By"));
        lastUpdateCol_c.setText(LanguageManager.getLocalString("Last_Update"));
        lastUpdatedByCol_c.setText(LanguageManager.getLocalString("Last_Updated_By"));
        divisionIdCol_c.setText(LanguageManager.getLocalString("Division_ID"));
        // Initialize the Appointment TableView
        appointmentIdCol_a.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol_a.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol_a.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol_a.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol_a.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol_a.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol_a.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol_a.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol_a.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactCol_a.setCellValueFactory(new PropertyValueFactory<>("contactId"));
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

        checkAppointments();
    }

    public void closeSchedule(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Used to set the selectedAppointment.
     */
    public void setSelectedAppointment() {
        selectedCustomer = null;
        selectedAppointment = (Appointment) appointmentTableView.getSelectionModel().getSelectedItem();
        if (selectedDate != null) {
            selectedDate = selectedAppointment.getStart();
        }
    }

    /**
     * Used to set the selectedCustomer.
     */
    public void setSelectedCustomer() {
        selectedAppointment = null;
        selectedDate = null;
        selectedCustomer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Opens the AppointmentForm view when the user presses the addAppointmentButton.
     */
    public void onAddButton(ActionEvent actionEvent) throws IOException {
        selectedAppointment = null;
        selectedCustomer = null;
        if (viewAppointments) {
            FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/AppointmentForm.fxml"));
            Parent root = loadSchedule.load();
            appointmentFormScene = new Scene(root);
            appointmentFormStage.setScene(appointmentFormScene);
            appointmentFormStage.setTitle(LanguageManager.getLocalString("Add_Appointment"));
            appointmentFormStage.setResizable(false);
            appointmentFormStage.showAndWait();
            setUpTables();
        } else {
            FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
            Parent root = loadSchedule.load();
            customerFormScene = new Scene(root);
            customerFormStage.setScene(customerFormScene);
            customerFormStage.setTitle(LanguageManager.getLocalString("Add_Customer"));
            customerFormStage.setResizable(false);
            customerFormStage.showAndWait();
            setUpTables();
        }
    }

    /**
     * Opens the UpdateAppointment view when the user presses the updateAppointmentButton.
     */
    public void onUpdateButton(ActionEvent actionEvent) throws IOException, SQLException {
        if (selectedAppointment != null || selectedCustomer != null) {
            FXMLLoader loadSchedule;
            if (viewAppointments) {
                loadSchedule = new FXMLLoader(getClass().getResource("/view/AppointmentForm.fxml"));
                Parent root = loadSchedule.load();
                appointmentFormScene = new Scene(root);
                appointmentFormStage.setScene(appointmentFormScene);
                appointmentFormStage.setTitle(LanguageManager.getLocalString("Update_Appointment"));
                appointmentFormStage.setResizable(false);
                appointmentFormStage.showAndWait();
            } else {
                loadSchedule = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
                Parent root = loadSchedule.load();
                customerFormScene = new Scene(root);
                customerFormStage.setScene(customerFormScene);
                customerFormStage.setTitle(LanguageManager.getLocalString("Update_Customer"));
                customerFormStage.setResizable(false);
                customerFormStage.showAndWait();
            }
            setUpTables();
        } else {
            Alerter.alert("Make_Selection", "Message");
        }

    }

    /**
     * Deletes the selected appointment when the user presses the deleteAppointmentButton.
     */
    public void onDeleteButton(ActionEvent actionEvent) throws SQLException {
        if (selectedAppointment != null || selectedCustomer != null) {
            if (viewAppointments) {
                if (Alerter.confirm("Delete_Appointment")) {
                    AppointmentDB.removeAppointment(selectedAppointment.getAppointmentId());
                }
            } else {
                if (Alerter.confirm("Delete_Customer")) {
                    AppointmentDB.removeCustomerAppointments(AppointmentDB.getAllAppointments(selectedCustomer.getCustomerId()), selectedCustomer.getCustomerId());
                    CustomerDB.removeCustomer(selectedCustomer.getCustomerId());
                } else {
                    System.out.println("Customer not deleted");
                }
            }
            setUpTables();
            selectedCustomer = null;
            selectedAppointment = null;
        } else {
            Alerter.alert("Please make a selection to delete.", "Delete Error");
        }
    }

    /**
     * Allows the user to toggle between the Appointment TableView and the Customer TableView
     */
    public void onToggleViewButton(ActionEvent actionEvent) throws SQLException {
        selectedCustomer = null;
        selectedAppointment = null;
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
            setUpTables();
        } else {
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
            setUpTables();
        }
    }

    /**
     * Set the selectedAppointment to the correct item in the TableView.
     */
    public void onAppointmentTableViewClicked(MouseEvent mouseEvent) {
        setSelectedAppointment();
        if (selectedAppointment != null) {
            System.out.println("Selected appointment ID: " + selectedAppointment.getAppointmentId());
        } else {
            System.out.println("No appointment selected.");
        }
    }

    /**
     * Set the selectedCustomer to the correct item in the TableView.
     */
    public void onCustomerTableViewClicked(MouseEvent mouseEvent) {
        setSelectedCustomer();
        if (selectedCustomer != null) {
            System.out.println("Selected customer ID: " + selectedCustomer.getCustomerId());
        } else {
            System.out.println("No customer selected.");
        }

    }

    /**
     * Opens the Login screen and closes the Schedule.
     */
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

    /**
     * Set up TableViews.
     */
    public void setUpTables() {
        try {
            customerList = CustomerDB.getAllCustomers();
            appointmentList = AppointmentDB.getAllAppointments();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        customerTableView.setItems(customerList);
        appointmentTableView.setItems(appointmentList);
    }

    public void toggle() {
        System.out.println("Sorted.");
    }

    public void onMonthToggle(ActionEvent actionEvent) {
        toggle();
    }

    public void onWeekToggle(ActionEvent actionEvent) {
        toggle();
    }

    private void checkAppointments() throws SQLException {
        boolean alert = false;
        int FIFTEEN_MINUTES = 900000;
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            appointments = AppointmentDB.getAllAppointments();
        } catch (SQLException sqlE) {
        }
        LocalTime now = ZonedDateTime.now(ZoneId.systemDefault()).toLocalTime();
        LocalDate today = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate();
        for (Appointment a : appointments) {
            if (a.getStart().toLocalDate().equals(today)) {
                LocalTime aTime = a.getStart().toLocalTime();
                if (aTime.compareTo(now) <= FIFTEEN_MINUTES && aTime.compareTo(now) > 0) {
                    Alerter.alert("Upcoming appointment within 15 minutes!\nAppointment ID: " + a.getAppointmentId() + "\nDate: "
                            + TimeManager.getDate(a.getStart()) + "\nTime: " + TimeManager.getTime(a.getStart()), "Upcoming Appointment!");
                    alert = true;
                }
            }
        }
        if (!alert) {
            Alerter.alert("No appointments scheduled within 15 minutes.", "No upcoming appointments.");
        }
    }

    public void onReportsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/Reports.fxml"));
        Parent root = loadSchedule.load();
        Scene reportScene = new Scene(root);
        Stage reportStage = new Stage();
        reportStage.setScene(reportScene);
        reportStage.setTitle(LanguageManager.getLocalString("Reports"));
        reportStage.setResizable(false);
        reportStage.show();
    }
}