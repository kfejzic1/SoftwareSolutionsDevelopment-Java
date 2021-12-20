package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.String;
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

    public ListView<String> listaStudenata;

    private TextField uneseniBroj;

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
    private void zatvori(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage  = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttonClickOk(ActionEvent actionEvent) {
        //Potrebno je proslijediti string iz textfielda u list view
        try{
            String s = new String(fldIme.getText());
//            Student student = new Student(fldIme.getText() + uneseniBroj.getText());
//            listaStudenata.getItems().add(student);
//            listaStudenata.refresh();
//            uneseniBroj.setText(""); //Da li je potrebno ocistiti fldText u KontrolerForma nakon unosa studenta

            zatvori(actionEvent);
        }catch (IllegalArgumentException e) {
            //Izbaci alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravno ime");
            alert.setHeaderText("Neispravno ime studenta");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void buttonClickCancel(ActionEvent actionEvent) {
        zatvori(actionEvent);
    }
}
