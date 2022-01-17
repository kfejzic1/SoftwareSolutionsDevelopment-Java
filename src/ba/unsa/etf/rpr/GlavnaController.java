package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
        DrzavaController ctrl = new DrzavaController(dao.gradovi());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"), Main.bundle);
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle(Main.bundle.getString("country"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);

        stage.setOnHiding(windowEvent -> {
            if(ctrl.getDrzava()!=null)
                dao.dodajDrzavu(ctrl.getDrzava());
        });

        stage.show();
    }

    public void dodajGradAction(ActionEvent actionEvent) throws IOException {
        GradController ctrl = new GradController(null, dao.drzave());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), Main.bundle);
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle(Main.bundle.getString("titleCity"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);

        stage.setOnHiding(windowEvent -> {  //Provjeri da li je dodan validan grad prilikom zatvaranja
            if(ctrl.getGrad() != null) {
                dao.dodajGrad(ctrl.getGrad());
                gradovi.setAll(dao.gradovi());
            }
        });

        stage.show();
    }

    public void izmijeniGradAction(ActionEvent actionEvent) throws IOException {
        GradController ctrl = new GradController(tableViewGradovi.getSelectionModel().getSelectedItem(), dao.drzave());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), Main.bundle);
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle(Main.bundle.getString("titleCity"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);

        stage.setOnHiding(windowEvent -> {
            if(ctrl.getGrad()!=null) {
                dao.izmijeniGrad(ctrl.getGrad());
                gradovi.setAll(dao.gradovi());
            }
        });

        stage.show();
    }

    public void obrisiGradAction (ActionEvent actionEvent) {
        Grad grad = tableViewGradovi.getSelectionModel().getSelectedItem();
        if(grad!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Main.bundle.getString("titleConfirm"));
            alert.setHeaderText("Brisanje: " + grad);
            alert.setContentText("Da li ste sigurni da želite izvršiti ovu operaciju?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                dao.obrisiGrad(grad);
                gradovi.setAll(dao.gradovi());
            }
        }
    }

    public void btnPrintAction (ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport(dao.getConnection());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void reload() {
        Main.bundle = ResourceBundle.getBundle("Translation");
        Scene scene  = tableViewGradovi.getScene();
        tableViewGradovi.getItems().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), Main.bundle);
        loader.setController(this);

        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnLanguageAction (ActionEvent actionEvent) {
        String bs = Main.bundle.getString("bs");
        String en = Main.bundle.getString("en");
        String fr = Main.bundle.getString("fr");
        String de = Main.bundle.getString("de");

        ChoiceDialog<String> choiceDialog = new ChoiceDialog(bs, bs, en, de, fr);
        choiceDialog.setTitle(Main.bundle.getString("language"));
        choiceDialog.setHeaderText(Main.bundle.getString("choiceHeaderText"));
        choiceDialog.setContentText(Main.bundle.getString("language") + ":");
        choiceDialog.showAndWait();

        String izabraniJezik = choiceDialog.getSelectedItem();
        if(izabraniJezik.equals(bs)) {
            Locale.setDefault(new Locale("bs", "BA"));
            reload();
        } else if(izabraniJezik.equals(en)) {
            Locale.setDefault(new Locale("en", "US"));
            reload();
        } else if(izabraniJezik.equals(fr)) {
            Locale.setDefault(new Locale("fr", "FR"));
            reload();
        } else if(izabraniJezik.equals(de)) {
            Locale.setDefault(new Locale("de", "DE"));
            reload();
        }
    }
}
