package ba.unsa.etf.rpr;

public abstract class Content {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content(String name) {
        this.name = name;
    }

    public Content() {
        this.name = "";
    }
}
