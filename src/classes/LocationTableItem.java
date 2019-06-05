package classes;

import javafx.beans.property.SimpleStringProperty;

public class LocationTableItem {

    private SimpleStringProperty locationID = new SimpleStringProperty();
    private SimpleStringProperty locationDescription = new SimpleStringProperty();

    public String getLocationID() {
        return locationID.get();
    }

    public SimpleStringProperty locationIDProperty() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID.set(locationID);
    }

    public String getLocationDescription() {
        return locationDescription.get();
    }

    public SimpleStringProperty locationDescriptionProperty() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription.set(locationDescription);
    }

    public LocationTableItem(String locationID, String locationDescription) {
        setLocationID(locationID);
        setLocationDescription(locationDescription);
    }
}
