package com.xyzcorp.functional;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalAdditionsTest {

    @Test
    void testGenerate() {
        List<LocalDateTime> localDateTimes =
            Stream.generate(() -> LocalDateTime.now())
                  .limit(500)
                  .toList();
        System.out.println(localDateTimes);
    }

    @Test
    void testIterate() {
        System.out.println(
            Stream.iterate(0, integer -> integer + 1)
                  .dropWhile(i -> i < 10)
                  .filter(x -> x % 2 == 0)
                  .takeWhile(i -> i < 100)
                  .toList());
    }

    @Test
    void testOptional() {
        Map<Integer, String> integerStringMap =
            Map.of(1, "One", 2, "Two", 3, "Three");
        String value = integerStringMap.get(1);
        Optional<String> stringOptional = Optional.ofNullable(value);

        Optional<String> result = stringOptional.map(s -> s + "!");
        System.out.println(result);
    }

    @Test
    void testOptionalNested() {
        Map<Integer, String> integerStringMap =
            Map.of(1, "One", 2, "Two", 3, "Three");

        Optional<String> optionalOne =
            Optional.ofNullable(integerStringMap.get(1));

        Optional<String> optionalTwo =
            optionalOne.flatMap(a ->
                Optional.ofNullable(
                    integerStringMap.get(2)).map(b -> a + ":" + b));
        System.out.println(optionalTwo);
    }

    @Test
    void streamWithPossibleException() {
        Stream<Integer> integerStream =
            Stream.of(1, 50, 0, 20, 10).flatMap(x -> {
                try {
                    return Stream.of(100 / x);
            } catch (ArithmeticException ae) {
                    return Stream.empty();
                }
            });
        integerStream.forEach(System.out::println);
    }

    @Test
    void testComposeTwoOptionals() {
        var o1 = Optional.of(30);
        var o2 = Optional.<Integer>empty();
        var o3 = Optional.of(20);

        Optional<Integer> integer = o1.flatMap(
            a -> o2.flatMap(b -> o3.map(c -> a + b + c)));
        System.out.println(integer);
    }

    @Test
    void testOptionStream() {
        Stream<Optional<Integer>> optionalStream =
            Stream.of(Optional.of(2),
                Optional.empty(),
                Optional.of(30),
                Optional.empty(),
                Optional.of(303),
                Optional.of(303)
            );

        Stream<Integer> integerStream =
            optionalStream.flatMap(Optional::stream);
        System.out.println(integerStream.collect(Collectors.toSet()));
    }

    @Test
    void testTeeing() {
        Double result =
            Stream.of(100, 100, 90, 50, 40, 80, 90, 100)
                  .collect(Collectors.teeing(
                      Collectors.summingDouble(value -> value),
                      Collectors.counting(),
                      (sum, count) -> sum / count));
        System.out.println(result);
    }
}
