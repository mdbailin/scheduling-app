package controller;

import database.AppointmentDB;
import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import resources.LanguageManager;
import utility.Alerter;
import utility.TimeManager;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 * Controller for the Schedule view.
 * */
public class Schedule {
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public RadioButton appointmentByMonthRadio;
    public RadioButton appointmentByWeekRadio;
    public Label timeZoneDescLabel;
    public Label timeZoneLabel;
    public Label serverTimeZoneDescLabel;
    public Label serverTimeZoneLabel;
    public ToggleButton toggleViewButton;
    public boolean viewAppointments = true;
    public TableView<Appointment> appointmentTableView;
    public TableView<Customer> customerTableView;
    public TableColumn<Appointment, Integer> appointmentIdCol_a;
    public TableColumn<Appointment, String> titleCol_a;
    public TableColumn<Appointment, String> descriptionCol_a;
    public TableColumn<Appointment, String> locationCol_a;
    public TableColumn<Appointment, String> contactCol_a;
    public TableColumn<Appointment, String> typeCol_a;
    public TableColumn<Appointment, ZonedDateTime> startCol_a;
    public TableColumn<Appointment, ZonedDateTime> endCol_a;
    public TableColumn<Appointment, Integer> customerIdCol_a;
    public TableColumn<Appointment, Integer> userIdCol_a;

    // Customer columns
    public TableColumn<Customer, Integer> customerIdCol_c;
    public TableColumn<Customer, String> customerNameCol_c;
    public TableColumn<Customer, String> addressCol_c;
    public TableColumn<Customer, Integer> postalCodeCol_c;
    public TableColumn<Customer, Integer> phoneCol_c;
    public TableColumn<Customer, ZonedDateTime> createDateCol_c;
    public TableColumn<Customer, String>  createdByCol_c;
    public TableColumn<Customer, Timestamp> lastUpdateCol_c;
    public TableColumn<Customer, String> lastUpdatedByCol_c;
    public TableColumn<Customer, Integer> divisionIdCol_c;

    public Button logOutButton;
    public Button reportsButton;
    public Rectangle appointmentRectangle;
    public Button dismissRectangleButton;
    public Label rectangleLabel;
    Scene loginScene;
    Stage loginStage = new Stage();
    Scene appointmentFormScene;
    Stage appointmentFormStage = new Stage();
    Scene customerFormScene;
    Stage customerFormStage = new Stage();
    /**
     * Toggle group to allow toggling of radio buttons.
     */
    static ToggleGroup radioToggle = new ToggleGroup();
    public static Appointment selectedAppointment = null;
    public static Customer selectedCustomer = null;
    public static ZonedDateTime selectedDate = null;
    public ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private boolean monthSort = true;

