package classes;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


@SuppressWarnings({"ALL", "SpellCheckingInspection"})
public class DatabaseConnector {
    private static final String DB_NAME = "posdatabase";
    /*public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Development Projects\\Databases\\" + DB_NAME;*/
    private static final String CONNECTION_STRING = "jdbc:sqlserver://localhost;databaseName=" + DB_NAME + ";integratedSecurity=true;";

    private static final String TABLE_SALECENTREUSERS = "SaleCentreAccess";
    private static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_USER_NAME = "Name";
    private static final String COLUMN_USER_PASSWORD = "Password";
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
    private static final DatabaseConnector instance = new DatabaseConnector();
    private final ArrayList<Integer> itemNumbers = new ArrayList<>();
    private final ArrayList<String> userIDs = new ArrayList<>();


    public static DatabaseConnector getInstance() {
        return instance;
    }

    @SuppressWarnings("UnusedReturnValue")
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


    public boolean checkUserExists(String userID, String password) {
        open();
        String sqlStatement = "SELECT * FROM " + TABLE_SALECENTREUSERS + " WHERE " + COLUMN_USER_ID + " = '"
                + userID + "' AND " + COLUMN_USER_PASSWORD + " = '" + password + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlStatement)) {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve user " + e.getMessage());
            close();
            return false;
        }

    }

    public String getUserName(String userID) {
        open();
        String sqlStatement = "SELECT Name FROM SaleCentreAccess WHERE ID = '" + userID +"'";
        String userName = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                userName = rs.getString(1);
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving username: " + e.getMessage());
        }
        return userName;
    }

    public ArrayList<String> getUserIDs() {
        open();
        String sqlStatement = "SELECT * FROM " + TABLE_SALECENTREUSERS;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                userIDs.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
        close();
        return userIDs;
    }

    public String getUserDetails(String userID) {
        open();
        String sqlStatement = "SELECT Name, AccessLevel FROM SaleCentreAccess WHERE" +
                " ID = '" + userID + "'";
        String userDetailString = "";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                String userName = rs.getString(1);
                int accessLevel = rs.getInt(2);
                userDetailString = userName + "," + accessLevel;
            }
            close();
            return userDetailString;
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            close();
            return null;
        }
    }

    public ArrayList<Integer> getItemNumbers() {
        open();
        String sqlStatement = "SELECT ItemNum FROM Products";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                itemNumbers.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        close();
        return itemNumbers;
    }



    public String getItemSummary(int itemNumber) {  //This is to retrieve basic item information for the Stock List Table
        open();
        String sqlStatement = "SELECT ItemDescription, Department, SubDepartment, Barcode, Price, ItemStatus FROM Products" +
                " WHERE ItemNum = " + itemNumber;
        String itemDetailString = "";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
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
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            close();
        }
        close();
        return itemDetailString;
    }

    public String getItemDetails(int itemNumber) {  //This is to return all details from the products table in a string with commas.
        open();
        String sqlStatement = "SELECT * FROM Products WHERE ItemNum = " + itemNumber;
        String itemDetailString = "";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                String itemDescription = rs.getString(2);
                String itemDepartment = rs.getString(3);
                String itemSubDepartment = rs.getString(4);
                String itemBarcode = rs.getString(5);
                String itemPrice = rs.getString(6);
                String itemPromotion = rs.getString(7);
                String itemWeighted = rs.getString(8);
                String itemRestricted = rs.getString(9);
                String itemDNS = rs.getString(10);
                String itemSOH = rs.getString(11);
                String itemSOO = rs.getString(12);
                String itemSIT = rs.getString(13);
                String itemStatus = rs.getString(14);
                String itemLock = rs.getString(15);
                itemDetailString = itemDescription + "," +
                        itemDepartment + "," + itemSubDepartment + "," + itemBarcode + "," +
                        itemPrice + "," + itemPromotion + "," + itemWeighted + "," + itemRestricted + ","
                        + itemDNS + "," + itemSOH + "," + itemSOO + "," + itemSIT + ","
                        + itemStatus + "," + itemLock;
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            close();
        }
        close();
        return itemDetailString;
    }

    public String getItemDescription(int itemNumber){
        open();
        String sqlStatement = "SELECT ItemDescription FROM Products WHERE ItemNum = " + itemNumber;
        String itemDescription = "";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                itemDescription = rs.getString(1);
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving item descriptions");
        }
        close();
        return itemDescription;
    }

    public ArrayList<Integer> getItemNumByDescription(String search){
        open();
        ArrayList<Integer> itemNumbers = new ArrayList<>();
        String sqlStatement = "SELECT ItemNum FROM Products WHERE ItemDescription LIKE '%" + search + "%'";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                itemNumbers.add(rs.getInt(1));
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving item numbers");
        }
        return itemNumbers;
    }

    public void createUser(String ID, String UserName, byte Access, String Password) {
        open();
        String sqlStatement = "INSERT INTO SaleCentreAccess VALUES('" + ID + "', '" +
                UserName + "', " + Access + ", '" + Password + "')";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
        close();
        return;
    }

    public void deleteUser(String ID) {
        open();
        String sqlStatement = "DELETE FROM SaleCentreAccess WHERE ID = '" + ID + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
        close();
    }

    public ResultSet getDepartments() {
        ResultSet rs = null;
        String sqlStatement = "SELECT DepartmentName from Departments";
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
        }
        catch (SQLException e) {
            System.out.println("Error retrieving departments: " + e.getMessage());
        }
        return rs;
    }

    public ResultSet getSubDepartments(String departmentName) {
        ResultSet rs = null;
        String sqlStatement = "SELECT SubDepartmentName from SubDepartments WHERE ParentDepartment = '" + departmentName + "'";
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
        }
        catch (SQLException e) {
            System.out.println("Error retrieving sub departments: " + e.getMessage());
        }
        return rs;
    }

    public boolean checkItemExists(int itemNumber){
        open();
        String sqlStatement = "SELECT COUNT(ItemNum) FROM Products WHERE ItemNum = " + itemNumber;
        boolean itemExists = false;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
            int result = rs.getInt(1);
                if (result == 0) {
                    itemExists = false;
                } else if (result == 1) {
                    itemExists = true;
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error checking for item: " + e.getMessage());
        }
        close();
        return itemExists;
    }

    public void saveStockAdjust(int itemNumber, int oldSOH, int adjust, int newSOH, String reason, String userName, String storePurchase){
        open();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        String dateTime = df.format(cal.getTime());
        String addRecordSqlStatement = "INSERT INTO StockAdjust VALUES(" + itemNumber + ", '" + reason + "', " + oldSOH + ", " + adjust
                + ", " + newSOH + ", '" + userName + "', '" + dateTime + "', '"+ storePurchase + "')";
        String editRecordSqlStatement = "UPDATE Products SET SOH = " + newSOH + " WHERE ItemNum = " + itemNumber;
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(addRecordSqlStatement);
            stmt.execute(editRecordSqlStatement);
        }
        catch (SQLException e){
            System.out.println("Error editing database: " + e.getMessage());
        }
        close();
    }

    public void saveLocationInStore(String locationID, String locationDescription){
        open();
        String sqlStatement = "INSERT INTO LocationInStore VALUES('" + locationID + "', '" +
                locationDescription + "')";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        }
        catch (SQLException e){
            System.out.println("Error saving Location in store");
        }
        close();
    }

    public void saveStorageLocation(String storageLocationID, String storageLocationDescription){
        open();
        String sqlStatement = "INSERT INTO StorageLocation VALUES('" + storageLocationID + "', '" +
                storageLocationDescription + "')";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        }
        catch (SQLException e){
            System.out.println("Error saving Location in store");
        }
        close();
    }

    public ArrayList<String> getLocationIDs(){
        open();
        ArrayList<String> locationIDs = new ArrayList<>();
        String sqlStatement = "SELECT LocationID FROM LocationInStore";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                locationIDs.add(rs.getString(1));
            }
        }
        catch (SQLException e){
            System.out.println("Could not retrieve location IDs: " + e.getMessage());
        }
        close();
        return locationIDs;
    }

    public String getLocationInStoreDescription(String locationID){
        open();
        String locationInStore = "";
        String sqlStatement = "SELECT LocationDescription FROM LocationInStore WHERE LocationID = '" + locationID + "'";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while(rs.next()){
                locationInStore = rs.getString(1);
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving location description: " + e.getMessage());
        }
        close();
        return locationInStore;
    }

    public ArrayList<String> getStorageLocationIDs(){
        open();
        ArrayList<String> storageLocationIDs = new ArrayList<>();
        String sqlStatement = "SELECT StorageLocationID FROM StorageLocation";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()){
                storageLocationIDs.add(rs.getString(1));
            }
        }
        catch (SQLException e){
            System.out.println("Could not retrieve storage location IDs: " + e.getMessage());
        }
        close();
        return storageLocationIDs;
    }

    public String getStorageLocationDescription(String storageLocationID){
        open();
        String storageLocation = "";
        String sqlStatement = "SELECT StorageLocationDescription FROM StorageLocation WHERE StorageLocationID = '" + storageLocationID + "'";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            while(rs.next()){
                storageLocation = rs.getString(1);
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving storage location description: " + e.getMessage());
        }
        close();
        return storageLocation;
    }

    public ResultSet getStockAdjustments(int itemNum){
        String sqlStatement = "SELECT * FROM StockAdjust WHERE ItemNum = " + itemNum;
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
        }
        catch (SQLException e){
            System.out.println("Error getting stock adjustments: " + e.getMessage());
        }
        return rs;
    }

    public void deleteLocation(String tableName, String idType, String locationID){
        open();
        String sqlStatement = "DELETE FROM " + tableName + " WHERE " + idType + " = '" + locationID +"'";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        }
        catch (SQLException e){
            System.out.println("Error deleting location: " + e.getMessage());
        }
    }


}