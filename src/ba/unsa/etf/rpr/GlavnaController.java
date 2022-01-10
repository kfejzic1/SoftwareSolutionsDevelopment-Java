package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GlavnaController {
    private GeografijaDAO dao = GeografijaDAO.getInstance();
    private ObservableList<Grad> gradovi = FXCollections.observableArrayList();

    @FXML
    private TableView<Grad> tableViewGradovi;
    @FXML
    private TableColumn<Grad, Integer> colGradId;
    @FXML
    private TableColumn<Grad, String> colGradNaziv;
    @FXML
    private TableColumn<Grad, Integer> colGradStanovnika;
    @FXML
    private TableColumn<Grad, Integer> colGradDrzava;

    @FXML
    private void initialize() {
        gradovi.addAll(dao.gradovi());
        tableViewGradovi.setItems(gradovi);
        colGradId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<>("drzava"));
    }

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
