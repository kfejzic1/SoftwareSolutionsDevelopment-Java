import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProfesorTest {

    @Test
    void testEquals() {
        Profesor p1 = new Profesor("Vedran", "Ljubović");
        Profesor p2 = new Profesor("Željko", "Jurić", 130);
        Profesor p3 = new Profesor("Vedran", "Ljubović", 140);

        assertEquals(p1, p3);
        assertNotEquals(p1, p2);
    }

    @Test
    void testToString() {
        Profesor p1 = new Profesor("Vedran", "Ljubović", 130);
        assertEquals(p1.toString(), "Vedran Ljubović, norma: 130");
    }
}