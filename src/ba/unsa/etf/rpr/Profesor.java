package ba.unsa.etf.rpr;

import java.util.Objects;

public class Profesor extends Osoba {
    private Integer norma;
    private Integer brojStudenataKojimaPredaje;

    public Profesor(String ime, String prezime) {
        super(ime, prezime);
        this.norma = 0;
        this.brojStudenataKojimaPredaje = 0;
    }

    public Integer getBrojStudenataKojimaPredaje() {
        return brojStudenataKojimaPredaje;
    }

    public void setBrojStudenataKojimaPredaje(Integer brojStudenataKojimaPredaje) {
        this.brojStudenataKojimaPredaje = brojStudenataKojimaPredaje;
    }

    public Profesor(String ime, String prezime, int norma) {
        super(ime, prezime);
        this.norma = norma;
        this.brojStudenataKojimaPredaje = 0;
    }

    public Integer getNorma() {
        return norma;
    }

    public void setNorma(int norma) {
        this.norma = norma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor profesor)) return false;
        return this.getIme().equals(profesor.getIme()) && this.getPrezime().equals(profesor.getPrezime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getIme(), this.getPrezime());
    }

    @Override
    public String toString() {
        return this.getIme() + " " + this.getPrezime();
    }
}
