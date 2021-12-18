package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.beans.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentiModel {
    private ObservableList<Student> studenti = FXCollections.observableArrayList();

    public ObservableList<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(ObservableList<Student> studenti) {
        this.studenti = studenti;
    }

    public void napuni() {
        studenti.add(new Student("Kenan Fejzić"));
        studenti.add(new Student("Suljo Suljić"));
        studenti.add(new Student("Hasan Hasanović"));
        studenti.add(new Student("Mujo Mujić"));
        studenti.add(new Student("Neko Nekić"));
    }
}
