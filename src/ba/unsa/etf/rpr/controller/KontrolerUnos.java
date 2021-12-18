package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KontrolerUnos {
    public ListView<Student> listaStudenata;

    public void setListaStudenata(ListView<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }

    @FXML
    private TextField txtFieldImePrezime;

    private void zatvori(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage  = (Stage) n.getScene().getWindow();
        stage.close();
    }

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

    public void buttonClickCancel(ActionEvent actionEvent) {
        zatvori(actionEvent);
    }
}
