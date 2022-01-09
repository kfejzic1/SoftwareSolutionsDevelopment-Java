package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GradController {

    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;

    public void okAction(ActionEvent actionEvent) {
        if(fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeValidno");
            fieldNaziv.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeValidno");
            fieldNaziv.getStyleClass().add("poljeValidno");
        }

        if(fieldBrojStanovnika.getText().isEmpty()) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeValidno");
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
