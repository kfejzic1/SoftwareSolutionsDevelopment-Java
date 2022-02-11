package ba.unsa.etf.rpr;

public interface Watchable {
    default int getDurationInMinutes() {
        return 0;
    }
}
