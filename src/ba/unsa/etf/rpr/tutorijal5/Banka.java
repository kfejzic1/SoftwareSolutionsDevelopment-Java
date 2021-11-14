package ba.unsa.etf.rpr.tutorijal5;

import java.util.ArrayList;
import java.util.List;

public class Banka {
    private static Long brojRacuna = 1000000000000000L;
    private List<Uposlenik> uposleni;
    private List<Korisnik> korisnici;

    public Banka() {
        uposleni = new ArrayList<>();
        korisnici = new ArrayList<>();
    }

    public List<Uposlenik> getUposleni() {
        return uposleni;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public Korisnik kreirajNovogKorisnika(String ime, String prezime) {
        Korisnik k = new Korisnik(ime, prezime);
        korisnici.add(k);
        return k;
    }

    public Uposlenik kreirajNovogUposlenog(String ime, String prezime) {
        Uposlenik u = new Uposlenik(ime, prezime);
        uposleni.add(u);
        return u;
    }

    public void kreirajRacunZaKorisnika(Korisnik korisnik) {
        if(!korisnici.contains(korisnik))
            throw new IllegalArgumentException("Korisnik nije učlanjen u banku");
        korisnik.dodajRacun(new Racun(brojRacuna,korisnik));
    }
}
