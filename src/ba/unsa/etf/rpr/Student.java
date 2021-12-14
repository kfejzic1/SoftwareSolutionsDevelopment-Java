package ba.unsa.etf.rpr;

import java.util.*;

public class Student extends Osoba {
    private final String indeks;
    private final Map<Predmet, Integer> ocjene;
    private final ArrayList<Predmet> upisaniPredmeti;
    private Semestar upisaniSemestar;

    public ArrayList<Predmet> getUpisaniPredmeti() {
        return upisaniPredmeti;
    }

    public Semestar getUpisaniSemestar() {
        return upisaniSemestar;
    }

    public Student(String ime, String prezime, String brojIndeksa) {
        super(ime, prezime);
        this.indeks = brojIndeksa;
        this.ocjene = new HashMap<>();
        this.upisaniPredmeti = new ArrayList<>();
    }

    public void upisiSemestar(Semestar semestar) {      //Upisuje određeni semestar
        if (!Objects.isNull(this.upisaniSemestar))
            throw new IllegalArgumentException("Student je vec upisan na neki od semestara!");
        this.upisaniSemestar = semestar;

        ArrayList<Predmet> predmeti = (ArrayList<Predmet>) semestar.getObavezniPredmeti();
        for(Predmet p : predmeti){
            Profesor profesor = p.getProfesor();

            profesor.povecajBrojStudenataKojimaPredaje();
            if(!p.daLiJeUpisan()){
                profesor.povecajNormuZa(p.getBrojCasova());
            }
        }

        upisaniPredmeti.addAll(semestar.getObavezniPredmeti());
    }

    public void upisiOcjenuIzPredmeta(Predmet predmet, int ocjena) {
        if (ocjene.containsKey(predmet))
            throw new IllegalArgumentException("Ocjena iz predmeta je vec upisana!");
        if(ocjena<6 || ocjena>10)
            throw new IllegalArgumentException("Ocjena mora biti između 6 i 10");

        ocjene.put(predmet, ocjena);
    }

    public int dajOcjenuIzPredmeta(Predmet predmet) {
        if (!this.upisaniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Student ne pohađa taj predmet!");
        if (Objects.isNull(this.ocjene.get(predmet)))
            return 5;

        return this.ocjene.get(predmet);
    }

    public int dajUkupanBrojECTSPoena() { //Koristiti za upis studenta na fakultet (nije moguce pokrenuti nastavu ako nema 30 ects poena)
        int ects = 0;
        for (Predmet p : upisaniPredmeti)
            ects += p.getEcts();

        return ects;
    }

    public void upisiIzborniPredmet(Predmet predmet) {
        if (!this.upisaniSemestar.getIzborniPredmeti().contains(predmet))
            throw new IllegalArgumentException("Predmet nije izborni u upisanom semestru!");
        if (this.upisaniPredmeti.contains(predmet))
            throw new IllegalArgumentException("Student je već upisan na ovaj predmet!");
        if (dajUkupanBrojECTSPoena() + predmet.getEcts() > 30)
            throw new IllegalArgumentException("Nije moguće imati više od 30 ECTS poena po semestru!");

        boolean predajeProfesor = false;
        for(Predmet p : upisaniPredmeti){   //Provjeravamo da li profesor zeljenog predmeta predaje vec studentu
            if(p.getProfesor().equals(predmet.getProfesor())){
                predajeProfesor = true;
                break;
            }
        }

        if(!predajeProfesor)    //Ako ne predaje, povećati broj studenata kojima taj profesor predaje
            predmet.getProfesor().povecajBrojStudenataKojimaPredaje();

        upisaniPredmeti.add(predmet);

        if (!predmet.daLiJeUpisan()) {     //Potrebno je odraditi update norme profesora jer se predmet prvi put upisuje
            predmet.getProfesor().povecajNormuZa(predmet.getBrojCasova());
            predmet.setJesteUpisan(true);
        }
    }

    public String dajPrepisOcjena() {
        StringBuilder s = new StringBuilder();

        for (Predmet predmet : this.upisaniPredmeti) {
            s.append(predmet.getNaziv()).append(" (").append(predmet.getEcts()).append(" ECTS) - ").append(dajOcjenuIzPredmeta(predmet)).append('\n');
        }

        return s.toString();
    }

    public Map<Predmet, Integer> getOcjene() {
        return ocjene;
    }

    public String getIndeks() {
        return indeks;
    }

    @Override
    public String toString() {
        return this.getIme() + " " + this.getPrezime() + "\n\tBroj indeksa: " + this.indeks;
    }
}
