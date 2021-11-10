package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;

public class RezultatKviza {
    private Kviz kviz;
    private double total;
    Map<Pitanje, Double> bodovi;

    public RezultatKviza(Kviz kviz) {
        this.kviz = kviz;
        this.total = 0;
        this.bodovi = new HashMap<>();
    }

    public Kviz getKviz() {
        return kviz;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
        String s = "Na kvizu \"" + kviz.getNaziv() + "\" ostvarili ste ukupno " + this.total + " poena. Raspored po pitanjima:\n";

        for(Map.Entry<Pitanje, Double> entry : bodovi.entrySet()){
            s = s + entry.getKey().getTekst() + " - " + entry.getValue() + "b\n";
        }

        return s.substring(0, s.length()-1);
    }
}
