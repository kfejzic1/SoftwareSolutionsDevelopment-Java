package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

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

            if(!grad.getSlika().isEmpty()){
                File file = new File(grad.getSlika());
                Image img = new Image(file.toURI().toString());
                imgViewSlika.setImage(img);
            }
        }
    }

    public void okAction(ActionEvent actionEvent) {
        if(fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeValidno");
            fieldNaziv.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeValidno");
            fieldNaziv.getStyleClass().add("poljeValidno");
        }

        if(fieldBrojStanovnika.getText().isEmpty() || Integer.parseInt(fieldBrojStanovnika.getText())<=0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeValidno");
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeValidno");
            fieldBrojStanovnika.getStyleClass().add("poljeValidno");
        }

        if(!fieldNaziv.getText().isEmpty() && !fieldBrojStanovnika.getText().isEmpty() && Integer.parseInt(fieldBrojStanovnika.getText())>0) {   //Polje je validno i vrsi se unos
            if(grad == null)
                grad = new Grad();
            grad.setNaziv(fieldNaziv.getText());
            grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
            grad.setDrzava(choiceDrzava.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) fieldNaziv.getScene().getWindow();
            stage.close();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public Grad getGrad() {return grad;}

    public void btnChangeAction (ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.show();
    }
}
