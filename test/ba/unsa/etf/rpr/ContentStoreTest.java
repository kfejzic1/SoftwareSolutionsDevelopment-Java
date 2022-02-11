package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentStoreTest {
    private ContentStore contentStore;

    @BeforeEach
    public void setup() {
        contentStore = new ContentStore();
    }

    @Test
    public void isItAddingContentToTheBeginning() {
        contentStore.addContent(new Movie("Movie1", 3.1, 59, List.of(Genre.COMEDY)));
        contentStore.addContent(new Movie("Movie2", 2.2, 49, List.of(Genre.ACTION)));
        contentStore.addContent(new Movie("Movie3", 1.3, 39, List.of(Genre.DRAMA)));
        List<Content> contents = contentStore.getContents();
        assertEquals(3, contents.size());
        assertEquals(new Movie("Movie3", 1.3, 39, List.of(Genre.DRAMA)), contents.get(0));
    }

    @Test
    public void doesCsvWithBracesWorkCorrectly() throws InvalidDurationException, InvalidRatingException {
        contentStore.addContent(new Movie("Movie1", 1, 10, List.of(Genre.COMEDY)));
        contentStore.addContent(new Series("Series1", 3, List.of(
                new Episode("Episode1", 12),
                new Episode("Episode2", 15),
                new Episode("Episode3", 17),
                new Episode("Episode4", 18)
        ), List.of(Genre.ROMANCE,Genre.ACTION)));
        contentStore.addContent(new Movie("Movie2", 2, 20, List.of(Genre.ROMANCE)));
        assertEquals("{Movie2,ROMANCE,2.0,20 minutes}" + System.lineSeparator() +
                "{Series1,3.0,4,ROMANCE,ACTION}" + System.lineSeparator() +
                "{Movie1,COMEDY,1.0,10 minutes}", contentStore.getContentsAsCsvFormatWithBraces());
    }

//    @Test
//    public void isContentConvertedToWatchableCorrectly() {
//        contentStore.addContent(new Movie("Movie1", 1, 10, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE)));
//        contentStore.addContent(new Movie("Movie2", 2, 20, List.of(Genre.ROMANCE)));
//        assertEquals(List.of(20, 12, 15, 17, 18, 10), contentStore.getWatchables().stream().map(Watchable::getDurationInMinutes).toList());
//    }
//
//    @Test
//    public void areMoviesAndSeriesMappedByGenreCorrectly() {
//        contentStore.addContent(new Movie("Movie1", 1, 10, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE)));
//        contentStore.addContent(new Movie("Movie2", 2, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        Map<String, ArrayList<Content>> mappedContents = contentStore.getContentsMappedByGenre();
//        assertEquals(2, mappedContents.get(Genre.COMEDY.toString()).size());
//        assertEquals(1, mappedContents.get(Genre.ROMANCE.toString()).size());
//        assertEquals(1, mappedContents.get(Genre.ACTION.toString()).size());
//    }
//
//    @Test
//    void getSeriesByRule() {
//        contentStore.addContent(new Movie("Movie1", 1, 10, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        ArrayList<Series> series = contentStore.getSeriesByRule(s -> s.getEpisodes().size() > 2);
//        assertEquals(2, series.size());
//    }
//
//    @Test
//    void getMovieByRule() {
//        contentStore.addContent(new Movie("Movie1", 1, 10, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        ArrayList<Movie> movies = contentStore.getMoviesByRule(m -> m.getRating() > 2);
//        assertEquals(4, movies.size());
//    }
//
//    @Test
//    void getMoviesAndSeriesLongerThan() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        ArrayList<Content> contents = contentStore.getMoviesAndSeriesLongerThan(65);
//        assertEquals(4, contents.size());
//    }
//
//    @Test
//    void getNTopRatedSeries() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        ArrayList<Series> series = contentStore.getNTopRatedSeries(2, true);
//        String result = series.stream().map(Series::toString).collect(Collectors.joining(","));
//        assertEquals("Series{Series1,4.0,3,ROMANCE,ACTION},Series{Series2,3.4,2,ROMANCE,ACTION}", result);
//        series = contentStore.getNTopRatedSeries(2, false);
//        result = series.stream().map(Series::toString).collect(Collectors.joining(","));
//        assertEquals("Series{Series2,3.4,2,ROMANCE,ACTION},Series{Series1,4.0,3,ROMANCE,ACTION}", result);
//    }
//
//    @Test
//    void removeMovieOrSeriesByName() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.removeMovieOrSeriesByName("Series1");
//        contentStore.removeMovieOrSeriesByName("Movie1");
//        String result = contentStore.getContents().stream().map(Content::toString).collect(Collectors.joining(","));
//        assertEquals("Series{Series2,3.4,2,ROMANCE,ACTION}," +
//                "Movie{Movie5,ACTION,COMEDY,4.0,20 minutes},Movie{Movie4,ACTION,COMEDY,3.5,1h,}," +
//                "Movie{Movie3,ACTION,COMEDY,3.0,1h,11 minutes},Movie{Movie2,ACTION,COMEDY,2.5,1h,9 minutes}", result);
//    }
//
//    @Test
//    void updateRatingForSeriesOrMovieByName() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Episode("Movie1", 60));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.updateRatingForSeriesOrMovieByName("Movie1", 5);
//        String result = contentStore.getContents().stream().map(Content::toString).collect(Collectors.joining(","));
//        assertEquals("Series{Series1,4.0,3,ROMANCE,ACTION}," +
//                "Series{Series2,3.4,2,ROMANCE,ACTION}," +
//                "Episode{Movie1,60}," +
//                "Movie{Movie5,ACTION,COMEDY,4.0,20 minutes}," +
//                "Movie{Movie4,ACTION,COMEDY,3.5,1h,}," +
//                "Movie{Movie3,ACTION,COMEDY,3.0,1h,11 minutes}," +
//                "Movie{Movie2,ACTION,COMEDY,2.5,1h,9 minutes}," +
//                "Series{Series1,3.0,4,ROMANCE,ACTION}," +
//                "Movie{Movie1,COMEDY,5.0,1h,10 minutes}", result);
//    }
//
//    @Test
//    void removeDuplicateContentsByName() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Episode("Movie1", 60));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.removeDuplicateContentsByName();
//        String result = contentStore.getContents().stream().map(Content::toString).collect(Collectors.joining(","));
//        assertEquals("Series{Series1,4.0,3,ROMANCE,ACTION}," +
//                "Series{Series2,3.4,2,ROMANCE,ACTION}," +
//                "Episode{Movie1,60},Movie{Movie5,ACTION,COMEDY,4.0,20 minutes}," +
//                "Movie{Movie4,ACTION,COMEDY,3.5,1h,}," +
//                "Movie{Movie3,ACTION,COMEDY,3.0,1h,11 minutes}," +
//                "Movie{Movie2,ACTION,COMEDY,2.5,1h,9 minutes}", result);
//    }
//
//    @Test
//    void calculateAverageContentRating() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Episode("Movie1", 60));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        double averageRating = contentStore.calculateAverageContentRating();
//        assertEquals(3.05, averageRating);
//    }
//
//    @Test
//    void reduceIntroDurationFromEpisodesOfSeriesByName() {
//        contentStore.addContent(new Movie("Movie1", 1, 70, List.of(Genre.COMEDY)));
//        contentStore.addContent(new Series("Series1", 3, List.of(
//                new Episode("Episode1", 12),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17),
//                new Episode("Episode4", 18)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Movie("Movie2", 2.5, 69, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie3", 3, 71, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie4", 3.5, 60, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Movie("Movie5", 4, 20, List.of(Genre.ACTION,Genre.COMEDY)));
//        contentStore.addContent(new Episode("Movie1", 60));
//        contentStore.addContent(new Series("Series2", 3.4, List.of(
//                new Episode("Episode1", 36),
//                new Episode("Episode2", 30)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.addContent(new Series("Series1", 4, List.of(
//                new Episode("Episode1", 9),
//                new Episode("Episode2", 15),
//                new Episode("Episode3", 17)
//        ), List.of(Genre.ROMANCE,Genre.ACTION)));
//        contentStore.reduceIntroDurationFromEpisodesOfSeriesByName("Series1");
//        String result = contentStore.getContents()
//                .stream()
//                .filter(content -> content instanceof Series).map(content -> (Series)content)
//                .flatMap(s -> s.getEpisodes().stream())
//                .map(episode -> ""+episode.getDurationInMinutes())
//                .collect(Collectors.joining(","));
//        assertEquals("1,5,7,36,30,2,5,7,8", result);
//    }
}
