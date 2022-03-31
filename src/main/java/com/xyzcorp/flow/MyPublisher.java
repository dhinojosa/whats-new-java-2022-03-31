package com.xyzcorp.flow;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MyPublisher implements Publisher<Long> {
    private ExecutorService executorService;

    public MyPublisher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(Subscriber<? super Long> s) {
        s.onSubscribe(new Subscription() {
            AtomicBoolean done = new AtomicBoolean(false);
            AtomicLong counter = new AtomicLong();
            AtomicLong requests = new AtomicLong();

            @Override
            public void request(long n) {
                requests.addAndGet(n);
                startLoop();
            }

            private void startLoop() {
                executorService.submit(() -> {
                    while (!done.get()) {
                        if (requests.decrementAndGet() >= 0) {
                            s.onNext(counter.incrementAndGet());
                        }
                    }
                });
            }

            @Override
            public void cancel() {
                done.set(true);
            }
        });

    }
}
