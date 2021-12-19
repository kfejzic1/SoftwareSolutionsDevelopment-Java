package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.Student;
import ba.unsa.etf.rpr.model.StudentiModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KontrolerForma {
    @FXML
    private TextField fldText;
    @FXML
    private ChoiceBox<String> choiceColor;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btn1;
    public ListView<Student> lvStudents;
    private StudentiModel studenti;
    private final String crvena = "obojiPozadinuUCrveno";
    private final String plava = "obojiPozadinuUPlavo";
    private final String zelena = "obojiPozadinuUZeleno";


    public KontrolerForma(StudentiModel studenti) {
        this.studenti = studenti;
    }

    @FXML
    public void initialize() {
        lvStudents.setItems(studenti.getStudenti());

        choiceColor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String value = choiceColor.getItems().get((Integer) t1);
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
            }
        });
    }

    private void obojiUBoju(String boja) {
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
        String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        fldText.setText(fldText.getText()+number);
    }

    public void buttonClickUnosStudenta(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novi.fxml"));
        Parent root = loader.load();

        KontrolerUnos kontrolerUnos = loader.getController();
        kontrolerUnos.setListaStudenata(lvStudents);

        stage.setTitle("Unos studenta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.setResizable(false);

    }
}
