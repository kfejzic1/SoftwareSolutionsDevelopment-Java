package ba.unsa.etf.rpr;

public class Grad {
    private int id, brojStanovnika;
    private String naziv;
    private Drzava drzava;

    public Grad(int id, String naziv, int brojStanovnika) {
        this.id = id;
        this.brojStanovnika = brojStanovnika;
        this.naziv = naziv;
        this.drzava = new Drzava(); //Kako izvesti dodavanje drzave u grad i obrnutoK
    }

    public Grad() {
        this.naziv = "";
        this.drzava = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
