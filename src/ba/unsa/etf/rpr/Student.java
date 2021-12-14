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

            profesor.setBrojStudenataKojimaPredaje(profesor.getBrojStudenataKojimaPredaje()+1);
            if(!p.daLiJeUpisan()){
                profesor.setNorma(p.getProfesor().getNorma()+p.getBrojCasova());
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

        upisaniPredmeti.add(predmet);
        predmet.getProfesor().setBrojStudenataKojimaPredaje(predmet.getProfesor().getBrojStudenataKojimaPredaje()+1);
        if (!predmet.daLiJeUpisan()) {     //Potrebno je odraditi update norme profesora jer se predmet prvi put upisuje
            predmet.getProfesor().setNorma(predmet.getProfesor().getNorma() + predmet.getBrojCasova());
            predmet.setDaLiJeUpisan(true);
        }
    }

    public String dajPrepisOcjena() {
        String s = "";

        for (Predmet predmet : this.upisaniPredmeti) {
            s += predmet.getNaziv() + " (" + predmet.getEcts() + " ECTS) - " + dajOcjenuIzPredmeta(predmet) + "\n";
        }

        return s;
    }

    public HashSet<Profesor> dajProfesoreKojiPredajuStudentu(){
        HashSet<Profesor> profesori = new HashSet<>();

        for(Predmet p : upisaniPredmeti)
            profesori.add(p.getProfesor());

        return profesori;
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
