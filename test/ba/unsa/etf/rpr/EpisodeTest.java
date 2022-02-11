package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EpisodeTest {
    @Test
    public void isExceptionThrownOnInvalidDuration() {
        try {
            new Episode("Episode1",0);
            fail("Duration must be a positive");
        } catch (InvalidDurationException exception) {
            assertEquals("Duration must be a positive", exception.getMessage());
        }
    }
}