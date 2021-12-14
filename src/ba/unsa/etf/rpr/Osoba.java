package ba.unsa.etf.rpr;

public abstract class Osoba {
    private final String ime;
    private final String prezime;

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
