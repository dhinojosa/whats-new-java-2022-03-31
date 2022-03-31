package com.xyzcorp.immutable;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class ImmutableCollectionsTest {
    @Test
    void testImmutableCollections() {
        List<Integer> classicList = new ArrayList<>();
        classicList.add(104);
        classicList.add(501);
        classicList.add(30);

        Collections.sort(classicList);
        System.out.println(classicList);

        //We cannot add
        List<Integer> asList = Arrays.asList(104, 30, 501);
        Collections.sort(asList);
        System.out.println(asList);

        //Immutable
        List<Integer> immutableList = List.of(30, 104, 501);
        //immutableList.add(330);
        //Collections.sort(immutableList);

        Stream<Integer> integerStream =
            immutableList.stream().sorted(Integer::compareTo);

        List<Integer> integerList = integerStream.toList();
        System.out.println(integerList);

        List<Integer> newList =
            Stream
                .concat(immutableList.stream(), Stream.of(400))
                .toList();
        System.out.println(newList);




    }
}
