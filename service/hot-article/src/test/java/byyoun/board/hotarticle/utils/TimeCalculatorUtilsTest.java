package byyoun.board.hotarticle.utils;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorUtilsTest {
    @Test
    void test() {
        Duration duration = TimeCalculatorUtils.calculateDurationToMidnight();

        // 22시 32분
        System.out.println("duration.getSeconds() / 60 = " + duration.getSeconds() / 60);

    }
}