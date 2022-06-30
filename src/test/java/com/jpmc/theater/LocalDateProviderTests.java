package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.util.LocalDateProvider;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }
}
