import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Fakultet {
    private ArrayList<Profesor> profesori;
    private ArrayList<Student> studenti;
    private String naziv;

    public Fakultet(String naziv) {
        this.naziv = naziv;
        this.profesori = new ArrayList<>();
        this.studenti = new ArrayList<>();
    }

    public Fakultet(ArrayList<Profesor> profesori, ArrayList<Student> studenti, String naziv) {
        this.profesori = profesori;
        this.studenti = studenti;
        this.naziv = naziv;
    }

    public ArrayList<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(ArrayList<Profesor> profesori) {
        this.profesori = profesori;
    }

    public ArrayList<Student> getStudenti() {
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

    public Set<Semestar> dajUpisaneSemestre(){
        Set<Semestar> semestri = new HashSet<>();

        for(Student s : studenti){
            semestri.add(s.getIndeks().getUpisaniSemestar());
        }

        return semestri;
    }

    public String izlistajProfesoreKojiNemajuNormu(){
        String temp = "";
        for(Profesor p : profesori){
            if(p.getNorma()<120)
                temp += p.toString();
        }

        return temp;
    }

    public String izlistajProfesoreKojiRadePrekoNorme(){
        String temp = "";
        for(Profesor p : profesori){
            if(p.getNorma()>150)
                temp += p.toString();
        }

        return temp;
    }
}
