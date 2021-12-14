package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Semestar {
    private final int redniBroj;
    private final List<Predmet> obavezniPredmeti;
    private final List<Predmet> izborniPredmeti;
    private final Ciklusi ciklus;

    private void updateNormeProfesora(Predmet predmet) {     //Povecava normu profesora za zadani predmet jednom
        if (!predmet.daLiJeUpisan()) {
            predmet.getProfesor().povecajNormuZa(predmet.getBrojCasova());
            predmet.setJesteUpisan(true);
        }
    }


    public Semestar(int redniBroj, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.ciklus = ciklus;
        this.obavezniPredmeti = new ArrayList<>();
        this.izborniPredmeti = new ArrayList<>();
    }

    public Semestar(int redniBroj, List<Predmet> obavezniPredmeti, List<Predmet> izborniPredmeti, Ciklusi ciklus) {
        this.redniBroj = redniBroj;
        this.obavezniPredmeti = obavezniPredmeti;
        this.izborniPredmeti = izborniPredmeti;
        this.ciklus = ciklus;

        for (Predmet p : this.obavezniPredmeti)
            updateNormeProfesora(p);
    }

    public List<Predmet> getObavezniPredmeti() {
        return obavezniPredmeti;
    }

    public List<Predmet> getIzborniPredmeti() {
        return izborniPredmeti;
    }

    public void dodajObavezniPredmet(Predmet predmet) {
        if (obavezniPredmeti.contains(predmet) || izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet se već nalazi u semestru!");

        this.obavezniPredmeti.add(predmet);
        updateNormeProfesora(predmet);
    }

    public void dodajIzborniPredmet(Predmet predmet) {
        if (izborniPredmeti.contains(predmet) || obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Predmet se već nalazi u semestru!");

        this.izborniPredmeti.add(predmet);
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

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder(redniBroj + ". semestar:\nObavezni predmeti:\n");
        for(Predmet p : obavezniPredmeti)
            temp.append(p).append('\n');

        if(izborniPredmeti.isEmpty()){
            temp.append("U ovom semestru nema izbornih predmeta.\n");
            return temp.toString();
        }

        temp.append("Izborni predmeti:\n");
        for(Predmet p : izborniPredmeti)
            temp.append(p).append('\n');

        return temp.toString();
    }
}
