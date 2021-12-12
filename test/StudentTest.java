import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testToString() {
        Student s = new  Student("Kenan", "Fejzić", new Indeks("18903"));
        assertEquals(s.toString(), "Kenan Fejzić\nBroj indeksa: 18903");
    }
}