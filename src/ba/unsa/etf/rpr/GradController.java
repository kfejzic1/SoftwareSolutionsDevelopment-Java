package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GradController {
    private ArrayList<Drzava> drzave;
    private Grad grad = null;

    @FXML
    private TextField fieldNaziv;
    @FXML
    private TextField fieldBrojStanovnika;
    @FXML
    private ChoiceBox<Drzava> choiceDrzava;
    @FXML
    private ImageView imgViewSlika;
    @FXML
    private TextField fldPostanskiBroj;

    public GradController(Grad grad, ArrayList<Drzava> spisakDrzava) {
        this.drzave=spisakDrzava;
        this.grad = grad;
    }

    @FXML
    private void initialize() {
        choiceDrzava.setItems(FXCollections.observableArrayList(drzave));
        choiceDrzava.setValue(drzave.get(0));
        if(grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(String.valueOf(grad.getBrojStanovnika()));
            choiceDrzava.getSelectionModel().select(grad.getDrzava());

            if(grad.getPostanskiBroj() != 0)
                fldPostanskiBroj.setText(String.valueOf(grad.getPostanskiBroj()));

            if(!grad.getSlika().isEmpty()){
                File file = new File(grad.getSlika());
                Image img = new Image(file.toURI().toString());
                imgViewSlika.setImage(img);
            }
        }
    }

    public void okAction(ActionEvent actionEvent) {
        boolean validanNaziv = false;
        boolean validanBrojStanovnika = false;
        final boolean[] validanPostanskiBroj = {true};
        if(grad == null)
            grad = new Grad();

        if(fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeValidno");
            fieldNaziv.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeValidno");
            fieldNaziv.getStyleClass().add("poljeValidno");
            validanNaziv = true;
        }

        if(fieldBrojStanovnika.getText().isEmpty() || Integer.parseInt(fieldBrojStanovnika.getText())<=0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeValidno");
            validanBrojStanovnika = true;
        }

        //Obrisati komentar za testiranje posljednjeg zadatka
        //Validacija poštanskog broja se vrši u novom threadu koji je zakomentarisan ispod
        try {
            //Provjera da li je postanski broj validan u BiH
            boolean finalValidanNaziv = validanNaziv;
            boolean finalValidanBrojStanovnika = validanBrojStanovnika;
            new Thread(() -> {
                try {
                    URL url = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + fldPostanskiBroj.getText());

                    //Izbrisati komentar za omogućenu validaciju poštanskog broja putem servisa

                    /*int broj = Integer.parseInt(fldPostanskiBroj.getText());
                    System.out.println("Provjeravam validnost poštanskog broja...");

                    BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream())); //Pokretanje servisa
                    if(input.readLine().equals("OK")) {
                        fldPostanskiBroj.getStyleClass().removeAll("poljeNijeValidno");
                        fldPostanskiBroj.getStyleClass().add("poljeValidno");
                    } else {
                        fldPostanskiBroj.getStyleClass().removeAll("poljeValidno");
                        fldPostanskiBroj.getStyleClass().add("poljeNijeValidno");
                        validanPostanskiBroj[0] = false;
                    }
                    input.close();
                    System.out.println("Provjereno");

                    if(validanPostanskiBroj[0])
                        grad.setPostanskiBroj(broj);
                    */

                    /*
                    P.S.
                        Obrisati komentar koji je postavljen nakon metode start() tj. Thread.sleep(12000).
                        Komentar se mora obrisati (otkomentarisati) kako bi se uspješno izvršila validacija u testovima jer
                        testovi ne čekaju dovoljno na validaciju, a zatvaraju prije prozor prije njenog izvršenja.
                        Zbog toga je postavljena metoda Thread.sleep kako bi forsirala validaciju, ali možda prilikom testiranja blokirala interfejs.
                        Za ručno testiranje, dovoljno je ostaviti metodu Thread.sleep zakomentarisanu i program će raditi kako treba.
                     */

                    if(finalValidanNaziv) {
                        grad.setNaziv(fieldNaziv.getText());
                    }
                    if(finalValidanBrojStanovnika) {
                        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
                    }
                    grad.setDrzava(choiceDrzava.getValue());

                    if(finalValidanNaziv && finalValidanBrojStanovnika) {   //Polja su validna i vrsi se unos
                        Platform.runLater(() -> {
                            Stage stage = (Stage) fieldNaziv.getScene().getWindow();
                            stage.close();
                        });
                    }
                } catch (IOException e) {
                    System.out.println("Greška u čitanju");
                } catch (Exception e) {
                    //Postoji znak koji nije broj u stringu
                    fldPostanskiBroj.getStyleClass().removeAll("poljeValidno");
                    fldPostanskiBroj.getStyleClass().add("poljeNijeValidno");
                }
            }).start();
            //Thread.sleep(12000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public Grad getGrad() {return grad;}

    public void btnChangeAction (ActionEvent actionEvent) throws IOException {
        PretragaController ctrl = new PretragaController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pretraga.fxml"), Main.bundle);
        loader.setController(ctrl);
        Parent root = loader.load();

        Stage stage  = new Stage();
        stage.setTitle(Main.bundle.getString("search"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();

        stage.setOnHiding(windowEvent -> {
            if(ctrl.getPath() != null)
                grad.setSlika(ctrl.getPath());
        }); //Moguce odabrati bilo koji fajl
    }

}
