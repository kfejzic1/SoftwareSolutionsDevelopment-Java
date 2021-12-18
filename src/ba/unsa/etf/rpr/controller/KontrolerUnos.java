package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.Student;
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
    private TextField txtFieldImePrezime;
    @FXML
    private ProgressBar progressBar;
    public ListView<Student> listaStudenata;

    @FXML
    public void initialize() {
        txtFieldImePrezime.textProperty().addListener(
                (observableValue, s, t1) ->
                        progressBar.progressProperty().bind(new SimpleDoubleProperty(txtFieldImePrezime.getText().length()/10.))
        );
    }

    public void setListaStudenata(ListView<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
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
            listaStudenata.getItems().add(new Student(txtFieldImePrezime.getText()));
            listaStudenata.refresh();
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
