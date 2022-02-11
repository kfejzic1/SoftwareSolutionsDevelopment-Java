package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.List;

import static ba.unsa.etf.rpr.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {
    @Test
    public void isTheToStringMethodImplementedCorrectly() {
        Series series = new Series();
        series.setName("Series1");
        series.setGenres(List.of(DRAMA,
                FANTASY,
                HORROR,
                MYSTERY,
                ROMANCE,
                THRILLER,
                WESTERN));
        assertEquals("Series{Series1,0.0,0,DRAMA,FANTASY,HORROR,MYSTERY,ROMANCE,THRILLER,WESTERN}",series.toString());
    }

    @Test
    public void isExceptionThrownOnInvalidRating() {
        try {
            new Series("Series1",0,List.of(),List.of(ACTION));
            fail("Rating must be between 1 and 5");
        } catch (InvalidRatingException exception) {
            assertEquals("Rating must be between 1 and 5", exception.getMessage());
        }
    }
}
