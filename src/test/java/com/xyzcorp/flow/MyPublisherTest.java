package com.xyzcorp.flow;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPublisherTest {
    @Test
    void testMyPublisher() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyPublisher myPublisher = new MyPublisher(executorService);

        myPublisher.subscribe(new Subscriber<Long>() {
            private Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(20);
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
                if (aLong == 19) s.request(1000);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });
    }
}
