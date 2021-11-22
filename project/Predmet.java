public class Predmet {
    private String naziv;
    private int ects;
    private boolean daLiJeIzborni;

    public Predmet(String naziv, int ects, boolean daLiJeIzborni) {
        this.naziv = naziv;
        this.ects = ects;
        this.daLiJeIzborni = daLiJeIzborni;
    }

    public String getNaziv() {
        return naziv;
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

    public boolean isDaLiJeIzborni() {
        return daLiJeIzborni;
    }

    public void setDaLiJeIzborni(boolean daLiJeIzborni) {
        this.daLiJeIzborni = daLiJeIzborni;
    }
}
