package ba.unsa.etf.rpr.t7;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class KorisnikController {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;

    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());
        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            listKorisnici.refresh();
         });

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty() );
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty() );
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty() );
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty() );
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty() );
            }
            if (newKorisnik == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldUsername.setText("");
                fldPassword.setText("");
            }
            else {
                fldIme.textProperty().bindBidirectional( newKorisnik.imeProperty() );
                fldPrezime.textProperty().bindBidirectional( newKorisnik.prezimeProperty() );
                fldEmail.textProperty().bindBidirectional( newKorisnik.emailProperty() );
                fldUsername.textProperty().bindBidirectional( newKorisnik.usernameProperty() );
                fldPassword.textProperty().bindBidirectional( newKorisnik.passwordProperty() );
            }
        });

        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }

            if(!oldIme.isEmpty() && !oldIme.equals(newIme) && model.getTrenutniKorisnik() != null)
                model.getTrenutniKorisnik().setPromijenjen(true);
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }

            if(!oldIme.isEmpty() && !oldIme.equals(newIme) && model.getTrenutniKorisnik() != null)
                model.getTrenutniKorisnik().setPromijenjen(true);
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }

            if(!oldIme.isEmpty() && !oldIme.equals(newIme) && model.getTrenutniKorisnik() != null)
                model.getTrenutniKorisnik().setPromijenjen(true);
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }

            if(!oldIme.isEmpty() && !oldIme.equals(newIme) && model.getTrenutniKorisnik() != null)
                model.getTrenutniKorisnik().setPromijenjen(true);
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");
            }

            if(!oldIme.isEmpty() && !oldIme.equals(newIme) && model.getTrenutniKorisnik() != null)
                model.getTrenutniKorisnik().setPromijenjen(true);
        });
    }

    public void dodajAction(ActionEvent actionEvent) {
        Korisnik noviKorisnik = new Korisnik(fldIme.getText(), fldPrezime.getText(), fldEmail.getText(), fldUsername.getText(), fldPassword.getText());
        model.getKorisnici().add(noviKorisnik);
        model.dodajKorisnika(noviKorisnik);

        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        for(Korisnik k : model.getKorisnici()) {
            if(k.isPromijenjen())
                model.izmijeniKorisnika(k);
        }
        System.exit(0);
    }

    public void obrisiAction(ActionEvent actionEvent) {
        Korisnik trenutni = model.getTrenutniKorisnik();
        model.getKorisnici().remove(trenutni);
        model.obrisiKorisnika(trenutni);
    }

    public void exitMenuItemAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void aboutMeniItemAction(ActionEvent actionEvent) {
        //Potrebno otvoriti novi prozor sa opisom stvari

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O aplikaciji");
        alert.setHeaderText("Verzija: 1.0.0");
        alert.setContentText("Github: https://github.com/RPR-2019/rpr21-zadaca4-kfejzic1"); //Kako postaviti clickable link

        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.showAndWait();

    }
}
