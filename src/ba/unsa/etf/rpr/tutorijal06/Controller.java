package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Controller {
    @FXML
    public Button btnKraj;

    @FXML
    public void kraj(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda");
        alert.setHeaderText("Izlaz iz programa");
        alert.setContentText("Da li ste sigurni da želite zatvoriti program?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            //Zatvori program
            Stage stage  = (Stage) btnKraj.getScene().getWindow();
            stage.close();
        } else {
            //Zatvori alert
            alert.close();
        }
    }
}
