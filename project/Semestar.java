import java.util.ArrayList;

public class Semestar {
    private int redniBroj;
    private ArrayList<Predmet> izborniPredmeti;
    private ArrayList<Predmet> obavezniPredmeti;

    public Semestar(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public ArrayList<Predmet> getIzborniPredmeti() {
        return izborniPredmeti;
    }

    public void setIzborniPredmeti(ArrayList<Predmet> izborniPredmeti) {
        this.izborniPredmeti = izborniPredmeti;
    }

    public ArrayList<Predmet> getObavezniPredmeti() {
        return obavezniPredmeti;
    }

    public void setObavezniPredmeti(ArrayList<Predmet> obavezniPredmeti) {
        this.obavezniPredmeti = obavezniPredmeti;
    }

    public void dodajIzborniPredmet(Predmet predmet){
        //dodaje predmete u izborne
        return;
    }

    public void dodajObavezniPredmet(Predmet predmet){
        //dodaje predmete u obavezne
        return;
    }

    boolean daLiJePredmetUSemestru(Predmet predmet){
        //Daje true ako se predmet nalazi u semestru
        return true;
    }
}
