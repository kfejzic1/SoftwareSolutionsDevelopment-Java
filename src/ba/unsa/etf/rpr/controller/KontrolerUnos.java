package ba.unsa.etf.rpr.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class KontrolerUnos {

    public void buttonClickOk(ActionEvent actionEvent) {
    }

    public void buttonClickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage  = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
