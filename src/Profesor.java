import java.util.Objects;

public class Profesor extends Osoba {
    int norma;

    public Profesor(String ime, String prezime) {
        super(ime, prezime);
        this.norma = 0;
    }

    public Profesor(String ime, String prezime, int norma) {
        super(ime, prezime);
        this.norma = norma;
    }

    public int getNorma() {
        return norma;
    }

    public void setNorma(int norma) {
        this.norma = norma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor profesor)) return false;
        return this.ime.equals(profesor.getIme()) && this.prezime.equals(profesor.getPrezime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ime, this.prezime);
    }

    @Override
    public String toString() {
        return super.toString() + ", norma: " + this.norma;
    }
}
