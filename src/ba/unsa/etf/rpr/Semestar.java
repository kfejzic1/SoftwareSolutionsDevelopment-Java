package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Semestar {
    private int redniBroj;
    private List<Predmet> obavezniPredmeti;
    private List<Predmet> izborniPredmeti;
    private Ciklusi ciklus;

    private void updateNormeProfesora(Predmet predmet) {     //Povecava normu profesora za zadani predmet jednom
        if (!predmet.daLiJeUpisan()) {
            Profesor profesor = predmet.getProfesor();
            profesor.setNorma(profesor.getNorma() + predmet.getBrojCasova());
            predmet.setDaLiJeUpisan(true);
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

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public List<Predmet> getObavezniPredmeti() {
        return obavezniPredmeti;
    }

    public void setObavezniPredmeti(ArrayList<Predmet> obavezniPredmeti) {
        this.obavezniPredmeti = obavezniPredmeti;
    }

    public List<Predmet> getIzborniPredmeti() {
        return izborniPredmeti;
    }

    public void setIzborniPredmeti(ArrayList<Predmet> izborniPredmeti) {
        this.izborniPredmeti = izborniPredmeti;
    }

    public Ciklusi getCiklus() {
        return ciklus;
    }

    public void setCiklus(Ciklusi ciklus) {
        this.ciklus = ciklus;
    }

    public void dodajObavezniPredmet(Predmet predmet) {
        if (obavezniPredmeti.contains(predmet) || izborniPredmeti.contains(predmet))
            throw new IllegalArgumentException("ba.unsa.etf.rpr.Predmet se već nalazi u semestru!");

        this.obavezniPredmeti.add(predmet);
        updateNormeProfesora(predmet);
    }

    public void dodajIzborniPredmet(Predmet predmet) {
        if (izborniPredmeti.contains(predmet) || obavezniPredmeti.contains(predmet))
            throw new IllegalArgumentException("ba.unsa.etf.rpr.Predmet se već nalazi u semestru!");

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
}
