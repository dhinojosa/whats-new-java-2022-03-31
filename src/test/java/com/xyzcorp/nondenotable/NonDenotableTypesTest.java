package com.xyzcorp.nondenotable;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class NonDenotableTypesTest {
    @Test
    void testRecordInMethod() {
        record Person(String firstName, int age) {
        }
        var p = new Person("Michael", 38);
        System.out.println(p.age);
        System.out.println(p.firstName);
    }

    @Test
    void testNonDenotableType() {
        var a = new Object() {
            String firstName = "Michael";
            int age = 38;
        };

        System.out.println(a.firstName);
        System.out.println(a.age);
    }


    @Test
    void testNonDenotableTypesInStream() {
        List<Integer> integers =
            Stream.of(10, 45, 102)
                  .map(i -> new Object() {
                      int next = i + 1;
                      int negative = -i;
                  })
                  .map(o -> o.negative * o.next)
                  .toList();
        System.out.println(integers);
    }

    @Test
    void abilityToUseObjectReturn() {
        var foo = new Foo();
        var o = foo.bar();

    }
}
