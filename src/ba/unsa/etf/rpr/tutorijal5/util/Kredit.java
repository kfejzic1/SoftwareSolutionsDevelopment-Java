package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Korisnik;

import java.util.List;

public class Kredit {
    public static Double dajKreditnuSposobnost(KreditnaSposobnost kredit, Korisnik korisnik){
        Double d = kredit.provjeri(korisnik.getRacun());
        return d;
    }

    public static void bezPrekoracenja(List<Korisnik> korisnici){
        korisnici.stream().filter(korisnik -> korisnik.getRacun().getStanjeRacuna() >= 0).forEach(System.out::println);
    }

    public static void odobriPrekoracenje(List<Korisnik> korisnici){
        korisnici.stream().filter(korisnik -> korisnik.getRacun().getStanjeRacuna() >= 10000).forEach(korisnik -> korisnik.getRacun().odobriPrekoracenje(1000D));
    }
}
