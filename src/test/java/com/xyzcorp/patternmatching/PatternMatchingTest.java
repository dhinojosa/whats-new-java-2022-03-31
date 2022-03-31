package com.xyzcorp.patternmatching;

import org.junit.jupiter.api.Test;

public class PatternMatchingTest {

    public String testItem(Object o) {
        if (o instanceof String s && s.length() < 10) {
            return "Small String";
        } else if (o instanceof String) {
            return "Large String";
        } else if (o instanceof Integer i) {
            return i < 10 ? "Small Number" : "Large Number";
        } else {
            return "Other";
        }
    }

    public String testItem2(Object o) {
        return switch(o) {
            case String s -> "String";
            case Integer i -> "Number";
            default -> "Nope";
        };
    }

    @Test
    void testPatternMatching() {
        System.out.println(testItem2("Hello"));
        System.out.println(testItem2(301));
    }
}
