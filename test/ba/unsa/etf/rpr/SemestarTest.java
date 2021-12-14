package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Ciklusi;
import ba.unsa.etf.rpr.Predmet;
import ba.unsa.etf.rpr.Profesor;
import ba.unsa.etf.rpr.Semestar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemestarTest {

    @Test   //Testovi su banalni
    void dodajObavezniPredmet() {
        Semestar semestar = new Semestar(1, Ciklusi.Bachelor);
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, new Profesor("Vedran", "Ljubović"));

        semestar.dodajObavezniPredmet(p1);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajObavezniPredmet(p1)),
                () -> {
                    try {
                        semestar.dodajObavezniPredmet(p1);
                        fail();
                    } catch (Exception e) {
                        assertEquals("Predmet se već nalazi u semestru!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void dodajIzborniPredmet() {
        Semestar semestar = new Semestar(1, Ciklusi.Bachelor);
        Predmet p2 = new Predmet("Random izborni predmet", 4, 40, new Profesor("Neko", "Nekić"));

        semestar.dodajIzborniPredmet(p2);

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajIzborniPredmet(p2)),
                () -> {
                    try {
                        semestar.dodajIzborniPredmet(p2);
                        fail();
                    } catch (Exception e) {
                        assertEquals("Predmet se već nalazi u semestru!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void testEquals() {
        Semestar s1 = new Semestar(1, Ciklusi.Bachelor);
        Semestar s2 = new Semestar(2, Ciklusi.PhD);
        Semestar s3 = new Semestar(1, Ciklusi.Bachelor);

        assertEquals(s1, s3);
        assertNotEquals(s1, s2);
    }
}