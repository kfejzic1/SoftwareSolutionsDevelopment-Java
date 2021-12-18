package ba.unsa.etf.rpr;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentiModel {
    private ObservableList<Student> studenti = FXCollections.observableArrayList();
    private ObjectProperty<Student> trenutniStudent = new SimpleObjectProperty<>();

    public ObservableList<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(ObservableList<Student> studenti) {
        this.studenti = studenti;
    }

    public Student getTrenutniStudent() {
        return trenutniStudent.get();
    }

    public ObjectProperty<Student> trenutniStudentProperty() {
        return trenutniStudent;
    }

    public void setTrenutniStudent(Student trenutniStudent) {
        this.trenutniStudent.set(trenutniStudent);
    }
}
