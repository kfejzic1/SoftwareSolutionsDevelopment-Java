public class Student extends Osoba{
    int norma;

    public Student(String ime, String prezime) {
        super(ime, prezime);
        this.norma = 0;
    }

    public Student(String ime, String prezime, int norma) {
        super(ime, prezime);
        this.norma = norma;
    }

    public int getNorma() {
        return norma;
    }

    public void setNorma(int norma) {
        this.norma = norma;
    }
}
