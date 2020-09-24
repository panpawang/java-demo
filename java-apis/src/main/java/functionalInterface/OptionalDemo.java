package functionalInterface;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();

        Optional<Object> empty2 = Optional.ofNullable(null);

        Optional<String> a = Optional.of("a");

        assertEquals("a",a.get());

        assertFalse(empty.isPresent());

        assertTrue(a.isPresent());

    }
}
