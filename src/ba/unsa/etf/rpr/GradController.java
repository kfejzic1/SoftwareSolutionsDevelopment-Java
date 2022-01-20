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
        final boolean[] validanPostanskiBroj = {false};
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

        try {
            Integer.parseInt(fldPostanskiBroj.getText());

            //Provjera da li je postanski broj validan u BiH

            URL url = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + fldPostanskiBroj.getText());

            new Thread(() -> {
                try {
                    System.out.println("Provjeravam validnost poštanskog broja...");
                    BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));

                    if(input.readLine().equals("OK")) {
                        fldPostanskiBroj.getStyleClass().removeAll("poljeNijeValidno");
                        fldPostanskiBroj.getStyleClass().add("poljeValidno");
                        validanPostanskiBroj[0] = true;
                    } else {
                        fldPostanskiBroj.getStyleClass().removeAll("poljeValidno");
                        fldPostanskiBroj.getStyleClass().add("poljeNijeValidno");
                    }
                    System.out.println("Provjereno");

                    if(validanPostanskiBroj[0])
                        grad.setPostanskiBroj(Integer.parseInt(fldPostanskiBroj.getText()));

                    input.close();
                } catch (IOException e) {
                    System.out.println("Greška u čitanju");
                }
            }).start();
            Thread.sleep(12000);
        } catch (Exception e) {
            //Postoji znak koji nije broj u stringu
            fldPostanskiBroj.getStyleClass().removeAll("poljeValidno");
            fldPostanskiBroj.getStyleClass().add("poljeNijeValidno");
        }
        if(validanNaziv)
            grad.setNaziv(fieldNaziv.getText());
        if(validanBrojStanovnika)
            grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
        grad.setDrzava(choiceDrzava.getValue());

        if(validanNaziv && validanBrojStanovnika) {   //Polja su validna i vrsi se unos
            Stage stage = (Stage) fieldNaziv.getScene().getWindow();
            stage.close();
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
