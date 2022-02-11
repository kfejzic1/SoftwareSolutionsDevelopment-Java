/*package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IspitGeografijaDAOTest {
    GeografijaDAO dao = GeografijaDAO.getInstance();

    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }


    @Test
    void testIzmijeniDrzavuNajveciGrad() {
        // Stavljam da je Graz najveći grad Austrije
        Drzava austrija = dao.nadjiDrzavu("Austrija");
        Grad graz = dao.nadjiGrad("Graz");
        austrija.setNajveciGrad(graz);
        dao.izmijeniDrzavu(austrija);

        // Uzimam drugu verziju za Austriju
        Drzava austrija2 = dao.nadjiDrzavu("Austrija");
        assertEquals("Graz", austrija2.getNajveciGrad().getNaziv());
        Grad graz2 = austrija2.getNajveciGrad();
        assertEquals(graz.getId(), graz2.getId());
    }


    @Test
    void testDodajDrzavuNajveciGrad() {
        // Dodajem dva grada, stavićemo da su u Francuskoj
        Drzava francuska = dao.nadjiDrzavu("Francuska");
        Grad washingtonDc = new Grad(0, "Washington", 500000, francuska);
        dao.dodajGrad(washingtonDc);
        Grad newYork = new Grad(0, "New York", 10000000, francuska);
        dao.dodajGrad(newYork);

        // Uzimamo gradove iz baze
        Grad w2 = dao.nadjiGrad("Washington");
        Grad ny2 = dao.nadjiGrad("New York");

        // Dodajemo novu državu
        Drzava usa = new Drzava(0, "Sjedinjene Američke Države", w2, ny2);
        dao.dodajDrzavu(usa);

        // Provjeravamo da li se ispravno dodalo
        Drzava usa2 = dao.nadjiDrzavu("Sjedinjene Američke Države");
        assertEquals("Sjedinjene Američke Države", usa2.getNaziv());
        assertEquals("Washington", usa2.getGlavniGrad().getNaziv());
        assertEquals(w2.getId(), usa2.getGlavniGrad().getId());
        assertEquals("New York", usa2.getNajveciGrad().getNaziv());
        assertEquals(ny2.getId(), usa2.getNajveciGrad().getId());
    }


    @Test
    void testObrisiNajveciGrad() {
        // Stavljam da je Graz najveći grad Austrije
        Drzava austrija = dao.nadjiDrzavu("Austrija");
        Grad graz = dao.nadjiGrad("Graz");
        austrija.setNajveciGrad(graz);
        dao.izmijeniDrzavu(austrija);

        // Test nema smisla ako najveći grad nije ispravno postavljen
        Drzava austrija2 = dao.nadjiDrzavu("Austrija");
        Grad graz2 = austrija2.getNajveciGrad();
        assertEquals(graz.getId(), graz2.getId());

        // Brišemo Graz iz baze
        dao.obrisiGrad(graz);

        // Glavni grad po defaultu je najveći
        Drzava austrija3 = dao.nadjiDrzavu("Austrija");
        assertEquals("Beč", austrija3.getNajveciGrad().getNaziv());
    }
}
*/