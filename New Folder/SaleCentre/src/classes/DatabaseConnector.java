package classes;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnector {
    public static final String DB_NAME = "posdatabase";
    /*public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Development Projects\\Databases\\" + DB_NAME;*/
    public static final String CONNECTION_STRING = "jdbc:sqlserver://localhost;databaseName="+ DB_NAME + ";integratedSecurity=true;";

    public static final String TABLE_SALECENTREUSERS = "SaleCentreAccess";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_USER_NAME = "Name";
    public static final String COLUMN_USER_PASSWORD = "Password";
    public static final String COLUMN_USER_ACCESS = "AccessLevel";
    public static final int INDEX_USER_ID = 1;
    public static final int INDEX_USER_NAME = 2;
    public static final int INDEX_USER_PASSWORD = 3;
    public static final int INDEX_USER_ACCESS = 4;

    public static final String TABLE_PRODUCTS = "Products";
    public static final String COLUMN_ITEM_NUM = "ItemNum";
    public static final String COLUMN_ITEM_DESCRIPTION = "ItemDescription";
    public static final String COLUMN_DEPARTMENT = "Department";
    public static final String COLUMN_SUB_DEPARTMENT = "SubDepartment";
    public static final String COLUMN_BARCODE = "Barcode";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_PROMOTION = "Promotion";
    public static final String COLUMN_WEIGHTED_ITEM = "WeightedItem";
    public static final String COLUMN_RESTRICTED_ITEM = "RestrictedItem";
    public static final String COLUMN_DO_NOT_SELL = "DoNotSell";
    public static final String COLUMN_SOH = "SOH";
    public static final String COLUMN_SOO = "SOO";
    public static final String COLUMN_SIT = "SIT";
    public static final String COLUMN_ITEM_STATUS = "ItemStatus";
    public static final String COLUMN_ITEM_LOCK = "ItemLock";
    public static final int INDEX_ITEM_NUM = 1;
    public static final int INDEX_ITEM_DESCRIPTION = 2;
    public static final int INDEX_DEPARTMENT = 3;
    public static final int INDEX_SUB_DEPARTMENT = 4;
    public static final int INDEX_BARCODE = 5;
    public static final int INDEX_PRICE = 6;
    public static final int INDEX_PROMOTION = 7;
    public static final int INDEX_WEIGHTED_ITEM = 8;
    public static final int INDEX_RESTRICTED_ITEM = 9;
    public static final int INDEX_DO_NOT_SELL = 10;
    public static final int INDEX_SOH = 11;
    public static final int INDEX_SOO = 12;
    public static final int INDEX_SIT = 13;
    public static final int INDEX_ITEM_STATUS = 14;
    public static final int INDEX_ITEM_LOCK = 15;

    public static final String TABLE_DEPARTMENTS = "Departments";
    public static final String COLUMN_DEPARTMENT_ID = "ID";
    public static final String COLUMN_DEPARTMENT_NAME = "Name";
    public static final int INDEX_DEPARMENT_ID = 1;
    public static final int INDEX_DEPARTMENT_NAME = 2;

    public static final String TABLE_SUB_DEPARTMENTS = "SubDepartments";
    public static final String COLUMN_SUB_DEPARTMENT_ID = "ID";
    public static final String COLUMN_SUB_DEPARTMENT_NAME = "SubDepartmentName";
    public static final String COLUMN_PARENT_DEPARTMENT = "ParentDepartment";
    public static final int INDEX_SUB_DEPARTMENT_ID = 1;
    public static final int INDEX_SUB_DEPARTMENT_NAME = 2;
    public static final int INDEX_PARENT_DEPARTMENT = 3;


    private Connection conn;
    private static DatabaseConnector instance = new DatabaseConnector();
    ArrayList<Integer> itemNumbers = new ArrayList<>();


    public static DatabaseConnector getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Error connecting to Database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    public void setSqlStatement(String sqlStatement) {
        String sqlStatement1 = sqlStatement;
    }

    public boolean getUser(String userID, String password) {
        open();
        String sqlStatement = "SELECT * FROM " + TABLE_SALECENTREUSERS + " WHERE " + COLUMN_USER_ID + " = '"
                + userID + "' AND " + COLUMN_USER_PASSWORD + " = '" + password + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlStatement)) {
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve user " + e.getMessage());
            close();
            return false;
        }

    }

    /*public int getItemNumber(){
        open();
        String itemNumListSqlStatement = "SELECT ItemNum FROM Products";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(itemNumListSqlStatement);
            while (rs.next()){
                itemNumbers = (rs.getInt(1));
            }
        }
        catch (SQLException e){
            System.out.println("Database Error: " + e.getMessage());
            close();
        }
        close();
        return itemNumbers;
    }*/

    public ArrayList<Integer> getItemNumbers(){
        open();
        String sqlStatement = "SELECT ItemNum FROM Products";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                itemNumbers.add(rs.getInt(1));
            }
        }
        catch (SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
        close();
        return itemNumbers;
    }

    public String getItems(int itemNumber) {
        open();
        String sqlStatement = "SELECT ItemDescription, Department, SubDepartment, Barcode, Price, ItemStatus FROM Products" +
                " WHERE ItemNum = " + itemNumber;
        String itemDetailString = "";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                String itemDescription = rs.getString(1);
                String itemDepartment = rs.getString(2);
                String itemSubDepartment = rs.getString(3);
                String itemBarcode = rs.getString(4);
                String itemPrice = rs.getString(5);
                String itemStatus = rs.getString(6);
                itemDetailString = itemNumber + ", " + itemDescription + ", " +
                        itemDepartment + ", " + itemSubDepartment + ", " + itemBarcode + ", " +
                        itemPrice + ", " + itemStatus;
            }
        }
        catch (SQLException e){
            System.out.println("Database Error: " + e.getMessage());
            close();
        }
        close();
        return itemDetailString;
    }

}