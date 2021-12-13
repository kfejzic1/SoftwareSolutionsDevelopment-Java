import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PredmetTest {

    @Test
    void testEquals() {
        Predmet p1 = new Predmet("Razvoj programskih rješenja", 7, 70, new Profesor("Vedran", "Ljubović"));
        Predmet p2 = new Predmet("Diskretna matematika", 7, 70, new Profesor("Željko", "Jurić"));
        Predmet p3 = new Predmet("Sistemsko programiranje", 5, 50, new Profesor("Samir", "Ribić"));
        Predmet p4 = new Predmet("Razvoj programskih rješenja", 10, 80, new Profesor("Neko", "Nekić"));

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p3);
        assertEquals(p1, p4);
    }

    @Test
    void getProfesor() {
        Predmet p = new Predmet("Razvoj programskih rješenja", 7, 70, new Profesor("Vedran", "Ljubović"));
        Profesor profesor = new Profesor("Vedran", "Ljubović");
        assertEquals(p.getProfesor(), profesor);
    }
}