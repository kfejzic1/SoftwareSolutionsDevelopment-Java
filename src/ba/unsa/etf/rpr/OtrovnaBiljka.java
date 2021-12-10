package ba.unsa.etf.rpr;

public class OtrovnaBiljka extends Biljka{

    public OtrovnaBiljka(String naziv, int jacina) {
        super(naziv, jacina);
    }

    @Override
    public String toString() {
        return "Otrov: " + this.getNaziv() + " - " + this.getJacina();
    }
}
