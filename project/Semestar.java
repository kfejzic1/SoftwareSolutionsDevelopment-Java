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
        if(izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je vec dodan u izborne!");
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je obavezan!");
        izborniPredmeti.add(predmet);
    }

    public void dodajObavezniPredmet(Predmet predmet){
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je vec dodan u obavezne!");
        if(izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je izborni!");
        obavezniPredmeti.add(predmet);
    }

    public boolean daLiJePredmetUSemestru(Predmet predmet){
        return obavezniPredmeti.contains(predmet) || izborniPredmeti.contains(predmet);
    }
}
