import java.util.ArrayList;

public class Semestar {
    private int redniBroj;
    private ArrayList<Predmet> obavezniPredmeti;
    private ArrayList<Predmet> izborniPredmeti;
    private ArrayList<Predmet> upisaniIzborniPredmeti;
    private Ciklusi ciklus;

    public Semestar(){
        this.obavezniPredmeti = new ArrayList<>();
        this.izborniPredmeti = new ArrayList<>();
        this.upisaniIzborniPredmeti = new ArrayList<>();
    }
    public Semestar(int redniBroj, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.ciklus = ciklus;
        this.obavezniPredmeti = null;
        this.izborniPredmeti = null;
        this.upisaniIzborniPredmeti = null;
    }

    public Semestar(int redniBroj, ArrayList<Predmet> obavezniPredmeti, ArrayList<Predmet> izborniPredmeti, ArrayList<Predmet> upisaniIzborniPredmeti, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.obavezniPredmeti = obavezniPredmeti;
        this.izborniPredmeti = izborniPredmeti;
        this.upisaniIzborniPredmeti = upisaniIzborniPredmeti;
        this.ciklus = ciklus;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public ArrayList<Predmet> getObavezniPredmeti() {
        return obavezniPredmeti;
    }

    public void setObavezniPredmeti(ArrayList<Predmet> obavezniPredmeti) {
        this.obavezniPredmeti = obavezniPredmeti;
    }

    public ArrayList<Predmet> getIzborniPredmeti() {
        return izborniPredmeti;
    }

    public void setIzborniPredmeti(ArrayList<Predmet> izborniPredmeti) {
        this.izborniPredmeti = izborniPredmeti;
    }

    public ArrayList<Predmet> getUpisaniIzborniPredmeti() {
        return upisaniIzborniPredmeti;
    }

    public void setUpisaniIzborniPredmeti(ArrayList<Predmet> upisaniIzborniPredmeti) {
        this.upisaniIzborniPredmeti = upisaniIzborniPredmeti;
    }

    public Ciklusi getCiklus() {
        return ciklus;
    }

    public void setCiklus(Ciklusi ciklus) {
        this.ciklus = ciklus;
    }

    public void dodajObavezniPredmet(Predmet predmet){
        this.obavezniPredmeti.add(predmet);
    }

    public void dodajIzborniPredmet(Predmet predmet){
        this.obavezniPredmeti.add(predmet);
    }

    public boolean daLiJePredmetUSemestru(Predmet predmet){
        return obavezniPredmeti.contains(predmet) || izborniPredmeti.contains(predmet);
    }

    public void upisiIzborniPredmet(Predmet predmet){
        //Potrebno je provjeriti da li ce ukupni broj ects poena preci 30
        if(daLiJePredmetUSemestru(predmet))
            throw new IllegalArgumentException("Predmet nije u semestru!");

        int ukupnoECTS=predmet.getEcts();
        for(Predmet p : obavezniPredmeti)
            ukupnoECTS+=p.getEcts();

        if(ukupnoECTS>30)
            throw new IllegalArgumentException("U semestru nije moguće imati više od 30 ECTS poena!");

        upisaniIzborniPredmeti.add(predmet);
    }
}
