package ba.unsa.etf.rpr.tutorijal5;

import ba.unsa.etf.rpr.tutorijal5.util.Kredit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KreditTest {
    List<Korisnik> korisnici = new ArrayList<>();
    Racun r;
    Korisnik k;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp(){
        k = new Korisnik("Mujo", "Suljić");
        r = new Racun(1L, k);
        k.dodajRacun(r);

        k.getRacun().odobriPrekoracenje(100D);
        k.getRacun().izvrsiIsplatu(90D);
        korisnici.add(k);

        Korisnik k1 = new Korisnik("Neko", "Nekić");
        Racun r1 = new Racun(1L, k1);
        k1.dodajRacun(r1);
        k1.getRacun().izvrsiUplatu(10000D);
        korisnici.add(k1);

        Korisnik k2 = new Korisnik("Neko", "Nekić");
        Racun r2 = new Racun(1L, k2);
        k2.dodajRacun(r2);
        k2.getRacun().odobriPrekoracenje(50D);
        k2.getRacun().izvrsiIsplatu(20D);
        korisnici.add(k2);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @org.junit.jupiter.api.Test
    void dajKreditnuSposobnost() {
        r.izvrsiUplatu(100000D);
        assertEquals(9991,
                Kredit.dajKreditnuSposobnost( l -> {
                    if(l.getStanjeRacuna()<10000)
                        return 0D;
                    return l.getStanjeRacuna()/10;
                }, k));
    }

    @org.junit.jupiter.api.Test
    void bezPrekoracenja(){
        Kredit.bezPrekoracenja(korisnici);
        assertEquals("Neko Nekić\r\n", outContent.toString());
    }

    @org.junit.jupiter.api.Test
    void odobriPrekoracenje(){
        korisnici.forEach(korisnik -> korisnik.getRacun().izvrsiUplatu(50000D));
        Kredit.odobriPrekoracenje(korisnici);
        assertAll(
                () -> assertEquals(1000D, korisnici.get(0).getRacun().getPrekoracenje()),
                () -> assertEquals(1000D, korisnici.get(1).getRacun().getPrekoracenje()),
                () -> assertEquals(1000D, korisnici.get(2).getRacun().getPrekoracenje())

        );
    }
}
