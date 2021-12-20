package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.model.StudentiModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KontrolerUnos {
    @FXML
    private TextField fldIme;
    @FXML
    private ProgressBar progressBar;

    ListView<String> listaStudenata;
    TextField uneseniBroj;
    StudentiModel studenti;

    @FXML
    public void initialize() {
        fldIme.textProperty().addListener(
                (observableValue, s, t1) -> {
                    progressBar.progressProperty().bind(new SimpleDoubleProperty(fldIme.getText().length()/10.));
                    if(progressBar.getProgress() >= 1) {
                        progressBar.getStyleClass().removeAll("crveniProgress");
                        progressBar.getStyleClass().add("zeleniProgress");
                    }
                    else {
                        progressBar.getStyleClass().removeAll("zeleniProgress");
                        progressBar.getStyleClass().add("crveniProgress");
                    }
                }
        );
    }

    public void setListaStudenata(ListView<String> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }
    public void setUneseniBroj(TextField uneseniBroj) {
        this.uneseniBroj = uneseniBroj;
    }
    public void setStudenti(StudentiModel studenti) { this.studenti = studenti; }

    private void zatvori(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage  = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttonClickOk(ActionEvent actionEvent) {
        if(fldIme.getText().length()<10) {      //Izbaci alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravno ime");
            alert.setHeaderText("Neispravno ime studenta");
            alert.setContentText("Ime studenta treba biti najmanje 10 karaktera dugačko");
            alert.showAndWait();
        } else {
            String noviStudent = fldIme.getText()+uneseniBroj.getText();
            listaStudenata.getItems().add(noviStudent);
            studenti.getStudenti().add(noviStudent);
            listaStudenata.refresh();

            zatvori(actionEvent);
        }
    }

    @FXML
    public void buttonClickCancel(ActionEvent actionEvent) {
        zatvori(actionEvent);
    }
}
