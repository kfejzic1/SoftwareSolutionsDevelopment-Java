package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GradController {
    private ArrayList<Drzava> drzave;
    private Grad grad = null;

    @FXML
    private TextField fieldNaziv;
    @FXML
    private TextField fieldBrojStanovnika;
    @FXML
    private ChoiceBox<Drzava> choiceDrzava;


    public GradController(ArrayList<Drzava> spisakDrzava) {this.drzave=spisakDrzava;}

    @FXML
    private void initialize() {
        choiceDrzava.setItems(FXCollections.observableArrayList(drzave));
    }

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

        if(!fieldNaziv.getText().isEmpty() && !fieldBrojStanovnika.getText().isEmpty()) {   //Polje je validno
            grad = new Grad();
            grad.setNaziv(fieldNaziv.getText());
            grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
            grad.setDrzava(choiceDrzava.getSelectionModel().getSelectedItem());
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public Grad getGrad() {return grad;}
}
