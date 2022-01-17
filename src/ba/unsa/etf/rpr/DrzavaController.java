package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    ArrayList<Grad> gradovi;
    Drzava drzava = null;

    @FXML
    private TextField fieldNaziv;
    @FXML
    private ChoiceBox<Grad> choiceGrad;

    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.gradovi = gradovi;
        this.drzava = drzava;
    }

    public DrzavaController(ArrayList<Grad> gradovi) { this.gradovi=gradovi;}

    @FXML
    private void initialize() {
        choiceGrad.setItems(FXCollections.observableList(gradovi));
        choiceGrad.setValue(gradovi.get(0));
    }

    public void btnOkAction(ActionEvent actionEvent) {
        if(fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeValidno");
            fieldNaziv.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeValidno");
            fieldNaziv.getStyleClass().add("poljeValidno");

            drzava = new Drzava();
            drzava.setNaziv(fieldNaziv.getText());
            drzava.setGlavniGrad(choiceGrad.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) fieldNaziv.getScene().getWindow();
            stage.close();
        }
    }

    public void btnCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public Drzava getDrzava() {
        return drzava;
    }
}
