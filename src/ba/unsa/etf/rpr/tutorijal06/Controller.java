package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Controller {
    private KorisniciModel model = new KorisniciModel();
    public ListView<Korisnik> listaKorisnika;

    @FXML
    Button btnKraj;
    @FXML
    TextField txtIme;
    @FXML
    TextField txtPrezime;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtKorisnickoIme;
    @FXML
    TextField txtLozinka;

    @FXML
    public void initialize() {
        System.out.println("Trenutni je: " + model.getTrenutniKorisnik());

        model.napuni();
        listaKorisnika.setItems(model.getKorisnici());

        model.trenutniKorisnikProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    if(oldValue != null) {
                        txtIme.textProperty().unbindBidirectional(oldValue.imeProperty());
                        txtPrezime.textProperty().unbindBidirectional(oldValue.prezimeProperty());
                        txtEmail.textProperty().unbindBidirectional(oldValue.emailProperty());
                        txtKorisnickoIme.textProperty().unbindBidirectional(oldValue.korisnickoImeProperty());
                        txtLozinka.textProperty().unbindBidirectional(oldValue.lozinkaProperty());
                    }
                    if(oldValue == null) {
                        txtIme.setText("");
                        txtPrezime.setText("");
                        txtEmail.setText("");
                        txtKorisnickoIme.setText("");
                        txtLozinka.setText("");
                    }
                    else {
                        txtIme.textProperty().bindBidirectional(newValue.imeProperty());
                        txtPrezime.textProperty().bindBidirectional(newValue.prezimeProperty());
                        txtEmail.textProperty().bindBidirectional(newValue.emailProperty());
                        txtKorisnickoIme.textProperty().bindBidirectional(newValue.korisnickoImeProperty());
                        txtLozinka.textProperty().bindBidirectional(newValue.lozinkaProperty());
                    }
                })
        );

        listaKorisnika.getSelectionModel().selectedItemProperty().addListener(((observableValue, newKorisnik, oldKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            listaKorisnika.refresh();
        }));
    }

    @FXML
    public void klikNaKorisnika(javafx.scene.input.MouseEvent mouseEvent) {
        model.setTrenutniKorisnik(listaKorisnika.getSelectionModel().getSelectedItem());
    }

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
