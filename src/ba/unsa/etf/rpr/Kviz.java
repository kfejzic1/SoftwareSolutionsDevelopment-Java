package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kviz {
    public enum SistemBodovanja{
        BINARNO ("binarno"),
        PARCIJALNO ("parcijalno"),
        PARCIJALNO_SA_NEGATIVNIM ("parcijalno sa negativnim bodovima");

        private String naziv;

        SistemBodovanja(String s){
            naziv = s;
        }

        @Override
        public String toString() {
            return this.naziv;
        }
    }

    private String naziv;
    private ArrayList<Pitanje> pitanja;
    private SistemBodovanja sistemBodovanja;

    public Kviz(String naziv, SistemBodovanja sistemBodovanja) {
        this.naziv = naziv;
        this.sistemBodovanja = sistemBodovanja;
        this.pitanja = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(ArrayList<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    void dodajPitanje(Pitanje pitanje){
        for(Pitanje p : pitanja){
            if(pitanje.getTekst().equalsIgnoreCase(p.getTekst()))
                throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
        }
        pitanja.add(pitanje);
    }

    @Override
    public String toString() {
        int i=1;
        String s = "Kviz \"" + this.naziv + "\" boduje se " + this.sistemBodovanja.toString() + ". Pitanja:\n";
        for(Pitanje p : pitanja){
            s = s + i + ". " + p.getTekst() + "(" + p.getBrojPoena() + "b)\n";

            HashMap<String, Odgovor> listaOdgovora = new HashMap<>(p.getOdgovori());
            for(Map.Entry<String, Odgovor> entry : listaOdgovora.entrySet()){
                s = s + "\t" + entry.getKey() + ": " + entry.getValue().getTekstOdgovora();
                if(entry.getValue().isTacno())
                    s = s + "(T)";
                s = s + "\n";
            }
            if(i != this.pitanja.size())
                s = s + "\n";
            i = i + 1;
        }

        return s.substring(0, s.length()-1);
    }

    public RezultatKviza predajKviz(Map<Pitanje,ArrayList<String>> pitanjaIOdgovori) {
        RezultatKviza rezultatKviza = new RezultatKviza(this);
        double ukupniPoeni = 0;
        int k = 0;
        for(Map.Entry<Pitanje, ArrayList<String>> entry : pitanjaIOdgovori.entrySet()){
            //Racunamo poene na pitanju pomocu funkcije izracunajPoene, gdje su kao odgovori value vrijednosti mape pitanjaIOdgovori, a pitanja kao key vrijednosti
            double poeniNaPitanju = entry.getKey().izracunajPoene(entry.getValue(), this.sistemBodovanja);

            //Dodajemo pitanje i osvojene bodove u mapu bodovi klase RezultatKviza koju vracamo kao rezultat
            rezultatKviza.getBodovi().put(entry.getKey(), poeniNaPitanju);

            k = k + 1;  //Indikator koliko je uneseno poena na pitanju

            ukupniPoeni = ukupniPoeni + poeniNaPitanju;
        }
        if(pitanja.size() > pitanjaIOdgovori.size()){
            //Nisu dati odgovori na sva pitanja, tako da za ostale moramo dodati nulu zato što Double po defaultu se inicijalizuje na null

            for(int i=0; i<pitanja.size() - pitanjaIOdgovori.size(); i++){
                rezultatKviza.getBodovi().put(pitanja.get(k), 0.);
            }
        }
        rezultatKviza.setTotal(ukupniPoeni);

        return rezultatKviza;
    }
}
