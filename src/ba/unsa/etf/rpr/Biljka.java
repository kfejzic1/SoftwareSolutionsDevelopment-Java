package ba.unsa.etf.rpr;

import java.util.Objects;

public abstract class Biljka {
    private String naziv;
    private int jacina;

    private boolean jacinaJeNegativna(int jacina){
        return jacina < 0;
    }

    public Biljka(String naziv, int jacina) {
        if(jacinaJeNegativna(jacina))
            throw new IllegalArgumentException("Jacina mora biti nenegativan cijeli broj");
        this.naziv = naziv;
        this.jacina = jacina;
    }

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
        if(jacinaJeNegativna(jacina))
            throw new IllegalArgumentException("Jacina mora biti nenegativan cijeli broj");
        this.jacina = jacina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biljka biljka)) return false;
        return naziv.equals(((Biljka) o).getNaziv());
    }

    /*@Override
    public int compareTo(Biljka drugaBiljka){
        return this.naziv.compareTo(drugaBiljka.naziv);
    }*/
}
