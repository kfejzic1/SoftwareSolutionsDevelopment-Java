package ba.unsa.etf.rpr;

public class Student {
    private String imeIPrezime;

    public Student(String imeIPrezime) {
        if(imeIPrezime.length()<10)
            throw new IllegalArgumentException("Ime studenta treba biti najmanje 10 karaktera dugačko");
        this.imeIPrezime = imeIPrezime;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }
}
