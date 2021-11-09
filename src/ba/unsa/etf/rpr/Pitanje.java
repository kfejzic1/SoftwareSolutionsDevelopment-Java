package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Kviz.SistemBodovanja;

import java.util.*;

public class Pitanje {
    private String tekst;
    private double brojPoena;
    private HashMap<String, Odgovor> odgovori;

    public Pitanje(String tekst, double brojPoena) {
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

    public void dodajOdgovor(String id, String tekst, boolean tacno){
        if(odgovori.containsKey(id))
            throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        odgovori.put(id, new Odgovor(tekst, tacno));
    }

    public void obrisiOdgovor(String id){
        if(!odgovori.containsKey(id))
            throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id);
    }

    List<Odgovor> dajListuOdgovora(){
        return new ArrayList<>(odgovori.values());
    }

    @Override
    public String toString() {
        String s = this.tekst + "(" + this.brojPoena + "b)\n";
        char it = 'a';

        int i = 0;
        for (Map.Entry<String, Odgovor> entry : odgovori.entrySet()){
            //Nije efektivno na ovaj nacin raditi sa stringom u petlji, pokusati drugi nacin
            s = s + "\t" + it + ": " + entry.getValue().getTekstOdgovora();
            it = (char) (it + 1);
            i = i + 1;
            if(i != odgovori.size())
                s = s + "\n";
        }

        return s;
    }

    public double izracunajPoene (List<String> id, SistemBodovanja s){
        for(String it : id){
            if(!odgovori.containsKey(it))
                throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
        }

        Set<String> testni = new HashSet<>(id);
        if(testni.size()!=id.size())    //Iskoristena osobina skupa, da se ne mogu pojavljivati duplikati
            throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");

        //Odrediti koliko ima tacnih odgovora
        int brojTacnihOdgovora = 0;
        for(Map.Entry<String, Odgovor> entry : this.odgovori.entrySet()){
            if(entry.getValue().isTacno())
                brojTacnihOdgovora = brojTacnihOdgovora + 1;
        }

        int brojZaokruzenihTacnihOdgovora = 0;
        int brojZaokruzenihNetacnihOdgovora = 0;
        for(String it : id) {
            if (this.odgovori.get(it).isTacno())     //Zaokruzen je tacan odgovor
                brojZaokruzenihTacnihOdgovora = brojZaokruzenihTacnihOdgovora + 1;
            else                                    //Zaokruzen je pogresan odgovor
                brojZaokruzenihNetacnihOdgovora = brojZaokruzenihNetacnihOdgovora + 1;
        }

        switch (s){
            case BINARNO -> {
                if(brojTacnihOdgovora == brojZaokruzenihTacnihOdgovora && brojZaokruzenihNetacnihOdgovora==0)
                    return this.brojPoena;
            }
            case PARCIJALNO -> {
                if(brojZaokruzenihTacnihOdgovora>0 && brojZaokruzenihNetacnihOdgovora==0)
                    return (this.brojPoena/odgovori.size())*brojZaokruzenihTacnihOdgovora;
            }
            case PARCIJALNO_SA_NEGATIVNIM -> {
                if(brojZaokruzenihNetacnihOdgovora != 0)    //Ako ima barem jedan netacno odgovoren, oduzimaju se bodovi
                    return -this.brojPoena/2;
                else
                    return (this.brojPoena/odgovori.size())*brojTacnihOdgovora;
            }
        }

        return 0;
    }
}
