public class Student extends Osoba{
    Indeks indeks;

    public Student(String ime, String prezime) {
        super(ime, prezime);
        this.indeks = null;
    }

    public Student(String ime, String prezime, Indeks indeks){
        super(ime, prezime);
        this.indeks = indeks;
    }

    public Indeks getIndeks(){
        return this.indeks;
    }

    public void setIndeks(Indeks indeks){
        this.indeks = indeks;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime + "\nBroj indeksa: " + this.indeks.getBrojIndeksa();
    }
}
