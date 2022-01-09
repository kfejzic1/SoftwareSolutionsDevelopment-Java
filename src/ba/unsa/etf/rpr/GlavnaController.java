package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GlavnaController {

    public void dodajDrzavuAction(ActionEvent actionEvent) throws IOException {
        DrzavaController ctrl = new DrzavaController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle("Država");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void dodajGradAction(ActionEvent actionEvent) throws IOException {
        GradController ctrl = new GradController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle("Grad");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }

    public void izmijeniGradAction(ActionEvent actionEvent) throws IOException {
        GradController ctrl = new GradController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle("Grad");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
    }
}
