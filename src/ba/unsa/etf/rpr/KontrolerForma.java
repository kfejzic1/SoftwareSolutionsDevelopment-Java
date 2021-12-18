package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class KontrolerForma {
    private SimpleStringProperty idNumber = new SimpleStringProperty("");

    public String getIdNumber() {
        return idNumber.get();
    }

    public SimpleStringProperty idNumberProperty() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    @FXML
    public void getNumber(ActionEvent actionEvent) {
        String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        this.idNumber.set(this.idNumber.get()+number);
    }

}
