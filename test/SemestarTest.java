import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemestarTest {
    Semestar semestar1 = new Semestar(1);
    Semestar semestar2 = new Semestar(2);

    Predmet p1 =  new Predmet("Razvoj programskih rješenja", 10);
    Predmet p2 = new Predmet("Sistemsko programiranje", 10);
    Predmet p3 = new Predmet("Logički dizajn", 10);
    Predmet p4 = new Predmet("Numerički algoritmi", 10);
    Predmet p5 = new Predmet("Diskretna matematika", 10);

    @Test
    void testDodajIzborniPredmet(){
        semestar1.dodajIzborniPredmet(p1);
        semestar1.dodajIzborniPredmet(p2);
        semestar1.dodajIzborniPredmet(p3);

        Exception exception1 = assertThrows(IllegalArgumentException.class, ()-> semestar1.dodajObavezniPredmet(p4));
        Exception exception2 = assertThrows(IllegalArgumentException.class, ()-> semestar1.dodajObavezniPredmet(p1));
        Exception exception3 = assertThrows(IllegalArgumentException.class, ()-> semestar1.dodajIzborniPredmet(p1));

        assertAll(
                () -> assertEquals("Broj ECTS poena u semestru ne moze biti veci od 30!", exception1.getMessage()),
                () -> assertEquals("Predmet je izborni!", exception2.getMessage()),
                () -> assertEquals("Predmet je vec dodan u izborne!", exception3.getMessage())
        );
    }
}