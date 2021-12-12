import java.util.Objects;

public class Predmet {
    private String naziv;
    private int ects, brojCasova;
    private boolean daLiJeIzborni;
    private Profesor profesor;

    public Predmet(String naziv, int ects, int brojCasova, boolean daLiJeIzborni, Profesor profesor) {
        this.naziv = naziv;
        this.ects = ects;
        this.brojCasova = brojCasova;
        this.daLiJeIzborni = daLiJeIzborni;
        this.profesor = profesor;
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

    public boolean daLiJeIzborni() {
        return daLiJeIzborni;
    }

    public void setDaLiJeIzborni(boolean daLiJeIzborni) {
        this.daLiJeIzborni = daLiJeIzborni;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
