package com.haulmont;

import com.haulmont.example.NumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedTest {
    private final int number;
    private final boolean expected;
    private final NumberService numberService;

    public ParametrizedTest(int number, boolean expected) {
        this.number = number;
        this.expected = expected;
        numberService = new NumberService();
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, false},
                {2, true},
                {4, true},
                {591, false}
        });
    }


    @Test
    public void testIsEven() {
        boolean even = numberService.isEven(number);
        assertEquals(even, expected);
    }

}
