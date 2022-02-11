package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    @Test
    public void isTheToStringMethodImplementedCorrectly() {
        Movie movie = new Movie();
        movie.setName("Movie1");
        movie.setDuration(90);
        movie.setGenres(List.of(Genre.ACTION, Genre.COMEDY));
        movie.setRating(4.5);
        assertEquals("Movie{Movie1,ACTION,COMEDY,4.5,1h,30 minutes}", movie.toString());
    }

    @Test
    public void isMovieComparableByRating() {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            movies.add(new Movie("M" + i, 1 + i / 10.0, 50, List.of(Genre.ROMANCE)));
        }
        Set<Movie> orderedMovies = new TreeSet<>(movies);
        String result = orderedMovies.stream().map(Movie::toString).collect(Collectors.joining(","));
        assertEquals("Movie{M0,ROMANCE,1.0,50 minutes}," +
                "Movie{M1,ROMANCE,1.1,50 minutes}," +
                "Movie{M2,ROMANCE,1.2,50 minutes}," +
                "Movie{M3,ROMANCE,1.3,50 minutes}," +
                "Movie{M4,ROMANCE,1.4,50 minutes}," +
                "Movie{M5,ROMANCE,1.5,50 minutes}," +
                "Movie{M6,ROMANCE,1.6,50 minutes}," +
                "Movie{M7,ROMANCE,1.7,50 minutes}," +
                "Movie{M8,ROMANCE,1.8,50 minutes}," +
                "Movie{M9,ROMANCE,1.9,50 minutes}", result);
    }
}
