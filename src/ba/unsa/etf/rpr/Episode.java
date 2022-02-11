package ba.unsa.etf.rpr;

public class Episode extends Content implements Watchable{
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Episode(String name, int duration) throws InvalidDurationException {
        if(duration <= 0)
            throw new InvalidDurationException("Duration must be a positive");
        this.setName(name);
        this.duration = duration;
    }

    @Override
    public int getDurationInMinutes() { //Provjeriti duration
        return duration;
    }
}
