import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    ArrayList<Predmet> generisiObaveznePredmete() {
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, new Profesor("Vedran", "Ljubović"));
        Predmet p2 = new Predmet("Diskretna matematika", 7, 70, new Profesor("Željko", "Jurić"));
        Predmet p3 = new Predmet("Logički dizajn", 5, 50, new Profesor("Novica", "Nosović"));
        Predmet p4 = new Predmet("Osnove baza podataka", 5, 50, new Profesor("Emir", "Buza"));

        ArrayList<Predmet> obavezni = new ArrayList<>();
        obavezni.add(p1);
        obavezni.add(p2);
        obavezni.add(p3);
        obavezni.add(p4);

        return obavezni;
    }

    ArrayList<Predmet> generisiIzbornePredmete() {
        Predmet p1 = new Predmet("Sistemsko programiranje", 5, 50, new Profesor("Samir", "Ribić"));
        Predmet p2 = new Predmet("Random izborni predmet", 4, 40, new Profesor("Neko", "Nekić"));

        ArrayList<Predmet> izborni = new ArrayList<>();
        izborni.add(p1);
        izborni.add(p2);

        return izborni;
    }

    Semestar semestar = new Semestar(1, generisiObaveznePredmete(), generisiIzbornePredmete(), Ciklusi.Bachelor);

    @Test
    void testUpisiSemestar() {
        Student student = new Student("Kenan", "Fejzić", "18903");
        student.upisiSemestar(semestar);

        try {
            student.upisiSemestar(semestar);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Student je vec upisan na neki od semestara!", e.getMessage());
        }
    }

    @Test
    void testOcjenaIzPredmeta() {
        Student student = new Student("Kenan", "Fejzić", "18903");
        student.upisiSemestar(semestar);

        student.upisiOcjenuIzPredmeta(semestar.getObavezniPredmeti().get(0), 8);

        assertAll(
                () -> {
                    try {
                        student.upisiOcjenuIzPredmeta(student.getUpisaniPredmeti().get(0), 10);
                        fail();
                    } catch (Exception e) {
                        assertEquals("Ocjena iz predmeta je vec upisana!", e.getMessage());
                    }
                },
                () -> {
                    try {
                        student.dajOcjenuIzPredmeta(new Predmet("Neki predmet", 6, 40, new Profesor("Neko", "Nekic")));
                    } catch (Exception e) {
                        assertEquals("Student ne pohađa taj predmet!", e.getMessage());
                    }
                },
                () -> assertEquals(student.dajOcjenuIzPredmeta(student.getUpisaniPredmeti().get(1)), 5),
                () -> assertEquals(student.dajOcjenuIzPredmeta(student.getUpisaniPredmeti().get(0)), 8)
        );
    }

    @Test
    void testUpisiIzborniPredmet() {
        Student student = new Student("Kenan", "Fejzić", "18903");
        student.upisiSemestar(semestar);

        student.upisiIzborniPredmet(semestar.getIzborniPredmeti().get(0));

        assertAll(
                () -> {
                    try {
                        student.upisiIzborniPredmet(new Predmet("Neki predmet", 6, 40, new Profesor("Neko", "Nekic")));
                        fail();
                    } catch (IllegalArgumentException e) {
                        assertEquals("Predmet nije izborni u upisanom semestru!", e.getMessage());
                    }
                },
                () -> {
                    try {
                        student.upisiIzborniPredmet(student.getUpisaniSemestar().getIzborniPredmeti().get(0));
                        fail();
                    } catch (IllegalArgumentException e) {
                        assertEquals("Student je već upisan na ovaj predmet!", e.getMessage());
                    }
                },
                () -> {
                    try {
                        student.upisiIzborniPredmet(student.getUpisaniSemestar().getIzborniPredmeti().get(1));
                        fail();
                    } catch (IllegalArgumentException e) {
                        assertEquals("Nije moguće imati više od 30 ECTS poena po semestru!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void testDajPrepisOcjena() {
        Student student = new Student("Kenan", "Fejzić", "18903");
        student.upisiSemestar(semestar);
        student.upisiIzborniPredmet(semestar.getIzborniPredmeti().get(0));

        student.upisiOcjenuIzPredmeta(student.getUpisaniPredmeti().get(0), 7);
        student.upisiOcjenuIzPredmeta(student.getUpisaniPredmeti().get(1), 8);
        student.upisiOcjenuIzPredmeta(student.getUpisaniPredmeti().get(2), 9);

        assertEquals(student.dajPrepisOcjena(),
                """
                        Razvoj programskih rješenja (7 ECTS) - 7
                        Diskretna matematika (7 ECTS) - 8
                        Logički dizajn (5 ECTS) - 9
                        Osnove baza podataka (5 ECTS) - 5
                        Sistemsko programiranje (5 ECTS) - 5
                        """);
    }

    @Test
    void testToString() {
        Student s = new Student("Kenan", "Fejzić", "18903");
        assertEquals(s.toString(), "Kenan Fejzić\nBroj indeksa: 18903");
    }
}