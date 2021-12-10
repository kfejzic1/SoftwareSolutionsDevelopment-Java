package ba.unsa.etf.rpr;

public class LjekovitaBiljka extends Biljka{

    public LjekovitaBiljka(String naziv, int jacina) {
        super(naziv, jacina);
    }

    @Override
    public String toString() {
        return "Lijek: " + this.getNaziv() + " - " + this.getJacina();
    }
}
