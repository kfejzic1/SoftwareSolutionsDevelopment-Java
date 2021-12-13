package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Fakultet {
    private List<Profesor> profesori;
    private List<Student> studenti;
    private List<Semestar> semestri;
    private String naziv;

    public Fakultet(String naziv) {
        this.naziv = naziv;
        this.profesori = new ArrayList<>();
        this.studenti = new ArrayList<>();
        this.semestri = new ArrayList<>();
    }

    public Fakultet(String naziv, List<Profesor> profesori, List<Student> studenti, List<Semestar> semestri) {
        this.profesori = profesori;
        this.studenti = studenti;
        this.naziv = naziv;
        this.semestri = semestri;
    }

    public List<Profesor> getProfesori() {
        return this.profesori;
    }

    public List<Semestar> getSemestri() {
        return semestri;
    }

    public void setSemestri(ArrayList<Semestar> semestri) {
        this.semestri = semestri;
    }

    public void setProfesori(ArrayList<Profesor> profesori) {
        this.profesori = profesori;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<Student> studenti) {
        this.studenti = studenti;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void dodajProfesora(Profesor profesor) {
        this.profesori.add(profesor);
    }

    public void dodajStudenta(Student student) {
        if (studenti.contains(student))
            throw new IllegalArgumentException("ba.unsa.etf.rpr.Student je vec upisan na ovaj fakultet!");

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
}
