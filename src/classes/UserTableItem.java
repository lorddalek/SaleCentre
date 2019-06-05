package classes;

import javafx.beans.property.SimpleStringProperty;

public class UserTableItem {

    private final SimpleStringProperty userID = new SimpleStringProperty();
    private final SimpleStringProperty userName = new SimpleStringProperty();
    private final SimpleStringProperty userAccess = new SimpleStringProperty();

    public String getUserID() {
        return userID.get();
    }

    public SimpleStringProperty userIDProperty() {
        return userID;
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public String getUserAccess() {
        return userAccess.get();
    }

    public SimpleStringProperty userAccessProperty() {
        return userAccess;
    }

    private void setUserID(String userID) {
        this.userID.set(userID);
    }

    private void setUserName(String userName) {
        this.userName.set(userName);
    }

    private void setUserAccess(String userAccess) {
        this.userAccess.set(userAccess);
    }

    public UserTableItem(String userID, String userName, String userAccess){
        setUserID(userID);
        setUserName(userName);
        setUserAccess(userAccess);
    }
}
