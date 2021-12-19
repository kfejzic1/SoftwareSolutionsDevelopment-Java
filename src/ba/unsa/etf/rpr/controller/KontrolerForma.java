package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.Student;
import ba.unsa.etf.rpr.model.StudentiModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KontrolerForma {
    @FXML
    private TextField fldText;
    @FXML
    private ChoiceBox choiceColor;
    private StudentiModel studenti;
    public ListView<Student> lvStudents;

    public KontrolerForma(StudentiModel studenti) {
        this.studenti = studenti;
    }

    @FXML
    public void initialize() {
        lvStudents.setItems(studenti.getStudenti());
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
