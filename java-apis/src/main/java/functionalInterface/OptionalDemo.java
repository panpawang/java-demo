package functionalInterface;

import java.util.Optional;


public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();

        Optional<Object> empty2 = Optional.ofNullable(null);

        Optional<String> a = Optional.of("a");

    }
}
