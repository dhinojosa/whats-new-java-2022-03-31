package com.xyzcorp.nondenotable;

public class Foo {
    public Object bar() {
        var o = new Object() {
            String name = "Ecuador";
            String capital = "Quito";
        };
        return o;
    }
}
