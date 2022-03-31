package com.xyzcorp.enhancedswitch;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;


public class EnhancedSwitchTest {
    @Test
    void testBasicSwitch() {
        Month birthMonth = JANUARY;
        int result = 0;
        switch (birthMonth) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                result = 31;
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                result = 30;
                break;
            default:
                result = 29;
        }
        assertThat(result).isEqualTo(31);
    }

    @Test
    void testEnhancedSwitch() {
        System.out.println(getDaysInMonth(AUGUST, 2000));
        System.out.println(getDaysInMonth(SEPTEMBER, 2022));
    }

    private int getDaysInMonth(Month birthMonth, int year) {
        return switch (birthMonth) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            case FEBRUARY -> {
                if (year % 4 == 0)
                    yield 29;
                else
                    yield 28;
            }
            default -> -1;
        };
    }
}
