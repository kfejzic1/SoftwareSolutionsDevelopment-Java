package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public class Series extends Content{
    double rating;
    List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    List<Genre> genres;

    public Series(String name, double rating, List<Episode> episodes, List<Genre> genres) throws InvalidRatingException {
        if(rating<1 || rating >5)
            throw new InvalidRatingException("Rating must be between 1 and 5");
        this.setName(name);
        this.rating = rating;
        this.episodes = episodes;
        this.genres = genres;
    }

    public Series() {
        super();
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

    private int getDuration() {
        if(episodes == null)
            return 0;
        int i=0;
        for (Episode e : episodes)
            i+=e.getDuration();
        return i;
    }

    @Override
    public String toString() {
        String rezultat = "Series{"  + this.getName() + "," + this.getRating() + "," + this.getDuration() + ",";
        for(int i=0; i<genres.size(); i++) {
            rezultat+=genres.get(i);
            if(i==genres.size()-1)
                break;
            rezultat+=",";
        }

        return rezultat+"}";
    }
}
