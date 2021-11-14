package ba.unsa.etf.rpr.tutorijal5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankaTest {
    Banka b;

    @BeforeEach
    void setUp() {
        b = new Banka();
    }


    @Test
    void kreirajNovogKorisnika() {
        assertEquals(0, b.getKorisnici().size());
        b.kreirajNovogKorisnika("Selma", "Selman");
        assertEquals(1, b.getKorisnici().size());
        assertEquals("Selma", b.getKorisnici().get(0).getIme());
    }

    @Test
    void dodajNovogUposlenog() {
        assertEquals(0, b.getUposleni().size());
        b.kreirajNovogUposlenog("Selma", "Selman");
        assertEquals(1, b.getUposleni().size());
        assertEquals("Selma", b.getUposleni().get(0).getIme());
    }

    @Test
    void kreirajRacun() {
        Korisnik selma = b.kreirajNovogKorisnika("Selma", "Selman");
        assertAll(
                () -> assertDoesNotThrow(() -> b.kreirajRacunZaKorisnika(selma)),
                () -> assertNotNull(selma.getRacun()),
                () -> assertEquals(1000000000000000L, selma.getRacun().getBrojRacuna()),
                () -> assertEquals(selma, selma.getRacun().getKorisnikRacuna())
        );
    }

    @Test
    void kreirajRacunIzuzetak() {
        Korisnik selma = b.kreirajNovogKorisnika("Selma", "Selman");
        Korisnik adis = new Korisnik("Adis", "Adisović");
        assertThrows(Exception.class, () -> b.kreirajRacunZaKorisnika(adis));
    }
}