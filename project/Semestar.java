import java.util.ArrayList;

public class Semestar {
    private int redniBroj;
    private ArrayList<Predmet> izborniPredmeti;
    private ArrayList<Predmet> obavezniPredmeti;

    public Semestar(int redniBroj) {
        this.redniBroj = redniBroj;
        this.izborniPredmeti = new ArrayList<>();
        this.obavezniPredmeti = new ArrayList<>();
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

    private int ukupanBrojECTS(){
        int ukupno = 0;
        for(Predmet p : izborniPredmeti){
            ukupno = ukupno + p.getEcts();
        }
        for(Predmet p : obavezniPredmeti){
            ukupno = ukupno + p.getEcts();
        }
        return ukupno;
    }

    public void dodajIzborniPredmet(Predmet predmet){
        if(izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je vec dodan u izborne!");
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je obavezan!");
        if(ukupanBrojECTS() + predmet.getEcts() > 30)
            throw new IllegalArgumentException("Broj ECTS poena u semestru ne moze biti veci od 30!");
        izborniPredmeti.add(predmet);
    }

    public void dodajObavezniPredmet(Predmet predmet){
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je vec dodan u obavezne!");
        if(izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je izborni!");
        if(ukupanBrojECTS() + predmet.getEcts() > 30)
            throw new IllegalArgumentException("Broj ECTS poena u semestru ne moze biti veci od 30!");
        obavezniPredmeti.add(predmet);
    }

    public boolean daLiJePredmetUSemestru(Predmet predmet){
        return obavezniPredmeti.contains(predmet) || izborniPredmeti.contains(predmet);
    }
}
