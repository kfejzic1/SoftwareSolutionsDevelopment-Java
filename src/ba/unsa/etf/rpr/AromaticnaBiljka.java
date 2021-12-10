package ba.unsa.etf.rpr;

public class AromaticnaBiljka extends Biljka{
    public AromaticnaBiljka(String naziv, int jacina) {
        super(naziv, jacina);
    }

    @Override
    public String toString() {
        return "Aroma: " + this.getNaziv() + " - " + this.getJacina();
    }
}
