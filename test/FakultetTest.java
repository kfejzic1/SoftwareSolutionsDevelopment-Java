import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakultetTest {

    Profesor profesor1 = new Profesor("Vedran", "Ljubović");
    Profesor profesor2 = new Profesor("Željko", "Jurić");
    Profesor profesor3 = new Profesor("Novica", "Nosović");
    Profesor profesor4 = new Profesor("Emir", "Buza");
    Profesor profesor5 = new Profesor("Samir", "Ribić");
    List<Profesor> profesori = Arrays.asList(profesor1, profesor2, profesor3, profesor4, profesor5);

    Predmet predmet1 = new Predmet("Razvoj programskih rješenja", 7, 100, profesor1);
    Predmet predmet2 = new Predmet("Diskretna matematika", 7, 100, profesor2);
    Predmet predmet3 = new Predmet("Logički dizajn", 5, 90, profesor3);
    Predmet predmet4 = new Predmet("Osnove baza podataka", 5, 90, profesor4);
    Predmet predmet5 = new Predmet("Sistemsko programiranje", 5, 50, profesor5);
    Predmet predmet6 = new Predmet("Random izborni predmet", 4, 40, profesor5);
    Predmet predmet7 = new Predmet("Razvoj mobilnih aplikacija", 4, 40, profesor4);
    Predmet predmet8 = new Predmet("CAD/CAM inženjering", 5, 80, profesor2);
    List<Predmet> obavezni1 = Arrays.asList(predmet1, predmet2);
    List<Predmet> obavezni2 = Arrays.asList(predmet3, predmet4);
    List<Predmet> izborni1 = Arrays.asList(predmet5, predmet6);
    List<Predmet> izborni2 = Arrays.asList(predmet7, predmet8);

    Student s1 = new Student("Kenan", "Fejzic", "18903");
    Student s2 = new Student("Mujo", "Mujic", "12345");
    List<Student> studenti = Arrays.asList(s1, s2);


    Semestar semestar1 = new Semestar(1, obavezni1, izborni1, Ciklusi.Bachelor);
    Semestar semestar2 = new Semestar(2, obavezni2, izborni2, Ciklusi.Bachelor);
    List<Semestar> semestri = Arrays.asList(semestar1, semestar2);

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