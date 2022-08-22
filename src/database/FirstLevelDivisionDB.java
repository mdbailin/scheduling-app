package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
/**
 * FirstLevelDivisionDB is responsible for all queries to the database regarding first level divisions.
 * */
public class FirstLevelDivisionDB {
    /**
     * Builds a hashtable consisting of first-level divisions mapped to their Division_ID.
     * */
    public static Hashtable<String, Integer> hashAllDivisionIds() {
        Hashtable<String, Integer> divisionHashtable = new Hashtable<>();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String division = results.getString("Division");
                int id = results.getInt("Division_ID");
                divisionHashtable.put(division, id);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionHashtable;
    }
    /**
     * Builds a hashtable consisting of Division_ID's mapped to their associated first-level division.
     * @return HashTable containing Division_ID's paired with Division names.
     * */
    public static Hashtable<Integer, String> hashAllDivisionNames() {
        Hashtable<Integer, String> divisionHashtable = new Hashtable<>();
        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int id = results.getInt("Division_ID");
                String division = results.getString("Division");

                divisionHashtable.put(id, division);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return divisionHashtable;
    }
    /**
     * Queries the database for US divisions.
     * @return ObservableList of Division name Strings.
     * */
    public static ObservableList<String> getUSDivisions() {
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
    /**
     * Queries the database for Canadian divisions.
     * @return ObservableList of Division name Strings.
     * */
    public static ObservableList<String> getCADivisions() {
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
    /**
     * Queries the database for UK divisions.
     * @return ObservableList of Division name Strings.
     * */
    public static ObservableList<String> getUKDivisions() {
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
