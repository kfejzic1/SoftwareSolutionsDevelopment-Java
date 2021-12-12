import java.util.*;

public class Indeks {
    private String brojIndeksa;
    private Semestar upisaniSemestar;
    private Map<Predmet, Integer> ocjene;

    public Indeks(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
        this.upisaniSemestar = new Semestar();
        this.ocjene = new HashMap<>();
    }

    public Indeks(String brojIndeksa, Semestar upisaniSemestar) {
        this.brojIndeksa = brojIndeksa;
        this.upisaniSemestar = upisaniSemestar;
        this.ocjene = new HashMap<>();
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public Semestar getUpisaniSemestar() {
        return upisaniSemestar;
    }

    public void upisiSemestar(Semestar upisaniSemestar) {
        if(!Objects.isNull(this.upisaniSemestar))
            throw new IllegalArgumentException("Student je vec upisan na " + this.upisaniSemestar.getRedniBroj() + ". semestar, " + this.upisaniSemestar.getCiklus() + " ciklusa!");
        this.upisaniSemestar = upisaniSemestar;
    }

    public void upisiOcjenuIzPredmeta(Predmet predmet, int ocjena){
        ocjene.put(predmet, ocjena);
    }

    public int dajOcjenuIzPredmeta(Predmet predmet){
        if(!this.upisaniSemestar.daLiJePredmetUSemestru(predmet))
            throw new IllegalArgumentException("Predmet se ne nalazi u semestru!");
        if(Objects.isNull(this.ocjene.get(predmet)))
            return 5;

        return this.ocjene.get(predmet);
    }

    public String dajPrepisOcjena(){
        String s = "";

        for(Map.Entry<Predmet, Integer> entry : ocjene.entrySet()){
            Predmet p = entry.getKey();
            s += p.getNaziv() + " (" + p.getEcts() + " ECTS) - " + entry.getValue() + "\n";
        }
        return s;
    }
}
