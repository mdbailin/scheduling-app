package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import utility.TimeManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class CountryDB {

    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int countryId = results.getInt("Country_ID");
                String country = results.getString("Country");
                ZonedDateTime createDate = TimeManager.toLocal(results.getTimestamp("Create_Date"));
                String createdBy = results.getString("Created_By");
                Timestamp lastUpdate = results.getTimestamp("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                Country c = new Country(countryId, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countryList.add(c);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return countryList;
    }
    public static ObservableList<String> getAllCountryNames() throws SQLException {
        ObservableList<String> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String country = results.getString("Country");
                countryList.add(country);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return countryList;
    }
}
