package com.xyzcorp.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

public class RXJavaTest {
    @Test
    void testSampleRXJava() {
        //Observable.fromPublisher(new MyPublisher(Executors
        // .newFixedThreadPool(3)));
        Observable<Integer> integerObservable =
            Observable.just(102, 40, 105, 300);

        //branch 1
        integerObservable.map(i -> i * 30).subscribe(x ->
                System.out.println("S1:" + x),
            Throwable::printStackTrace,
            () -> System.out.println("Done"));

        //branch 2
        integerObservable.map(i -> i % 2 == 0).subscribe(
            x -> System.out.println("S2:" + x),
            Throwable::printStackTrace,
            () -> System.out.println("Done"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testRXJavaAsync() {
        Observable.just(10, 40, 90, 160, 499, 1043)
                  .doOnNext(i -> System.out.printf("L1: %d [%s]\n", i,
                      Thread.currentThread()))
                  .map(x -> x * 3)
                  .observeOn(Schedulers.computation())
                  .subscribeOn(Schedulers.io())
                  .doOnNext(i -> System.out.printf("L2: %d [%s]\n", i,
                      Thread.currentThread()))
                  .filter(x -> x % 3 == 0)
                  .doOnNext(i -> System.out.printf("L3: %d [%s]\n", i,
                      Thread.currentThread()))
                  .observeOn(Schedulers.io())
                  .subscribe(x -> {
                      System.out.printf("onNext: %d [%s]\n", x,
                          Thread.currentThread());
                  });

    }
}
