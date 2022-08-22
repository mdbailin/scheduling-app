package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * CountryDB is responsible for all queries to the database regarding Country objects.
 * */
public class CountryDB {
    /**
     * Queries the database for all Country names.
     * @return ObservableList containing all Country names.
     * */
    public static ObservableList<String> getAllCountryNames() {
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
