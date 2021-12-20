package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void napuni() {
        korisnici.add(new Korisnik("Kenan", "Fejzic", "kfejzi1@etf.unsa.ba", "kfejzic1", "12345"));
        korisnici.add(new Korisnik("Neko", "Nekic", "nekonekic@gmail.com", "nekoNekic", "123"));
    }
}
