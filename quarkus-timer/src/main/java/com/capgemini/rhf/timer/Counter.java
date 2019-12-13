package com.capgemini.rhf.timer;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;


@Component
public class Counter {
    private final AtomicInteger value = new AtomicInteger(0);

    public int increment() {
        return value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}
