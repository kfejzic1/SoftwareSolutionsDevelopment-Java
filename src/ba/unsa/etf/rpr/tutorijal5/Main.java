package ba.unsa.etf.rpr.tutorijal5;

import ba.unsa.etf.rpr.tutorijal5.util.Kredit;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Korisnik> korisnici = new ArrayList<>();

        Korisnik k = new Korisnik("Mujo", "Suljić");
        Racun r = new Racun(1L, k);
        k.dodajRacun(r);

        k.getRacun().odobriPrekoracenje(100D);
        k.getRacun().izvrsiIsplatu(90D);
        korisnici.add(k);

        Korisnik k1 = new Korisnik("Neko", "Nekić");
        Racun r1 = new Racun(1L, k1);
        k1.dodajRacun(r1);
        k1.getRacun().izvrsiUplatu(50D);
        korisnici.add(k1);

        Korisnik k2 = new Korisnik("Neko", "Nekić");
        Racun r2 = new Racun(1L, k2);
        k2.dodajRacun(r2);
        k2.getRacun().odobriPrekoracenje(50D);
        k2.getRacun().izvrsiIsplatu(20D);
        korisnici.add(k2);

        Kredit.bezPrekoracenja(korisnici);
    }
}
