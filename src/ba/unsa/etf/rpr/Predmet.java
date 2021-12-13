package ba.unsa.etf.rpr;

import java.util.Objects;

public class Predmet {
    private String naziv;
    private int ects, brojCasova;
    private boolean daLiJeUpisan;
    private Profesor profesor;

    public boolean daLiJeUpisan() {
        return daLiJeUpisan;
    }

    public void setDaLiJeUpisan(boolean daLiJeUpisan) {
        this.daLiJeUpisan = daLiJeUpisan;
    }

    public Predmet(String naziv, int ects, int brojCasova, Profesor profesor) {
        this.naziv = naziv;
        this.ects = ects;
        this.brojCasova = brojCasova;
        this.profesor = profesor;
        this.daLiJeUpisan = false;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predmet predmet)) return false;
        return naziv.equals(predmet.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
