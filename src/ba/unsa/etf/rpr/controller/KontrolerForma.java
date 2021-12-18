package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.beans.Student;
import ba.unsa.etf.rpr.model.StudentiModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class KontrolerForma {
    @FXML
    private TextField fldText;
    private StudentiModel studenti;
    public ListView<Student> lvStudents;

//    public KontrolerForma(StudentiModel studenti) {
//        this.studenti = studenti;
//    }

//    @FXML
//    public void initialize() {
//        lvStudents.setItems(studenti.getStudenti());
//    }

    @FXML
    public void getNumber(ActionEvent actionEvent) {
        String number = ((Button)actionEvent.getSource()).getText(); //Uzima broj kao text buttona

        fldText.setText(fldText.getText()+number);
    }

}
