package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty ime = new SimpleStringProperty("");
    private SimpleStringProperty prezime = new SimpleStringProperty("");
    private SimpleStringProperty email = new SimpleStringProperty("");
    private SimpleStringProperty korisnickoIme = new SimpleStringProperty("");
    private SimpleStringProperty lozinka = new SimpleStringProperty("");

    public Korisnik(String ime, String prezime, String email, String korisnickoIme, String lozinka) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.email = new SimpleStringProperty(email);
        this.korisnickoIme = new SimpleStringProperty(korisnickoIme);
        this.lozinka = new SimpleStringProperty(lozinka);
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getKorisnickoIme() {
        return korisnickoIme.get();
    }

    public SimpleStringProperty korisnickoImeProperty() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme.set(korisnickoIme);
    }

    public String getLozinka() {
        return lozinka.get();
    }

    @Override
    public String toString() {
        return  ime.get() +
                " " + prezime.get();
    }

    public SimpleStringProperty lozinkaProperty() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka.set(lozinka);
    }
}
