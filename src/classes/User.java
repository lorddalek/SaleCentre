package classes;

public class User {

    private final String ID;
    private final String UserName;
    private final String Password;
    private final byte Access;

    public User(String ID, String userName, String password, byte access) {
        this.ID = ID;
        UserName = userName;
        Password = password;
        Access = access;
    }

    private String getID() {
        return ID;
    }

    private String getUserName() {
        return UserName;
    }

    private String getPassword() {
        return Password;
    }

    private byte getAccess() {
        return Access;
    }

    public void createUser(){
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.createUser(getID(), getUserName(), getAccess(), getPassword());
    }
}
