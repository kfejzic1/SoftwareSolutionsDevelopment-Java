package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Eliksir implements Konzumabilno{
    public int compareTo(Eliksir eliksir) {
        return this.naziv.compareTo(eliksir.getNaziv());
    }

    public enum TipEliksira {
        BOOSTER, LIJEK, OTROV, PARFEM
    }
    private String naziv;
    private int jacina;
    private ArrayList<Biljka> biljke;
    private TipEliksira tipEliksira;

    @Override
    public String konzumiraj(){
        if(tipEliksira==TipEliksira.BOOSTER)
            return "{Sve +" + jacina + "}";
        if(tipEliksira==TipEliksira.LIJEK)
            return "{Zdravlje +" + jacina + "}";
        if(tipEliksira==TipEliksira.OTROV)
            return "{Zdravlje -" + jacina + "}";
        if(tipEliksira==TipEliksira.PARFEM)
            return "{Miris +" + jacina + "}";
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eliksir)) return false;
        Eliksir eliksir = (Eliksir) o;
        return naziv.equals(eliksir.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    public String getNaziv() {
        return naziv;
    }

    public int getJacina() {
        return jacina;
    }

    public ArrayList<Biljka> getBiljke() {
        return biljke;
    }

    public TipEliksira getTipEliksira() {
        return tipEliksira;
    }


    public Eliksir(String naziv, List<Biljka> biljke) {
        if(naziv.isBlank())
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez naziva");
        if(biljke.size()==0)
            throw new IllegalArgumentException("Ne moze se kreirati eliksir bez biljaka");

        this.naziv = naziv;
        this.biljke = new ArrayList<>(biljke);
        int jacinaAromaticnihBiljaka=0;
        int jacinaLjekovitihBiljaka=0;
        int jacinaOtrovnihBiljaka=0;

        for(Biljka x : biljke){
            if(x instanceof AromaticnaBiljka)
                jacinaAromaticnihBiljaka += x.getJacina();
            else if(x instanceof LjekovitaBiljka)
                jacinaLjekovitihBiljaka += x.getJacina();
            else
                jacinaOtrovnihBiljaka += x.getJacina();
        }

        if(jacinaAromaticnihBiljaka == jacinaLjekovitihBiljaka && jacinaAromaticnihBiljaka==jacinaOtrovnihBiljaka){
            this.tipEliksira=TipEliksira.BOOSTER;
            this.jacina = jacinaAromaticnihBiljaka + jacinaLjekovitihBiljaka + jacinaOtrovnihBiljaka;
        }
        else if(jacinaAromaticnihBiljaka>jacinaLjekovitihBiljaka && jacinaAromaticnihBiljaka>jacinaOtrovnihBiljaka){
            this.tipEliksira = TipEliksira.PARFEM;
            this.jacina = jacinaAromaticnihBiljaka;
        }
        else if(jacinaLjekovitihBiljaka>jacinaAromaticnihBiljaka && jacinaLjekovitihBiljaka>jacinaOtrovnihBiljaka){
            this.tipEliksira = TipEliksira.LIJEK;
            this.jacina = jacinaLjekovitihBiljaka;
        }
        else if(jacinaOtrovnihBiljaka>jacinaAromaticnihBiljaka && jacinaOtrovnihBiljaka>jacinaLjekovitihBiljaka){
            this.tipEliksira = TipEliksira.OTROV;
            this.jacina = jacinaOtrovnihBiljaka;
        }
    }


}
