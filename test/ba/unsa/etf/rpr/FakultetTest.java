package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FakultetTest {

    Profesor profesor1 = new Profesor("Vedran", "Ljubović");
    Profesor profesor2 = new Profesor("Željko", "Jurić");
    Profesor profesor3 = new Profesor("Novica", "Nosović");
    Profesor profesor4 = new Profesor("Emir", "Buza");
    Profesor profesor5 = new Profesor("Samir", "Ribić");
    ArrayList<Profesor> profesori = new ArrayList<>();

    ArrayList<Predmet> obavezni1 = new ArrayList<>();
    ArrayList<Predmet> obavezni2 = new ArrayList<>();
    ArrayList<Predmet> izborni1 = new ArrayList<>();
    ArrayList<Predmet> izborni2 = new ArrayList<>();


    Student s1 = new Student("Kenan", "Fejzic", "18903");
    Student s2 = new Student("Mujo", "Mujic", "12345");
    ArrayList<Student> studenti = new ArrayList<>();


    Semestar semestar1 = new Semestar(1, obavezni1, izborni1, Ciklusi.Bachelor);
    Semestar semestar2 = new Semestar(2, obavezni2, izborni2, Ciklusi.Bachelor);
    ArrayList<Semestar> semestri = new ArrayList<>();

    @BeforeEach
    void init(){
        profesori.add(profesor1);
        profesori.add(profesor2);
        profesori.add(profesor3);
        profesori.add(profesor4);
        profesori.add(profesor5);

        //Problem dubokih kopija, potrebno je poslati plitku kopiju u arrayListu profesori, ili koristiti listu

        Predmet predmet1 = new Predmet("Razvoj programskih rješenja", 7, 100, profesori.get(0));
        Predmet predmet2 = new Predmet("Diskretna matematika", 7, 100, profesori.get(1));
        Predmet predmet3 = new Predmet("Logički dizajn", 5, 90, profesori.get(2));
        Predmet predmet4 = new Predmet("Osnove baza podataka", 5, 90, profesori.get(3));
        Predmet predmet5 = new Predmet("Sistemsko programiranje", 5, 50, profesori.get(4));
        Predmet predmet6 = new Predmet("Random izborni predmet", 4, 40, profesori.get(4));
        Predmet predmet7 = new Predmet("Razvoj mobilnih aplikacija", 4, 40, profesori.get(3));
        Predmet predmet8 = new Predmet("CAD/CAM inženjering", 5, 80, profesori.get(1));

        obavezni1.add(predmet1);
        obavezni1.add(predmet2);
        obavezni2.add(predmet3);
        obavezni2.add(predmet4);

        izborni1.add(predmet5);
        izborni1.add(predmet6);
        izborni2.add(predmet7);
        izborni2.add(predmet8);

        studenti.add(s1);
        studenti.add(s2);

        semestri.add(semestar1);
        semestri.add(semestar2);
    }

    @Test
    void testDodajProfesoraIStudenta(){
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        assertAll(
                () -> {
                    try{
                        fakultet.dodajStudenta(s1);
                        fail();
                    }catch (IllegalArgumentException e){
                        assertEquals("Student je vec upisan na ovaj fakultet!", e.getMessage());
                    }
                },
                () ->{
                    try{
                        fakultet.dodajProfesora(profesor2);
                        fail();
                    }catch (IllegalArgumentException e){
                        assertEquals("Profesor vec radi na ovom fakultetu!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void izlistajProfesoreKojiNemajuNormu() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0));
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1));

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));

        assertEquals(fakultet.izlistajProfesoreKojiNemajuNormu(),
                """
                        Vedran Ljubović, norma: 100
                        Novica Nosović, norma: 90
                        Samir Ribić, norma: 90
                        """);
    }

    @Test
    void izlistajProfesoreKojiRadePrekoNorme() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0));
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1));

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));

        assertEquals(fakultet.izlistajProfesoreKojiRadePrekoNorme(), "Željko Jurić, norma: 180\n");
    }
}