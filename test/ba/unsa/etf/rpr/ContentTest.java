package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class ContentTest {
    @Test
    public void isAbstractClassWithPrivateMemberWithGetterAndSetter() {
        Class<Content> contentClass = Content.class;
        assertTrue(Modifier.isAbstract(contentClass.getModifiers()));
        try {
            Field field = contentClass.getDeclaredField("name");
            assertTrue(Modifier.isPrivate(field.getModifiers()));
            contentClass.getDeclaredMethod("setName", String.class);
            contentClass.getDeclaredMethod("getName");
        } catch (NoSuchFieldException | NoSuchMethodException exception) {
            fail("Field/method name must exist");
        }
    }
}
