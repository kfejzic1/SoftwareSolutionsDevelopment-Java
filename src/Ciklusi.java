public enum Ciklusi {
    Bachelor("Bachelor"), Master("Master"), PhD("PhD");

    private final String name;

    Ciklusi(String s){
        name = s;
    }

    public String toString(){
        return this.name;
    }
}
