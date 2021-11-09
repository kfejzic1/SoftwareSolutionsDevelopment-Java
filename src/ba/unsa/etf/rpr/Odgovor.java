package ba.unsa.etf.rpr;

public class Odgovor {
    private String tekstOdgovora;
    private boolean tacno;

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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof Odgovor){
            Odgovor o = (Odgovor) obj;
            return this.tacno == o.tacno && this.tekstOdgovora.equals(o.tekstOdgovora);
        }
        return false;
    }
}
