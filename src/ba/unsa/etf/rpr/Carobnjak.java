package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Carobnjak {
    private ArrayList<Biljka> biljke;
    private ArrayList<Eliksir> eliksiri;

    public Carobnjak() {
        this.biljke = new ArrayList<>();
        this.eliksiri = new ArrayList<>();
    }

    public static ArrayList<Eliksir> napraviEliksireIzBiljaka(ArrayList<Biljka> biljke){
        ArrayList<Eliksir> eliksiri = new ArrayList<>();
        for(Biljka x: biljke){
            eliksiri.add(new Eliksir(x.getNaziv(), biljke));
        }

        return eliksiri;
    }

    void dodajBiljku(Biljka biljka){
        biljke.add(biljka);
    }

    ArrayList<Biljka> dajBiljke(Predicate<Biljka> pravilo){
         return this.biljke.stream().filter(pravilo).collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Eliksir> dajEliksire(Predicate<Eliksir> pravilo){
        return this.eliksiri.stream().filter(pravilo).collect(Collectors.toCollection(ArrayList::new));
    }

    Set<Eliksir> dajEliksirePoNazivuAbecedno(){
        return new HashSet<Eliksir>(eliksiri);
    }

    Map<Eliksir.TipEliksira, ArrayList<Eliksir>> dajEliksirePoTipovima(){
        LinkedHashMap<Eliksir.TipEliksira, ArrayList<Eliksir>> temp = new LinkedHashMap<>();
        for(Eliksir e : this.eliksiri){
            temp.put(e.getTipEliksira(), eliksiri);
        }
        return temp;
    }

    void dodajEliksir(Eliksir eliksir) throws ZabranjenEliksirIzuzetak {
        if(eliksiri.size()!=0 && eliksiri.contains(eliksir))
            throw new ZabranjenEliksirIzuzetak("Taj eliksir vec postoji");
        eliksiri.add(eliksir);
    }

    Eliksir napraviEliksir(String naziv, Predicate<Biljka> kojeBiljke) throws ZabranjenEliksirIzuzetak{
        Eliksir novi = new Eliksir(naziv, this.biljke.stream().filter(kojeBiljke).collect(Collectors.toCollection(ArrayList::new)));
        return novi;
    }

    Eliksir dajKoktel(String naziv, Predicate<Eliksir> kojiEliksiri){
        return null;
    }

    String konzumirajEliksir(Eliksir eliksir){
        if(!eliksiri.contains(eliksir))
            throw new IllegalArgumentException("Eliksir nema na stanju");

        String s = "";
        int indeks=0;
        for(int i=0; i<eliksiri.size(); i++){
            if(eliksiri.get(i).equals(eliksir)){
                s += eliksiri.get(i).konzumiraj();
                indeks = i;
                break;
            }
        }
        eliksiri.remove(indeks);
        return s;
    }

    String konzumirajEliksire(ArrayList<Eliksir> e){
        return "";
    }

    String konzumirajSve(){
        return "";
    }
}
