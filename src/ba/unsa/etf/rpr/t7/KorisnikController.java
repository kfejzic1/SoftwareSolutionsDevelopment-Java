package ba.unsa.etf.rpr.t7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class KorisnikController {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public ImageView imgViewSlike;

    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        File file = new File("resources/img/face-smile.png");
        Image image = new Image(file.toURI().toString());
        imgViewSlike.setImage(image);

        listKorisnici.setItems(model.getKorisnici());

        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);

            String imageUrl = model.getTrenutniKorisnik().getSlika();
            if(imageUrl.equals("resources/img/face-smile.png")) {
                File file1 = new File(imageUrl);
                Image image1 = new Image(file1.toURI().toString());
                imgViewSlike.setImage(image1);
            }
            else {
                Image image1 = new Image(imageUrl);
                imgViewSlike.setImage(image1);
            }
            imgViewSlike.maxWidth(128);
            imgViewSlike.minHeight(128);
            imgViewSlike.setFitHeight(128);
            imgViewSlike.setFitWidth(128);

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
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }

    public void dodajAction(ActionEvent actionEvent) {
        model.dodajKorisnika();
        Korisnik noviKorisnik = new Korisnik("", "", "", "", "");
        model.getKorisnici().add(noviKorisnik);
        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void obrisiAction(ActionEvent actionEvent) {
        model.obrisiKorisnika();
        model.getKorisnici().remove(model.getTrenutniKorisnik());
        listKorisnici.refresh();
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

    public void saveMeniItemAction (ActionEvent actionEvent) {
        FileChooser izbornik = new FileChooser();
        izbornik.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tekstualna datoteka: ", "*.txt"));

        File file = izbornik.showSaveDialog(fldIme.getScene().getWindow());

        if(file!=null) {
            model.zapisiDatoteku(file);
        }
    }

    public void printMeniItemAction (ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(model.getConn());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void reload() {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Scene scene  = fldIme.getScene();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/korisnici.fxml"), bundle);
        loader.setController(this);

        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bosanskiMeniItemAction (ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        reload();
    }

    public void engleskiMeniItemAction (ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en", "US"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        reload();
    }

    public void btnSlikaAction (ActionEvent actionEvent) {
        System.out.println("Kliknuto!");
            try {
                SlikeController ctrl = new SlikeController(model);
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/slike.fxml" ), bundle);
                loader.setController(ctrl);
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Pretraga slika");
                stage.setScene(new Scene(root));
                stage.show();
                stage.toFront();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