    /**
     * Initializes Schedule by translating all text and filling the TableView then checks if
     * there are any appointments occuring within 15 minutes.
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
        dismissRectangleButton.setText(LanguageManager.getLocalString("Acknowledge"));
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

    /**
     * Closes the Schedule window.
     * */
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
    public void onAddButton() throws IOException {
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
    public void onUpdateButton() throws IOException {
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
    public void onDeleteButton() throws SQLException {
        if (selectedAppointment != null || selectedCustomer != null) {
            if (viewAppointments) {
                if (Alerter.confirm("Delete_Appointment")) {
                    AppointmentDB.removeAppointment(selectedAppointment.getAppointmentId());
                }
            } else {
                if (Alerter.confirm("Delete_Customer")) {
                    AppointmentDB.removeCustomerAppointments(AppointmentDB.getAllAppointments(selectedCustomer.getCustomerId()), selectedCustomer.getCustomerId());
                    CustomerDB.removeCustomer(selectedCustomer.getCustomerId());
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
    public void onToggleViewButton() {
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
    public void onAppointmentTableViewClicked() {
        setSelectedAppointment();
    }

    /**
     * Set the selectedCustomer to the correct item in the TableView.
     */
    public void onCustomerTableViewClicked() {
        setSelectedCustomer();
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
        appointmentTableView.setItems(sortAppointments(monthSort));
    }

    /**
     * Contains Lambda 1
     * Returns a SortedList of Appointments from the appointmentList ObservableList.
     * The Comparator is implemented as a lambda expression, so I can utilize its abstract method of compare() in the
     * creation of the SortedList.
     * @param sort The value of monthSort is passed in and used to determine whether the list is sorted by Month or by Week.
     * @return SortedList generated by passing in appointmentList and a comparator.
     * */
    private SortedList<Appointment> sortAppointments(boolean sort) {
        return new SortedList<>( appointmentList,
                (Appointment appointment1, Appointment appointment2) -> {
            if (sort) {
                return Integer.compare(appointment1.getStart().getMonthValue(), appointment2.getStart().getMonthValue());
            }
            else {
                return Integer.compare(appointment1.getStart().getDayOfWeek().getValue(), appointment2.getStart().getDayOfWeek().getValue());
            }

        });
    }

    /**
     * Used to switch between month-sort and week-sort.
     * */
    public void toggle() {
        if (monthSort) {
            monthSort = false;
        }
        else {
            monthSort = true;
        }
        setUpTables();
    }

    /**
     * Call toggle() when the month radio is selected.  */
    public void onMonthToggle() {
        toggle();
    }

    /**
     * Call toggle() when the week radio is selected.  */
    public void onWeekToggle() {
        toggle();
    }

    /**
     * Used to check if there are any appointments occurring within 15 minutes of local time.
     * */
    private void checkAppointments() {
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
                    showAppointmentWarning(true, a.getAppointmentId(), a.getStart());
                    alert = true;
                }
            }
        }
        if (!alert) {
            showAppointmentWarning(false, -1, ZonedDateTime.now());
        }
    }

    /**
     * Used to show the Reports stage when the Report button is pressed.  */
    public void onReportsButton() throws IOException {
        FXMLLoader loadSchedule = new FXMLLoader(getClass().getResource("/view/Report.fxml"));
        Parent root = loadSchedule.load();
        Scene reportScene = new Scene(root);
        Stage reportStage = new Stage();
        reportStage.setScene(reportScene);
        reportStage.setTitle(LanguageManager.getLocalString("Reports"));
        reportStage.setResizable(false);
        reportStage.show();
    }

    /**
     * Hides the Appointment warning banner after the user presses the Acknowledge button.  */
    public void onDismissRectangleButton() {
        hideAppointmentWarning();
    }

    /**
     * Translates and prepares a warning banner to be shown upon user login.
     * @param imminent Boolean value to determine whether there is an appointment in 15 minutes or less.
     * @param start the ZonedDateTime that represents the start date of the appointment.
     * */
    private void showAppointmentWarning(boolean imminent, int id, ZonedDateTime start) {
        if (imminent) {
            appointmentRectangle.setFill(Paint.valueOf("#d71b4a"));
            String alert = "" + LanguageManager.getLocalString("Appointment_ID") + ": " + id + " " +
                    LanguageManager.getLocalString("Begins_In_15") + " " +
                    LanguageManager.getLocalString("Date") +
                    ": " + TimeManager.getDate(start) + ", " +
                    LanguageManager.getLocalString("Time") +
                    ": " + TimeManager.getTime(start);
            rectangleLabel.setText(alert);
        }
        else {
            appointmentRectangle.setFill(Paint.valueOf("#1c7fd6"));
            rectangleLabel.setText(LanguageManager.getLocalString("No_Appointments_Scheduled_Within_15_Minutes"));
        }
        rectangleLabel.setVisible(true);
        rectangleLabel.setDisable(false);
        appointmentRectangle.setVisible(true);
        appointmentRectangle.setDisable(false);
        dismissRectangleButton.setVisible(true);
        dismissRectangleButton.setDisable(false);
    }

    /**
     * Used to hide the Appointment warning banner.
     * */
    private void hideAppointmentWarning() {
        rectangleLabel.setVisible(false);
        rectangleLabel.setDisable(true);
        appointmentRectangle.setVisible(false);
        appointmentRectangle.setDisable(true);
        dismissRectangleButton.setVisible(false);
        dismissRectangleButton.setDisable(true);
    }
}
