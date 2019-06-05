package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListTableItem {

    private SimpleIntegerProperty identifier = new SimpleIntegerProperty(0);
    private SimpleStringProperty description = new SimpleStringProperty("");

    public int getIdentifier() {
        return identifier.get();
    }

    public SimpleIntegerProperty identifierProperty() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier.set(identifier);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ListTableItem(int identifier, String description) {
        setIdentifier(identifier);
        setDescription(description);
    }
}
