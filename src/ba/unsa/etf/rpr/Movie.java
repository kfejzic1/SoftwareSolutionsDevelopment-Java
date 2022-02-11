package ba.unsa.etf.rpr;

import java.util.List;

public class Movie extends Content implements Comparable<Movie>,Watchable {
    private Double rating;
    private int duration;
    private List<Genre> genres;

    public Movie() {
        super();
    }

    public Movie(String s, double v, int i, List<Genre> genres) {
        if(i<=0) {
            try {
                throw new InvalidDurationException("Duration must be positive");
            } catch (InvalidDurationException e) {
                e.printStackTrace();
            }
        } else if(v<1 || v>5) {
            try {
                throw new InvalidRatingException("Rating must be between 1 and 5");
            } catch (InvalidRatingException e) {
                e.printStackTrace();
            }
        }
        this.setName(s);
        this.duration = i;
        this.rating = v;
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        String rezultat = "Movie{"  + this.getName() + ",";
        for(Genre g : genres) {
            rezultat+=g + ",";
        }
        rezultat += rating + ",";
        if(duration/60 >=1)
            rezultat+= duration/60 + "h,";


        return rezultat + duration%60 + " minutes" + '}';
    }

    @Override
    public int compareTo(Movie o) {
        return rating.compareTo(o.getRating());
    }

    @Override
    public int getDurationInMinutes() { //Provjeriti duration
        return duration;
    }
}
