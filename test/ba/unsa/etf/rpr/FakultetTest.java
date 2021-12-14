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
    Student s3 = new Student("Suljo", "Suljic", "19000");
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
        studenti.add(s3);

        semestri.add(semestar1);
        semestri.add(semestar2);
    }

    @Test
    void testDodajProfesora(){
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);
        try{
            fakultet.dodajProfesora(profesor2);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Profesor vec radi na ovom fakultetu!", e.getMessage());
        }
    }

    @Test
    void testDodajStudenta() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);
        try{
            fakultet.dodajStudenta(s1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Student je vec upisan na ovaj fakultet!", e.getMessage());
        }
    }

    @Test
    void dajProfesoreKojiNemajuNormu() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0));
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1));

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));

        assertEquals(fakultet.dajProfesoreKojiNemajuNormu(),
                """
                        Vedran Ljubović, norma: 100
                        Novica Nosović, norma: 90
                        Samir Ribić, norma: 90
                        """);
    }

    @Test
    void dajProfesoreKojiRadePrekoNorme() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0));
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1));

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));

        assertEquals(fakultet.dajProfesoreKojiRadePrekoNorme(), "Željko Jurić, norma: 180\n");
    }

    @Test
    void dajProfesoreSortiranePoNormi() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0)); //Predaju Vedran i Željko
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1)); //Predaju Novica i Emir
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(2), fakultet.getSemestri().get(0)); //Predaju Vedran i Željko

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));  //Predaje Samir
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));  //Predaje Samir

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));  //Predaje Željko
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));  //Predaje Emir

        //Maksimalno imaju 3 studenta, Vedran 2, Željko 2, Novica i Emir 1, Samir 1

        assertEquals("""
                Novica Nosović, norma: 90
                Samir Ribić, norma: 90
                Emir Buza, norma: 130
                Vedran Ljubović, norma: 200
                Željko Jurić, norma: 280
                """, fakultet.dajProfesoreSortiranePoNormi());
    }

    @Test
    void dajProfesoreSortiranePoBrojuStudenata() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0)); //Predaju Vedran i Željko
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(1), fakultet.getSemestri().get(1)); //Predaju Novica i Emir
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(2), fakultet.getSemestri().get(0)); //Predaju Vedran i Željko

        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(0));  //Predaje Samir
        fakultet.getStudenti().get(0).upisiIzborniPredmet(fakultet.getStudenti().get(0).getUpisaniSemestar().getIzborniPredmeti().get(1));  //Predaje Samir

        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(0));  //Predaje Željko
        fakultet.getStudenti().get(1).upisiIzborniPredmet(fakultet.getStudenti().get(1).getUpisaniSemestar().getIzborniPredmeti().get(1));  //Predaje Emir

        assertEquals("""
                Novica Nosović, broj studenata: 1
                Emir Buza, broj studenata: 1
                Samir Ribić, broj studenata: 1
                Vedran Ljubović, broj studenata: 2
                Željko Jurić, broj studenata: 3
                """, fakultet.dajProfesoreSortiranePoBrojuStudenata());
    }

    @Test
    void testDajStudentaSaBrojemIndeksa() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);
        fakultet.upisiStudentaNaSemestar(fakultet.getStudenti().get(0), fakultet.getSemestri().get(0));

        assertThrows(IllegalArgumentException.class, () -> fakultet.dajStudentaSaBrojemIndeksa("18904"),"Ne postoji student sa datim brojem indeksa!");
        assertEquals(fakultet.getStudenti().get(0), fakultet.dajStudentaSaBrojemIndeksa("18903"));
    }

    @Test
    void testDajPrepisOcjenaStudentaSaBrojemIndeksa() {
        Fakultet fakultet = new Fakultet("ETF Sarajevo", profesori, studenti, semestri);

        Student s = fakultet.getStudenti().get(0);
        fakultet.upisiStudentaNaSemestar(s, fakultet.getSemestri().get(0));
        s.upisiOcjenuIzPredmeta(s.getUpisaniPredmeti().get(0), 8);
        s.upisiOcjenuIzPredmeta(s.getUpisaniPredmeti().get(1), 9);

        assertThrows(IllegalArgumentException.class, () -> fakultet.dajPrepisOcjenaStudentaSaBrojemIndeksa("18904"), "Ne postoji student sa datim brojem indeksa!");
        assertEquals("""
                Razvoj programskih rješenja (7 ECTS) - 8
                Diskretna matematika (7 ECTS) - 9
                """, fakultet.dajPrepisOcjenaStudentaSaBrojemIndeksa("18903"));
    }
}