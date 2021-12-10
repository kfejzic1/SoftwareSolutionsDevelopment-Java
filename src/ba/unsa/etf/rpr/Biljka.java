package ba.unsa.etf.rpr;

import java.util.Objects;

public abstract class Biljka {
    private String naziv;
    private int jacina;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getJacina() {
        return jacina;
    }

    public void setJacina(int jacina) {
        this.jacina = jacina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biljka)) return false;
        Biljka biljka = (Biljka) o;
        return naziv.equals(((Biljka) o).getNaziv());
    }

    @Override
    public int compareTo(Biljka drugaBiljka){
        return this.naziv.compareTo(drugaBiljka.naziv);
    }


}
