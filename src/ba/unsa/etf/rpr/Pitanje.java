package ba.unsa.etf.rpr;

import java.util.HashMap;

public class Pitanje {
    private String tekst;
    private double brojPoena;
    private HashMap<String, Odgovor> odgovori;

    public Pitanje(String tekst, int brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        this.odgovori = new HashMap<>();
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public HashMap<String, Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(HashMap<String, Odgovor> odgovori) {
        this.odgovori = odgovori;
    }
}
