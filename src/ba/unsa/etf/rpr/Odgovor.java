package ba.unsa.etf.rpr;

public class Odgovor {
    private String tekstOdgovora;
    boolean tacno;

    public String getTekstOdgovora() {
        return tekstOdgovora;
    }

    public void setTekstOdgovora(String tekstOdgovora) {
        this.tekstOdgovora = tekstOdgovora;
    }

    public boolean isTacno() {
        return tacno;
    }

    public void setTacno(boolean tacno) {
        this.tacno = tacno;
    }

    public Odgovor(String tekstOdgovora, boolean tacno) {
        this.tekstOdgovora = tekstOdgovora;
        this.tacno = tacno;
    }
}
