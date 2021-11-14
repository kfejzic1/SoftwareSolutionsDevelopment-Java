package ba.unsa.etf.rpr.tutorijal5;

public class Korisnik extends Osoba {
    private Racun racun;

    public Korisnik(String ime, String prezime) {
        super(ime, prezime);
    }

    public Racun getRacun() {
        return racun;
    }

    public boolean dodajRacun(Racun r) { racun = r; return true; }
}
