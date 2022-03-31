package com.xyzcorp;

import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        long pid = ProcessHandle.current().pid();
        System.out.printf("PID: %d", pid);
        Thread.sleep(120000);

        Stream<ProcessHandle> children =
            ProcessHandle.current().children();
    }
}
