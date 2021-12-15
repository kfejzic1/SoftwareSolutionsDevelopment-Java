package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Comparator;

public class Fakultet {
    private final ArrayList<Profesor> profesori;
    private final ArrayList<Student> studenti;
    private final ArrayList<Semestar> semestri;
    private final String naziv;

    public Fakultet(String naziv, ArrayList<Profesor> profesori, ArrayList<Student> studenti, ArrayList<Semestar> semestri) {
        this.profesori = profesori;
        this.studenti = studenti;
        this.naziv = naziv;
        this.semestri = semestri;
    }

    public Fakultet(String naziv, ArrayList<Profesor> profesori, ArrayList<Semestar> semestri) {
        this.profesori = profesori;
        this.semestri = semestri;
        this.naziv = naziv;
        this.studenti = new ArrayList<>();
    }

    public ArrayList<Semestar> getSemestri() {
        return semestri;
    }


    public ArrayList<Student> getStudenti() {
        return studenti;
    }

    public void dodajProfesora(Profesor profesor) {
        if(profesori.contains(profesor))
            throw new IllegalArgumentException("Profesor vec radi na ovom fakultetu!");

        this.profesori.add(profesor);
    }

    public void dodajStudenta(Student student) {
        if (studenti.contains(student))
            throw new IllegalArgumentException("Student je vec upisan na ovaj fakultet!");

        this.studenti.add(student);
    }

    public void upisiStudentaNaSemestar(Student student, Semestar semestar) {
        student.upisiSemestar(semestar);
    }

    private String ispisiSveProfesore(ArrayList<Profesor> profesori, boolean norma){
        StringBuilder temp = new StringBuilder();
        for(Profesor p : profesori){
            temp.append(p);
            if(norma)
                temp.append(", norma: ").append(p.getNorma()).append('\n');
            else
                temp.append(", broj studenata: ").append(p.getBrojStudenataKojimaPredaje()).append('\n');
        }

        return temp.toString();
    }

    public ArrayList<Profesor> getProfesori() {
        return profesori;
    }

    public String dajProfesoreSortiranePoNormi() {
        ArrayList<Profesor> tempProfesori = getProfesori();

        tempProfesori.sort(Comparator.comparing(Profesor::getNorma));

        return ispisiSveProfesore(tempProfesori, true);
    }

    public String dajProfesoreSortiranePoBrojuStudenata() {
        ArrayList<Profesor> tempProfesori = this.profesori;

        tempProfesori.sort(Comparator.comparing(Profesor::getBrojStudenataKojimaPredaje));

        return ispisiSveProfesore(tempProfesori, false);
    }

    public String dajProfesoreKojiNemajuNormu() {
        StringBuilder temp = new StringBuilder();

        final int donjaGranica = 120;
        for (Profesor p : profesori) {
            if (p.getNorma() < donjaGranica)
                temp.append(p).append(", norma: ").append(p.getNorma()).append('\n');
        }

        return temp.toString();
    }

    public String dajProfesoreKojiRadePrekoNorme() {
        StringBuilder temp = new StringBuilder();

        final int gornjaGranica = 150;
        for (Profesor p : profesori) {
            if (p.getNorma() > gornjaGranica)
                temp.append(p).append(", norma: ").append(p.getNorma()).append('\n');
        }

        return temp.toString();
    }

    public String getNaziv() {
        return naziv;
    }

    public Student dajStudentaSaBrojemIndeksa(String brojIndeksa){
        for(Student s : studenti){
            if(s.getIndeks().equals(brojIndeksa)){
                return s;
            }
        }
        throw new IllegalArgumentException("Ne postoji student sa datim brojem indeksa!");
    }

    public String dajPrepisOcjenaStudentaSaBrojemIndeksa(String brojIndeksa){
        for(Student s : studenti){
            if(s.getIndeks().equals(brojIndeksa)){
                return s.dajPrepisOcjena();
            }
        }
        throw new IllegalArgumentException("Ne postoji student sa datim brojem indeksa!");
    }
}
