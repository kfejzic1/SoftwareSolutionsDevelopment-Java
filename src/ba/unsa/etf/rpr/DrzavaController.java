package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DrzavaController {

    public TextField fieldNaziv;


    public void btnOkAction(ActionEvent actionEvent) {
        if(fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeValidno");
            fieldNaziv.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeValidno");
            fieldNaziv.getStyleClass().add("poljeValidno");
        }
    }

    public void btnCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
