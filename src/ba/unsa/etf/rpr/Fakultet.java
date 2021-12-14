package ba.unsa.etf.rpr;

import java.util.ArrayList;
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

    public void upisiStudentaNaSemestar(Student student, Semestar semestar) throws IllegalArgumentException {
        student.upisiSemestar(semestar);
    }

    public String izlistajProfesoreKojiNemajuNormu() {
        String temp = "";

        for (Profesor p : profesori) {
            if (p.getNorma() < 120)
                temp += p + "\n";
        }

        return temp;
    }

    public String izlistajProfesoreKojiRadePrekoNorme() {
        String temp = "";
        for (Profesor p : profesori) {
            if (p.getNorma() > 150)
                temp += p + "\n";
        }

        return temp;
    }

    public String getNaziv() {
        return naziv;
    }

    public int dajBrojStudenataKojimaPredajeProfesor(Profesor profesor){
        int i = 0;

        for(Student student : studenti){
            if(student.dajProfesoreKojiPredajuStudentu().contains(profesor))
                i++;
        }

        return i;
    }
}
