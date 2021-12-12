import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Semestar {
    private int redniBroj;
    private ArrayList<Predmet> obavezniPredmeti;
    private ArrayList<Predmet> izborniPredmeti;
    private ArrayList<Predmet> upisaniIzborniPredmeti;
    private Ciklusi ciklus;

    private void updateNormeProfesora(Predmet predmet){
        //Vrši se nakon upisa semestra
        Profesor profesor = predmet.getProfesor();
        profesor.setNorma(profesor.getNorma()+ predmet.getBrojCasova());
    }

    public Semestar(){
        this.obavezniPredmeti = new ArrayList<>();
        this.izborniPredmeti = new ArrayList<>();
        this.upisaniIzborniPredmeti = new ArrayList<>();
        this.ciklus = Ciklusi.Bachelor;
    }

    public Semestar(int redniBroj, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.ciklus = ciklus;
        this.obavezniPredmeti = new ArrayList<>();
        this.izborniPredmeti = new ArrayList<>();
        this.upisaniIzborniPredmeti = new ArrayList<>();
    }

    public Semestar(int redniBroj, ArrayList<Predmet> obavezniPredmeti, ArrayList<Predmet> izborniPredmeti, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.obavezniPredmeti = obavezniPredmeti;
        this.izborniPredmeti = izborniPredmeti;
        this.ciklus = ciklus;
        this.upisaniIzborniPredmeti = new ArrayList<>();

        for(Predmet p : obavezniPredmeti)
            updateNormeProfesora(p);
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
        if(predmet.daLiJeIzborni())
            throw new IllegalArgumentException("Predmet nije obavezan!");
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet se već nalazi u semestru!");

        this.obavezniPredmeti.add(predmet);
        updateNormeProfesora(predmet);
    }

    public void dodajIzborniPredmet(Predmet predmet){
        if(!predmet.daLiJeIzborni())
            throw new IllegalArgumentException("Predmet nije izborni!");
        if(izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet se već nalazi u semestru!");

        this.izborniPredmeti.add(predmet);
    }

    public boolean daLiJePredmetUSemestru(Predmet predmet){
        return obavezniPredmeti.contains(predmet) || upisaniIzborniPredmeti.contains(predmet);
    }

    public void upisiIzborniPredmet(Predmet predmet){
        //Potrebno je provjeriti da li ce ukupni broj ects poena preci 30
        if(obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet je obavezan!");
        if(!izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet se ne nalazi u semestru!");

        int ukupnoECTS=predmet.getEcts();
        for(Predmet p : obavezniPredmeti)
            ukupnoECTS+=p.getEcts();

        if(ukupnoECTS>30)
           throw new IllegalArgumentException("U semestru nije moguće imati više od 30 ECTS poena!");

        upisaniIzborniPredmeti.add(predmet);
        updateNormeProfesora(predmet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Semestar semestar)) return false;
        return redniBroj == semestar.redniBroj && ciklus == semestar.ciklus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redniBroj, ciklus);
    }
}
