package ba.unsa.etf.rpr.beans;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleStringProperty imeIPrezime;

    public Student(String imeIPrezime) {
        if(imeIPrezime.length()<10)
            throw new IllegalArgumentException("Ime studenta treba biti najmanje 10 karaktera dugačko");
        this.imeIPrezime = new SimpleStringProperty(imeIPrezime);
    }

    public String getImeIPrezime() {
        return imeIPrezime.get();
    }

    public SimpleStringProperty imeIPrezimeProperty() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        if(imeIPrezime.length()<10)
            throw new IllegalArgumentException("Ime studenta treba biti najmanje 10 karaktera dugačko");
        this.imeIPrezime.set(imeIPrezime);
    }

    @Override
    public String toString() {
        return imeIPrezime.get();
    }
}
