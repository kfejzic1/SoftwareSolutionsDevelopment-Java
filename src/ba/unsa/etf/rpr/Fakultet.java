package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public List<Semestar> getSemestri() {
        return semestri;
    }


    public List<Student> getStudenti() {
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
        String temp = "";
        for(Profesor p : profesori){
            temp+=p;
            if(norma)
                temp+=", norma: " + p.getNorma() + "\n";
            else
                temp+= ", broj studenata: " + p.getBrojStudenataKojimaPredaje() + "\n";
        }

        return temp;
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
        String temp = "";

        final int donjaGranica = 120;
        for (Profesor p : profesori) {
            if (p.getNorma() < donjaGranica)
                temp += p + ", norma: " + p.getNorma() + "\n";
        }

        return temp;
    }

    public String dajProfesoreKojiRadePrekoNorme() {
        String temp = "";

        final int gornjaGranica = 150;
        for (Profesor p : profesori) {
            if (p.getNorma() > gornjaGranica)
                temp += p + ", norma: " + p.getNorma() + "\n";
        }

        return temp;
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
