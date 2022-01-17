package ba.unsa.etf.rpr;

public class Drzava {
    int id;
    String naziv;
    Grad glavniGrad;

    @Override
    public String toString() {
        return naziv;
    }

    public Drzava() {
        this.glavniGrad = null;
        this.naziv = "";
    }

    public Drzava(int id, String naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
