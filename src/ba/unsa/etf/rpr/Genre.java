package ba.unsa.etf.rpr;

public enum Genre {
    ACTION("ACTION"), COMEDY("COMEDY"), ROMANCE("ROMANCE"), DRAMA("DRAMA"), FANTASY("FANTASY"),
    HORROR("HORROR"),
    MYSTERY("MYSTERY"),
    THRILLER("THRILLER"),
    WESTERN("WESTERN");
    private String name;
    private Genre(String n) {
        this.name = n;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
