package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.String;
import ba.unsa.etf.rpr.model.StudentiModel;
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

    private StudentiModel studenti;
    private final java.lang.String crvena = "obojiPozadinuUCrveno";
    private final java.lang.String plava = "obojiPozadinuUPlavo";
    private final java.lang.String zelena = "obojiPozadinuUZeleno";


    //public KontrolerForma(StudentiModel studenti) {
//        this.studenti = studenti;
//    }

    /*
    -----------
    -----------
    -----------
    KADA SE OBRISE CONTROLLER IZ FXML-A NIJE MOGUCE POKRENUTI TESTOVE!
    DA LI U NAZIVU STUDENTA "STUDENTX", BROJ X PREDSTAVLJA REDNI BROJ U LISTI
    ILI JE TO BROJ KOJI JE UNESEN SA BUTTONA?
    STUDENTI SE MOGU ČUVATI U MAPI GDJE JE KEY STUDENT A VALUE BROJ KOJI JE UNESEN
    PITATI TUTORA
    -----------
    -----------
    -----------
     */

    @FXML
    public void initialize() {
//        lvStudents.setItems(studenti.getStudenti());

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

    private void obojiUBoju(java.lang.String boja) {
        if(boja.equals("default"))
            gridPane.getChildren().forEach(child -> {
                child.getStyleClass().removeAll("obojiPozadinuUCrveno", "obojiPozadinuUPlavo", "obojiPozadinuUZeleno");
            });
        else
            gridPane.getChildren().forEach(child -> {
                child.getStyleClass().removeAll("obojiPozadinuUCrveno", "obojiPozadinuUPlavo", "obojiPozadinuUZeleno");
                child.getStyleClass().add(boja);
            });
    }

    @FXML
    public void getNumber(ActionEvent actionEvent) {
        java.lang.String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        fldText.setText(fldText.getText()+number);
    }

    public void buttonClickUnosStudenta(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novi.fxml"));
        //loader.setController(new KontrolerUnos());
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
