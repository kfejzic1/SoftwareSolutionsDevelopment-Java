package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Korisnik;

import java.util.List;
import java.util.stream.Stream;

public class Kredit {
    Double dajKreditnuSposobnost(KreditnaSposobnost kredit, Korisnik korisnik) throws Exception {
        Double d = kredit.provjeri(korisnik.getRacun());
        if(d.equals(0D))
            throw new Exception("Korisnik nema kreditne sposobnosti.");
        return d;
    }

    void bezPrekoracenja(List<Korisnik> korisnici){
        korisnici.stream().filter(korisnik -> korisnik.getRacun().getStanjeRacuna() >= 0).forEach(System.out::println);
    }

    void odobriPrekoracenje(List<Korisnik> korisnici){
        korisnici.stream().filter(korisnik -> korisnik.getRacun().getStanjeRacuna() >= 10000).forEach(korisnik -> korisnik.getRacun().odobriPrekoracenje(1000D));
    }
}
