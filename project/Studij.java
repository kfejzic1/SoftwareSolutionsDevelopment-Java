import java.util.HashMap;
import java.util.Map;

public class Studij {
    private HashMap<Student, Semestar> upisaniStudenti;

    public Studij() {
        this.upisaniStudenti = new HashMap<>();
    }

    public void upisiStudentaNaSemestar(Student student, Semestar semestar){
        if(upisaniStudenti.containsKey(student))
            throw new IllegalArgumentException("Student je vec upisan!");
        upisaniStudenti.put(student, semestar);
    }

    public String dajStudenteNaPredmetu(Predmet predmet){
        //Ispisuje studente na predmetu
        //Pristupa predmetu preko value hashmape, zatim uzima predmet iz semestra
        String s = "Studenti koji su upisani na predmet " + predmet.getNaziv() + ":\n";

        for(Map.Entry<Student, Semestar> entry : upisaniStudenti.entrySet()){
            if(entry.getValue().daLiJePredmetUSemestru(predmet)){
                s = s + entry.getKey().getIme() + " " + entry.getKey().getPrezime() + "\n";
            }
        }
        return s;
    }
}
