package ba.unsa.etf.rpr.tutorijal5.util;

import ba.unsa.etf.rpr.tutorijal5.Korisnik;

public class Kredit {
    Double dajKreditnuSposobnost(KreditnaSposobnost kredit, Korisnik korisnik) throws Exception {
        Double d = kredit.provjeri(korisnik.getRacun());
        if(d.equals(0D))
            throw new Exception("Korisnik nema kreditne sposobnosti.");
        return d;
    }


}
