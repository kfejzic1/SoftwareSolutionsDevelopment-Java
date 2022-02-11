package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class WatchableTest {
    @Test
    public void hasDefaultImplementation() {
        Class<Watchable> watchable = Watchable.class;
        try {
            Method method = watchable.getMethod("getDurationInMinutes");
            assertTrue(method.isDefault());
        } catch (NoSuchMethodException e) {
            System.err.println("Method getDurationInMinutes does not exist");
            fail();
        }
    }
}