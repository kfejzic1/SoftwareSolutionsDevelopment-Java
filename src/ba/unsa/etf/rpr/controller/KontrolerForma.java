package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.model.StudentiModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KontrolerForma {
    @FXML
    private TextField fldText;
    @FXML
    private ChoiceBox<java.lang.String> choiceColor;
    @FXML
    private GridPane gridPane;
    @FXML
    private Slider sliderStudents;
    @FXML
    private ListView<String> lvStudents;

    private StudentiModel studenti = new StudentiModel();

    private static final java.lang.String crvena = "obojiPozadinuUCrveno";
    private static final java.lang.String plava = "obojiPozadinuUPlavo";
    private static final java.lang.String zelena = "obojiPozadinuUZeleno";

    private void obojiUBoju(java.lang.String boja) {
        if(boja.equals("default"))
            gridPane.getChildren().forEach(child -> child.getStyleClass().removeAll(crvena, plava, zelena));
        else
            gridPane.getChildren().forEach(child -> {
                child.getStyleClass().removeAll(crvena, plava, zelena);
                child.getStyleClass().add(boja);
            });
    }

    private void dodajListenerNaChoiceBox() {   //Dodaje Listener na choiceColor
        choiceColor.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            java.lang.String value = choiceColor.getItems().get((Integer) t1);
            switch (value){
                case ("Crvena"):
                    obojiUBoju(crvena);
                    break;
                case ("Plava"):
                    obojiUBoju(plava);
                    break;
                case("Zelena"):
                    obojiUBoju(zelena);
                    break;
                case("Default"):
                    obojiUBoju("default");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + value);
            }
        });
    }

    private void dodajListenerNaSlider() {
        sliderStudents.valueProperty().addListener((observableValue, number, t1) -> {
            StudentiModel temp = new StudentiModel();
            for(int i=0; i<t1.intValue(); i++) {
                if(i>=studenti.getStudenti().size())
                    break;
                temp.getStudenti().add(studenti.getStudenti().get(i));
            }
            lvStudents.setItems(temp.getStudenti());
        });
    }

    @FXML
    public void initialize() {
        studenti.napuni();
        dodajListenerNaSlider();

        //Postavlja početne vrijednosti listViewa na minimalnu vrijednost slidera
        lvStudents.setItems(FXCollections.observableArrayList(studenti.getStudenti().subList(0, (int)sliderStudents.getMin())));

        dodajListenerNaChoiceBox();
    }

    @FXML
    public void getNumber(ActionEvent actionEvent) {
        java.lang.String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        fldText.setText(fldText.getText()+number);
    }

    public void buttonClickUnosStudenta(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novi.fxml"));
        Parent root = loader.load();

        KontrolerUnos kontrolerUnos = loader.getController();
        kontrolerUnos.setListaStudenata(lvStudents);
        kontrolerUnos.setUneseniBroj(fldText);

        stage.setTitle("Unos studenta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.setResizable(false);
    }
}
