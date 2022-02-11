package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IspitDrzavaTest {
    @Test
    void najveciGradTest() {
        Drzava d = new Drzava(100, "Sjedinjene Američke Države", null);
        Grad g = new Grad(50, "Washington", 500000, d);
        d.setGlavniGrad(g);
        Grad g2 = new Grad(51, "New York", 15000000, d);
        d.setNajveciGrad(g2);
        assertEquals("New York", d.getNajveciGrad().getNaziv());
    }

    @Test
    void ctorTest() {
        // Ovdje testiramo konstruktor sa 4 parametra
        Grad g = new Grad(50, "Washington", 500000, null);
        Grad g2 = new Grad(51, "New York", 15000000, null);
        Drzava d = new Drzava(100, "Sjedinjene Američke Države", g, g2);
        assertEquals("New York", d.getNajveciGrad().getNaziv());
    }
}
