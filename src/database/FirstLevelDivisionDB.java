package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionDB {
    public static ObservableList<String> getAllDivisionNames() throws SQLException {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String division = results.getString("Division");
                divisionList.add(division);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionList;
    }
    public static ObservableList<String> getUSDivisions() throws SQLException {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID BETWEEN 1 AND 54";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String division = results.getString("Division");
                divisionList.add(division);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionList;
    }
    public static ObservableList<String> getCADivisions() throws SQLException {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID BETWEEN 60 AND 72";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String division = results.getString("Division");
                divisionList.add(division);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionList;
    }
    public static ObservableList<String> getUKDivisions() throws SQLException {
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID BETWEEN 101 AND 104";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String division = results.getString("Division");
                divisionList.add(division);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionList;
    }
}
