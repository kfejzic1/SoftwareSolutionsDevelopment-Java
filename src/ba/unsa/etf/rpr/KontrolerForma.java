package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class KontrolerForma {
    @FXML
    private TextField fldText;

    @FXML
    public void getNumber(ActionEvent actionEvent) {
        String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        fldText.setText(fldText.getText()+number);
    }

}
