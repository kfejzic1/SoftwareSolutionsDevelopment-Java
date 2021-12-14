package ba.unsa.etf.rpr;

import java.util.Objects;

public class Predmet {
    private final String naziv;
    private final int ects;
    private final int brojCasova;
    private boolean jesteUpisan;
    private final Profesor profesor;

    public boolean daLiJeUpisan() {
        return jesteUpisan;
    }

    public void setJesteUpisan(boolean jesteUpisan) {
        this.jesteUpisan = jesteUpisan;
    }

    public Predmet(String naziv, int ects, int brojCasova, Profesor profesor) {
        this.naziv = naziv;
        this.ects = ects;
        this.brojCasova = brojCasova;
        this.profesor = profesor;
        this.jesteUpisan = false;
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

    public int getEcts() {
        return ects;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return naziv + ", " + ects + " ECTS, " + brojCasova + " časova.\n\tProfesor: " + profesor;
    }
}
