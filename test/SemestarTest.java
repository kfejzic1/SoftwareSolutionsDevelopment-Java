import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SemestarTest {

    ArrayList<Predmet> generisiObavezne(){
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, false, new Profesor("Vedran", "Ljubović"));
        Predmet p2 = new Predmet("Diskretna matematika", 7, 70, false, new Profesor("Željko", "Jurić"));
        Predmet p3 = new Predmet("Logički dizajn", 5, 50, false, new Profesor("Novica", "Nosović"));
        Predmet p4 = new Predmet("Osnove baza podataka", 5, 50, false, new Profesor("Emir", "Buza"));

        ArrayList<Predmet> obavezni = new ArrayList<>();
        obavezni.add(p1);
        obavezni.add(p2);
        obavezni.add(p3);
        obavezni.add(p4);

        return obavezni;
    }

    ArrayList<Predmet> generisiIzborne(){
        Predmet p1 = new Predmet("Sistemsko programiranje", 5, 50, true, new Profesor("Samir", "Ribić"));
        Predmet p2 = new Predmet("Random izborni predmet", 4, 40, true, new Profesor("Neko", "Nekić"));

        ArrayList<Predmet> izborni = new ArrayList<>();
        izborni.add(p1);
        izborni.add(p2);

        return izborni;
    }

    @Test
    void dodajObavezniPredmet() {
        Semestar semestar = new Semestar(1, Ciklusi.Bachelor);
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, false, new Profesor("Vedran", "Ljubović"));
        Predmet p2 = new Predmet("Random izborni predmet", 4, 40, true, new Profesor("Neko", "Nekić"));

        semestar.dodajObavezniPredmet(p1);
        assertAll(
                () -> assertEquals(semestar.getObavezniPredmeti().get(0), p1),
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajObavezniPredmet(p1)),
                () -> {
                    try{
                        semestar.dodajObavezniPredmet(p1);
                        fail();
                    }catch (Exception e){
                        assertEquals("Predmet se već nalazi u semestru!", e.getMessage());
                    }
                },
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajObavezniPredmet(p2)),
                () -> {
                    try {
                        semestar.dodajObavezniPredmet(p2);
                        fail();
                    }catch (Exception e){
                        assertEquals("Predmet nije obavezan!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void dodajIzborniPredmet() {
        Semestar semestar = new Semestar(1, Ciklusi.Bachelor);
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, false, new Profesor("Vedran", "Ljubović"));
        Predmet p2 = new Predmet("Random izborni predmet", 4, 40, true, new Profesor("Neko", "Nekić"));

        semestar.dodajIzborniPredmet(p2);

        assertAll(
                () -> assertEquals(semestar.getIzborniPredmeti().get(0), p2),
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajIzborniPredmet(p2)),
                () -> {
                    try{
                        semestar.dodajIzborniPredmet(p2);
                        fail();
                    }catch (Exception e){
                        assertEquals("Predmet se već nalazi u semestru!", e.getMessage());
                    }
                },
                () -> assertThrows(IllegalArgumentException.class, () -> semestar.dodajIzborniPredmet(p2)),
                () -> {
                    try {
                        semestar.dodajIzborniPredmet(p1);
                        fail();
                    }catch (Exception e){
                        assertEquals("Predmet nije izborni!", e.getMessage());
                    }
                }
        );
    }

    @Test
    void upisiIzborniPredmet() {
        ArrayList<Predmet> obavezni = generisiObavezne();
        ArrayList<Predmet> izborni = generisiIzborne();
        Predmet predmet = new Predmet ("Neki predmet", 5, 50, false, new Profesor("Neki", "Nekic"));
        Semestar semestar = new Semestar(1, obavezni, izborni, Ciklusi.Bachelor);
        semestar.upisiIzborniPredmet(izborni.get(0));

        assertAll(
                () -> {
                    try{
                        semestar.upisiIzborniPredmet(obavezni.get(0));
                        fail();
                    }catch(Exception e){
                        assertEquals("Predmet je obavezan!", e.getMessage());
                    }
                },
                () -> {
                    try{
                        semestar.upisiIzborniPredmet(predmet);
                        fail();
                    }catch(Exception e){
                        assertEquals("Predmet se ne nalazi u semestru!", e.getMessage());
                    }
                },
                () -> {
                  try{
                      Predmet p = semestar.getIzborniPredmeti().get(1);
                      semestar.upisiIzborniPredmet(p);
                  }catch (Exception e){
                      assertEquals("U semestru nije moguće imati više od 30 ECTS poena!", e.getMessage());
                  }
                },
                () -> assertEquals(semestar.getUpisaniIzborniPredmeti().get(0), izborni.get(0))
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